package gameStates;

import java.util.ArrayList;
import java.util.Arrays;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

import util.Fc;

public class Instructions extends Menus{
	private static final int ID = 15;
	public static int IDFrom = 1;

	@Override
	protected void initialisations() {
		super.title = "Instructions";
		super.options = new ArrayList<String>(Arrays.asList("??", "??", "??", "Retour:<Esc>"));
		super.listTo = new ArrayList<Integer>(Arrays.asList(0));	
	}


	@Override
	protected void shortcut(Input input, StateBasedGame game) {
		if (input.isKeyPressed(Input.KEY_ESCAPE))
			game.enterState(IDFrom, Fc.fot, Fc.fit);		
	}

	@Override
	protected void enterPushed(Input input, StateBasedGame game) {
		if(selection >= this.listTo.size())
			game.enterState(IDFrom, Fc.fot, Fc.fit);
		else
			game.enterState(new Menu().getID(), Fc.fot, Fc.fit);	
	}
	
	@Override
	public int getID() {
		return Instructions.ID;
	}


	@Override
	protected void checkUpdate(GameContainer container, StateBasedGame game) {
		// TODO Auto-generated method stub
		
	}
}
