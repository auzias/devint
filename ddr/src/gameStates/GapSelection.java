package gameStates;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import util.Fc;

public class GapSelection extends BasicGameState {
	private static final int ID = 19;
	private static double GAP;
	private static String _gap = new String();
	private double _gapValue;
	private String msg1 = "Entre le gap";
	private String msg2 = "(en seconde) :";

	@Override
	public void init(GameContainer arg0, StateBasedGame arg1)
			throws SlickException {
		this._gapValue = 0;
		setGAP(0);
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		g.setColor(Fc.blanc);
		g.fillRect(0, 0, Fc.width, Fc.height);
		int y = 100;
		Fc.fontEffect.drawString((Fc.width - Fc.fontEffect.getWidth(msg1)) / 2, y, msg1, Fc.kit.getTextColor());
		y += Fc.fontEffect.getHeight(msg1);
		Fc.fontEffect.drawString((Fc.width - Fc.fontEffect.getWidth(msg2)) / 2, y, msg2, Fc.kit.getTextColor());
		y += Fc.fontEffect.getHeight(msg2);
		Fc.fontEffect.drawString((Fc.width - Fc.fontEffect.getWidth(_gap)) / 2, y, _gap, Fc.kit.getTextColor());
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int arg2)
			throws SlickException {
		Input input = container.getInput();
		if (input.isKeyPressed(Input.KEY_ESCAPE))
			game.enterState(new Menu().getID(), Fc.fot, Fc.fit);
		if ((input.isKeyPressed(Input.KEY_ENTER)) && _gapValue > 0 ) {
			setGAP(_gapValue);
			game.enterState(new BpmSelection().getID(), Fc.fot, Fc.fit);
		}
	}

	@Override
	public int getID() {
		return GapSelection.ID;
	}
	
	@Override
	public void keyReleased(int key, char c) {
		if (key == Input.KEY_BACK)
			if (_gap.length() > 0)
				_gap = _gap.substring(0, _gap.length() - 1);

		if (_gap.length() < 4) {
			if (key == Input.KEY_1)
				_gap = _gap + "1";
			if (key == Input.KEY_2)
				_gap = _gap + "2";
			if (key == Input.KEY_3)
				_gap = _gap + "3";
			if (key == Input.KEY_4)
				_gap = _gap + "4";
			if (key == Input.KEY_5)
				_gap = _gap + "5";
			if (key == Input.KEY_6)
				_gap = _gap + "6";
			if (key == Input.KEY_7)
				_gap = _gap + "7";
			if (key == Input.KEY_8)
				_gap = _gap + "8";
			if (key == Input.KEY_9)
				_gap = _gap + "9";
			if (key == Input.KEY_0)
				_gap = _gap + "0";
			if(!_gap.contains("."))
				if (key == Input.KEY_COMMA || key == Input.KEY_DECIMAL || key == Input.KEY_PERIOD)
					_gap = _gap + ".";
			if(!_gap.equals(""))
				_gapValue = (new Double(_gap)).doubleValue();
		}
	}

	public static void setGAP(double _gapValue2) {
		GAP = _gapValue2;
	}

	public static double getGAP() {
		return GAP;
	}
}
