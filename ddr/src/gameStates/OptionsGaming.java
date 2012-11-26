package gameStates;

import java.util.ArrayList;
import java.util.Arrays;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

import util.Fc;

public class OptionsGaming extends Menus {
	private static final int ID = 16;

	@Override
	protected void initialisations() {
		super.title = "Options";
		this.options = new ArrayList<String>(Arrays.asList("Aide vocale : ON/OFF", "Changer les couleurs", "Retour:<Esc>"));
		this.listTo = new ArrayList<Integer>(Arrays.asList(0));
	}

	@Override
	protected void shortcut(Input input, StateBasedGame game) {
		if (input.isKeyPressed(Input.KEY_ENTER) || input.isKeyPressed(Input.KEY_ESCAPE)) {
			Fc.pause = false;
			game.enterState(new Pause().getID(), Fc.fot, Fc.fit);
		}
	}

	@Override
	protected void enterPushed(Input input, StateBasedGame game) {

	}

	@Override
	public int getID() {
		return OptionsGaming.ID;
	}

	@Override
	protected void checkUpdate(GameContainer container, StateBasedGame game) {

	}
}
