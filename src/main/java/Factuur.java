import java.time.LocalDate;
import java.io.Serializable;
import java.util.Iterator;

public class Factuur implements Serializable {

    private Long id;

    private LocalDate datum;

    private double korting;

    private double totaal;

    private int gepasseerdeartikelen;
    private double kassatotaal;

    public Factuur() {
        totaal = 0;
        korting = 0;
    }

    public Factuur(Dienblad klant, LocalDate datum) {
        this();
        this.datum = datum;

        verwerkBestelling(klant);
    }

    /**
     * Verwerk artikelen en pas kortingen toe.
     *
     * Zet het totaal te betalen bedrag en het
     * totaal aan ontvangen kortingen.
     *
     * @param //klant
     *
     */
    private void verwerkBestelling(Dienblad klant) {
        Iterator<Artikel> artikelen = klant.getArtikelIterator();
        double modifier = 1;
        double totaal = 0;
        while (artikelen.hasNext()) {
            Artikel artikel = artikelen.next();
            gepasseerdeartikelen++;
            totaal = totaal + artikel.get_prijs();
        }
        Persoon k = klant.getKlant();
        Betaalwijze b = k.getBetaalwijze();
        if (k instanceof KortingskaartHouder) {
            double korting = ((KortingskaartHouder) k).geefKortingsPercentage();
            modifier = modifier - korting;
            if (((KortingskaartHouder) k).heeftMaximum()) {
                double max = ((KortingskaartHouder) k).geefMaximum();
                if ((totaal * korting) > max) {
                    totaal = totaal - max;
                } else {
                    totaal = totaal * modifier;
                }
            } else {
                totaal = totaal * modifier;
            }
        }
        try {
            b.betaal(totaal);
            this.kassatotaal = kassatotaal + totaal;
        }catch(TeWeinigGeldException e){
            System.out.println("De Betaling is mislukt " + k.getVoornaam() + " heeft niet genoeg geld!");
            e.printStackTrace();
        }
    }

    /*
     * @return het totaalbedrag
     */
    public double getTotaal() {
        return totaal;
    }

    /**
     * @return de toegepaste korting
     */
    public double getKorting() {
        return korting;
    }

    /**
     * @return een printbaar bonnetje
     */
    public String toString() {
        return "Totaal: " + totaal;
    }
}