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
        boolean bestaatDatumBoolean = false;
        if (dag > 0 && dag <= 31 && maand >= 1 && maand <= 12 && jaar >= 1900 && jaar <= 2100) {
            switch (maand) {
                case 1:
                case 3:
                case 5:
                case 7:
                case 8:
                case 10:
                case 12:
                    bestaatDatumBoolean = true;
                    break;

                case 4:
                case 6:
                case 9:
                case 11:
                    if (dag < 31) {
                        bestaatDatumBoolean = true;
                    }
                    break;

                case 2:
                    boolean isSchrikkelJaar = true;
                    int year = jaar % 4;
                    int year1 = jaar % 100;
                    int year2 = jaar % 400;

                    if (year != 0 || year1 == 0 && year2 != 0){
                        isSchrikkelJaar = false;
                    }

                    if (isSchrikkelJaar && dag < 30 || !isSchrikkelJaar && dag < 29) {
                        bestaatDatumBoolean = true;
                    }
                    break;
            }
        }
        return bestaatDatumBoolean;
    }

    /**
     * Getter voor Sting weergave van datum
     *
     * @return Geboortedatum
     */

    public int getDag() {
        return this.dag;
    }

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
