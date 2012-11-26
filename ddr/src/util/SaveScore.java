package util;

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
		root = new Element(Fc.root);
		document = new Document(root);
	}

	/**
	 * Make/Display and Save a new XML Tree
	 * 
	 * @param equipes
	 *            ArrayList<Equipe>
	 */
	static public void creerDocument(ArrayList<ScoreToSave> scores) {

		for (ScoreToSave sc : scores) {
			Element score = new Element(Fc.score); // <Score> </Score>
			root.addContent(score);

			Element nom = new Element(Fc.nom); // <Nom> sc.getNom() </Nom>
			nom.setText(sc.getName());
			score.addContent(nom);
			
			Element chanson = new Element(Fc.chanson); // <chanson> sc.getMusique() </chanson>
			chanson.setText(sc.getMusique());
			score.addContent(chanson);

			String str2 = "" + sc.getResult();
			Element result = new Element(Fc.resultat); // <Resultat>
			// eq.getValue()
			// </Resultat>
			result.setText(str2);
			score.addContent(result);

		}
		enregistrer("ressources/data/" + Fc.gamers.getLevel().toString() + Fc.gamers.getMod().toString() + ".xml");
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