package webClient.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class TemperatureModel {

    private double day;
    private double min;
    private double max;
    private double night;
    private double eve;
    private double morn;
}
