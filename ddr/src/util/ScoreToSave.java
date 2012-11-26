package util;

import gameStates.MusicChoice;

/**
 * is used to create scores
 * 
 * @version 1.0
 * @author Montiel
 * 
 * 
 */

public class ScoreToSave {
	private long result;
	private String name;
	private String musique;

	/**
	 * Default Constructor: Create a new Score() with default fields
	 */
	public ScoreToSave() {
		result = 0;
		name = "Nom par défaut";
		musique = "Musique par défaut";
	}

	/**
	 * Constructor: Initialise all fields to create a new Score()
	 * 
	 * @param name
	 *            String
	 * @param result
	 *            int
	 */
	public ScoreToSave(String name, long result) {
		this.result = result;
		this.name = name;
		this.musique = MusicChoice.trackChosen;
	}

	public long getResult() {
		return result;
	}

	public void setResult(long result) {
		this.result = result;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMusique() {
		return musique;
	}

	public void setMusique(String musique) {
		this.musique = musique;
	}

	/**
	 * Return the result of Score
	 * 
	 * @return int
	 */
	public String toString() {
		int max = 6;
		if (name.length() < 6)
			do {
				name += " ";
			} while (name.length() < 8);
		return "" + name.toUpperCase().substring(0, 1)
				+ name.toLowerCase().substring(1, max) + " " + result;
	}
}