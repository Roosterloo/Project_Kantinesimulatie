import javax.persistence.EntityManager;

public class Kantine {

    private Kassa kassa;
    private KassaRij kassarij;
    private KantineAanbod kantineAanbod;
    private EntityManager manager;

    /**
     * Constructor
     */
    public Kantine(EntityManager entityManager) {
        manager = entityManager;
        this.kassarij = new KassaRij();
        this.kassa = new Kassa(kassarij, manager);
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

    public void informatie_kassa(){
        this.kassa.hoeveelheidGeldInKassa();
        this.kassa.aantalArtikelen();
        this.kassa.resetKassa();
    }

    public double hoeveelheidGeldInKassa(){
        return kassa.hoeveelheidGeldInKassa();
    }

    public void resetKassa(){
        kassa.resetKassa();
    }

    public void setKantineAanbod(KantineAanbod kantineaanbod){
        this.kantineAanbod = kantineaanbod;
    }

    public KantineAanbod getKantineAanbod() {
        return kantineAanbod;
    }
}