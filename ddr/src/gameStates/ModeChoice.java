package gameStates;

import java.util.ArrayList;
import java.util.Arrays;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

import util.Fc;
import util.Mode;

/**
 * Etat "Jouer" de l'automate
 * On souhaite savoir si le joueur
 * veut le mode solo ou un mode multi
 * @author auzias
 *
 */
public class ModeChoice extends Menus {
	private static final int ID = 2;

	@Override
	protected void initialisations() {
		super.title = "Choix du mode";
		super.options = new ArrayList<String>(Arrays.asList("Solo:S", "Multi:M", "Retour:<Esc>"));
		super.listTo = new ArrayList<Integer>(Arrays.asList(new LevelChoice().getID(), new Multi().getID(), new Menu().getID()));	
	}

	@Override
	protected void shortcut(Input input, StateBasedGame game) {
		if (input.isKeyPressed(Input.KEY_S)) {
			Fc.gamers.setMod(Mode.Mod.SOLO);
			game.enterState(new LevelChoice().getID(), Fc.fot, Fc.fit);
		}
		if (input.isKeyPressed(Input.KEY_M))
			game.enterState(new Multi().getID(), Fc.fot, Fc.fit);
		if (input.isKeyPressed(Input.KEY_ESCAPE))
			game.enterState(new Menu().getID(), Fc.fot, Fc.fit);	
	}

	@Override
	protected void enterPushed(Input input, StateBasedGame game) {
		if(selection >= this.listTo.size())
			game.enterState(new Menu().getID(), Fc.fot, Fc.fit);
		else {
			if(selection == 0) {
				Fc.gamers.setMod(Mode.Mod.SOLO);
				game.enterState(new LevelChoice().getID(), Fc.fot, Fc.fit);
			}
			if(selection == 1) {
				game.enterState(new Multi().getID(), Fc.fot, Fc.fit);
			}
		}
	}


	@Override
	public int getID() {
		return ModeChoice.ID;
	}

	@Override
	protected void checkUpdate(GameContainer container, StateBasedGame game) {
		// TODO Auto-generated method stub
		
	}
}