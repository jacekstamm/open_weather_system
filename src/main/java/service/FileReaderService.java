package service;

import exception.ConfigFileNotFoundException;
import exception.MissingDataInConfigFile;
import exception.WrongConfigFileExtensionException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class FileReaderService {

    public Map<String, List<Double>> readConfigFile(String filePath) {
        Map<String, List<Double>> configMap = new HashMap<>();
        if (!filePath.contains(".txt")) {
            throw new WrongConfigFileExtensionException();
        } else {
            try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
                Scanner scanner = new Scanner(new File(filePath));
                scanner.useDelimiter(",");
                String line;
                while ((line = br.readLine()) != null) {
                    List<Double> coordinatesList = new ArrayList<>();
                    String[] lineSplitted = line.split(",");
                    if (lineSplitted.length != 3) {
                        throw new MissingDataInConfigFile();
                    }
                    String city = lineSplitted[0];
                    coordinatesList.add(0, Double.parseDouble(lineSplitted[1]));
                    coordinatesList.add(1, Double.parseDouble(lineSplitted[2]));
                    configMap.put(city, coordinatesList);
                }
            } catch (FileNotFoundException ex) {
                throw new ConfigFileNotFoundException(filePath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return configMap;
    }
}
