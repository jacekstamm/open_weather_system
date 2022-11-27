package exception;

public class ForecastRequestInputException extends RuntimeException {
    public ForecastRequestInputException(int arguments) {
        super("Please check you input. There should be 4 arguments and was " + arguments);
    }
}
