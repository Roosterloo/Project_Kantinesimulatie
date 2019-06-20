public class TeWeinigGeldException extends Exception{

    private double hoeveelheid_geld;
    private String bericht;

    public TeWeinigGeldException(){
    }

    public TeWeinigGeldException(Exception e){
        super(e);
    }

    public TeWeinigGeldException(String message){
        super(message);
    }
}