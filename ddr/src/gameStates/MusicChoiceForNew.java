package gameStates;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;


import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

import util.Fc;

/**
 * On souhaite savoir quelle musique le(s) joueur(s) veut
 * 
 * @author auzias
 * 
 */
public class MusicChoiceForNew extends Menus {
	private static final int ID = 18;
	private String[] listefichiers;
	private boolean isPlaying;
	private int previousSelection;


	@Override
	protected void initialisations() {
		super.title = "Choix de la musique";
		File repertoire = new File(Fc.pathMp3);
		this.listefichiers = repertoire.list();
		super.options = new ArrayList<String>(Arrays.asList(listefichiers));
		super.listTo = new ArrayList<Integer>(Arrays.asList(0));
		this.isPlaying = false;
		this.previousSelection = 0;
	}

	@Override
	protected void shortcut(Input input, StateBasedGame game) {
		if (input.isKeyPressed(Input.KEY_ESCAPE)) {
			this.stop();
			game.enterState(new Menu().getID(), Fc.fot, Fc.fit);
		}
	}

	@Override
	protected void enterPushed(Input input, StateBasedGame game) {
		this.stop();
		game.enterState(new GapSelection().getID(), Fc.fot, Fc.fit);
	}

	@Override
	protected void checkUpdate(GameContainer container, StateBasedGame game) {
		if(!isPlaying) {
			this.isPlaying = true;
			String filePath = Fc.pathMp3 + listefichiers[super.selection];
			Fc.song = new File(filePath);
			try {
				Fc.player.open(Fc.song);
				Fc.player.play();
				Fc.volUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		if(super.selection != this.previousSelection){
			this.previousSelection = super.selection;
			this.isPlaying = false;
			this.stop();
		}
	}

	private void stop() {
		try {
			Fc.player.stop();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public int getID() {
		return MusicChoiceForNew.ID;
	}
}
