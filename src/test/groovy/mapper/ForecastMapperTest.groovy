package mapper

import spock.lang.Specification
import spock.lang.Unroll
import webClient.model.HourModel
import webClient.model.RainModel
import webClient.model.TemperatureModel

class ForecastMapperTest extends Specification {

    private ForecastMapper mapper = new ForecastMapper()

    @Unroll
    def 'should get rain equals #rain from HourModel'() {
        when:
        double result = mapper.getVolume(new HourModel(1669471200, 2.90, rain as RainModel))

        then:
        result == expected

        where:
        rain                | expected
        null                | 0
        new RainModel(1.45) | 1.45
    }

    def 'should return proper formatted date from timestamp'() {
        when:
        String result = mapper.getFormattedDate(1669471200)

        then:
        result == '26.11.2022 15:00'
    }

    def 'should return calculated average temperature'() {
        when:
        String result = mapper.calculateAverageTemperature(new TemperatureModel(day, min, max, night, eve, morn))

        then:
        result == calculations

        where:
        day   | min    | max   | night | eve   | morn  | calculations
        20.54 | 12.55  | 30.99 | 15.78 | 16.76 | 14.67 | '16,94'
        0.0   | -18.76 | 29.45 | 5.91  | 12.93 | 15.99 | '8,71'
    }
}
