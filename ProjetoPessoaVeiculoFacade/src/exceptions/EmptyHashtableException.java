package exceptions;

public class EmptyHashtableException extends Exception{
    public EmptyHashtableException() {
    }

    public EmptyHashtableException(String msg) {
        super(msg);
    }
}
