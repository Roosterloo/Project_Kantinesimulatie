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
            // per dag nu even vast 10 + i personen naar binnen
            // laten gaan, wordt volgende week veranderd...
            int personen = 10 + i;
            // for lus voor personen
            for (int p = 0; p < personen; p++) {
                //kantine.loopPakSluitAan();
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