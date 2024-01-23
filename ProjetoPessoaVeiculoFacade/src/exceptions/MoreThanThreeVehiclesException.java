package exceptions;

public class MoreThanThreeVehiclesException extends Exception{

    public MoreThanThreeVehiclesException() {
    }

    public MoreThanThreeVehiclesException(String msg) {
        super(msg);
    }
}