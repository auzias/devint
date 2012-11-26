package devint.projet;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.Sound;

public class GameOver extends BasicGameState {
	public static final int ID = 5;
	private StateBasedGame game;
	private static String playerName;
	private static Score winner = new Score();
	private Sound entreTonNom;
	private Sound perdu;
	private Sound meilleurScore;
	private Sound bonScore;
	private boolean entreTonNomDit;
	private boolean perduDit;
	private boolean meilleurScoreDit;
	private boolean bonScoreDit;


	@Override
	public int getID() {
		return ID;
	}

	public void init(GameContainer container, StateBasedGame game)
	throws SlickException {
		this.game = game;
		this.playerName = new String();
		entreTonNomDit = false;
		bonScoreDit = false;
		perduDit = false;
		meilleurScoreDit = false;
		this.entreTonNom = new Sound("../ressources/data/aide/GameOver/entretonnom.wav");
		this.perdu = new Sound("../ressources/data/aide/GameOver/perdu.wav");
		this.meilleurScore = new Sound("../ressources/data/aide/GameOver/meilleurscore.wav");
		this.bonScore = new Sound("../ressources/data/aide/GameOver/bonscore.wav");		
	}

	public void render(GameContainer container, StateBasedGame game, Graphics g)
	throws SlickException {
		g.setColor(new Color(255, 255, 255));
		g.fillRect(0, 0, 1024, 768);

		//Si on a pas dit "tu as perdu" on le dit
		if(!perduDit) {
			perdu.play();
			perduDit = true;
		}

		if(perduDit && !perdu.playing()) { //Si on a fini de dire "tu as perdu"
			//ET :
			//qu'il a LE meilleur score ET qu'on l'a pas dit :
			if(winner.getResult() >= Menu.getBestScore() && !meilleurScoreDit) {
				meilleurScore.play();
				meilleurScoreDit = true;
			}
			//OU qu'il a un bon score mais PAS le meilleur ET qu'on l'a pas dit :
			if((winner.getResult() >= Menu.getSeventhScore() && !(winner.getResult() > Menu.getBestScore())) && !bonScoreDit) {
				bonScore.play();
				bonScoreDit = true;
			}
			//OU qu'il a un score de merde
			if((meilleurScoreDit || bonScoreDit || winner.getResult() < Menu.getSeventhScore()) && !entreTonNomDit) {
				entreTonNom.play();
				entreTonNomDit = true;
			}
		}


		Cross2Sophia.fUnicodeFont.drawString(10, 10, "Bravo !",Cross2Sophia.noir);
		Cross2Sophia.fUnicodeFont.drawString(10, 160, "Ton score est : " + winner.getResult(),Cross2Sophia.noir);

		if(winner.getResult() >= Menu.getBestScore())
			Cross2Sophia.fUnicodeFont.drawString(10, 290, "MEILLEUR SCORE! Enregistre le!" + playerName,Cross2Sophia.noir);
		else {
			if(winner.getResult() < Menu.getBestScore() && winner.getResult() >= Menu.getSeventhScore())
				Cross2Sophia.fUnicodeFont.drawString(10, 290, "Bon score! Enregistre le!\n",Cross2Sophia.noir);
			else
				Cross2Sophia.fUnicodeFont.drawString(10, 290, "Pas mal. Essaie encore!\n",Cross2Sophia.noir);
		}

		this.winner = new Score(playerName, winner.getResult());

		Cross2Sophia.fUnicodeFont.drawString(10, 380, "Entre ton prenom :\n" + playerName,Cross2Sophia.noir);
	}
	public void update(GameContainer container, StateBasedGame game, int delta)
	throws SlickException {
	}

	/**
	 * @return the playerName
	 */
	public static Score getNewScore() {
		playerName = new String();
		return winner;
	}

	public static void setNewScore(int score) {
		winner.setResult(score);
	}

	public void keyReleased(int key, char c) {
		if (key == Input.KEY_ENTER) {
			entreTonNomDit = false;
			perduDit = false;
			meilleurScoreDit = false;
			bonScoreDit = false;
			entreTonNom.stop();
			perdu.stop();
			meilleurScore.stop();
			bonScore.stop();
			if(playerName == null)
				playerName = "Inconnu";
			Menu.setNewScore(true);
			game.enterState(Menu.ID);
		}
		if (key == Input.KEY_BACK) {
			if (playerName.length() > 0) {
				playerName = playerName.substring(0, playerName.length() - 1);
			}
		}
		if (playerName.length() < 20) {
			if (key == Input.KEY_A) {
				playerName = playerName + "A";
			}
			if (key == Input.KEY_B) {
				playerName = playerName + "B";
			}
			if (key == Input.KEY_C) {
				playerName = playerName + "C";
			}
			if (key == Input.KEY_D) {
				playerName = playerName + "D";
			}
			if (key == Input.KEY_E) {
				playerName = playerName + "E";
			}
			if (key == Input.KEY_F) {
				playerName = playerName + "F";
			}
			if (key == Input.KEY_G) {
				playerName = playerName + "G";
			}
			if (key == Input.KEY_H) {
				playerName = playerName + "H";
			}
			if (key == Input.KEY_I) {
				playerName = playerName + "I";
			}
			if (key == Input.KEY_J) {
				playerName = playerName + "J";
			}
			if (key == Input.KEY_K) {
				playerName = playerName + "K";
			}
			if (key == Input.KEY_L) {
				playerName = playerName + "L";
			}
			if (key == Input.KEY_M) {
				playerName = playerName + "M";
			}
			if (key == Input.KEY_N) {
				playerName = playerName + "N";
			}
			if (key == Input.KEY_O) {
				playerName = playerName + "O";
			}
			if (key == Input.KEY_P) {
				playerName = playerName + "P";
			}
			if (key == Input.KEY_Q) {
				playerName = playerName + "Q";
			}
			if (key == Input.KEY_R) {
				playerName = playerName + "R";
			}
			if (key == Input.KEY_S) {
				playerName = playerName + "S";
			}
			if (key == Input.KEY_T) {
				playerName = playerName + "T";
			}
			if (key == Input.KEY_U) {
				playerName = playerName + "U";
			}
			if (key == Input.KEY_V) {
				playerName = playerName + "V";
			}
			if (key == Input.KEY_W) {
				playerName = playerName + "W";
			}
			if (key == Input.KEY_X) {
				playerName = playerName + "X";
			}
			if (key == Input.KEY_Y) {
				playerName = playerName + "Y";
			}
			if (key == Input.KEY_Z) {
				playerName = playerName + "Z";
			}
			if (key == Input.KEY_SPACE) {
				playerName = playerName + " ";
			}
		}
	}
}