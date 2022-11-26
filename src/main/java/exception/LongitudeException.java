package exception;

public class LongitudeException extends RuntimeException {
    public LongitudeException() {
        super("Longitude found outside of allowed values");
    }
}
