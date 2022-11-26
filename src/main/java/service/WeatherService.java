package service;

import dto.DailyForecastDto;
import dto.HourlyForecastDto;
import exception.ForecastRequestInputException;
import exception.LatitudeException;
import exception.LongitudeException;
import exception.MissingCityNameInConfigFile;
import exception.WrongForecastTypeCException;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import mapper.ForecastMapper;
import webClient.ForecastWebClient;
import webClient.model.DailyForecastModel;
import webClient.model.HourlyForecastModel;

@RequiredArgsConstructor
public class WeatherService {

    private final ForecastWebClient webClient;
    private final FileReaderService fileReaderService;
    private final ForecastMapper mapper;

    public DailyForecastDto getDailyForecast(String apiKey, String configurationFilePath, String cityName) {
        List<Double> coordinatesList = getCoordinatesList(configurationFilePath, cityName);
        DailyForecastModel dailyForecastModel = webClient.getDailyForecast(apiKey, coordinatesList.get(0), coordinatesList.get(1));
        return mapper.mapDailyModelToDto(dailyForecastModel);
    }

    public HourlyForecastDto getHourlyForecast(String apiKey, String configurationFilePath, String cityName) {
        List<Double> coordinatesList = getCoordinatesList(configurationFilePath, cityName);
        HourlyForecastModel hourlyForecastModel = webClient.getHourlyForecast(apiKey, coordinatesList.get(0), coordinatesList.get(1));
        return mapper.mapHourlyModelToDto(hourlyForecastModel);
    }

    public void validateForecastRequest(String[] requestInput) {
        int requestArguments = requestInput.length;
        if (requestInput.length != 4) {
            throw new ForecastRequestInputException(requestArguments);
        }
        String forecastType = requestInput[2];
        if (!forecastType.equalsIgnoreCase("daily") && !forecastType.equalsIgnoreCase("hourly")) {
            throw new WrongForecastTypeCException(requestInput[2]);
        }
    }

    protected List<Double> getCoordinatesList(String configurationFilePath, String cityName) {
        Map<String, List<Double>> configMap = fileReaderService.readConfigFile(configurationFilePath);
        if (!configMap.containsKey(cityName.toUpperCase())) {
            throw new MissingCityNameInConfigFile();
        }
        return validCoordinates(configMap.get(cityName.toUpperCase()));
    }

    private List<Double> validCoordinates(List<Double> listToCheck) {
        if (listToCheck.get(0) > 90 || listToCheck.get(0) < -90) {
            throw new LatitudeException();
        }
        if (listToCheck.get(1) > 180 || listToCheck.get(1) < -180) {
            throw new LongitudeException();
        }
        return listToCheck;
    }
}
