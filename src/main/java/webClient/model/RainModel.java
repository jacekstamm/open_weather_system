package webClient.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RainModel {

    @JsonProperty("1h")
    private double _1h;
}
