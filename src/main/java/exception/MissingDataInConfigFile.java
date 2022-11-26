package exception;

public class MissingDataInConfigFile extends RuntimeException {
    public MissingDataInConfigFile() {
        super("Please check config file. There are some missing data!");
    }
}
