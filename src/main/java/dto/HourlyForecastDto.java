package dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Builder
@Getter
public class HourlyForecastDto {

    private List<HourForecastDto> hours;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(System.lineSeparator());
        for (HourForecastDto hour : this.hours) {
            sb.append(hour)
              .append(System.lineSeparator());
        }
        return sb.toString();
    }
}
