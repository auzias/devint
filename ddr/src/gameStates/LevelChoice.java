package gameStates;

import java.util.ArrayList;
import java.util.Arrays;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

import util.*;

/**
 * On souhaite savoir quel niveau le(s) joueur(s) veut
 * 
 * @author auzias
 * 
 */
public class LevelChoice extends Menus {
	private static final int ID = 7;

	@Override
	protected void initialisations() {
		super.title = "Choix du niveau";
		super.options = new ArrayList<String>(Arrays.asList("Facile:F",
				"Moyen:M", "Difficile:D", "Infaisable:I", "Retour:<Esc>"));
	}

	@Override
	protected void shortcut(Input input, StateBasedGame game) {
		if (input.isKeyPressed(Input.KEY_F)) {
			Fc.gamers.setLevel(Levels.Level.EASY);
			game.enterState(new MusicChoice().getID(), Fc.fot, Fc.fit);
		}
		if (input.isKeyPressed(Input.KEY_M)) {
			Fc.gamers.setLevel(Levels.Level.MIDDLE);
			game.enterState(new MusicChoice().getID(), Fc.fot, Fc.fit);
		}
		if (input.isKeyPressed(Input.KEY_D)) {
			Fc.gamers.setLevel(Levels.Level.HARD);
			game.enterState(new MusicChoice().getID(), Fc.fot, Fc.fit);
		}
		if (input.isKeyPressed(Input.KEY_I)) {
			Fc.gamers.setLevel(Levels.Level.UNDOABLE);
			game.enterState(new MusicChoice().getID(), Fc.fot, Fc.fit);
		}
		if (input.isKeyPressed(Input.KEY_ESCAPE))
			game.enterState(new ModeChoice().getID(), Fc.fot, Fc.fit);
	}

	@Override
	protected void enterPushed(Input input, StateBasedGame game) {
		if (selection >= this.options.size())
			game.enterState(new ModeChoice().getID(), Fc.fot, Fc.fit);
		else {
			switch (selection) {
			case 0:
				Fc.gamers.setLevel(Levels.Level.EASY);
				break;
			case 1:
				Fc.gamers.setLevel(Levels.Level.MIDDLE);
				break;
			case 2:
				Fc.gamers.setLevel(Levels.Level.HARD);
				break;
			case 3:
				Fc.gamers.setLevel(Levels.Level.UNDOABLE);
				break;
			default:
				Fc.gamers.setLevel(Levels.Level.EASY);
				break;
			}
			game.enterState(new MusicChoice().getID(), Fc.fot, Fc.fit);
		}
	}

	@Override
	public int getID() {
		return LevelChoice.ID;
	}

	@Override
	protected void checkUpdate(GameContainer container, StateBasedGame game) {
		// TODO Auto-generated method stub
		
	}
}
