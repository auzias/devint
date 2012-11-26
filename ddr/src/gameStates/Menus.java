package gameStates;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import util.Fc;
import util.PrintList;
import util.PrintSelection;

public abstract class Menus extends BasicGameState {
	protected String title;
	protected ArrayList<String> options = new ArrayList<String>();
	protected ArrayList<Integer> listTo;
	protected int selection = 0;
	protected PrintList menu;
	protected PrintSelection ps;
	protected String[] listeFichiersMP3;

	@SuppressWarnings("unchecked")
	@Override
	public void init(GameContainer arg0, StateBasedGame arg1)
			throws SlickException {
		this.initialisations();
		

		this.menu = new PrintList(title, options);
		this.ps = new PrintSelection(menu);
		//A faire qu'une fois
		Fc.fontEffect.getEffects().add(new org.newdawn.slick.font.effects.ColorEffect(java.awt.Color.WHITE));
		Fc.fontEffect.addAsciiGlyphs();
		Fc.fontEffect.loadGlyphs();
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		//Affichage du menu :
		this.menu.print(g, selection);
		//Affichage de la sélection :
		this.ps.print(g, selection);
		this.selection = ps.getSelection();
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int arg2)
			throws SlickException {
		Input input = container.getInput();
		//Gestion des raccourcis :
		this.shortcut(input, game);
		//Gestion de la sélection :
		if (input.isKeyPressed(Input.KEY_DOWN))
				this.selection++;
		if (input.isKeyPressed(Input.KEY_UP)) 
				this.selection--;
		//Gestion de la validation :
		if (input.isKeyPressed(Input.KEY_ENTER))
			this.enterPushed(input, game);
		this.checkUpdate(container, game);
	}

	//Methodes pour initialiser les listes :
	protected abstract void initialisations();
	//Methodes utiliser dans update()
	protected abstract void shortcut(Input input, StateBasedGame game);
	protected abstract void enterPushed(Input input, StateBasedGame game);
	protected abstract void checkUpdate(GameContainer container, StateBasedGame game);
}
