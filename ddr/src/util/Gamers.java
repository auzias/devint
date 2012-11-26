package util;

import java.util.ArrayList;

import org.newdawn.slick.*;

import entities.Fleche;

import util.Mode.Mod;
import util.Levels.*;

public class Gamers {
	private String name1;
	private String name2;
	private Score score1 = new Score();
	private Score score2 = new Score();
	private Mode mode = new Mode(Mod.SOLO);
	private Levels level = new Levels(Levels.Level.EASY);
	private ArrayList<Fleche> fleches = new ArrayList<Fleche>();

	public Gamers() {
	}

	public Mode getMode() {
		return mode;
	}

	public Mode.Mod getMod() {
		return mode.getCurrentMod();
	}

	public void setMod(Mod mode) {
		this.mode.setCurrentMod(mode);
	}

	public String getName1() {
		return name1;
	}

	public void setName1(String name1) {
		this.name1 = name1;
	}

	public String getName2() {
		return name2;
	}

	public void setName2(String name2) {
		this.name2 = name2;
	}

	public Levels getLevel() {
		return level;
	}

	public void setLevel(Level niveau) {
		this.level = new Levels(niveau);
	}

	public long getPoints1() {
		return score1.getPoints();
	}

	public long getPoints2() {
		return score2.getPoints();
	}

	public long getAvance1() {
		return score1.getEnAvance();
	}

	public long getRetard1() {
		return score1.getEnRetard();
	}

	public void setScore1(long score1) {
		this.score1.setPoints(score1);
	}

	public void setScore2(long score2) {
		this.score2.setPoints(score2);
	}

	public void mouvement(GameContainer container, int delta)
			throws SlickException {
		// Deplacements des fleches :
		for (Fleche fleche : fleches) {
			float fdelta = delta * 0.25f;
			float fdeltaE1 = delta * fleche.getSpeed();
			fleche.setPosition(fdeltaE1, (int) fdelta);
		}
	}

	public void draw(Graphics g) throws SlickException {
		Fc.fontEffect.drawString(Fc.xPrintScore1, Fc.yPrintScore,
				"" + this.getPoints1(), Fc.kit.getBackGroundText());
		int joueur = 1;
		this.drawCombo(g, this.score1.getCombo(), joueur);
		joueur = 2;
		if (Fc.gamers.getMod() == Mode.Mod.VS) {
			Fc.fontEffect.drawString(
					Fc.width - Fc.fontEffect.getWidth("" + this.getPoints2())
							- 3, Fc.yPrintScore, "" + this.getPoints2(),
					Fc.kit.getBackGroundText());
			this.drawCombo(g, this.score2.getCombo(), joueur);
		}
	}

	private void drawCombo(Graphics g, int combo, int joueur) {
		float tailleBarre = 400;
		float taille = (combo * (tailleBarre / Fc.comboUltime));
		float largeurBarre = 40;
		float haut = Fc.yFlecheImmobile + Fc.tailleFleche;
		float bas = haut + tailleBarre;
		float x = (joueur == 1) ? 10 : Fc.width - 10 - largeurBarre;
		int radius = 6;
		// Barre combo max :
		g.setColor(Fc.kit.getTextColor());
		g.fillRoundRect(x, haut, largeurBarre, Fc.comboUltime
				* (tailleBarre / Fc.comboUltime), radius);
		// Barre combo actuelle :
		g.setColor(Fc.kit.getBackGroundText());
		g.fillRoundRect(x, bas - taille, largeurBarre, taille, radius);
		g.drawString("" + combo, x, 0);
	}

	public void restart() {
		name1 = "";
		name2 = "";
		score1 = new Score();
		score2 = new Score();
		mode = Fc.gamers.getMode();
		level = Fc.gamers.getLevel();
		this.fleches = new ArrayList<Fleche>();
	}

	public void moyenne1Update(int position) {
		this.score1.moyenneUpdate(position);
	}

	public int getNote1() {
		return this.score1.getPositionMoyenne();
	}

	public void moyenne2Update(int position) {
		this.score2.moyenneUpdate(position);
	}

	public void setCombo1Down() {
		this.score1.comboDown();
	}

	public void printEnd() {
		int x = 10;
		int y = 10;
		int lineHeight = 80;
		String note = "";
		if (Fc.gamers.getNote1() == 0)
			note = "AAA Félicitation!";
		if (0 < Fc.gamers.getNote1() && Fc.gamers.getNote1() <= 10)
			note = "A";
		if (10 < Fc.gamers.getNote1() && Fc.gamers.getNote1() <= 40)
			note = "B";
		if (40 < Fc.gamers.getNote1() && Fc.gamers.getNote1() <= 100)
			note = "C";
		if (100 < Fc.gamers.getNote1() && Fc.gamers.getNote1() <= 150)
			note = "D";
		if (150 < Fc.gamers.getNote1() && Fc.gamers.getNote1() <= 200)
			note = "E";
		if (200 < Fc.gamers.getNote1())
			note = "F";

		String commentaire = "Tu appuies trop ";
		commentaire += (Fc.gamers.getAvance1() > Fc.gamers.getRetard1()) ? "tôt"
				: "tard";
		int xJoueur = (Fc.width - Fc.fontEffect.getWidth("Joueur 1")) / 2;
		Fc.fontEffect.drawString(xJoueur, y, "Joueur 1",
				Fc.kit.getBackGroundText());
		Fc.fontEffect.drawString(x, (y += lineHeight), "Note : " + note,
				Fc.kit.getBackGroundText());
		Fc.fontEffect.drawString(x, (y += lineHeight), commentaire,
				Fc.kit.getBackGroundText());
		Fc.fontEffect
				.drawString(x, (y += lineHeight),
						"Score : " + Fc.gamers.getPoints1(),
						Fc.kit.getBackGroundText());
		if (Fc.gamers.getMod() == Mode.Mod.VS) {
			
			if (Fc.gamers.getNote2() == 0)
				note = "AAA Félicitation!";
			if (0 < Fc.gamers.getNote2() && Fc.gamers.getNote2() <= 10)
				note = "A";
			if (10 < Fc.gamers.getNote2() && Fc.gamers.getNote2() <= 40)
				note = "B";
			if (40 < Fc.gamers.getNote2() && Fc.gamers.getNote2() <= 100)
				note = "C";
			if (100 < Fc.gamers.getNote2() && Fc.gamers.getNote2() <= 150)
				note = "D";
			if (150 < Fc.gamers.getNote2() && Fc.gamers.getNote2() <= 200)
				note = "E";
			if (200 < Fc.gamers.getNote2())
				note = "F";

			commentaire = "Tu appuies trop ";
			commentaire += (Fc.gamers.getAvance2() > Fc.gamers.getRetard2()) ? "tôt"
					: "tard";
			
			y += lineHeight;
			Fc.fontEffect.drawString(xJoueur, (y += lineHeight), "Joueur 2",
					Fc.kit.getBackGroundText());
			Fc.fontEffect.drawString(x, (y += lineHeight), "Note : " + note,
					Fc.kit.getBackGroundText());
			Fc.fontEffect.drawString(x, (y += lineHeight), commentaire,
					Fc.kit.getBackGroundText());
			Fc.fontEffect.drawString(x, (y += lineHeight), "Score : "
					+ Fc.gamers.getPoints2(), Fc.kit.getBackGroundText());
		}
		// Fc.fontEffect.drawString(300, 0, "Moyenne : " + Fc.gamers.getNote1(),
		// Fc.kit.getBackGroundText());
		// Fc.fontEffect.drawString(x, 200, "Avance : " +
		// Fc.gamers.getAvance1(), Fc.kit.getBackGroundText());
		// Fc.fontEffect.drawString(x, 300, "Retard : " +
		// Fc.gamers.getRetard1(), Fc.kit.getBackGroundText());
	}

	private int getNote2() {
		return this.score2.getPositionMoyenne();
	}

	private int getRetard2() {
		return score2.getEnRetard();
	}

	private int getAvance2() {
		return score2.getEnAvance();
	}

	public void moyenne1Worst() {
		this.score1.moyenneWorst();
	}
}
