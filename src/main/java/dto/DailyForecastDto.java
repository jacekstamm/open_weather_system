package dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Builder
@Getter
public class DailyForecastDto {

    private List<DayForecastDto> days;

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(System.lineSeparator());
        for (int i = 1; i < this.days.size(); i++) {
            DayForecastDto day = days.get(i);
            sb.append(day)
              .append(System.lineSeparator());
        }
        return sb.toString();
    }
}
