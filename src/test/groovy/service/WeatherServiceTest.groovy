package service

import dto.DailyForecastDto
import dto.DayForecastDto
import dto.HourForecastDto
import dto.HourlyForecastDto
import exception.*
import mapper.ForecastMapper
import spock.lang.Specification
import spock.lang.Unroll
import webClient.ForecastWebClient

import static constants.WebClientConstants.getDailyForecastModel
import static constants.WebClientConstants.getHourlyForecastModel

class WeatherServiceTest extends Specification {

    private static final String PATH_BEGIN = 'src/test/testConfigFiles/'
    private static final String API_KEY = 'apiKey'
    private static final String FILE_NAME = 'src/test/testConfigFiles/correctConfigFile.txt'
    private static final String CITY_NAME = 'Warsaw'
    private static final double LATITUDE = 89.2297
    private static final double LONGITUDE = 21.0122

    private ForecastWebClient webClient
    private WeatherService service

    def setup() {
        webClient = Stub(ForecastWebClient)
        service = new WeatherService(webClient, new FileReaderService(), new ForecastMapper())
    }

    def 'should get correct DailyForecast from given data'() {
        given:
        webClient.getDailyForecast(API_KEY, LATITUDE, LONGITUDE) >> getDailyForecastModel()
        service.getCoordinatesList(FILE_NAME, CITY_NAME) >> [LATITUDE, LONGITUDE]

        when:
        DailyForecastDto result = service.getDailyForecast(API_KEY, FILE_NAME, CITY_NAME)

        then:
        List<DayForecastDto> dayForecastList = result.days
        verifyAll {
            dayForecastList.size() == 8
            checkDayResults(dayForecastList.get(0), '2022-11-26', 34.85, 24.42, '28,91', 0.2)
            checkDayResults(dayForecastList.get(1), '2022-11-27', -30.86, -24.06, '-27,07', 0.38)
            checkDayResults(dayForecastList.get(2), '2022-11-28', -33.72, -29.26, '-32,1', 0.0)
            checkDayResults(dayForecastList.get(3), '2022-11-29', -33.4, -16.65, '-22,03', 1.0)
            checkDayResults(dayForecastList.get(4), '2022-11-30', -19.55, -10.04, '-14,15', 1.0)
            checkDayResults(dayForecastList.get(5), '2022-12-01', -18.85, -8.72, '-15,04', 1.0)
            checkDayResults(dayForecastList.get(6), '2022-12-02', -25.03, -17.95, '-21,57', 1.0)
            checkDayResults(dayForecastList.get(7), '2022-12-03', -31.12, -27.02, '-29,26', 0.0)
        }
    }

    def 'should get correct HourlyForecast from given data'() {
        given:
        webClient.getHourlyForecast(API_KEY, LATITUDE, LONGITUDE) >> getHourlyForecastModel()
        service.getCoordinatesList(FILE_NAME, CITY_NAME) >> [LATITUDE, LONGITUDE]

        when:
        HourlyForecastDto result = service.getHourlyForecast(API_KEY, FILE_NAME, CITY_NAME)

        then:
        List<HourForecastDto> hourForecastList = result.hours
        verifyAll {
            hourForecastList.size() == 48
            checkHourResult(hourForecastList.get(0), '26.11.2022 14:00', 28.51, 'unknown')
            checkHourResult(hourForecastList.get(1), '26.11.2022 15:00', 28.33, '0.37')
            checkHourResult(hourForecastList.get(2), '26.11.2022 16:00', 25.81, '0.27')
            checkHourResult(hourForecastList.get(3), '26.11.2022 17:00', 26.94, '0.65')
            checkHourResult(hourForecastList.get(4), '26.11.2022 18:00', 28.22, '1.17')
        }
    }

    @Unroll
    def 'should throw #exception when forecast request got invalid data'() {
        when:
        service.validateForecastRequest(wrongRequest as String[])

        then:
        thrown(exception)

        where:
        wrongRequest                               | exception
        ["value1", "value2", "value3"]             | ForecastRequestInputException
        ["1", "2", "3", "4", "5"]                  | ForecastRequestInputException
        ["value1", "value2", "secondly", "value3"] | WrongForecastTypeCException
    }

    @Unroll
    def 'should throw #exception when config file got errors'() {
        when:
        service.getCoordinatesList(filePath, cityName)

        then:
        thrown(exception)

        where:
        filePath                                         | cityName | exception
        PATH_BEGIN + 'configWithWrongExtension.csv'      | 'Warsaw' | WrongConfigFileExtensionException
        PATH_BEGIN + 'configWithoutThirdArgument.txt'    | 'Warsaw' | MissingDataInConfigFile
        PATH_BEGIN + 'something.txt'                     | 'Warsaw' | ConfigFileNotFoundException
        PATH_BEGIN + 'configWithoutCityName.txt'         | 'Berlin' | MissingCityNameInConfigFile
        PATH_BEGIN + 'configWithLatitudeOutOfRange.txt'  | 'Warsaw' | LatitudeException
        PATH_BEGIN + 'configWithLongitudeOutOfRange.txt' | 'Warsaw' | LongitudeException
    }

    private static void checkDayResults(DayForecastDto day, String date, double minTemp, double maxTemp,
                                        String averageTemp, double possibleOfRain) {
        assert day.date.toString() == date
        assert day.minTemperature == minTemp
        assert day.maxTemperature == maxTemp
        assert day.getDayAverageTemperature() == averageTemp
        assert day.getProbabilityOfPrecipitation() == possibleOfRain
    }

    private static void checkHourResult(HourForecastDto hour, String data, double temperature, String rainModel) {
        assert hour.date == data
        assert hour.temperature == temperature
        assert hour.precipitationVolume == rainModel
    }
}
