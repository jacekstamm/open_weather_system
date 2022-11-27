package webClient.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@JsonIgnoreProperties({"lat", "lon", "timezone", "timezone_offset", "alerts"})
@Builder
@AllArgsConstructor
@Getter
public class HourlyForecastModel {

    private List<HourModel> hourly;
}
