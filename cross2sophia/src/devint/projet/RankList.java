package devint.projet;

import java.util.ArrayList;

/**
 * 
 * @version 1.0
 * @author Montiel
 * 
 * 
 */

public class RankList {

	private ArrayList<Score> listeScores;

	/**
	 * Constructor:
	 * 
	 * @param listeE
	 *            ArrayList<Equipe>
	 * @param name
	 *            String
	 */
	public RankList(ArrayList<Score> listeE) {
		listeScores = listeE;

	}

	/**
	 * Default Constructor: Create a new RankList() with default fields
	 */
	public RankList() {
		listeScores = new ArrayList<Score>();

	}

	public ArrayList<Score> getTopTen() {
		ArrayList<Score> liste = new ArrayList<Score>();
		Score s1 = new Score("", 0);

		for (int i = listeScores.size(); i != listeScores.size() - 10; i--) {
			s1 = listeScores.get(i);
			// liste.add(s1);
		}
		return liste;
	}

	/**
	 * Return the ArrayList<Score>
	 * 
	 * @return ArrayList<Score>
	 */
	public ArrayList<Score> getListeScores() {
		for (int i = 1; i < listeScores.size(); i++) {
			for (int j = i; j > 0; j--) {

				if (listeScores.get(j).getResult() > listeScores.get(j - 1)
						.getResult()) {
					Score temp = listeScores.get(j - 1);
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
	public void addScore(Score sc)

	{
		listeScores.add(sc);
	}

	public void addScore(String name, int result) {
		Score s1 = new Score(name, result);
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
