package webClient.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;

@JsonIgnoreProperties({"feels_like", "pressure", "humidity", "dew_point", "uvi", "clouds", "visibility",
  "weather", "wind_speed", "wind_deg", "wind_gust", "pop", "snow"})
@AllArgsConstructor
@Getter
public class HourModel {

    private int dt;
    private double temp;
    private RainModel rain;
}
