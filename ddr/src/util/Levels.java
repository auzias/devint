package util;

public class Levels {
	public enum Level {
		EASY,
		MIDDLE,
		HARD,
		UNDOABLE
	}
	private Level currentLevel;
	
	public Levels(Level niveau) {
		this.setCurrentLevel(niveau);
	}

	public void setCurrentLevel(Level currentLevel) {
		this.currentLevel = currentLevel;
	}

	public Level getCurrentLevel() {
		return currentLevel;
	}

	public String toString() {
		switch (currentLevel) {
		case EASY:
			return "easy";
		case MIDDLE:
			return "middle";
		case HARD:
			return "hard";
		case UNDOABLE:
			return "undoable";
		default:
			return "Niveau non trouvé";
		}
	}

	public int getPointsMax() {
		switch (currentLevel) {
		case EASY:
			return Fc.pointsEasy;
		case MIDDLE:
			return Fc.pointsMiddle;
		case HARD:
			return Fc.pointsHard;
		case UNDOABLE:
		default:
			return Fc.pointsUndoable;
		}
	}
}