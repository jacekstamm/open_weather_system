package webClient.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class TemperatureModel {

    private double day;
    private double min;
    private double max;
    private double night;
    private double eve;
    private double morn;
}
