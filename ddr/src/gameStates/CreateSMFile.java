package gameStates;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import util.Fc;

public class CreateSMFile extends BasicGameState {
	private static final int ID = 21;
	private static String msg = "INTERFACE DE CREATION";
	
	@Override
	public void init(GameContainer arg0, StateBasedGame arg1)
			throws SlickException {
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		//System.out.println("gap : " + GapSelection.getGAP());
		//System.out.println("bpm : " + BpmSelection.getBPM());
		g.setColor(Fc.blanc);
		g.fillRect(0, 0, Fc.width, Fc.height);
		int y = 100;
		Fc.fontEffect.drawString((Fc.width - Fc.fontEffect.getWidth(msg)) / 2, y, msg, Fc.kit.getTextColor());
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int arg2)
			throws SlickException {
		Input input = container.getInput();
		if (input.isKeyPressed(Input.KEY_ENTER))
			game.enterState(new Menu().getID(), Fc.fot, Fc.fit);
	}

	@Override
	public int getID() {
		return CreateSMFile.ID;
	}
}
