package devint.projet;

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Question {

	private FileReader monFichier = null;
	private BufferedReader tampon = null;
	private Random random = new Random();
	private String[] tabChaine = null;
	private ArrayList<String> listQuestions;
	private ArrayList<String> listTemp;
	private int nbQuestions;
	private int question = 0;
	private String goodReponse;
	private String reponse;

	public Question() {

		listQuestions = new ArrayList<String>();

		try {
			monFichier = new FileReader("ressources/data/questions.csv");
			tampon = new BufferedReader(monFichier);

			while (true) {
				// Lit une ligne de test.csv
				String ligne = tampon.readLine();
				// Vérifie la fin de fichier
				if (ligne == null)
					break;

				tabChaine = ligne.split(";");
				for (int i = 0; i < tabChaine.length; i++) {
					listQuestions.add(tabChaine[i]);
				}

				nbQuestions = listQuestions.size() / 5;
			} // Fin du while
		} catch (IOException exception) {
			exception.printStackTrace();
		} finally {
			try {
				tampon.close();
				monFichier.close();

			} catch (IOException exception1) {
				exception1.printStackTrace();
			}

		}

		nbQuestions = listQuestions.size() / 5;
		question = random.nextInt(nbQuestions);

		String goodReponse = listQuestions.get(question * 5 + 1);

	}

	public void resetRandom() {
		question = random.nextInt(nbQuestions);
	}

	public String getGoodReponse() {
		return goodReponse;
	}

	public void setGoodReponse(String goodReponse) {
		this.goodReponse = goodReponse;
	}

	public ArrayList<String> melanger() {

		ArrayList<String> listTemp = new ArrayList<String>();

		addReponses(listTemp);

		if (listTemp.size() > 0) {
			ArrayList<String> copy = new ArrayList<String>();
			for (String str : listTemp)
				copy.add(str);
			Random generator = new Random();
			ArrayList<String> result = new ArrayList<String>();
			do {
				int index = (int) (generator.nextDouble() * (double) copy
						.size());
				result.add(copy.remove(index));
			} while (copy.size() > 0);
			return result;
		} else
			return new ArrayList<String>();
	}

	public void addReponses(ArrayList<String> liste) {

		for (int i = 1; i < 5; i++) {
			liste.add(listQuestions.get(question * 5 + i));
		}

	}

	public void setReponse(String reponse) {
		this.reponse = reponse;
	}

	public boolean compareToGoodReponse(String rep) {
		if (rep.equals(goodReponse)) {
			return true;
		}
		return false;
	}

	public String printQ() {
		return listQuestions.get(question * 5);

	}

}
