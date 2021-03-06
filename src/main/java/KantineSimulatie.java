import java.util.*;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class KantineSimulatie {

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

    //Maakt een EntityManager aan
    private static final EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("KantineSim");
    private EntityManager manager;

    /**
     * Constructor
     *
     */
    public KantineSimulatie() {
        manager = ENTITY_MANAGER_FACTORY.createEntityManager();
        kantine = new Kantine(manager);
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
     * @param //indexen
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
     * @param //dagen
     */
    public void simuleer(int dagen) {
        // totalen
        double[] dagomzet = new double[dagen];
        int[] dagaantallen = new int[dagen];

        // for lus voor dagen
        for(int i = 0; i < dagen; i++) {

            // bedenk hoeveel personen vandaag binnen lopen
            int aantalpersonen = 100 ;

            int aantal_studenten = 0;
            int aantal_docenten = 0;
            int aantal_medewerkers = 0;

            // laat de personen maar komen...
            for(int j = 0; j < aantalpersonen; j++) {

                // maakt een dienblad aan
                // bedenk hoeveel artikelen worden gepakt
                Dienblad dienblad = new Dienblad();
                int aantalartikelen = AANTAL_ARTIKELEN;

                //genereert de random locatie voor een artikel om korting the krijgen
                int randomartikel = getRandomValue(0, AANTAL_ARTIKELEN - 1);

                // Genereert een Persoon en geeft een betaalwijze
                int randomint = getRandomValue(0,100);
                int randombetaal = getRandomValue(0,1);
                int sal = getRandomValue(0,2500);
                if (randomint < 89) {
                    Student student = new Student(0, "", "", null, 'M', 0, "");
                    dienblad.setKlant(student);
                    if(randombetaal == 0){
                        Betaalwijze pinpas = new Pinpas();
                        pinpas.saldo = sal;
                        student.setBetaalwijze(pinpas);
                    } else if (randombetaal == 1){
                        Betaalwijze contact = new Contant();
                        contact.saldo = sal;
                        student.setBetaalwijze(contact);
                    }
                    toString(student);
                    aantal_studenten ++;

                } else if (randomint > 89 && randomint < 99) {
                    Docent docent = new Docent(0, "", "", null, 'M', "", "");
                    dienblad.setKlant(docent);
                    if(randombetaal == 0){
                        Betaalwijze pinpas = new Pinpas();
                        pinpas.saldo = sal;
                        docent.setBetaalwijze(pinpas);
                    } else if (randombetaal == 1){
                        Betaalwijze contact = new Contant();
                        contact.saldo = sal;
                        docent.setBetaalwijze(contact);
                    }
                    toString(docent);
                    aantal_docenten ++;

                } else {
                    KantineMedewerker kantinemedewerker = new KantineMedewerker(0, "", "", null, 'M', 0, false);
                    dienblad.setKlant(kantinemedewerker);
                    if(randombetaal == 0){
                        Betaalwijze pinpas = new Pinpas();
                        pinpas.saldo = sal;
                        kantinemedewerker.setBetaalwijze(pinpas);
                    } else if (randombetaal == 1){
                        Betaalwijze contact = new Contant();
                        contact.saldo = sal;
                        kantinemedewerker.setBetaalwijze(contact);
                    }
                    toString(kantinemedewerker);
                    aantal_medewerkers ++;
                }

                // genereer de "artikelnummers", dit zijn indexen
                // van de artikelnamen
                int[] tepakken = getRandomArray(
                    aantalartikelen, 0, aantalartikelen -1);

                // vind de artikelnamen op basis van
                // de indexen hierboven
                String[] artikelen = geefArtikelNamen(tepakken);

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

            //uitvoerenQuery();

            //kijkt hoeveel geld er in de kassa zit
            kantine.hoeveelheidGeldInKassa();

            // Voegt de dagelijkse omzet en aantal artikelen toe aan een array
            dagomzet[i] = kantine.hoeveelheidGeldInKassa();
            dagaantallen[i] = kantine.hoeveelheidArtikelen();

            // reset de kassa voor de volgende dag
            kantine.resetKassa();

        }
        // Roept de administratieklasse aan om de gemiddelde dagomzet te berekenen,
        // het gemiddelde aantal producten per dag te berekenen
        // en de omzet op elke weekdag te berekenen
        Administratie.berekenGemiddeldAantal(dagaantallen);
        Administratie.berekenGemiddeldeOmzet((dagomzet));
        Administratie.berekenDagOmzet(dagomzet);
        uitvoerenQuery();
        manager.close();
        ENTITY_MANAGER_FACTORY.close();
    }

    private void uitvoerenQuery() {
     Query query = manager.createQuery(
             "SELECT SUM(totaal), SUM(korting), AVG(totaal),AVG(korting) From Factuur ");
     List<Object[]> resultList = query.getResultList();
     resultList.forEach(r -> System.out.println("Totale omzet en totale korting\n" +(Arrays.toString((r)))));

     Query query1 = manager.createQuery(
             "SELECT AVG(totaal),AVG(korting) From Factuur");
     List<Object[]> resultList1 = query1.getResultList();
     resultList1.forEach(r -> System.out.println("Gemiddelde omzet en gemiddelde korting\n" +(Arrays.toString((r)))));

     Query query2 = manager.createQuery(
     "SELECT id, klantnaam, totaal, korting, datum From Factuur ORDER BY totaal DESC ");
     query2.setMaxResults(3);
     List<Object[]> resultList2 = query2.getResultList();
     System.out.println("Drie facturen met de hoogste omzet");
     resultList2.forEach(r -> System.out.println((Arrays.toString((r)))));
     }


    private void toString(Persoon p) {
        System.out.println("De naam van deze klant klant is: " + p.getVoornaam() + " " + p.getAchternaam());
        System.out.println("Deze klant was een: " + p.getClass().getName());
    }

    public static void main(String[] args) {
        KantineSimulatie kantinesim = new KantineSimulatie();
        kantinesim.simuleer(Administratie.DAYS_IN_WEEK);
    }
}