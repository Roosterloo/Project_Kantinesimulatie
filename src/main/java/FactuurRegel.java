import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Factuurregel")
public class FactuurRegel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;

    @Transient
    private Artikel artikel;

    @Column(name = "Artikel_Naam")
    private String artikelnaam;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "Factuur_ID", nullable = false)
    private Factuur factuur;

    public FactuurRegel() {
    }

    public FactuurRegel(Factuur factuur, Artikel artikel) {
        this.factuur = factuur;
        this.artikel = artikel;
        this.artikelnaam = artikel.get_naam();
    }

    public void setFactuur(Factuur factuur) {
        this.factuur = factuur;
    }
    public void setArtikel(Artikel artikel){
        this.artikel = artikel;
    }

    public Factuur getFactuur(){
        return factuur;
    }

    public Artikel getArtikel() {
        return artikel;
    }

    /**
     * @return een printbare factuurregel
     */
    public String toString() {
        return artikelnaam;
    }
}