/*package gameStates;

import entities.Fleche;
import javazoom.jlgui.basicplayer.BasicPlayerException;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import util.Fc;

public class DanceMatConf extends BasicGameState {
	public static final int ID = 17;
	private boolean configOk;

	@Override
	public void init(GameContainer arg0, StateBasedGame game)
	throws SlickException {
		this.configOk = false;
	}

	@Override
	public void render(GameContainer container, StateBasedGame arg1, Graphics g)
	throws SlickException {
		
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)
	throws SlickException {
		// From : MusicChoice | Pause, Back : pause
		Input input = container.getInput();
		//Lecture (ou reprise) de la chanson :
		if(!isPlaying && !Fc.pause) {
			this.isPlaying = true;
			try {
				if(this.first) {
					Fc.player.open(Fc.song);
					Fc.player.play();
					this.first = false;
				}
				else
					Fc.player.resume();
				Fc.volUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		if (input.isKeyPressed(Input.KEY_ESCAPE)) {
			try {
				Fc.player.pause();
				this.isPlaying = false;
				Fc.pause = true;
			} catch (BasicPlayerException e) {
				e.printStackTrace();
			}
			game.enterState(new Pause().getID(), Fc.fot, Fc.fit);
			Fc.pause = false;
		}

		// Gestion des mouvements :
		this.mouvement(container, delta);
		// Gère la flèche qui sort de l'écran
		if (fleche.getY() <= -150)
			fleche = new Fleche();
	}

	@Override
	public int getID() {
		return Gaming.ID;
	}

	private void mouvement(GameContainer container, int delta) {
		// Deplacements player et de l'ennemi :
		float fdelta = delta * 0.25f;
		float fdeltaE1 = delta * fleche.getSpeed();
		fleche.setDirection(container, fdeltaE1, (int)fdelta);
	}
}*/