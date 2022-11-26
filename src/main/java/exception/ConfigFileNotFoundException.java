package exception;

public class ConfigFileNotFoundException extends RuntimeException {
    public ConfigFileNotFoundException(String filePath) {
        super("Configuration File with name " + filePath + " doesn't exist.");
    }
}
