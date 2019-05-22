public class Datum {

	private int dag;
	private int maand;
	private int jaar;


	public Datum(int dag, int maand, int jaar) {
		this.dag = dag;
		this.maand = maand;
		this.jaar = jaar;
	}

	public Datum() {
		this.dag = 0;
		this.maand = 0;
		this.jaar = 0;
	}

	public boolean bestaatDatum(int dag, int maand, int jaar){
		if (dag >= 1 && maand >= 1 && maand <= 12 && jaar >= 1900 && jaar <= 2100){
			switch (maand){

			}
				return true;

		}
		return false;
	}

	/**
	 * Getter voor Sting weergave van datum
	 * @return Geboortedatum
	 */

	public int getDag() { return this.dag; }

	public int getMaand() {
		return this.maand;
	}

	public int getJaar() {
		return this.jaar;
	}

	public void setDag(int dag) {
		this.dag = dag;
	}

	public void setMaand(int maand) {
		this.maand = maand;
	}

	public void setJaar(int jaar) {
		this.jaar = jaar;
	}

	public String getDatumAsString() {
		int day = getDag();

		return "day";
		}
}
