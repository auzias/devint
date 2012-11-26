package devint.projet;

import java.awt.Font;
import java.util.ArrayList;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Transform;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class GameStates extends BasicGameState {
	private Map map;
	private Player player;
	private Enemy enemy;
	private int highScore = 200;
	private Questions questions = new Questions();
	private int stateId = 0;
	private StateBasedGame game;
	public static final int ID = 0;
	

	

	public enum STATES {
		START_GAME_STATE, GAMING_STATE, PAUSE_GAME_STATE, HIGHSCORE_STATE, GAME_OVER_STATE, QUESTION_STATE
	}

	private STATES currentState = STATES.START_GAME_STATE;

	public GameStates(int stateId) {
		this.stateId = stateId;
	}

	@Override
	public int getID() {
		return stateId;
	}

	@Override
	public void init(GameContainer container, StateBasedGame sb)
			throws SlickException {
		player = new Player();
		map = new Map("testisD");
		new Sound("ressources/data/coin.wav").play();
		
		
	}

	@Override
	public void enter(GameContainer container, StateBasedGame sb)
			throws SlickException {
		super.enter(container, sb);
		currentState = STATES.START_GAME_STATE;
	}

	@Override
	public void render(GameContainer container, StateBasedGame sb, Graphics g)
			throws SlickException {

	}

	@Override
	public void update(GameContainer container, StateBasedGame sb, int delta)
			throws SlickException {

		Input input = container.getInput();
		boolean endLoop = false;

		switch (currentState) {
		case START_GAME_STATE:
			game.enterState(Gaming.ID);
			break;
		case HIGHSCORE_STATE:
			game.enterState(Scores.ID, Menu.fot, Menu.fit);
			break;
		case PAUSE_GAME_STATE:
			do {
				if (input.isKeyPressed(Input.KEY_ESCAPE))
					System.exit(0);
				if (input.isKeyPressed(Input.KEY_ENTER)) {
					game.enterState(Gaming.ID);
					endLoop = true;
				}
			} while (endLoop);
			break;
		case GAME_OVER_STATE:
			break;
		case GAMING_STATE:
			break;
		case QUESTION_STATE:
			do {
				if (input.isKeyPressed(Input.KEY_ESCAPE))
					System.exit(0);
				if (input.isKeyPressed(Input.KEY_ENTER)) {
					game.enterState(Gaming.ID);
					endLoop = true;
				}
			} while (endLoop);
			break;
		}
	}
}