package webClient.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
@JsonIgnoreProperties({"lat", "lon", "minutely", "timezone", "timezone_offset"})
public class DailyForecastModel {

    private List<DayModel> daily;
}
