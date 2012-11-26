package util;

import java.util.ArrayList;

/**
 * 
 * @version 1.0
 * @author Montiel
 * 
 * 
 */

public class RankList {

	private ArrayList<ScoreToSave> listeScores;

	/**
	 * Constructor:
	 * 
	 * @param listeE
	 *            ArrayList<Equipe>
	 * @param name
	 *            String
	 */
	public RankList(ArrayList<ScoreToSave> listeE) {
		listeScores = listeE;
	}

	/**
	 * Default Constructor: Create a new RankList() with default fields
	 */
	public RankList() {
		listeScores = new ArrayList<ScoreToSave>();

	}

	/**
	 * Return the ArrayList<Score>
	 * 
	 * @return ArrayList<Score>
	 */
	public ArrayList<ScoreToSave> getListeScores() {
		for (int i = 1; i < listeScores.size(); i++) {
			for (int j = i; j > 0; j--) {

				if (listeScores.get(j).getResult() > listeScores.get(j - 1)
						.getResult()) {
					ScoreToSave temp = listeScores.get(j - 1);
					listeScores.set(j - 1, listeScores.get(j));
					listeScores.set(j, temp);
				}
			}
		}

		return listeScores;

	}

	/**
	 * Add a new Score
	 * 
	 * @param eq
	 *            Score
	 */
	public void addScore(ScoreToSave sc)

	{
		listeScores.add(sc);
	}

	public void addScore(String name, int result) {
		ScoreToSave s1 = new ScoreToSave(name, result);
		listeScores.add(s1);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		String structure = "TOP 10 SCORES: \n\n";
		for (int k = 0; k != listeScores.size(); k++) {
			structure += listeScores.get(k) + "\n";
		}
		return structure;
	}
}