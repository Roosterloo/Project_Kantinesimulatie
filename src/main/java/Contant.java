public class Contant extends Betaalwijze {
    /**
     * Methode om betaling af te handelen
     */
    public void betaal(double tebetalen) throws TeWeinigGeldException{
        if(saldo >= tebetalen){
            saldo = saldo - tebetalen;
        } else {
            String message = "Deze persoon heeft niet genoeg geld!";
            throw new TeWeinigGeldException(message);
        }
    }
}