public class KantineSimulatie {

    private Kantine kantine;

    public static final int DAGEN = 7;

    /**
     * Constructor
     */
    public KantineSimulatie() {
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
        for(int i = 0; i < dagen; i++) {
            // per dag nu even vast 10 + i personen naar binnen
            // laten gaan, wordt volgende week veranderd...

            // for lus voor personen
            for (int p = 0; p < 10 + i; p++){
                kantine.loopPakSluitAan();
            }
            // voert de kassa methodes uit
            kantine.verwerkRijVoorKassa();

            //toont de data in de kassa en reset deze
            kantine.informatie_kassa();

        }
    }

    /**
     * Start een simulatie
     */
    public static void main(String[] args) {
        int dagen;

        if (args.length == 0) {
            dagen = DAGEN;
        } else {
            dagen = Integer.parseInt(args[0]);
        }

        simulate(dagen);
    }
}