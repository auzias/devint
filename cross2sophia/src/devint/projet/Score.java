package devint.projet;

/**
 * is used to create scores
 * 
 * @version 1.0
 * @author Montiel
 * 
 * 
 */

public class Score {
	int result;
	String name;

	/**
	 * Default Constructor: Create a new Score() with default fields
	 */
	public Score() {
		result = 0;
		name = "Nom par défaut";
	}

	/**
	 * Constructor: Initialise all fields to create a new Score()
	 * 
	 * @param name
	 *            String
	 * @param result
	 *            int
	 */
	public Score(String name, int result) {
		this.result = result;
		this.name = name;
	}

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Return the result of Score
	 * 
	 * @return int
	 */

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		int max = 6;
		if (name.length() < 6)
			do{
			name += " ";
			}while(name.length() < 8);
			return "" + name.toUpperCase().substring(0, 1)
					+ name.toLowerCase().substring(1, max) + " " + result;
	}
}
