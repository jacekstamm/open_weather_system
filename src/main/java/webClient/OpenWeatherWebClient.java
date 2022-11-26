package webClient;

import static config.PropertiesLoader.getProperty;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import webClient.model.DailyForecastModel;
import webClient.model.ForecastType;
import webClient.model.HourlyForecastModel;

@NoArgsConstructor
public class OpenWeatherWebClient implements ForecastWebClient {

    @Override
    public DailyForecastModel getDailyForecast(String apiKey, double lat, double lon) {
        InputStream dailyForecastStream = getForecastWithExcluded(apiKey, lat, lon, "current,hourly,minutely,alerts");
        return (DailyForecastModel) mapInputStream(dailyForecastStream, ForecastType.DAILY);
    }

    @Override
    public HourlyForecastModel getHourlyForecast(String apiKey, double lat, double lon) {
        InputStream hourlyForecastStream = getForecastWithExcluded(apiKey, lat, lon, "current,daily,minutely,alerts");
        return (HourlyForecastModel) mapInputStream(hourlyForecastStream, ForecastType.HOURLY);
    }

    private InputStream getForecastWithExcluded(String apiKey, double lat, double lon, String excluded) {
        String baseUrl = getProperty("open.weather.base.url");
        try {
           URL url = new URL(baseUrl + lat + "&lon=" + lon + "&exclude=" + excluded + "&units=metric&appid=" + apiKey);

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setDoOutput(true);
            connection.setRequestProperty("Authorization", "Bearer " + "bearerString");
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

            return connection.getInputStream();
        } catch (IOException e) {
            throw new RuntimeException("IOException appear. Problems with GET request to OpenWeather");
        }
    }

    @SneakyThrows
    private Object mapInputStream(InputStream inputStream, ForecastType forecastType) {
        ObjectMapper mapper = new ObjectMapper();
            if (forecastType == ForecastType.DAILY) {
                return mapper.readValue(inputStream, DailyForecastModel.class);
            } else {
                return mapper.readValue(inputStream, HourlyForecastModel.class);
            }
    }
}
