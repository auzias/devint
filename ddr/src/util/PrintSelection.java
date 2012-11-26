package util;

import org.newdawn.slick.*;

public class PrintSelection {
	private PrintList pl;
	private int nbOptions = 0;
	private int selection = 0;

	public PrintSelection(PrintList pl) {
		this.pl = pl;
		this.nbOptions = pl.getOptions().size();
	}

	public void print(Graphics g, int sel) {
		sel = (sel >= this.nbOptions) ? 0 : sel;
		sel = (sel < 0) ? this.nbOptions-1 : sel;
		this.selection = sel;
		int n = (pl.getOptions().get(this.selection).length() < Fc.maxChar) ? pl.getOptions().get(this.selection).length() : Fc.maxChar;
		//Affichage de la selection
		g.setColor(Fc.kit.getTextColor());

		int print = (this.selection >= 5) ? 5 : this.selection;
		g.fillRoundRect(10, 140 + 95 * print, 1000, 92, 10);
		Fc.fontEffect.drawString(pl.getyOptions().get(this.selection),
				130 + 94 * print, pl.getOptions().get(this.selection).substring(0,n), Fc.kit.getBackGroundText());
	}

	public int getSelection() {
		return selection;
	}
}