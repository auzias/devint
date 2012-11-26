package util;

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
	public Restaurer(String level, String mod) {
		this("../ressources/data/" + level.toLowerCase() + mod.toLowerCase() + ".xml");
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
		List listeScores = racine.getChildren(Fc.score);
		ArrayList<ScoreToSave> scoresRes = new ArrayList<ScoreToSave>();
		Iterator j = listeScores.iterator();
		while (j.hasNext()) {
			Element score = (Element) j.next();
			String name = score.getChild(Fc.nom).getText();
			String song = score.getChild(Fc.chanson).getText();
			long result = Long.parseLong(score.getChild(Fc.resultat).getText());
			ScoreToSave s1 = new ScoreToSave(name, result);
			scoresRes.add(s1);
		}
		RankList rankListRes = new RankList(scoresRes);
		return rankListRes;
	}
}