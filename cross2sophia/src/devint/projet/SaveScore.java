package devint.projet;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.jdom.*;
import org.jdom.input.*;
import org.jdom.output.*;

/**
 * 
 * is used to create/display/save a new XML Tournament Tree
 * 
 * @version 1.0
 * @author Montiel&Abdelkader
 * 
 * 
 */

public class SaveScore {

	static Element root;
	static org.jdom.Document document;

	/**
	 * Constructor: Default Constructor: Create a new JDom() with default fields
	 */
	public SaveScore() {
		root = new Element("Classement");
		document = new Document(root);
	}

	/**
	 * Make/Display and Save a new XML Tree
	 * 
	 * @param equipes
	 *            ArrayList<Equipe>
	 */
	static public void creerDocument(ArrayList<Score> scores) {

		for (Score sc : scores) {
			Element score = new Element("Score"); // <Score> </Score>
			root.addContent(score);

			Element nom = new Element("Nom"); // <Nom> sc.getNom() </Nom>
			nom.setText(sc.getName());
			score.addContent(nom);

			String str2 = "" + sc.getResult();
			Element result = new Element("Resultat"); // <Resultat>
			// eq.getValue()
			// </Resultat>
			result.setText(str2);
			score.addContent(result);

		}
		// afficher();

		enregistrer("../ressources/data/RankList.xml");
	}

	/**
	 * Display the XML Tree
	 */
	static void afficher() {
		// Affichage
		try {
			XMLOutputter sortie = new XMLOutputter(Format.getPrettyFormat());
			sortie.output(document, System.out);
		} catch (java.io.IOException e) {
		}
	}

	/**
	 * Save the XML Tree
	 * 
	 * @param filename
	 *            String
	 */
	static void enregistrer(String filename) {
		if (filename.toLowerCase().endsWith(".xml") == true) {
			try {
				XMLOutputter sortie = new XMLOutputter(Format.getPrettyFormat());
				sortie.output(document, new FileOutputStream(filename));
			}
			catch (java.io.IOException e) {
			}
		}
		else {
			System.err.println("Le fichier de sortie doit etre au format XML");
		}
	}
}