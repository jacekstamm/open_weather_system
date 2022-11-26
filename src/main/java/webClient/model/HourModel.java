package webClient.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonIgnoreProperties({"feels_like", "pressure", "humidity", "dev_point", "dew_point", "uvi", "clouds", "visibility",
  "weather", "wind_speed", "wind_deg", "wind_gust", "pop", "snow"})
@NoArgsConstructor
@AllArgsConstructor
@Data
public class HourModel {

    private int dt;
    private double temp;
    private RainModel rain;
}
