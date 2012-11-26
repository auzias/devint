package util;

import java.util.ArrayList;
import org.newdawn.slick.*;

public class PrintList {
	private String title;
	private ArrayList<String> options = new ArrayList<String>();
	private ArrayList<Integer> yOptions = new ArrayList<Integer>();

	public PrintList(String title, ArrayList<String> options) {
		this.title = title;
		this.options = options;
		for (int i = 0; i < options.size(); i++) {
			int n = (getOptions().get(i).length() < Fc.maxChar) ? getOptions()
					.get(i).length() : Fc.maxChar;
			yOptions.add(
					i,
					(Fc.width - Fc.fontEffect.getWidth(options.get(i).substring(0,
							n))) / 2);
		}
	}

	public void print(Graphics g, int sel) {
		// Création du fond blanc :
		g.setColor(Fc.blanc);
		g.fillRect(0, 0, Fc.width, Fc.height);
		// Cadre noir du titre :
		g.setColor(Fc.kit.getBackGroundTitle());
		g.fillRoundRect(10, 23, 1000, 90, 10);
		// Titre du menu :
		Fc.fontEffect.drawString(500 - (Fc.fontEffect.getWidth(title)) / 2, 12,
				title, Fc.kit.getTitleColor());
		// background :
		g.setColor(Fc.kit.getBackGroundText());
		for (int i = 0, z = 130; i < yOptions.size(); i++, z += 92)
			g.fillRoundRect(10, 10 + z + 3 * i, 1000, 92, 10);
		// Textes des options :

		sel = (sel >= options.size()) ? 0 : sel;

		int first = (sel <= 5) ? 0 : sel - 5;
		int last = ((first + 6) > options.size()) ? options.size()
				: (first + 6);

		for (int i = first, j = 0; i < last; i++, j++) {
			int n = (options.get(i).length() < Fc.maxChar) ? options.get(i).length() : Fc.maxChar;
			Fc.fontEffect.drawString(yOptions.get(i), Fc.xOptions.get(j), options.get(i).substring(0, n), Fc.kit.getTextColor());
		}
		if(options.size() > 6)
			Fc.fontEffect.drawString(Fc.height/2+Fc.fontEffect.getWidth("(''')")/2, Fc.xOptions.get(Fc.xOptions.size()-1), "(''')", Fc.kit.getTextColor());
	}

	public ArrayList<String> getOptions() {
		return options;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}

	public void setOptions(ArrayList<ScoreToSave> options) {
		this.options.clear();
		for(Object o : options)
			this.options.add(o.toString());
	}

	public ArrayList<Integer> getyOptions() {
		return yOptions;
	}
}
