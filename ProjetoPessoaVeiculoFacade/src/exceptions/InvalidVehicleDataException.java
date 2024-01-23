package exceptions;

public class InvalidVehicleDataException extends Exception{
    public InvalidVehicleDataException() {
    }

    public InvalidVehicleDataException(String msg) {
        super(msg);
    }
}