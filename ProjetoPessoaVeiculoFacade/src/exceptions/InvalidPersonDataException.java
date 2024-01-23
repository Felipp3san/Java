package exceptions;

public class InvalidPersonDataException extends Exception{
    public InvalidPersonDataException() {
    }

    public InvalidPersonDataException(String msg) {
        super(msg);
    }
}
