public class Persoon{

    private int BSN;
    private String voornaam;
    private String achternaam;
    private int geboortedatum;
    private char geslacht;

    public Persoon(int BSN, String voornaam, String achternaam, int geboortedatum, char geslacht) {
        this.BSN = BSN;
        this.voornaam = voornaam;
        this.achternaam = achternaam;
        this.geboortedatum = geboortedatum;
        this.geslacht = geslacht;
    }
}