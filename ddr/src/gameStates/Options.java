package gameStates;

import java.util.ArrayList;
import java.util.Arrays;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

import util.Fc;

public class Options extends Menus {
	private static final int ID = 13;


	@Override
	protected void initialisations() {
		super.title = "Options";
		super.options = new ArrayList<String>(Arrays.asList("Changer kit graphique:K", "Monter le volume:M", "Baisser le volume:B", "Créer une danse:C", "Retour:<Esc>"));
		super.listTo = new ArrayList<Integer>(Arrays.asList(0));
	}

	@Override
	protected void shortcut(Input input, StateBasedGame game) {
		if (input.isKeyPressed(Input.KEY_K))
			Fc.changeKit();
		if (input.isKeyPressed(Input.KEY_M))
			Fc.volUp();
		if (input.isKeyPressed(Input.KEY_B))
			Fc.volDown();
		if (input.isKeyPressed(Input.KEY_C))
			game.enterState(new MusicChoiceForNew().getID(), Fc.fot, Fc.fit);
		if (input.isKeyPressed(Input.KEY_ESCAPE))
			game.enterState(new Menu().getID(), Fc.fot, Fc.fit);
	}

	@Override
	protected void enterPushed(Input input, StateBasedGame game) {
		switch (selection) {
		case 0:
			Fc.changeKit();
			break;
		case 1:
			Fc.volUp();
			break;
		case 2:
			Fc.volDown();
			break;
		case 3:
			game.enterState(new MusicChoiceForNew().getID(), Fc.fot, Fc.fit);
			break;
		case 4:
			game.enterState(new Menu().getID(), Fc.fot, Fc.fit);
			break;
		default:
			break;
		}
	}

	@Override
	public int getID() {
		return Options.ID;
	}

	@Override
	protected void checkUpdate(GameContainer container, StateBasedGame game) {
		
	}
}