public class TeWeinigGeldException extends Exception{

    public TeWeinigGeldException(){}

    public TeWeinigGeldException(Exception e){
        super(e);
    }

    public TeWeinigGeldException(String message){
        super(message);
    }
}