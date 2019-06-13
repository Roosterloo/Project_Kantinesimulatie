public class Docent extends Persoon{

    private String afkorting;
    private String afdeling;

    public Docent(int BSN, String voornaam, String achternaam, Datum geboortedatum, char geslacht, String afkorting, String afdeling){
        super(BSN, voornaam, achternaam, geboortedatum, geslacht);
        this.afkorting = afkorting;
        this.afdeling = afdeling;
    }

    public void setAfkorting(String afkorting) {
        this.afkorting = afkorting;
    }

    public void setAfdeling(String afdeling) {
        this.afdeling = afdeling;
    }

    public String getAfkorting() {
        return afkorting;
    }

    public String getAfdeling() {
        return afdeling;
    }
}