package devint.projet;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.jdom.*;
import org.jdom.input.*;
import org.jdom.output.*;
import org.jdom.filter.*;

/**
 * 
 * is used to Restore and modify a XML file
 * 
 * @version 1.0
 * @author Montiel
 * 
 * 
 */

public class Restaurer {
	static org.jdom.Document document;
	static Element racine;
	SAXBuilder sxb;

	/**
	 * Constructor: Default Constructor: Create a new Restaurer() with default
	 * fields
	 */
	public Restaurer() {
		this("ressources/data/RankList.xml");
	}

	/**
	 * Constructor: Initialise all fields to create a new Restaurer()
	 * 
	 * @param documentXML
	 *            String
	 */
	public Restaurer(String documentXML) {
		sxb = new SAXBuilder();
		try {
			document = sxb.build(new File(documentXML));
		} catch (Exception e) {
			e.printStackTrace();
		}
		racine = document.getRootElement();
	}

	public RankList restaurer() {
		List listeScores = racine.getChildren("Score");
		ArrayList<Score> scoresRes = new ArrayList<Score>();
		Iterator j = listeScores.iterator();
		while (j.hasNext()) {
			Element score = (Element) j.next();
			String name = score.getChild("Nom").getText();
			int result = Integer.parseInt(score.getChild("Resultat").getText());
			Score s1 = new Score(name, result);

			scoresRes.add(s1);
		}
		RankList rankListRes = new RankList(scoresRes);

		return rankListRes;
	}
}