package webClient.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@JsonIgnoreProperties({"sunrise", "sunset", "moonrise", "moonset", "moon_phase", "feels_like", "pressure", "humidity", "dew_point",
  "wind_speed", "wind_deg", "wind_gust", "weather", "clouds", "rain", "uvi", "snow"})
public class DayModel {

    private int dt;
    private TemperatureModel temp;
    private double pop;
}
