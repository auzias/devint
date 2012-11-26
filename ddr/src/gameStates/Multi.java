package gameStates;

import java.util.ArrayList;
import java.util.Arrays;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

import util.Fc;
import util.Mode.Mod;

/**
 * Multi joueurs :
 * On souhaite savoir si les
 * joueurs veulent le COOP ou VS
 * @author auzias
 *
 */
public class Multi extends Menus {
	private static final int ID = 3;

	@Override
	protected void initialisations() {
		super.title = "Choix mode Multijoueur";
		super.options = new ArrayList<String>(Arrays.asList("Coop:C", "VS:V", "Retour:<Esc>"));
		super.listTo = new ArrayList<Integer>(Arrays.asList(new LevelChoice().getID(), new LevelChoice().getID(), new Menu().getID()));	
	}

	@Override
	protected void shortcut(Input input, StateBasedGame game) {
		if (input.isKeyPressed(Input.KEY_C)) {
			Fc.gamers.setMod(Mod.COOP);
			game.enterState(new LevelChoice().getID(), Fc.fot, Fc.fit);
		}
		if (input.isKeyPressed(Input.KEY_V)) {
			Fc.gamers.setMod(Mod.VS);
			game.enterState(new LevelChoice().getID(), Fc.fot, Fc.fit);
		}
		if (input.isKeyPressed(Input.KEY_ESCAPE))
			game.enterState(new ModeChoice().getID(), Fc.fot, Fc.fit);
	}

	@Override
	protected void enterPushed(Input input, StateBasedGame game) {
		if(selection >= this.listTo.size())
			game.enterState(new ModeChoice().getID(), Fc.fot, Fc.fit);
		else {
			if(selection == 0)
				Fc.gamers.setMod(Mod.COOP);
			if(selection == 1)
				Fc.gamers.setMod(Mod.VS);
			game.enterState(new LevelChoice().getID(), Fc.fot, Fc.fit);
		}
	}

	@Override
	public int getID() {
		return Multi.ID;
	}

	@Override
	protected void checkUpdate(GameContainer container, StateBasedGame game) {
		// TODO Auto-generated method stub
		
	}
}