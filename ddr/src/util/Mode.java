package util;

public class Mode {
	public enum Mod {
		SOLO,
		COOP,
		VS
	}

	private Mod currentMod;

	public Mode(Mod niveau) {
		this.setCurrentMod(niveau);
	}

	public void setCurrentMod(Mod currentMod) {
		this.currentMod = currentMod;
	}

	public Mod getCurrentMod() {
		return currentMod;
	}

	public String toString() {
		switch (currentMod) {
		case SOLO:
			return "solo";
		case COOP:
			return "coop";
		case VS:
			return "vs";
		default:
			return "Mode non trouvé";
		}
	}
}