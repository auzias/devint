package devint.projet;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class Gaming extends BasicGameState {
	public static final int ID = 1;
	private Map map;
	private static Player player;
	private ArrayList<Enemy> enemies;
	private static int highScore = 200;
	public static int niveau;
	private Sound f1;
	private Sound f2;
	private Sound f3;
	private Sound debut;
	private boolean f1Dit;
	private boolean f2Dit;
	private boolean f3Dit;
	private boolean debutDit = true;
	private static boolean lireQuestions = false;

	public static int getPlayerScore() {
		return player.getScore();
	}

	public static void setPlayerScoreReponse() {
		player.setScore(player.getScore() + 2000);
		if(player.getLife() < 4)
			player.setLife(player.getLife() + 1);
	}

	@Override
	public int getID() {
		return ID;
	}

	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		player = new Player();
		enemies = new ArrayList<Enemy>();
		niveau = 1;
		enemies.add(new Enemy(niveau));
		map = new Map("testis");
		
		f1 = new Sound("../ressources/data/aide/Gaming/but.wav");
		f2 = new Sound("../ressources/data/aide/Gaming/commenthautbas.wav");
		f3 = new Sound("../ressources/data/aide/Gaming/piecequestionmonstres.wav");
		debut = new Sound("../ressources/data/aide/Gaming/debutjeu.wav");
	}

	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		Input input = container.getInput();
		if (input.isKeyPressed(Input.KEY_ESCAPE)) {
			f1.stop();
			f2.stop();
			f3.stop();
			debut.stop();
			game.enterState(Pause.ID,Menu.fit,Menu.fot);
		} else {
			if (player.getQuestion() == 4) {
				player.setQuestion(0);
				player.setLastDir("none");
				f1.stop();
				f2.stop();
				f3.stop();
				debut.stop();
				Gaming.setLireQuestions(true);
				g.setColor(new Color(255, 255, 255));
				g.fillRect(0, 0, 1024, 768);
				game.enterState(Questions.ID);
			}

		

			if (player.getLife() <= 0) {
				GameOver.setNewScore(player.getScore());
				player = new Player();
				f1.stop();
				f2.stop();
				f3.stop();
				debut.stop();
				map.getLooser().play();
				game.enterState(GameOver.ID);
			}

			// Gestion des niveaux :
			if (player.getScore() >= 6000 && player.getScore() < 15000 && niveau != 2) {
				niveau = 2;				// Map 1 avec 2 ennemis
				enemies.add(new Enemy(niveau));
			} else if (player.getScore() >= 15000 && player.getScore() < 25000 && niveau != 3) {
				niveau = 3;				// Map 2 avec 1 ennemi
				map = new Map("testisD"); 
				enemies.clear();
				enemies.add(new Enemy(niveau));
			} else if (player.getScore() >= 25000 && player.getScore() < 30000 && niveau != 4) {
				niveau = 4;				// Map 2 avec 2 ennemis
				enemies.clear();
				enemies.add(new Enemy(niveau));
				enemies.add(new Enemy(niveau));
				map = new Map("testisD"); 
			} else if (player.getScore() >= 30000 && player.getScore() < 45000 && niveau != 5) {
				niveau = 5;				// Map 2 avec 4 ennemis
				map = new Map("testisD");
				enemies.clear();
				enemies.add(new Enemy(niveau));
				enemies.add(new Enemy(niveau));
				enemies.add(new Enemy(niveau));
				enemies.add(new Enemy(niveau));
			} else if (player.getScore() >= 45000 && niveau != 6) {
				niveau = 6;				// Mp 2 avec 5 ennemis
				map = new Map("testisD"); 
				enemies.clear();
				enemies.add(new Enemy(niveau));
				enemies.add(new Enemy(niveau));
				enemies.add(new Enemy(niveau));
				enemies.add(new Enemy(niveau));
				enemies.add(new Enemy(niveau));
				enemies.add(new Enemy(niveau));
			}

			// Dessin de la map
			map.getTiledMap().render(0, 0);

			for (int i = 0; i < enemies.size(); i++) {
				// Creation nouvel ennemi :
				if (enemies.get(i).getX() < -101 || enemies.get(i).getX() > 1101) {
					enemies.remove(i);
					enemies.add(i, new Enemy(niveau));
				}
				// Affichage du kit graphique :
				map.displayKit(g, player, enemies);
			}
			// Gestion du player :
			player.doAction(container, g, enemies, map);
			this.displayInfoPlayer(g);
			//this.displayInfoPositions(g);
		}
		
		if(debutDit) {
			debut.play();
			debutDit=false;
		}
		
		if (!debutDit) {
			if (input.isKeyPressed(Input.KEY_F1)) {
				debut.stop();
				f2.stop();
				f3.stop();
				f1.play();
			} else if (input.isKeyPressed(Input.KEY_F2)) {
				debut.stop();
				f1.stop();
				f3.stop();
				f2.play();
			} else if (input.isKeyPressed(Input.KEY_F3)) {
				debut.stop();
				f1.stop();
				f2.stop();
				f3.play();
			}
		}

		g.drawImage(Map.sac, 420, 580);
	}

	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {
		// Gestion du kit graphique :
		map.setKitGraphic(container);
		// Gestion des mouvements :
		this.mouvement(container, delta);
	}

	private void mouvement(GameContainer container, int delta) {
		// Deplacements player et de l'ennemi :
		float fdelta = delta * 0.25f;
		player.setDirection(container, map, fdelta, delta, niveau);
		for (int i = 0; i < enemies.size(); i++) {
			float fdeltaE1 = delta * enemies.get(i).getSpeed();
			enemies.get(i).setDirection(container, map, fdeltaE1, delta);
		}
	}

	private void displayInfoPlayer(Graphics g) {
		// Barre vie max :
		g.setColor(new Color(255, 255, 255));
		g.fillRect(10, 50, 40, Player.getLifemax() * 150);
		// Barre vie actuelle :
		g.setColor(new Color(0, 0, 0));
		g.fillRect(10, 50+(int) (150 * (player.getLifemax() - player.getLife())), 40, -(int) (150 * (player.getLifemax() - player.getLife())));
		//g.fillRect(10, 50, 40, 600 - (player.getLife() * 150));
		// Barre High score :
		g.setColor(new Color(0, 0, 0));
		g.fillRect(940, 50, 40, 600);
		// Barre score actuel :
		if (player.getScore() > Menu.getSeventhScore()) {
			g.setColor(new Color(255, 255, 0));
			g.fillRect(940, 650, 40, -(int) (100 * player.getScore() / (Menu.getSeventhScore() * 10)) * 6);
		} else {
			g.setColor(new Color(200, 100, 0));
			g.fillRect(940, 650, 40,-(int) (100 * player.getScore() / Menu.getSeventhScore()) * 6);
		}
		Cross2Sophia.fUnicodeFont.drawString(60, 50,"VIE", Cross2Sophia.rouge);
		Cross2Sophia.fUnicodeFont.drawString(650, 50,"SCORE", Cross2Sophia.rouge);
		Cross2Sophia.fUnicodeFont.drawString(560, 650,""+player.getScore() + " pts", Cross2Sophia.rouge);
		Cross2Sophia.fUnicodeFont.drawString(5, 640,""+player.getLife()+" vies", Cross2Sophia.rouge);
		/*
		g.setColor(new Color(0, 0, 0));
		g.drawString("Score: " + player.getScore(), 10, 580);
		g.drawString("Pieces: " + player.getCoins(), 10, 600);
		*/
	}

	private void displayInfoPositions(Graphics g) {
		g.setColor(new Color(0, 0, 0));
		g.drawString("X: " + player.getX() + "\nY: " + player.getY(), 10, 500);
		g.drawString("Xe: " + enemies.get(0).getX() + "\nYe: "
				+ enemies.get(0).getY(), 10, 540);
		g.drawString("Nbr ennemies : " + enemies.size(), 200, 540);
	}

	/**
	 * @return the lireQuestions
	 */
	public static boolean isLireQuestions() {
		return lireQuestions;
	}

	/**
	 * @param lireQuestions the lireQuestions to set
	 */
	public static void setLireQuestions(boolean etat) {
		lireQuestions = etat;
	}
	
}