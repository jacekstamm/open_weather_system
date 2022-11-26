package webClient;

import webClient.model.DailyForecastModel;
import webClient.model.HourlyForecastModel;

public interface ForecastWebClient {
    DailyForecastModel getDailyForecast(String apiKey, double lat, double lon);
    HourlyForecastModel getHourlyForecast(String apiKey, double lat, double lon);
}
