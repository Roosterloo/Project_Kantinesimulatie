public class Administratie {


    private static final int DAYS_IN_WEEK = 7;

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
        System.out.println("Het gemiddelde aantal is: " + totaal / aantal.length);
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
        System.out.println("De gemiddelde omzet is: " + totaal / omzet.length);
        return totaal / omzet.length;
    }

    /**
     * Methode om dagomzet uit te rekenen
     *
     * @param //omzet
     * @return array (7 elementen) met dagomzetten
     */

    public static double[] berekenDagOmzet(double[] omzet) {
        double[] temp = new double[DAYS_IN_WEEK];
        for(int i = 0; i < DAYS_IN_WEEK; i++) {
            int j = 0;
            while((i + DAYS_IN_WEEK * j) <= omzet.length - 1) {
                temp[i] += omzet[i + DAYS_IN_WEEK * j];
                j++;
            }
        }
        for(int i = 0; i < temp.length; i++){
            System.out.println("Dag " + (i + 1) + "heeft als omzet: " + temp[i]);
        }
        return temp;
    }
}
