package dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Builder
@Getter
public class HourForecastDto {
    private String date;
    private double temperature;
    private String precipitationVolume;

    public String toString() {
        return this.date + ", " + this.temperature + ", " + this.precipitationVolume;
    }
}
