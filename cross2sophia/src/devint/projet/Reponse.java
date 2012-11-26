package devint.projet;

public class Reponse {
	private String reponse;
	private boolean bonne;
	
	public Reponse(String reponse, boolean bonne) {
		this.reponse = reponse;
		this.bonne = bonne;
	}

	public String getReponse() {
		return reponse;
	}

	public void setReponse(String reponse) {
		this.reponse = reponse;
	}

	public boolean isBonne() {
		return bonne;
	}

	public void setBonne(boolean bonne) {
		this.bonne = bonne;
	}
}
