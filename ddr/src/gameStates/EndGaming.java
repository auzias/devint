package gameStates;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

import util.*;

public class EndGaming extends BasicGameState {
	public static final int ID = 22;
	private ScoreToSave score1;
	private ScoreToSave score2;
	private int step = 0;
	private String playerName = "";
	private String gamer1 = "";
	private String gamer2 = "";
	private boolean done = false;

	@Override
	public void init(GameContainer containter, StateBasedGame game)
			throws SlickException {
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		switch (step) {
		case 0:
		default:
			step = 0;
			Fc.gamers.printEnd();			
			break;
		case 1:
			enterName(1);
			gamer1 = playerName;
			score1 = new ScoreToSave(gamer1, Fc.gamers.getPoints1());
			break;
		case 2:
			if (Fc.gamers.getMod() == Mode.Mod.VS) {
				enterName(2);
				gamer2 = playerName;
				score1 = new ScoreToSave(gamer2, Fc.gamers.getPoints2());
			}
			else
				game.enterState(new Menu().getID(), Fc.fot, Fc.fit);
			break;
		case 3:
			//save scores
			game.enterState(new Menu().getID(), Fc.fot, Fc.fit);
			break;
		}
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {
		Fc.volMin();
		// From : MusicChoice | Pause, Back : pause
		Input input = container.getInput();
		if (input.isKeyPressed(Input.KEY_ESCAPE))
			game.enterState(new Menu().getID(), Fc.fot, Fc.fit);
		if (input.isKeyPressed(Input.KEY_ENTER))
			step++;
	}

	public void enterName(int num) {
		if(num == 2 && !done) {
			done = true;
			playerName = "";
		}
		int lineHeight = 80;
		int y = 10;
		int xEntre = (Fc.width - Fc.fontEffect.getWidth("Prenom (Joueur" + num + ")")) / 2;
		int xNom = (Fc.width - Fc.fontEffect.getWidth(playerName)) / 2;
		Fc.fontEffect.drawString(xEntre, y, "Prenom (Joueur" + num + ")",
				Fc.kit.getBackGroundText());
		Fc.fontEffect.drawString(xNom, (y += lineHeight), playerName,
				Fc.kit.getBackGroundText());
	}
	
	public void keyReleased(int key, char c) {
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
	
	@Override
	public int getID() {
		return EndGaming.ID;
	}
}