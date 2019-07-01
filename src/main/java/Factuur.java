import javax.persistence.*;
import java.time.LocalDate;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Entity
@Table(name = "Factuur")
@NamedQuery(name = "Factuur.findAll", query = "SELECT DISTINCT(f) FROM Factuur f")
public class Factuur implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "factuur_id", unique = true, nullable = false)
    private Long id;

    @Column(name = "Naam_van_de_klant")
    private String klantnaam;

    @Column(name = "Totaalbedrag")
    private double totaal;

    @Column(name = "Totale_korting")
    private double korting;

    @Column(name = "Hoeveelheid_Gescande_Artikelen")
    private int gepasseerdeartikelen;

    @Column(name = "Datum")
    private LocalDate datum;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "factuur", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FactuurRegel> regels = new ArrayList<>();

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
        while (artikelen.hasNext()) {
            Artikel artikel = artikelen.next();
            gepasseerdeartikelen++;
            totaal = totaal + artikel.get_prijs();
            FactuurRegel factuurRegel = new FactuurRegel(this, artikel);
            this.regels.add(factuurRegel);
        }
        Persoon k = klant.getKlant();
        this.klantnaam = k.getVoornaam() + " " + k.getAchternaam();
        if (k instanceof KortingskaartHouder) {
            double kortingskaartkorting = ((KortingskaartHouder) k).geefKortingsPercentage();
            double modifier = 1;
            modifier = modifier - kortingskaartkorting;
            if (((KortingskaartHouder) k).heeftMaximum()) {
                double max = ((KortingskaartHouder) k).geefMaximum();
                if (totaal * kortingskaartkorting > max) {
                    setKorting(max);
                    totaal = totaal - max;
                } else {
                    setKorting(totaal * kortingskaartkorting);
                    totaal = totaal * modifier;
                }
            } else {
                setKorting(totaal * kortingskaartkorting);
                totaal = totaal * modifier;
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
        //Getallen worden afgerond naar 2 decimalen achter de comma voor het bonnetje via de function Math
        //dit lukt echter niet altijd, bijvoorbeeld 10.00 is nu 10.0
        String prijs = "Prijs: €" + (Math.round(totaal * 100.0) / 100.0) + "\n";
        String kortingstring = "Korting: €" + (Math.round(korting * 100.0) / 100.0) + "\n";
        String hoeveelgescand = "Hoeveelheid gescande artikelen: " + gepasseerdeartikelen + "\n";
        String date = "Datum: " + datum + "\n";
        StringBuilder sb = new
                StringBuilder();
        int i = 1;
        for(FactuurRegel fr : regels){
            sb.append("Artikel nummer ").append(i).append(": ").append(fr.toString()).append("\n");
            i++;
        }

        return bon + naamvanklant + sb + prijs + kortingstring + hoeveelgescand + date;
    }

    public int geefGepasseerdeArtikelen(){
        return gepasseerdeartikelen;
    }
}