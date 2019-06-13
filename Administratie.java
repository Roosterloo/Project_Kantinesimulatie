public class Administratie {

    /**
     * Deze methode berekent van de int array aantal de gemiddelde waarde
     *
     * @param //aantal
     * @return het gemiddelde
     */

    private Administratie(){
    }

    public static double berekenGemiddeldAantal(int[] aantal) {
        int totaal = 0;
        for(int waarde : aantal){
            totaal = totaal + waarde;
        }
        return totaal / aantal.length;
    }

    /**
     * Deze methode berekent van de double array omzet de gemiddelde waarde
     *
     * @param omzet
     * @return het gemiddelde
     */
    public static double berekenGemiddeldeOmzet(double[] omzet) {
        double totaal = 0;
        for(double waarde : omzet){
            totaal = totaal + waarde;
        }
        return totaal / omzet.length;
    }

    /**
     * Methode om dagomzet uit te rekenen
     *
     * @param omzet
     * @return array (7 elementen) met dagomzetten
     */

    /*public static double[] berekenDagOmzet(double[] omzet) {
        double[] temp = new double[7];
        for(int i = 0; i < 7; i++) {

            int j = 0;
            while( ... ) {
                temp[i] += omzet[i + 7 * j];

                // omitted

            }
        }
        return temp;
    }
     */
}
