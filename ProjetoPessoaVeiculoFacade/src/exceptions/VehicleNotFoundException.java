package exceptions;

public class VehicleNotFoundException extends Exception {

    public VehicleNotFoundException() {
    }

    public VehicleNotFoundException(String msg) {
        super(msg);
    }
}
