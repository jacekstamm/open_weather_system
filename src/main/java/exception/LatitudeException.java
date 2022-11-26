package exception;

public class LatitudeException extends RuntimeException {
    public LatitudeException() {
        super("Latitude found outside of allowed values");
    }
}
