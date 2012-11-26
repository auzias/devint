package gameStates;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javazoom.jlgui.basicplayer.BasicPlayerException;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

import util.*;

import entities.Fleche;

public class Gaming extends BasicGameState {
	public static final int ID = 8;
	private static ArrayList<Fleche> fleches = new ArrayList<Fleche>();
	private static ArrayList<Fleche> flechesToPrint = new ArrayList<Fleche>();
	private boolean isPlaying;
	private boolean tricheEnd = false;
	private static boolean first;

	@Override
	public void init(GameContainer containter, StateBasedGame game)
	throws SlickException {
		this.isPlaying = false;
		first = true;
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
	throws SlickException {
		g.setColor(Fc.noir);
		g.fillRect(0, 0, Fc.width, Fc.height);
		g.setColor(Fc.blanc);
		g.drawString(Fc.gamers.getMod().toString(), 100, 0);
		g.drawString(Fc.gamers.getLevel().toString(), 200, 0);
		for (Fleche fleche : flechesToPrint)
			g.drawAnimation(fleche.getSprite(), fleche.getX(), fleche.getY());
		this.printInfo(g);
		Fc.gamers.draw(g);
	}

	private void printInfo(Graphics g) throws SlickException {
		g.drawImage(new Image("./../ressources/gaucheImmobile.png"),
				Fc.xGauche, Fc.yFlecheImmobile);
		g.drawImage(new Image("./../ressources/hautImmobile.png"), Fc.xHaut,
				Fc.yFlecheImmobile);
		g.drawImage(new Image("./../ressources/basImmobile.png"), Fc.xBas,
				Fc.yFlecheImmobile);
		g.drawImage(new Image("./../ressources/droiteImmobile.png"),
				Fc.xDroite, Fc.yFlecheImmobile);
		Fc.gamers.draw(g);
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)
	throws SlickException {
		// From : MusicChoice | Pause, Back : pause
		Input input = container.getInput();
		if (input.isKeyPressed(Input.KEY_C))
			tricheEnd  = true;
		// Lecture (ou reprise) de la chanson :
		if (!isPlaying && !Fc.pause) {
			this.isPlaying = true;
			try {
				if (first) {
					Fc.player.open(Fc.song);
					Fc.player.play();
					Progress.start();
					this.flechesUpdate();
					first = false;
				} else {
					Fc.player.resume();
					Progress.resume();
				}
				Fc.volUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		int joueur = 1;
		if (input.isKeyPressed(Input.KEY_LEFT))
			this.check(DIRECTION.GAUCHE, joueur);
		if (input.isKeyPressed(Input.KEY_UP))
			this.check(DIRECTION.HAUT, joueur);
		if (input.isKeyPressed(Input.KEY_DOWN))
			this.check(DIRECTION.BAS, joueur);
		if (input.isKeyPressed(Input.KEY_RIGHT))
			this.check(DIRECTION.DROITE, joueur);
		joueur = 2;
		if (input.isKeyPressed(Input.KEY_Q))
			this.check(DIRECTION.GAUCHE, joueur);
		if (input.isKeyPressed(Input.KEY_Z))
			this.check(DIRECTION.HAUT, joueur);
		if (input.isKeyPressed(Input.KEY_S))
			this.check(DIRECTION.BAS, joueur);
		if (input.isKeyPressed(Input.KEY_D))
			this.check(DIRECTION.DROITE, joueur);

		if (input.isKeyPressed(Input.KEY_ESCAPE)) {
			try {
				Fc.player.pause();
				Progress.stop();
				this.isPlaying = false;
				Fc.pause = true;
			} catch (BasicPlayerException e) {
				e.printStackTrace();
			}
			game.enterState(new Pause().getID(), Fc.fot, Fc.fit);
			Fc.pause = false;
		}
		// Gestion des fleches a afficher :
		if (!fleches.isEmpty() && Progress.stime() > Fc.gap)
			if (fleches.get(0).getMomentRef() - Fc.tempsDeplacement <= (int) Progress
					.stime()) {
				flechesToPrint.add(fleches.get(0));
				fleches.remove(0);
			}
		// Gestion des mouvements :
		this.mouvement(container, delta);
		// Gère la flèche qui sort de l'écran
		for (int i = 0; i < flechesToPrint.size(); i++)
			if (flechesToPrint.get(i).getY() <= Fc.horsEcran) {
				flechesToPrint.remove(i);
				if (Fc.gamers.getMod() != Mode.Mod.VS) {
					Fc.gamers.setScore1(Fc.gamers.getPoints1() - (Fc.gamers.getLevel().getPointsMax()/5));
					Fc.gamers.setCombo1Down();
					Fc.gamers.moyenne1Worst();
				}
				if (i > 0)
					i--;
			}
		this.checkEnd(game);
	}

	private void flechesUpdate() throws SlickException, IOException {
		switch (Fc.gamers.getLevel().getCurrentLevel()) {
		case EASY:
		default:
			this.cleanList();
			this.cleanList(2);
			break;
		case MIDDLE:
			this.cleanList();
			this.cleanList(4);
			break;
		case HARD:
			this.cleanList();
			break;
		case UNDOABLE:
			this.dirtyListUndoable();
			break;
		}
		for(Fleche fleche : fleches)
			System.out.println(":" + fleche);
		System.out.println("fleches.size():" + fleches.size());
	}

	private void cleanList() {
		// Suppression des fleches superposées, des fleches "aucune" :
		while(fleches.get(0).getDir() == DIRECTION.AUCUNE)
			fleches.remove(0);
		for (int i = fleches.size()-1; i >= 1; i--) {
			if (fleches.get(i).getDir() == DIRECTION.AUCUNE
					|| ((fleches.get(i).getMomentRef() == fleches.get(i - 1).getMomentRef())))
				fleches.remove(i);
		}
	}

	private void dirtyListUndoable() throws SlickException, IOException {
		fleches.clear();
		ArrayList<Fleche> toAdd = new ArrayList<Fleche>();
		for (int i = 0; i < Fc.getTimeInSec(Fc.song.getCanonicalPath()); i++) {
			toAdd.clear();
			switch (new Random().nextInt(5) + 1) {
			case 1:
				toAdd.add(0, new Fleche(DIRECTION.HAUT, i));
				toAdd.add(1, new Fleche(DIRECTION.DROITE, i));
				toAdd.add(2, new Fleche(DIRECTION.GAUCHE, i));
				break;
			case 2:
				toAdd.add(0, new Fleche(DIRECTION.BAS, i));
				toAdd.add(1, new Fleche(DIRECTION.HAUT, i));
				toAdd.add(2, new Fleche(DIRECTION.DROITE, i));
				break;
			case 3:
				toAdd.add(0, new Fleche(DIRECTION.BAS, i));
				toAdd.add(1, new Fleche(DIRECTION.HAUT, i));
				toAdd.add(2, new Fleche(DIRECTION.GAUCHE, i));
				break;
			case 4:
				toAdd.add(0, new Fleche(DIRECTION.BAS, i));
				toAdd.add(1, new Fleche(DIRECTION.DROITE, i));
				toAdd.add(2, new Fleche(DIRECTION.GAUCHE, i));
				break;
			case 5:
			default:
				toAdd.add(0, new Fleche(DIRECTION.BAS, i));
				toAdd.add(1, new Fleche(DIRECTION.HAUT, i));
				toAdd.add(2, new Fleche(DIRECTION.DROITE, i));
				toAdd.add(3, new Fleche(DIRECTION.GAUCHE, i));
				break;
			}
			fleches.addAll(toAdd);
		}
		System.out.println("fleches.size():" + fleches.size());
	}

	private void cleanList(int i) {
		for (int j = fleches.size(); j >= 1; j--)
			if (j % i == 0)
				fleches.remove(j - 1);
	}

	private void check(DIRECTION dir, int joueur) {
		for (int i = 0; i < flechesToPrint.size(); i++) {
			if (flechesToPrint.get(i).getDir() == dir) {
				double denom = 1;
				int position = (int) flechesToPrint.get(i).getY();
				denom = (position == Fc.yFlecheImmobile) ? 1
						: Fc.yFlecheImmobile - (double) position;
				long points = (long) (Fc.gamers.getLevel().getPointsMax() * Math
						.abs(49 / denom));
				if (joueur == 1) {
					Fc.gamers.setScore1(Fc.gamers.getPoints1() + points);
					Fc.gamers.moyenne1Update(position);
				} else {
					Fc.gamers.setScore2(Fc.gamers.getPoints2() + points);
					Fc.gamers.moyenne2Update(position);
				}
				if (Fc.gamers.getMod() != Mode.Mod.VS)
					flechesToPrint.remove(i);
				break;
			}
		}
	}

	private void checkEnd(StateBasedGame game) {
		Progress.stop();
		double duree = Progress.stime();
		Progress.resume();
		try {
			if (tricheEnd || Fc.getTimeInSec(Fc.song.getCanonicalPath()) <= duree
					|| (fleches.isEmpty() && flechesToPrint.isEmpty())) {
				Fc.player.stop();
				Fc.volMin();
				Fc.player.play();
				game.enterState(new EndGaming().getID(), Fc.fot, Fc.fit);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	static void loadSM() {
		try {
			fleches = SMFile.loadSM(MusicChoice.trackChosen);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public int getID() {
		return Gaming.ID;
	}
	
	public static void clearList() {
		fleches.clear();
		flechesToPrint.clear();
	}

	public static void restart() {
		first = true;
		clearList();
		if(MusicChoice.trackChosen != null)
			loadSM();
	}

	private void mouvement(GameContainer container, int delta)
	throws SlickException {
		for (Fleche fleche : flechesToPrint) {
			float fdelta = delta * 0.25f;
			float fdeltaE1 = delta * fleche.getSpeed();
			fleche.setPosition(fdeltaE1, (int) fdelta);
		}
	}
}