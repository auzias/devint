package util;

public enum DIRECTION {
	GAUCHE, HAUT, BAS, DROITE, AUCUNE;

	public static int getX(DIRECTION dir) {
		switch (dir) {
		case GAUCHE:
			return Fc.xGauche;
		case HAUT:
			return Fc.xHaut;
		case BAS:
			return Fc.xBas;
		case DROITE:
			return Fc.xDroite;
		case AUCUNE:
		default:
			return Fc.xAucune;
		}
	}

	public String toString(DIRECTION dir) {
		switch (dir) {
		case GAUCHE:
			return "gauche";
		case HAUT:
			return "haut";
		case BAS:
			return "bas";
		case DROITE:
			return "droite";
		case AUCUNE:
		default:
			return "aucune";
		}
	}
}