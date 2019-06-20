import java.util.ArrayList;
import java.util.Iterator;

public class Kassa {
    private KassaRij kassarij;
    private double kassatotaal;
    private int gepasseerdeartikelen;

    /**
     * Constructor
     */
    public Kassa(KassaRij kassarij) {
        this.kassarij = kassarij;
    }

    /**
     * Vraag het aantal artikelen en de totaalprijs op.
     * Tel deze gegevens op bij de controletotalen die voor
     * de kassa worden bijgehouden. De implementatie wordt
     * later vervangen door een echte betaling door de persoon.
     *
     * @param klant die moet afrekenen
     */
    public void rekenAf(Dienblad klant) {
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