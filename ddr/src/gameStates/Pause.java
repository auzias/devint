package gameStates;

import java.util.ArrayList;
import java.util.Arrays;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

import util.Fc;

public class Pause extends Menus {
	private static final int ID = 9;

	@Override
	protected void initialisations() {
		super.title = "Pause";
		super.options = new ArrayList<String>(Arrays.asList("Continuer:C", "Recommencer:R", "Options:O", "Instructions:I", "Menu principal:<Esc>"));
		super.listTo = new ArrayList<Integer>(Arrays.asList(new Gaming().getID(), new Gaming().getID(), new OptionsGaming().getID(), new Instructions().getID() , new Menu().getID()));	
	}

	@Override
	protected void shortcut(Input input, StateBasedGame game) {
		if (input.isKeyPressed(Input.KEY_C))
			game.enterState(new Gaming().getID(), Fc.fot, Fc.fit);
		if (input.isKeyPressed(Input.KEY_R)) {
			Fc.restartGame(game);
			game.enterState(new Gaming().getID(), Fc.fot, Fc.fit);
		} if (input.isKeyPressed(Input.KEY_O))
			game.enterState(new OptionsGaming().getID(), Fc.fot, Fc.fit);
		if (input.isKeyPressed(Input.KEY_I)) {
			System.out.println("PAUSE<shortcut>, this.getID():" + this.getID());
			Instructions.IDFrom = this.getID();
			game.enterState(new Instructions().getID(), Fc.fot, Fc.fit);
		} if(input.isKeyPressed(Input.KEY_ESCAPE)) {
			game.enterState(new Menu().getID(), Fc.fot, Fc.fit);
		}
	}

	@Override
	protected void enterPushed(Input input, StateBasedGame game) {
		if(selection == 1) {
			Fc.restartGame(game);
		} if(selection == 3) {
			System.out.println("PAUSE<enter>, this.getID():" + this.getID());
			Instructions.IDFrom = this.getID();
		} if(selection >= this.listTo.size()) {
			game.enterState(new Menu().getID(), Fc.fot, Fc.fit);
		}
		else
			game.enterState(this.listTo.get(selection), Fc.fot, Fc.fit);	
	}

	@Override
	public int getID() {
		return Pause.ID;
	}

	@Override
	protected void checkUpdate(GameContainer container, StateBasedGame game) {
	}
}
