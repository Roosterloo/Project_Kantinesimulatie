public class Artikel{

    private String naam;
    private double prijs;
    private double korting;
    private double modifier;

    public Artikel (String naam, double prijs){
        this.naam = naam;
        this.prijs = prijs;
        this.korting = 0;
    }

    public Artikel(String naam, double prijs, double korting){
        this.naam = naam;
        this.prijs = prijs;
        this.korting = korting;
        modifier = 1;
        berekenMetKorting();
    }

    public Artikel(){
    }

    public String get_naam(){
        return this.naam;
    }

    public double get_prijs(){
        return this.prijs;
    }

    public void set_naam(String naam){
        this.naam = naam;
    }

    public void set_prijs(double prijs){
        this.prijs = prijs;
    }

    public void tostring(){
        System.out.println("Naam: " + naam);
        System.out.println("Prijs:" + prijs);
    }

    public double getKorting() {
        return korting;
    }

    public void setKorting(double korting) {
        this.korting = korting;
    }

    public void berekenMetKorting(){
        this.prijs = this.prijs * (modifier - korting);
    }
}
