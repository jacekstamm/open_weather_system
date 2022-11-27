package dto;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Builder
@Getter
public class DayForecastDto {

    private Date date;
    private double minTemperature;
    private double maxTemperature;
    private String dayAverageTemperature;
    private double probabilityOfPrecipitation;


    public String toString() {
        return  this.date + ", " + this.minTemperature +  ", " + this.maxTemperature +
          ", " + this.dayAverageTemperature + ", " + this.probabilityOfPrecipitation;
    }
}
