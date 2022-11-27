package webClient.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@JsonIgnoreProperties({"lat", "lon", "timezone", "timezone_offset", "alerts"})
@Builder
@AllArgsConstructor
@Getter
@NoArgsConstructor
public class HourlyForecastModel {

    private List<HourModel> hourly;
}
