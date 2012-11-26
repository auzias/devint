package devint.projet;

import java.awt.Font;
import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;
import org.newdawn.slick.util.Log;

public class Menu extends BasicGameState {
	public static final FadeOutTransition fot = Cross2Sophia.fot;
	public static final FadeInTransition fit = Cross2Sophia.fit;
	private final String[] options = { "Jouer", "Meilleurs scores", "Quitter" };
	private final int[] yOptions = { 0, 0, 0 };
	public static final int ID = 0;
	private StateBasedGame game;
	private RankList rl;
	private GameContainer container;
	private Restaurer restaurer;
	private static boolean newScore;
	private static RankList ranklist;
	private SaveScore sc;
	private static ArrayList<Score> listetriee;
	public static String nickName;
	private static final int YMEILLEURESCORE = 380;
	private int selection = 0;
	private Sound bienvenue;
	private Sound commencerPartie;
	private Sound afficherScores;
	private Sound quitter;
	private boolean commencerPartieDit;
	private boolean afficherScoresDit;
	private boolean quitterDit;
	
	public static Image haut;
	public static Image bas;
	public static Image espace;
	public static Image echap;
	public static Image entree;
	public static Image f4;
	public static Image f9;
	public static Image f10;
	public static Image f11;
	public static Image f12;

	@Override
	public int getID() {
		return ID;
	}

	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {

		haut = new Image("../ressources/data/haut.png");
		bas = new Image("../ressources/data/bas.png");
		espace = new Image("../ressources/data/espace.png");
		echap = new Image("../ressources/data/echap.png");
		entree = new Image("../ressources/data/entree.png");
		f4 = new Image("../ressources/data/f4.png");
		f9 = new Image("../ressources/data/f9.png");
		f10 = new Image("../ressources/data/f10.png");
		f11 = new Image("../ressources/data/f11.png");
		f12 = new Image("../ressources/data/f12.png");

		bienvenue = new Sound("../ressources/data/aide/Menu/bienvenue.wav");
		commencerPartie = new Sound("../ressources/data/aide/Menu/commencer partie.wav");
		afficherScores = new Sound("../ressources/data/aide/Menu/affichercores.wav");
		quitter = new Sound("../ressources/data/aide/Menu/quit.wav");

		this.game = game;
		this.container = container;
		// container.setFullscreen(true);
		container.setShowFPS(false);

		newScore = false;
		restaurer = new Restaurer("../ressources/data/RankList.xml");
		ranklist = restaurer.restaurer();
		sc = new SaveScore();
		listetriee = new ArrayList<Score>();

		bienvenue.play();

		for (int i = 0; i < yOptions.length; i++)
			yOptions[i] = (1000 - Cross2Sophia.fUnicodeFont.getWidth(options[i])) / 2;

		Cross2Sophia.fUnicodeFont.getEffects().add(
				new org.newdawn.slick.font.effects.ColorEffect(
						java.awt.Color.WHITE));
		Cross2Sophia.fUnicodeFont.addAsciiGlyphs();
		Cross2Sophia.fUnicodeFont.loadGlyphs();
	}

	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		Input input = container.getInput();
		if (input.isKeyPressed(Input.KEY_ESCAPE))
			System.exit(0);

		g.setColor(new Color(255, 255, 255));
		g.fillRect(0, 0, 1024, 768);

		if (input.isKeyPressed(Input.KEY_DOWN)) {
			if (selection >= 2)
				selection = 2;
			else {
				selection++;
				bienvenue.stop();
				commencerPartie.stop();
				afficherScores.stop();
				quitter.stop();
			}
		}
		if (input.isKeyPressed(Input.KEY_UP)) {
			if (selection <= 0)
				selection = 0;
			else {
				selection--;
				bienvenue.stop();
				commencerPartie.stop();
				afficherScores.stop();
				quitter.stop();
				}
		}
		
		if (!bienvenue.playing()) {
			if (selection == 0 && !commencerPartieDit) {
				commencerPartie.play();
				commencerPartieDit = true;
				afficherScoresDit = false;
				quitterDit = false;
			} else if (selection == 1 && !afficherScoresDit) {
				afficherScores.play();
				commencerPartieDit = false;
				afficherScoresDit = true;
				quitterDit = false;
			} else if (selection == 2 && !quitterDit) {
				//quitter.play();
				Cross2Sophia.voix.playShortText("Quitter");
				commencerPartieDit = false;
				afficherScoresDit = false;
				quitterDit = true;
			}
		}

		// Cadres bleus :
		g.setColor(Cross2Sophia.bleu);
		for (int i = 0, z = 10; i < 3; i++, z += 90)
			g.fillRoundRect(10, 10 + z + 3 * i, 1000, 90, 10);

		// Textes des options :
		for (int i = 0; i < yOptions.length; i++)
			Cross2Sophia.fUnicodeFont.drawString(yOptions[i], 10 + 90 * i,
					options[i], Cross2Sophia.jaune);

		// Affichage selection :
		g.setColor(Cross2Sophia.jaune);
		g.fillRoundRect(10, 20 + 90 * selection + 3 * selection, 1000, 90, 10);
		Cross2Sophia.fUnicodeFont.drawString(yOptions[selection],
				10 + 90 * selection, options[selection], Cross2Sophia.bleu);

		// Meilleures scores :
		g.setColor(new Color(255, 0, 255));
		for (int y = YMEILLEURESCORE; y < YMEILLEURESCORE + 10; y++) {
			g.drawRoundRect(10, y, 1000, 380, 10);
			g.drawRoundRect(10, y, 1000, 100, 10);
		}
		Cross2Sophia.fUnicodeFont.drawString(10, YMEILLEURESCORE,
				"Meilleurs scores :", Cross2Sophia.noir);
		for (int i = 1, z = YMEILLEURESCORE + 100; i < 4; i++, z = z + 90) {
			Cross2Sophia.fUnicodeFont.drawString(10, z, "" + i + " "
					+ ranklist.getListeScores().get(i - 1).toString(),
					Cross2Sophia.noir);
		}
	}

	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {
		if (newScore == true) {
			restaurer = new Restaurer("../ressources/data/RankList.xml");
			ranklist = restaurer.restaurer();

			ranklist.addScore(GameOver.getNewScore());
			listetriee = ranklist.getListeScores();
			sc = new SaveScore();
			sc.creerDocument(listetriee);
			newScore = false;
		}
	}

	public void keyReleased(int key, char c) {
		Input input = container.getInput();
		if (input.isKeyPressed(Input.KEY_ENTER)) {
			if (selection == 0) {
				try {
					game.getState(Gaming.ID).init(container, game);
				} catch (SlickException e) {
					Log.error(e);
				}
				bienvenue.stop();
				commencerPartie.stop();
				afficherScores.stop();
				quitter.stop();
				game.enterState(Gaming.ID, fot, fit);
			} else if (selection == 1) {
				try {
					game.getState(Scores.ID).init(container, game);
				} catch (SlickException e) {
					Log.error(e);
				}
				bienvenue.stop();
				commencerPartie.stop();
				afficherScores.stop();
				quitter.stop();
				game.enterState(Scores.ID, fot, fit);
			} else {
				System.exit(0);
			}
		}
	}

	public static void setNewScore(boolean newS) {
		newScore = newS;
	}

	public static int getSeventhScore() {
		listetriee = ranklist.getListeScores();
		return listetriee.get(6).getResult();
	}

	public static int getBestScore() {
		listetriee = ranklist.getListeScores();
		return listetriee.get(0).getResult();
	}
}