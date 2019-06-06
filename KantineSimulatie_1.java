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
        for (int i = 0; i < dagen; i++) {
            // per dag nu even vast 10 + i personen naar binnen
            // laten gaan, wordt volgende week veranderd...
            int personen = 10 + i;
            // for lus voor personen
            for (int p = 0; p < personen; p++) {
                kantine.loopPakSluitAan();
            }
            // verwerk rij voor de kassa
            kantine.verwerkRijVoorKassa();

            // toon dagtotalen (artikelen en geld in kassa)
            kantine.hoeveelheidGeldInKassa();

            // reset de kassa voor de volgende dag
            kantine.resetKassa();
        }
    }

    /**
     * Start een simulatie
     */
    public static void main(String[] args) {
        KantineSimulatie kantinesim = new KantineSimulatie();
        int dagen;

        if (args.length == 0) {
            dagen = DAGEN;
        } else {
            dagen = Integer.parseInt(args[0]);
        }
        kantinesim.simuleer(dagen);
    }
}