public class Kantine {

    private Kassa kassa;
    private KassaRij kassarij;

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
    public void loopPakSluitAan() {
        Persoon persoon = new Persoon();
        Artikel artikel = new Artikel();
        Artikel artikel1 = new Artikel();
        Dienblad dienblad = new Dienblad();

        dienblad.voegToe(artikel);
        dienblad.voegToe(artikel1);
        dienblad.setKlant(persoon);
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

}