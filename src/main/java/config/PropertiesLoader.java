package config;

import exception.PropertyLoaderException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class PropertiesLoader {

    private final static String PROPERTIES_PATH = "src/main/resources/application.properties";

    public static String getProperty(String propertyName) {
        Properties properties = new Properties();
        try {
            FileReader reader = new FileReader(PROPERTIES_PATH);
            properties.load(reader);
        } catch (IOException exception) {
            throw new PropertyLoaderException();
        }
        return properties.getProperty(propertyName);
    }
}
