package webClient.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class TemperatureModel {

    private double day;
    private double min;
    private double max;
    private double night;
    private double eve;
    private double morn;
}
