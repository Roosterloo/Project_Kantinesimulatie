import javax.persistence.Column;
import java.time.LocalDate;
import java.io.Serializable;
import java.util.Iterator;

public class Factuur implements Serializable {

    @Column(name = "ID")
    private Long id;

    @Column(name = "Datum")
    private LocalDate datum;

    @Column(name = "Totaalbedrag")
    private double totaal;

    @Column(name = "Gepasseerde Artikelen")
    private int gepasseerdeartikelen;

    @Column(name = "De totale korting")
    private double korting;

    @Column(name = "Naam van de klant")
    private String klantnaam;

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
     * <p>
     * Zet het totaal te betalen bedrag en het
     * totaal aan ontvangen kortingen.
     *
     * @param //klant
     */
    private void verwerkBestelling(Dienblad klant) {
        Iterator<Artikel> artikelen = klant.getArtikelIterator();
        double modifier = 1;
        totaal = 0;
        while (artikelen.hasNext()) {
            Artikel artikel = artikelen.next();
            gepasseerdeartikelen++;
            totaal = totaal + artikel.get_prijs();
        }
        Persoon k = klant.getKlant();
        this.klantnaam = k.getVoornaam() + " " + k.getAchternaam();
        if (k instanceof KortingskaartHouder) {
            korting = ((KortingskaartHouder) k).geefKortingsPercentage();
            setKorting(korting);
            modifier = modifier - korting;
            if (((KortingskaartHouder) k).heeftMaximum()) {
                double max = ((KortingskaartHouder) k).geefMaximum();
                if ((totaal * korting) > max) {
                    totaal = totaal - max;
                    totaal = Math.round(totaal);
                } else {
                    totaal = totaal * modifier;
                    totaal = Math.round(totaal);
                }
            } else {
                totaal = totaal * modifier;
                totaal = Math.round(totaal);
            }
        }
    }

    /**
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

    public void setKorting(double korting){
        this.korting = korting;
    }

    /**
     * @return een printbaar bonnetje
     */
    public String toString() {
        String bon = "Bon, ID: " + id + "\n";
        String naamvanklant = "Naam van de klant: " + klantnaam + "\n";
        String prijs = "Prijs: " + totaal + "\n";
        String kortingstring = "Korting: " + korting + "%" + "\n";
        String date = "Datum: " + datum + "\n";
        return bon + naamvanklant + prijs + kortingstring + date;
    }

    public int geefGepasseerdeArtikelen(){
        return gepasseerdeartikelen;
    }
}