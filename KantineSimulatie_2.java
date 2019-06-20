import java.util.*;

public class KantineSimulatie_2 {

    // kantine
    private Kantine kantine;

    // kantineaanbod
    private KantineAanbod kantineaanbod;

    // random generator
    private Random random;

    // aantal artikelen
    private static final int AANTAL_ARTIKELEN = 4;

    // artikelen
    private static final String[] artikelnamen = new String[]
        {"Koffie", "Broodje pindakaas", "Broodje kaas", "Appelsap"};

    // prijzen
    private static double[] artikelprijzen = new double[]{1.50, 2.10, 1.65, 1.65};

    // minimum en maximum aantal artikelen per soort
    private static final int MIN_ARTIKELEN_PER_SOORT = 10000;
    private static final int MAX_ARTIKELEN_PER_SOORT = 20000;

    // minimum en maximum aantal personen per dag
    private static final int MIN_PERSONEN_PER_DAG = 50;
    private static final int MAX_PERSONEN_PER_DAG = 100;

    // minimum en maximum artikelen per persoon
    private static final int MIN_ARTIKELEN_PER_PERSOON = 1;
    private static final int MAX_ARTIKELEN_PER_PERSOON = 4;

    /**
     * Constructor
     *
     */
    public KantineSimulatie_2() {
        kantine = new Kantine();
        random = new Random();
        int[] hoeveelheden = getRandomArray(
            AANTAL_ARTIKELEN,
            MIN_ARTIKELEN_PER_SOORT,
            MAX_ARTIKELEN_PER_SOORT);
        kantineaanbod = new KantineAanbod(
            artikelnamen, artikelprijzen, hoeveelheden);

        kantine.setKantineAanbod(kantineaanbod);
    }

    /**
     * Methode om een array van random getallen liggend tussen
     * min en max van de gegeven lengte te genereren
     *
     * @param //lengte
     * @param //min
     * @param //max
     * @return De array met random getallen
     */
    private int[] getRandomArray(int lengte, int min, int max) {
        int[] temp = new int[lengte];
        for(int i = 0; i < lengte ;i++) {
            temp[i] = getRandomValue(min, max);
        }

        return temp;
    }

    /**
     * Methode om een random getal tussen min(incl)
     * en max(incl) te genereren.
     *
     * @param //min
     * @param //max
     * @return Een random getal
     */
    private int getRandomValue(int min, int max) {
        return random.nextInt(max - min + 1) + min;
    }

    /**
     * Methode om op basis van een array van indexen voor de array
     * artikelnamen de bijhorende array van artikelnamen te maken
     *
     * @param indexen
     * @return De array met artikelnamen
     */
    private String[] geefArtikelNamen(int[] indexen) {
        String[] artikelen = new String[indexen.length];

        for(int i = 0; i < indexen.length; i++) {
            artikelen[i] = artikelnamen[indexen[i]];

        }

        return artikelen;
    }

    /**
     * Deze methode simuleert een aantal dagen
     * in het verloop van de kantine
     *
     * @param dagen
     */
    public void simuleer(int dagen) {
        // for lus voor dagen
        for(int i = 0; i < dagen; i++) {

            // bedenk hoeveel personen vandaag binnen lopen
            int aantalpersonen = 100 ;

            int aantal_studenten = 0;
            int aantal_docenten = 0;
            int aantal_medewerkers = 0;

            // laat de personen maar komen...
            for(int j = 0; j < aantalpersonen; j++) {

                // maak persoon en dienblad aan, koppel ze
                // en bedenk hoeveel artikelen worden gepakt
                Persoon persoon = new Persoon();
                Dienblad dienblad = new Dienblad();
                dienblad.setKlant(persoon);
                int aantalartikelen = AANTAL_ARTIKELEN;

                // genereer de "artikelnummers", dit zijn indexen
                // van de artikelnamen
                int[] tepakken = getRandomArray(
                    aantalartikelen, 0, aantalartikelen -1);

                // vind de artikelnamen op basis van
                // de indexen hierboven
                String[] artikelen = geefArtikelNamen(tepakken);

                int randomint = random.nextInt(100);
                if (randomint < 89) {
                    Student student = new Student(0, "", "", null, 'M', 0, "");
                    System.out.println(student);
                    toString(student);
                    aantal_studenten ++;

                } else if (randomint > 89 && randomint < 99) {
                    Docent docent = new Docent(0, "", "", null, 'M', "", "");
                    System.out.println(docent);
                    toString(docent);
                    aantal_docenten ++;

                } else {
                    KantineMedewerker kantinemedewerker = new KantineMedewerker(0, "", "", null, 'M', 0, false);
                    System.out.println(kantinemedewerker);
                    toString(kantinemedewerker);
                    aantal_medewerkers ++;
                }

                // loop de kantine binnen, pak de gewenste
                // artikelen, sluit aan
                kantine.loopPakSluitAan(dienblad, artikelen);
            }

            //print hoeveelheid van elk type klant
            System.out.println(aantal_studenten);
            System.out.println(aantal_docenten);
            System.out.println(aantal_medewerkers);


            // verwerk rij voor de kassa
            kantine.verwerkRijVoorKassa();
            // druk de dagtotalen af en hoeveel personen binnen
            // zijn gekomen
            kantine.hoeveelheidGeldInKassa();

            // reset de kassa voor de volgende dag
            kantine.resetKassa();
        }
    }

    private void toString(Persoon p) {
        System.out.println("Deze klant was een: " + p.getClass().getName());
    }

    public static void main(String[] args) {
        int dagen = 7;
        int echtedagen = 0;
        if (args.length == 0) {
            echtedagen = dagen;
        } else {
            echtedagen = Integer.parseInt(args[0]);
        }
        KantineSimulatie_2 kantinesim = new KantineSimulatie_2();
        kantinesim.simuleer(echtedagen);
    }
}