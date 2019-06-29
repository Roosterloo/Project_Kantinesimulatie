import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.time.LocalDate;

public class Kassa {
    private KassaRij kassarij;
    private double korting;
    private double totaal;
    private double kassatotaal;
    private int gepasseerdeartikelen;
    private EntityManager manager;
    private EntityTransaction transaction;
    private LocalDate datum;

    /**
     * Constructor
     */
    public Kassa(KassaRij kassarij, EntityManager entityManager) {
        this.totaal = 0;
        this.korting = 0;
        this.kassarij = kassarij;
        this.manager = entityManager;
        this.transaction = null;
        this.datum = LocalDate.of(2019, 6, 26);
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
        Factuur fac = new Factuur(klant, datum);
        Persoon k = klant.getKlant();
        Betaalwijze b = k.getBetaalwijze();
        try {
            // Get a transaction, sla het factuur op en commit de transactie
            transaction = manager.getTransaction();
            transaction.begin();
            b.betaal(totaal);
            this.kassatotaal += totaal;
            manager.persist(fac);
            transaction.commit();
            System.out.println(fac.toString());
        } catch (TeWeinigGeldException e) {
            //Als er te weninig geld is, rollback
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println("De Betaling is mislukt " + k.getVoornaam() + " heeft niet genoeg geld!");
            e.printStackTrace();
        }
        this.korting = fac.getKorting();
        this.kassatotaal = kassatotaal + fac.getTotaal();
        this.gepasseerdeartikelen = fac.geefGepasseerdeArtikelen();
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