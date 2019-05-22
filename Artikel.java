public class Artikel{

    private String naam;
    private int prijs;

    public Artikel (String naam, int prijs){
        this.naam = naam;
        this.prijs = prijs;
    }

    public Artikel(){
    }

    public String get_naam(){
        return this.naam;
    }

    public int get_prijs(){
        return this.prijs;
    }

    public void set_naam(String naam){
        this.naam = naam;
    }

    public void set_prijs(int prijs){
        this.prijs = prijs;
    }

    public void tostring(){
        System.out.println("Naam: " + naam);
        System.out.println("Prijs:" + prijs);
    }
}
