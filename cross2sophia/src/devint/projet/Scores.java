package devint.projet;

import java.awt.Font;
import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.util.Log;

import devint.projet.GameStates.STATES;
import org.newdawn.slick.Input;

public class Scores extends BasicGameState {

	public static final int ID = 4;

	private StateBasedGame game;
	private Restaurer restaurer;
	private boolean newScore;
	private RankList ranklist;
	private SaveScore sc;
	private int debut;
	private ArrayList<Score> listetriee;

	@Override
	public int getID() {
		return ID;
	}

	public void init(GameContainer container, StateBasedGame game)
	throws SlickException {
		newScore = false;
		debut = 1;
		restaurer = new Restaurer("../ressources/data/RankList.xml");
		ranklist = restaurer.restaurer();
		sc = new SaveScore();
		listetriee = new ArrayList<Score>();
	}

	public void render(GameContainer container, StateBasedGame game, Graphics g)
	throws SlickException {
		



		g.setColor(new Color(255, 255, 255));
		g.fillRect(0, 0, 1024, 768);
		g.setColor(new Color(255, 0, 255));
		for (int y = 10; y < 20; y++) {
			g.drawRoundRect(10, y, 1000, 100, 10);
			g.drawRoundRect(10, y, 1000, 720, 10);
		}

		restaurer = new Restaurer("../ressources/data/RankList.xml");
		ranklist = restaurer.restaurer();

		listetriee = ranklist.getListeScores();

		Input input = container.getInput();
		if (input.isKeyPressed(Input.KEY_DOWN)) {
			if (debut >= ranklist.getListeScores().size() - 7)
				debut = ranklist.getListeScores().size() - 7;
			else
				debut++;
		}
		if (input.isKeyPressed(Input.KEY_UP)) {
			if (debut <= 1)
				debut = 1;
			else
				debut--;
		}

		Cross2Sophia.fUnicodeFont.drawString(240, 10, "Meilleurs scores :", Cross2Sophia.noir);
		for (int i = debut, z = 100; i < debut + 7; i++, z += 90)
			Cross2Sophia.fUnicodeFont.drawString(10, z, "" + i + " - "
					+ ranklist.getListeScores().get(i - 1).toString(), Cross2Sophia.noir);
		
		g.drawImage(Menu.haut, 800, 170);
		g.drawImage(Menu.bas, 800, 450);
	}

	public void update(GameContainer container, StateBasedGame game, int delta)
	throws SlickException {
		Input input = container.getInput();
		if (input.isKeyPressed(Input.KEY_ESCAPE))
			game.enterState(Menu.ID);

		if (newScore == true) {
			restaurer = new Restaurer("../ressources/data/RankList.xml");
			ranklist = restaurer.restaurer();

			listetriee = ranklist.getListeScores();
			sc = new SaveScore();
			sc.creerDocument(listetriee);
			newScore = false;
			game.enterState(Gaming.ID);
		}
	}

	public void setNewScore(boolean newScore) {
		this.newScore = newScore;
	}

	public void keyReleased(int key, char c) {

	}
}