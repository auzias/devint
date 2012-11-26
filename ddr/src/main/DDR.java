package main;

import gameStates.*;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

import util.Fc;

public class DDR extends StateBasedGame {
	public DDR() {
		super("Dirty Dance Revolution");// Titre de la fenêtre
	}

	public static void main(String[] args) throws SlickException {
		AppGameContainer app = new AppGameContainer(new DDR());
		app.setDisplayMode(Fc.width, Fc.height, Fc.fullScreen);
		app.setShowFPS(false);
		app.start();
	}

	@Override
	public void initStatesList(GameContainer arg0) throws SlickException {
		// On *doit* ajouter toutes les classes qui extends BasicGameState
		// Avec le premier state qui doit être le menu principal (l'entry point
		// de l'apply)
		this.addState(new Menu());
		this.addState(new Gaming());
		this.addState(new Instructions());
		this.addState(new LevelChoice());
		this.addState(new ModeChoice());
		this.addState(new Multi());
		this.addState(new MusicChoice());
		this.addState(new Options());
		this.addState(new OptionsGaming());
		this.addState(new Pause());
		this.addState(new Scores());
		this.addState(new MusicChoiceForNew());
		this.addState(new GapSelection());
		this.addState(new BpmSelection());
		this.addState(new CreateSMFile());
		this.addState(new EndGaming());
	}
}