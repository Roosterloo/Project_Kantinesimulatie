public class Kantine {

    private Kassa kassa;
    private KassaRij kassarij;
    private KantineAanbod kantineAanbod;

    /**
     * Constructor
     */
    public Kantine() {
        this.kassarij = new KassaRij();
        this.kassa = new Kassa(kassarij);
    }

    /**
     * In deze methode wordt een Persoon en Dienblad gemaakt
     * en aan elkaar gekoppeld. Maak twee Artikelen aan
     * en plaats deze op het dienblad. Tenslotte sluit de
     * Persoon zich aan bij de rij voor de kassa.
     */
    public void loopPakSluitAan(Dienblad dienblad) {
        this.kassarij.sluitAchteraan(dienblad);
    }

    /**
     * Deze methode handelt de rij voor de kassa af.
     */
    public void verwerkRijVoorKassa() {
        while (this.kassarij.erIsEenRij()) {
            this.kassarij.eerstePersoonInRij();
        }
    }

    public void informatie_kassa(){
        this.kassa.hoeveelheidGeldInKassa();
        this.kassa.aantalArtikelen();
        this.kassa.resetKassa();
    }

    public void setKantineAanbod(KantineAanbod kantineAanbod){
        this.kantineAanbod = kantineAanbod;
    }

    public double hoeveelheidGeldInKassa(){
        return kassa.hoeveelheidGeldInKassa();
    }

    public void resetKassa(){
        kassa.resetKassa();
    }
}