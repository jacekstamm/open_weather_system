package dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class HourForecastDto {
    private String date;
    private double temperature;
    private double precipitationVolume;

    public String toString() {
        return this.date + ", " + this.temperature + ", " + this.precipitationVolume;
    }
}
