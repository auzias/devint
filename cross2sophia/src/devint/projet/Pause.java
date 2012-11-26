package devint.projet;

import java.awt.Font;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;
import org.newdawn.slick.util.Log;

import devint.projet.GameStates.STATES;
import org.newdawn.slick.Input;

public class Pause extends BasicGameState {
	private final FadeOutTransition fot = Cross2Sophia.fot;
	private final FadeInTransition fit = Cross2Sophia.fit;
	public static final int ID = 2;
	private StateBasedGame game;
	private final String[] options = { "Continuer", "Menu", "Quitter" };
	private final int[] yOptions = { 0, 0, 0 };
	private int selection;
	private GameContainer container;
	private Sound jeuEnPause;
	private Sound menu;
	private boolean jeuEnPauseDit;
	private boolean continuerPartieDit;
	private boolean menuDit;
	private boolean quitterDit;

	@Override
	public int getID() {
		return ID;
	}

	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		this.game = game;
		this.container = container;
		jeuEnPauseDit = true;

		menu = new Sound("../ressources/data/aide/Pause/alleraumenu.wav");
		jeuEnPause = new Sound("../ressources/data/aide/Pause/pause.wav");

		for (int i = 0; i < yOptions.length; i++)
			yOptions[i] = (1000 - Cross2Sophia.fUnicodeFont
					.getWidth(options[i])) / 2;
		Cross2Sophia.fUnicodeFont.getEffects().add(
				new org.newdawn.slick.font.effects.ColorEffect(
						java.awt.Color.WHITE));
		Cross2Sophia.fUnicodeFont.addAsciiGlyphs();
		Cross2Sophia.fUnicodeFont.loadGlyphs();
	}

	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		Input input = container.getInput();
		g.setColor(new Color(255, 255, 255));
		g.fillRect(0, 0, 1024, 768);

		if (input.isKeyPressed(Input.KEY_DOWN)) {
			jeuEnPause.stop();
			Cross2Sophia.voix.stop();
			menu.stop();
			if (selection >= 2) {
				selection = 2;
			} else
				selection++;
		}
		if (input.isKeyPressed(Input.KEY_UP)) {
			jeuEnPause.stop();
			Cross2Sophia.voix.stop();
			menu.stop();

			if (selection <= 0)
				selection = 0;
			else
				selection--;
		}

		if (jeuEnPauseDit) {
			jeuEnPauseDit = false;
			jeuEnPause.play();
		}

		if (!jeuEnPauseDit && !jeuEnPause.playing()) {
			if (selection == 0 && !continuerPartieDit) {
				Cross2Sophia.voix.playShortText("Reprendre la partie");
				continuerPartieDit = true;
				menuDit = false;
				quitterDit = false;
			} else if (selection == 1 && !menuDit) {
				menu.play();
				continuerPartieDit = false;
				menuDit = true;
				quitterDit = false;
			} else if (selection == 2 && !quitterDit) {
				Cross2Sophia.voix.playShortText("Quitter le jeu");
				continuerPartieDit = false;
				menuDit = false;
				quitterDit = true;
			}
		}

		Cross2Sophia.fUnicodeFont.drawString(280, 10, "PAUSE",
				Cross2Sophia.noir);
		// Cadres bleus :
		g.setColor(Cross2Sophia.bleu);
		for (int i = 0, z = 10; i < 3; i++, z += 90)
			g.fillRoundRect(10, 10 + z + 3 * i, 1000, 90, 10);

		// Textes des options :
		for (int i = 0; i < yOptions.length; i++)
			Cross2Sophia.fUnicodeFont.drawString(yOptions[i], 10 + 90 * i,
					options[i], Cross2Sophia.jaune);

		// Affichage sélection :
		g.setColor(Cross2Sophia.jaune);
		g.fillRoundRect(10, 20 + 90 * selection + 3 * selection, 1000, 90, 10);
		Cross2Sophia.fUnicodeFont.drawString(yOptions[selection],
				10 + 90 * selection, options[selection], Cross2Sophia.bleu);

		game.enterState(Pause.ID);
		// if (input.isKeyPressed(Input.KEY_ESCAPE))
		// System.exit(0);
		if (input.isKeyPressed(Input.KEY_ENTER)) {
			if (selection == 0) {
				jeuEnPause.stop();
				Cross2Sophia.voix.stop();
				menu.stop();
				jeuEnPauseDit = true;
				game.enterState(Gaming.ID);
			}
			if (selection == 1) {
				jeuEnPause.stop();
				Cross2Sophia.voix.stop();
				menu.stop();
				jeuEnPauseDit = true;
				game.enterState(Menu.ID);
			}
			if (selection == 2)
				System.exit(0);
		}

		if (input.isKeyPressed(Input.KEY_ESCAPE))
			System.exit(0);
	}

	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {
	}

	public void keyReleased(int key, char c) {
		/*
		 * Input input = container.getInput(); if
		 * (input.isKeyPressed(Input.KEY_UP)) { switch (selection) { case 0:
		 * game.enterState(Gaming.ID); break; case 1: System.exit(0); case 2:
		 * game.enterState(Menu.ID); break; case 3: System.exit(0); default:
		 * game.enterState(Pause.ID); } } System.out.println("selection = " +
		 * selection);
		 */
	}
}