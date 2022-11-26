package webClient.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@JsonIgnoreProperties({"lat", "lon", "minutely", "timezone", "timezone_offset"})
public class DailyForecastModel {

    private List<DayModel> daily;
}
