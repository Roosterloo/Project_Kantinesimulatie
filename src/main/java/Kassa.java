import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.Iterator;
import java.time.LocalDate;

public class Kassa {
    private KassaRij kassarij;
    private double korting;
    private double totaal;
    private double kassatotaal;
    private int gepasseerdeartikelen;
    private EntityManager manager;
    private LocalDate datum;

    /**
     * Constructor
     */
    public Kassa(KassaRij kassarij, EntityManager entityManager) {
        totaal = 0;
        korting = 0;
        this.kassarij = kassarij;
        manager = entityManager;
        datum = LocalDate.of(2019, 5, 16);
    }

    /**
     * Vraag het aantal artikelen en de totaalprijs op.
     * Tel deze gegevens op bij de controletotalen die voor
     * de kassa worden bijgehouden. De implementatie wordt
     * later vervangen door een echte betaling door de persoon.
     *
     * @param //klant die moet afrekenen
     */
    public void rekenAf(Dienblad klant) {
        Factuur factuur = new Factuur(klant, datum);
        Persoon k = klant.getKlant();
        Betaalwijze b = k.getBetaalwijze();
        try {
            b.betaal(totaal);
            this.kassatotaal = kassatotaal + totaal;
            System.out.println(factuur.toString());
            factuur.maakIDHoger();
            manager.persist(factuur);
        }catch(TeWeinigGeldException e){
            System.out.println("De Betaling is mislukt " + k.getVoornaam() + " heeft niet genoeg geld!");
            e.printStackTrace();
        }
        this.korting = factuur.getKorting();
        this.kassatotaal = kassatotaal + factuur.getTotaal();
        this.gepasseerdeartikelen = factuur.geefGepasseerdeArtikelen();
    }


    /**
     * Geeft het aantal artikelen dat de kassa heeft gepasseerd,
     * vanaf het moment dat de methode resetWaarden is aangeroepen.
     *
     * @return aantal artikelen
     */
    public int aantalArtikelen() {
        return gepasseerdeartikelen;
    }

    /**
     * Geeft het totaalbedrag van alle artikelen die de kass
     * zijn gepasseerd, vanaf het moment dat de methode
     * resetKassa is aangeroepen.
     *
     * @return hoeveelheid geld in de kassa
     */
    public double hoeveelheidGeldInKassa() {
        return kassatotaal;
    }

    /**
     * reset de waarden van het aantal gepasseerde artikelen en
     * de totale hoeveelheid geld in de kassa.
     */
    public void resetKassa() {
        gepasseerdeartikelen = 0;
        kassatotaal = 0;
    }
}