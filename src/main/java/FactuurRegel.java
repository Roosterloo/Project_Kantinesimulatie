import javax.persistence.*;
import java.io.Serializable;

public class FactuurRegel implements Serializable {
    @Id
    @Column(name = "id'")
    private Long id;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "factuurId")
    private Factuur factuur;

    @Column(name = "artikel")
    private Artikel artikel;

    public FactuurRegel() {
    }




    public FactuurRegel(Factuur factuur, Artikel artikel) {
        this.factuur = factuur;
        this.artikel = artikel;
    }

    /**
     * @return een printbare factuurregel
     */
    public String toString() {
        //String factuur = "D" + factuur;
        //for(FactuurRegel regel: Factuur.regels());
        //String artikel = "" + artikel;
        //return factuur;
        return "";
    }
}