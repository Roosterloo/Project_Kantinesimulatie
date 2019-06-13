public class KantineMedewerker extends Persoon{

    private int medewerkersnummer;
    private Boolean kassamedewerker;

    public KantineMedewerker(int BSN, String voornaam, String achternaam, Datum geboortedatum, char geslacht, int medewerkersnummer, Boolean kassamedewerker){
        super(BSN, voornaam, achternaam, geboortedatum, geslacht);
        this.medewerkersnummer = medewerkersnummer;
        this.kassamedewerker = kassamedewerker;
    }

    public void setMedewerkersnummer(int medewerkersnummer) {
        this.medewerkersnummer = medewerkersnummer;
    }

    public void setKassamedewerker(Boolean kassamedewerker) {
        this.kassamedewerker = kassamedewerker;
    }

    public int getMedewerkersnummer() {
        return medewerkersnummer;
    }

    public Boolean getKassamedewerker() {
        return kassamedewerker;
    }
}