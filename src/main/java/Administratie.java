public class Administratie {


    public static final int DAYS_IN_WEEK = 7;

    private Administratie(){
    }

    /**
     * Deze methode berekent van de int array aantal de gemiddelde waarde
     *
     * @param //aantal
     * @return het gemiddelde
     */
    public static double berekenGemiddeldAantal(int[] aantal) {
        int totaal = 0;
        for(int waarde : aantal){
            totaal = totaal + waarde;
        }
        System.out.println("Het gemiddelde aantal producten per dag is: " + totaal / aantal.length);
        return totaal / aantal.length;
    }

    /**
     * Deze methode berekent van de double array omzet de gemiddelde waarde
     *
     * @param //omzet
     * @return het gemiddelde
     */
    public static double berekenGemiddeldeOmzet(double[] omzet) {
        double totaal = 0;
        for(double waarde : omzet){
            totaal = totaal + waarde;
        }
        //De math function die door honderd gedeelt wordt geeft een bedrag terug dat op 2 getallen na de comma wordt afgerond
        System.out.println("De gemiddelde dagomzet is: €" + Math.round(totaal / omzet.length * 100.0) / 100.0);
        return totaal / omzet.length;
    }

    /**
     * Methode om dagomzet uit te rekenen
     *
     * @param //omzet
     * @return array (7 elementen) met dagomzetten
     */

    public static double[] berekenDagOmzet(double[] omzet) {
        String[] dagen = {"Maandag", "Dinsdag", "Woemsdag", "Donderdag","Vrijdag","Zaterdag","Zondag"};
        double[] temp = new double[DAYS_IN_WEEK];
        for(int i = 0; i < DAYS_IN_WEEK; i++) {
            int j = 0;
            while((i + DAYS_IN_WEEK * j) <= omzet.length - 1) {
                temp[i] += omzet[i + DAYS_IN_WEEK * j];
                j++;
            }
        }
        for(int i = 0; i < temp.length; i++){
            //De math function die door honderd gedeelt wordt geeft een bedrag terug dat op 2 getallen na de comma wordt afgerond
            System.out.println(dagen[i] + " heeft als omzet: €" + Math.round(temp[i] * 100.0) / 100.0);
        }
        return temp;
    }
}
