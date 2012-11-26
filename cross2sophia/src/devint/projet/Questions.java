package devint.projet;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class Questions extends BasicGameState {

	public static final int ID = 3;

	/** The index of the selected option */

	private StateBasedGame game;
	private FileReader monFichier = null;
	private BufferedReader tampon = null;
	private Random random = new Random();
	private String[] tabChaine = null;
	private ArrayList<String> listQuestions;
	private ArrayList<String> listeRepMelangees;
	private int nbQuestions;
	private int question;
	private String goodReponse;
	private String reponse;
	private GameContainer container;
	private boolean enCours;
	private boolean lues;
	private Sound bonneReponse;
	private Sound mauvaiseReponse;
	private static int debogage = 22;

	@Override
	public int getID() {
		return ID;
	}

	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		Cross2Sophia.fUnicodeFont.getEffects().add(new org.newdawn.slick.font.effects.ColorEffect(java.awt.Color.BLACK));
		Cross2Sophia.fUnicodeFont.addAsciiGlyphs();
		Cross2Sophia.fUnicodeFont.loadGlyphs();

		bonneReponse = new Sound("../ressources/data/sons/bonnereponse.wav");
		mauvaiseReponse = new Sound(
				"../ressources/data/sons/mauvaisereponse.wav");

		enCours = false;
		lues = false;

		listQuestions = new ArrayList<String>();
		listeRepMelangees = new ArrayList<String>();

		try {
			monFichier = new FileReader("../ressources/data/questions.csv");
			tampon = new BufferedReader(monFichier);
			while (true) {
				// Lit une ligne de test.csv
				String ligne = tampon.readLine();
				// VÃ©rifie la fin de fichier
				if (ligne == null)
					break;
				tabChaine = ligne.split(";");
				for (int i = 0; i < tabChaine.length; i++)
					listQuestions.add(tabChaine[i]);

				nbQuestions = listQuestions.size() / 5;

				for (int i = 1; i < nbQuestions; i++)
					listQuestions.set(i * 5,
							listQuestions.get(i * 5).replace("%", "\n"));

			} // Fin du while
		} catch (IOException exception) {
			exception.printStackTrace();
		} finally {
			try {
				tampon.close();
				monFichier.close();
			} catch (IOException exception1) {
				exception1.printStackTrace();
			}
		}
		nbQuestions = (listQuestions.size() / 5);
		question = debogage;
		goodReponse = listQuestions.get(question+1);
		debogage++;
		melanger();
	}

	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		g.setColor(new Color(255, 255, 255));
		g.fillRect(0, 0, 1024, 768);
		g.setColor(new Color(0, 0, 0));

		Cross2Sophia.fUnicodeFont.drawString(10, 0,
				listQuestions.get(question * 5), Cross2Sophia.noir);

		int y = 285;
		Cross2Sophia.fUnicodeFont.drawString(200, y, listeRepMelangees.get(0),
				Cross2Sophia.noir);
		g.drawImage(Menu.f9, 50, 267);
		Cross2Sophia.fUnicodeFont.drawString(200, y += 125,
				listeRepMelangees.get(1), Cross2Sophia.noir);
		g.drawImage(Menu.f10, 50, 392);
		Cross2Sophia.fUnicodeFont.drawString(200, y += 125,
				listeRepMelangees.get(2), Cross2Sophia.noir);
		g.drawImage(Menu.f11, 50, 517);
		Cross2Sophia.fUnicodeFont.drawString(200, y += 125,
				listeRepMelangees.get(3), Cross2Sophia.noir);
		g.drawImage(Menu.f12, 50, 642);
		attente(1000);

/*		if (Gaming.isLireQuestions()) {
			if (!lues) {

				Cross2Sophia.voix.playText("Question :"
						+ listQuestions.get(question * 5) + "Réponse F 9 pour "
						+ listeRepMelangees.get(0) + "Réponse F 10 pour "
						+ listeRepMelangees.get(1) + "Réponse F 11 pour "
						+ listeRepMelangees.get(2) + "Réponse F 12 pour "
						+ listeRepMelangees.get(3));

				lues = true;

			}
			Gaming.setLireQuestions(false);
		}
*/
	}

	private void attente(long i) {
		try {
			Thread.sleep(i);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {
		Input input = container.getInput();
		boolean repondu = false;
		if (input.isKeyPressed(Input.KEY_SPACE)) {
			Gaming.setLireQuestions(true);
			lues = false;
		}
		if (input.isKeyPressed(Input.KEY_F9)
				&& !input.isKeyPressed(Input.KEY_F10)
				&& !input.isKeyPressed(Input.KEY_F11)
				&& !input.isKeyPressed(Input.KEY_F12)) {
			reponse = listeRepMelangees.get(0);
			if (reponse.equals(goodReponse)) {
				bonneReponse.play();
				Gaming.setPlayerScoreReponse();
				repondu = true;
			} else
				mauvaiseReponse.play();
			question = debogage;
			goodReponse = listQuestions.get(question+1);
			debogage++;
			listeRepMelangees = new ArrayList<String>();
			melanger();
			repondu = true;
		}

		if (input.isKeyPressed(Input.KEY_F10)
				&& !input.isKeyPressed(Input.KEY_F9)
				&& !input.isKeyPressed(Input.KEY_F11)
				&& !input.isKeyPressed(Input.KEY_F12)) {
			reponse = listeRepMelangees.get(1);
			if (reponse.equals(goodReponse)) {
				bonneReponse.play();
				Gaming.setPlayerScoreReponse();
				repondu = true;
			} else
				mauvaiseReponse.play();
			question = debogage;
			goodReponse = listQuestions.get(question+1);
			debogage++;
			listeRepMelangees = new ArrayList<String>();
			melanger();
			repondu = true;
		}

		if (input.isKeyPressed(Input.KEY_F11)
				&& !input.isKeyPressed(Input.KEY_F10)
				&& !input.isKeyPressed(Input.KEY_F9)
				&& !input.isKeyPressed(Input.KEY_F12)) {
			reponse = listeRepMelangees.get(2);
			if (reponse.equals(goodReponse)) {
				bonneReponse.play();
				Gaming.setPlayerScoreReponse();
				game.enterState(Gaming.ID);
			} else
				mauvaiseReponse.play();
			question = debogage;
			goodReponse = listQuestions.get(question+1);
			debogage++;
			listeRepMelangees = new ArrayList<String>();
			melanger();
			repondu = true;
		}

		if (input.isKeyPressed(Input.KEY_F12)
				&& !input.isKeyPressed(Input.KEY_F10)
				&& !input.isKeyPressed(Input.KEY_F11)
				&& !input.isKeyPressed(Input.KEY_F9)) {
			reponse = listeRepMelangees.get(3);
			if (reponse.equals(goodReponse)) {
				bonneReponse.play();
				Gaming.setPlayerScoreReponse();
			} else
				mauvaiseReponse.play();
			question = debogage;
			goodReponse = listQuestions.get(question+1);
			debogage++;
			listeRepMelangees = new ArrayList<String>();
			melanger();
			repondu = true;
		}

		if (repondu) {
			enCours = false;
			lues = false;
			game.enterState(Gaming.ID);
		} else {
			game.enterState(Questions.ID);
		}
	}

	public void keyReleased(int key, char c) {
		if (key == Input.KEY_ENTER)
			game.enterState(Gaming.ID);
		if (key == Input.KEY_ESCAPE)
			game.getContainer().exit();
	}

	public void melanger() {
		ArrayList<String> listTemp = new ArrayList<String>();

		for (int i = 1; i < 5; i++)
			listTemp.add(listQuestions.get(question * 5 + i));

		if (listTemp.size() > 0) {
			ArrayList<String> copy = new ArrayList<String>();
			for (String str : listTemp)
				copy.add(str);
			Random generator = new Random();

			do {
				int index = (int) (generator.nextDouble() * (double) copy
						.size());
				listeRepMelangees.add(copy.remove(index));
			} while (copy.size() > 0);
		}
	}

}