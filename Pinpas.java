public class Pinpas extends Betaalwijze {

    private double kredietlimiet;

    /**
     * Methode om kredietlimiet te zetten
     * @param kredietlimiet
     */
    public void setKredietLimiet(double kredietlimiet) {
        this.kredietlimiet = kredietlimiet;
    }

    /**
     * Methode om betaling af te handelen
     */
    public void betaal(double tebetalen) throws TeWeinigGeldException {
       if(saldo + kredietlimiet >= tebetalen){
           saldo = saldo - tebetalen;
       } else {
           String message = "Deze persoon heeft niet genoeg geld!";
           throw new TeWeinigGeldException(message);
       }
    }
}
