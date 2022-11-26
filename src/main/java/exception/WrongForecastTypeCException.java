package exception;

public class WrongForecastTypeCException extends RuntimeException {
    public WrongForecastTypeCException(String wrongInput) {
        super("Wrong Forecast type has been chosen. {" + wrongInput +  "} You can choose between [daily, hourly] type of forecast");
    }
}
