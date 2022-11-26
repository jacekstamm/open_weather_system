package exception;

public class WrongConfigFileExtensionException extends RuntimeException {
    public WrongConfigFileExtensionException() {
        super("Wrong configuration file extension! This system is accepting only .txt files");
    }
}
