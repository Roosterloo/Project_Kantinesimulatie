public class Datum {

	private int dag;
	private int maand;
	private int jaar;

	public Datum(int dag, int maand, int jaar) {
		this();
		if (bestaatDatum(dag, maand, jaar)) {
			this.dag = dag;
			this.maand = maand;
			this.jaar = jaar;
		}
	}

	public Datum() {
		this.dag = 0;
		this.maand = 0;
		this.jaar = 0;
	}

	public boolean bestaatDatum(int dag, int maand, int jaar) {
		boolean isSchrikkelJaar = false;
		if (dag > 0 && dag <= 31 && maand >= 1 && maand <= 12 && jaar >= 1900 && jaar <= 2100) {
			if (maand == 1 || maand == 3 || maand == 5 || maand == 7 || maand == 8 || maand == 10 || maand == 12) {
				return true;
			} else if (maand == 4 || maand == 6 || maand == 9 || maand == 11 && dag < 31) {
				return true;
			}
		} else if (maand == 2) {
			int year = jaar % 4;
			int year1 = jaar % 100;
			int year2 = jaar % 400;

			if (year == 0) {
				isSchrikkelJaar = true;
				if (year1 == 0) {
					isSchrikkelJaar = false;
					if (year2 == 0) {
						isSchrikkelJaar = true;
					}
				}
			}

			if (isSchrikkelJaar && dag < 30 || !isSchrikkelJaar && dag < 29) {
				return true;
			}
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
		return getDag() + ":" + getMaand() + ":" + getJaar();
		}
}
