import javax.persistence.EntityManager;

public class Kantine {

    private Kassa kassa;
    private KassaRij kassarij;
    private KantineAanbod kantineAanbod;

    /**
     * Constructor
     */
    public Kantine(EntityManager entityManager) {
        this.kassarij = new KassaRij();
        this.kassa = new Kassa(kassarij, entityManager);
    }

    /**
     * In deze methode wordt een Persoon en Dienblad gemaakt
     * en aan elkaar gekoppeld. Maak twee Artikelen aan
     * en plaats deze op het dienblad. Tenslotte sluit de
     * Persoon zich aan bij de rij voor de kassa.
     */
    public void loopPakSluitAan(Dienblad dienblad, String[] artikelnamen) {
        for(String art : artikelnamen){
            dienblad.voegToe(kantineAanbod.getArtikel(art));
        }
        this.kassarij.sluitAchteraan(dienblad);
    }

    /**
     * Deze methode handelt de rij voor de kassa af.
     */
    public void verwerkRijVoorKassa() {
        while (this.kassarij.erIsEenRij()) {
            kassa.rekenAf(this.kassarij.eerstePersoonInRij());
        }
    }

    /**
     * Deze methode regelt de informatie van de kassa en hierna reset het de kassa
     */
    public void informatie_kassa(){
        this.kassa.hoeveelheidGeldInKassa();
        this.kassa.aantalArtikelen();
        this.kassa.resetKassa();
    }

    /**
     * Deze methode geeft de hoeveelheid geld in de kassa terug
     * @return De hoeveelheid geld in de kassa
     */
    public double hoeveelheidGeldInKassa(){
        return kassa.hoeveelheidGeldInKassa();
    }

    /**
     * Deze methode geeft de hoeveelheid artikelen die de kassa heeft gescand terug
     * @return aantalArtikelen de hoeveelheid artikelen die gescand zijn
     */
    public int hoeveelheidArtikelen(){ return kassa.aantalArtikelen();}

    /**
     * Deze methode reset de kassa
     */
    public void resetKassa(){
        kassa.resetKassa();
    }

    /**
     * Deze methode set de kantineaanbod voor de kantine
     * @param /kantineaanbod de kantineaanbod die klaargezet is
     */
    public void setKantineAanbod(KantineAanbod kantineaanbod){
        this.kantineAanbod = kantineaanbod;
    }

    /**
     * Deze methode geeft het kantineaanbod terug
     * @return Kantineaanbod het kantineaanbod
     */
    public KantineAanbod getKantineAanbod() {
        return kantineAanbod;
    }
}