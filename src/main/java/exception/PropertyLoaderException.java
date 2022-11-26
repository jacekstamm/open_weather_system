package exception;

public class PropertyLoaderException extends RuntimeException {
    public PropertyLoaderException() {
        super("There is a problem with loading data from property file.");
    }
}
