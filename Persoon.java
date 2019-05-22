public class Persoon{

    private int BSN;
    private String voornaam;
    private String achternaam;
    private Datum geboortedatum;;
    private char geslacht;

    public Persoon(int BSN, String voornaam, String achternaam, Datum geboortedatum, char geslacht) {
        this.BSN = BSN;
        this.voornaam = voornaam;
        this.achternaam = achternaam;
        this.geboortedatum = geboortedatum;
        setGeslacht(geslacht);
    }

    public Persoon() {
        this.BSN = 0;
        this.voornaam = "";
        this.achternaam = "";
        this.geboortedatum = null;
        this.geslacht = 'a';

    }

    public void setBSN(int BSN) {
        this.BSN = BSN;
    }

    public void setVoornaam(String voornaam) {
        this.voornaam = voornaam;
    }

    public void setAchternaam(String achternaam) {
        this.achternaam = achternaam;
    }

    public void setGeboortedatum(Datum geboortedatum) {
        this.geboortedatum = geboortedatum;
    }

    public void setGeslacht(char geslacht) {
        geslacht = Character.toUpperCase(geslacht);
        switch (geslacht){
            case 'M': this.geslacht = geslacht; break;
            case 'V': this.geslacht = geslacht; break;
            default: System.out.println("De waarde is niet goed gezet!"); break;
        }
    }

    public int getBSN() {
        return BSN;
    }

    public String getVoornaam() {
        return voornaam;
    }

    public String getAchternaam() {
        return achternaam;
    }

    public String getGeboortedatum() {
        return geboortedatum.getDatumAsString();
    }

    public String getGeslacht() {
        if(geslacht == 'M'){
            return "Man";
        } else if (geslacht == 'V'){
            return "Vrouw";
        } else{
            return "Onbekend";
        }
    }
}