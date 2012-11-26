package gameStates;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import util.Fc;

public class BpmSelection  extends BasicGameState {
	private static final int ID = 20;
	private static double BPM;
	private static String _bpm = new String();;
	private double _bpmValue;
	private String msg = "Entre le bpm :";
	
	@Override
	public void init(GameContainer arg0, StateBasedGame arg1)
			throws SlickException {
		this._bpmValue = 0;
		setBPM(0);
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		g.setColor(Fc.blanc);
		g.fillRect(0, 0, Fc.width, Fc.height);
		int y = 100;
		Fc.fontEffect.drawString((Fc.width - Fc.fontEffect.getWidth(msg))/2, y, msg, Fc.kit.getTextColor());
		y += Fc.fontEffect.getHeight(msg);
		Fc.fontEffect.drawString((Fc.width - Fc.fontEffect.getWidth(_bpm))/2, y, _bpm, Fc.kit.getTextColor());	
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int arg2)
			throws SlickException {
		Input input = container.getInput();
		if ((input.isKeyPressed(Input.KEY_ENTER)) && _bpmValue > 0 ) {
			setBPM(_bpmValue);
			game.enterState(new CreateSMFile().getID(), Fc.fot, Fc.fit);
		}
	}

	@Override
	public int getID() {
		return BpmSelection.ID;
	}
	
	@Override
	public void keyReleased(int key, char c) {
		if (key == Input.KEY_BACK)
			if (_bpm.length() > 0)
				_bpm = _bpm.substring(0, _bpm.length() - 1);
		if (_bpm.length() < 6) {
			if (key == Input.KEY_1)
				_bpm = _bpm + "1";
			if (key == Input.KEY_2)
				_bpm = _bpm + "2";
			if (key == Input.KEY_3)
				_bpm = _bpm + "3";
			if (key == Input.KEY_4)
				_bpm = _bpm + "4";
			if (key == Input.KEY_5)
				_bpm = _bpm + "5";
			if (key == Input.KEY_6)
				_bpm = _bpm + "6";
			if (key == Input.KEY_7)
				_bpm = _bpm + "7";
			if (key == Input.KEY_8)
				_bpm = _bpm + "8";
			if (key == Input.KEY_9)
				_bpm = _bpm + "9";
			if (key == Input.KEY_0)
				_bpm = _bpm + "0";
			if(!_bpm.contains("."))
				if (key == Input.KEY_COMMA || key == Input.KEY_DECIMAL || key == Input.KEY_PERIOD)
					_bpm = _bpm + ".";
			if(!_bpm.equals(""))
				_bpmValue = (new Double(_bpm)).doubleValue();
		}
	}

	public static void setBPM(double bpm) {
		BPM = bpm;
	}

	public static double getBPM() {
		return BPM;
	}
}
