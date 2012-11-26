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
public class MusicChoice extends Menus {
	private static final int ID = 6;
	private boolean isPlaying;
	private int previousSelection;
	private ArrayList<String> listeMP3;
	public static String trackChosen = null;


	@Override
	protected void initialisations() {
		super.title = "Choix de la musique";

		super.listTo = new ArrayList<Integer>(Arrays.asList(0));
		
		File repertoireMp3 = new File(Fc.pathMp3);
		this.listeFichiersMP3 = repertoireMp3.list();
		
		listeMP3 = new ArrayList<String>(Arrays.asList(listeFichiersMP3));
		
		for(int i=0;i<=listeFichiersMP3.length-1;i++)
		{
			if(new File(Fc.pathSM+listeMP3.get(i).substring(0,listeMP3.get(i).length()-4)+".sm").exists())
					{
				super.options.add(listeMP3.get(i));
					}
		}
		
		
		
		
		
		this.isPlaying = false;
		this.previousSelection = 0;
	}

	@Override
	protected void shortcut(Input input, StateBasedGame game) {
		if (input.isKeyPressed(Input.KEY_ESCAPE)) {
			this.stop();
			game.enterState(new LevelChoice().getID(), Fc.fot, Fc.fit);
		}
	}

	@Override
	protected void enterPushed(Input input, StateBasedGame game) {
		this.stop();
		if (selection >= this.options.size())
			game.enterState(new LevelChoice().getID(), Fc.fot, Fc.fit);
		else
			game.enterState(new Gaming().getID(), Fc.fot, Fc.fit);
	}

	@Override
	protected void checkUpdate(GameContainer container, StateBasedGame game) {
		if(!isPlaying) {
			this.isPlaying = true;

			trackChosen = options.get(super.selection).substring(0, options.get(super.selection).length()-4);
			String filePath = Fc.pathMp3 + trackChosen + ".mp3";
			Fc.song = new File(filePath);
			
			try {
				Gaming.loadSM();
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
		return MusicChoice.ID;
	}
}