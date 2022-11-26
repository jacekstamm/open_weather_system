package exception;

public class MissingCityNameInConfigFile extends RuntimeException {
    public MissingCityNameInConfigFile() {
        super("Please check your given con");
    }
}
