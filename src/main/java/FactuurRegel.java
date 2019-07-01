import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Factuurregel")
public class FactuurRegel implements Serializable {
    @Id
    @Column(name = "id")
    private Long id;

    @Column
    private Artikel artikel;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "factuur_id", nullable = false)
    private Factuur factuur;

    public FactuurRegel() {
    }


    public FactuurRegel(Factuur factuur, Artikel artikel) {
        this.factuur = factuur;
        this.artikel = artikel;
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
        if (factuur.getId(){

        }
        String factuur = "D" + factuur;

        return factuur;
        return "test ";
    }
}