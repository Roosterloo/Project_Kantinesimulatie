import java.util.*;

public class KantineSimulatie_1 {

    private Kantine kantine;

    public static final int DAGEN = 7;

    /**
     * Constructor
     */
    public KantineSimulatie_1() {
        kantine = new Kantine();
    }

    /**
     * Deze methode simuleert een aantal dagen in het
     * verloop van de kantine
     *
     * @param dagen
     */
    public void simuleer(int dagen) {
        // herhaal voor elke dag

        for (int i = 0; i < dagen; i++) {
            //maakt random object aan
            Random random = new Random();
            //hoeveelheid klanten
            int personen = 100;

            int aantal_studenten = 0;
            int aantal_docenten = 0;
            int aantal_medewerkers = 0;

            // for lus voor personen
            for (int p = 0; p < personen; p++) {
                //maakt een getal tussen de 0 en 100 aan
                int randomint = random.nextInt(100);
                if (randomint < 89) {
                    Student student = new Student(0, "", "", null, 'M', 0, "");
                    System.out.println(student);
                    aantal_studenten ++;

                } else if (randomint > 89 && randomint < 99) {
                    Docent docent = new Docent(0, "", "", null, 'M', "", "");
                    System.out.println(docent);
                    aantal_docenten ++;

                } else {
                    KantineMedewerker kantinemedewerker = new KantineMedewerker(0, "", "", null, 'M', 0, false);
                    System.out.println(kantinemedewerker);
                    aantal_medewerkers ++;
                }

                //kantine.loopPakSluitAan();}
            }

            //print hoeveelheid van elk type klant
            System.out.println(aantal_studenten);
            System.out.println(aantal_docenten);
            System.out.println(aantal_medewerkers);

            // voert de kassa methodes uit
            kantine.verwerkRijVoorKassa();
            //toont de data in de kassa en reset deze
            kantine.informatie_kassa();

        }
    }




    @Override
    public String toString() {
        return "Deze klant was een:" + getClass().getName();
    }

    /**
     * Start een simulatie
     */
    public static void main(String[] args) {
        KantineSimulatie_1 kantinesim = new KantineSimulatie_1();
        int dagen = 0;

        if (args.length == 0) {
            dagen = DAGEN;
        } else {
            dagen = Integer.parseInt(args[0]);
        }
        kantinesim.simuleer(dagen);
    }
}