package error;

public class InvalidLeftHandValueException extends RuntimeException{
    public InvalidLeftHandValueException(String mesg){
        super(mesg);
    }
}
