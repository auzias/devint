package util;

import org.newdawn.slick.Color;

public class KitGraphique {
	private Color titleColor;
	private Color textColor;
	private Color backGroundTitle;
	private Color backGroundText;
	public static final KitGraphique kit1 = new KitGraphique(Fc.noir, Fc.bleu,
			Fc.blanc, Fc.jaune);
	public static final KitGraphique kit2 = new KitGraphique(Fc.noir, Fc.noir,
			Fc.bleu, Fc.vert);
	public static final KitGraphique kit3 = new KitGraphique(Fc.rouge,
			Fc.orange, Fc.blanc, Fc.noir);
	public static final KitGraphique kit4 = new KitGraphique(Fc.jaune, Fc.bleu,
			Fc.noir, Fc.blanc);

	public KitGraphique(Color title, Color text, Color backGroundTitle,
			Color backGroundText) {
		super();
		this.titleColor = title;
		this.textColor = text;
		this.backGroundTitle = backGroundTitle;
		this.backGroundText = backGroundText;
	}

	public Color getTitleColor() {
		return titleColor;
	}

	public Color getTextColor() {
		return textColor;
	}

	public Color getBackGroundTitle() {
		return backGroundTitle;
	}

	public Color getBackGroundText() {
		return backGroundText;
	}
}