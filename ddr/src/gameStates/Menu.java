package gameStates;

import java.util.ArrayList;
import java.util.Arrays;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

import util.Fc;

/**
 * Classe du menu principal. Au lancement du jeu le joueur se trouve dans cet
 * "état" (basicGameState)
 * 
 * @author auzias
 * 
 */
public class Menu extends Menus {
	private static final int ID = 1;

	@Override
	protected void initialisations() {
		super.title = "Dirty Dance Revolution";
		super.listTo = new ArrayList<Integer>(Arrays.asList(new ModeChoice().getID(), new Options().getID(), new Instructions().getID(), new Scores().getID()));
		super.options = new ArrayList<String>(Arrays.asList("Jouer:J", "Options:O", "Instructions:I", "Score:S", "Quitter:<Esc>"));		
	}

	@Override
	protected void shortcut(Input input, StateBasedGame game) {
		if (input.isKeyPressed(Input.KEY_J)) {
			game.enterState(new ModeChoice().getID(), Fc.fot, Fc.fit);
		} if (input.isKeyPressed(Input.KEY_O)) {
			game.enterState(new Options().getID(), Fc.fot, Fc.fit);
		} if (input.isKeyPressed(Input.KEY_I)) {
			Instructions.IDFrom = this.getID();
			game.enterState(new Instructions().getID(), Fc.fot, Fc.fit);
		} if (input.isKeyPressed(Input.KEY_S)) {
			game.enterState(new Scores().getID(), Fc.fot, Fc.fit);
		} if (input.isKeyPressed(Input.KEY_ESCAPE))
			System.exit(0);
	}

	@Override
	protected void enterPushed(Input input, StateBasedGame game) {
		if(selection == 2)
			Instructions.IDFrom = this.getID();
		if(selection >= this.listTo.size())
			System.exit(0);
		else
			game.enterState(this.listTo.get(selection), Fc.fot, Fc.fit);
	}

	@Override
	public int getID() {
		return Menu.ID;
	}

	@Override
	protected void checkUpdate(GameContainer container, StateBasedGame game) {
		Fc.restartGame(game);
	}
}