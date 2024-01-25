package exceptions;

public class EmptyDatabaseTableException extends Exception{
    public EmptyDatabaseTableException() {
    }

    public EmptyDatabaseTableException(String msg) {
        super(msg);
    }
}
