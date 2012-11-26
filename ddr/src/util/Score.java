package util;

public class Score {
	private int positionMoyenne = 0;
	private int nombreFleches = 0;
	private int somme = 0;
	private long points = 0;
	private int combo = 1;
	private int maxCombo = 0;
	private int enRetard = 0;
	private int enAvance = 0;

	public Score() {
		positionMoyenne = 0;
		nombreFleches = 0;
		somme = 0;
		points = 0;
		combo = 1;
		maxCombo = 0;
		enRetard = 0;
		enAvance = 0;
	}

	public void moyenneUpdate(double position) {
		this.nombreFleches++;
		if((30-position) > 0)
			this.enRetard++;
		else
			this.enAvance++;
		
		if(Math.abs(30-position) < 20)
			this.setCombo(this.getCombo() + 1);
		else
			this.setCombo(this.getCombo() - 1);
		
		this.somme += Math.abs(30-position);
		this.positionMoyenne = this.somme / this.nombreFleches;
	}

	public int getPositionMoyenne() {
		return positionMoyenne;
	}
	
	public long getPoints() {
		return points;
	}
	
	public void setPoints(long points) {
		this.points = points;
	}
	
	public int getEnRetard() {
		return enRetard;
	}

	public int getEnAvance() {
		return enAvance;
	}

	public int getCombo() {
		return combo;
	}
	
	public void setCombo(int combo) {
		if(combo >= Fc.comboUltime )
			combo = Fc.comboUltime;
		if(combo <= 1)
			combo = 1;
		this.combo = combo;
		if(this.combo > this.maxCombo)
			this.maxCombo = this.combo;
	}

	public void comboDown() {
		combo = (combo > 1) ? combo-- : 1;
	}

	public void moyenneWorst() {
		this.enRetard++;
		this.nombreFleches++;
		this.somme += 20;
		this.positionMoyenne = this.somme / this.nombreFleches;
	}
}