import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Factuurregel")
public class FactuurRegel implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column
    private String artikel = "";

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "factuur_id", nullable = false)
    private Factuur factuur;

    public FactuurRegel() {
    }

    public FactuurRegel(Factuur factuur, String artikel) {
        this.factuur = factuur;
        this.artikel = artikel;
    }

    public void setFactuur(Factuur factuur) {
        this.factuur = factuur;
    }
    public void setArtikel(String artikel){
        this.artikel += artikel + " ";
    }

    public Factuur getFactuur(){
        return factuur;
    }

    public String getArtikel() {
        return artikel;
    }

    /**
     * @return een printbare factuurregel
     */
    public String toString() {
        String bericht = "Artikelen: " + artikel;

        return bericht;
    }
}