import java.util.ArrayList;
import java.util.LinkedList;

public class KassaRij {
    private ArrayList<Dienblad> dienblad;
    private Dienblad klant;

    /**
     * Constructor
     */
    public KassaRij() { dienblad = new ArrayList<>();}

    public KassaRij(Dienblad klant) { this.klant = klant;}

    /**
     * Persoon sluit achter in de rij aan
     *
     * @param klant
     */
    public void sluitAchteraan(Dienblad klant) {
        dienblad.add(klant);
    }

    /**
     * Indien er een rij bestaat, de eerste klant uit
     * de rij verwijderen en retourneren.
     * Als er niemand in de rij staat geeft deze null terug.
     *
     * @return Eerste klant in de rij of null
     */
    public Dienblad eerstePersoonInRij() {
        Dienblad volgende_klant = null;
        if (erIsEenRij() == true){
            volgende_klant = dienblad.get(0);
            dienblad.remove(0);
        }
        return volgende_klant;
        }

    /**
     * Methode kijkt of er personen in de rij staan.
     *
     * @return Of er wel of geen rij bestaat
     */
    public boolean erIsEenRij() {
        if (dienblad.size() == 0) {
            return false;
        }
        else{
            return true;
        }
    }

    public void rijlengte_tostring(){
        System.out.println("Er staan op het moment " + dienblad.size() + " mensen in de rij");
    }
}