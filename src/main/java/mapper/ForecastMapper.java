package mapper;

import dto.DailyForecastDto;
import dto.DayForecastDto;
import dto.HourForecastDto;
import dto.HourlyForecastDto;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import webClient.model.DailyForecastModel;
import webClient.model.DayModel;
import webClient.model.HourModel;
import webClient.model.HourlyForecastModel;
import webClient.model.TemperatureModel;

public class ForecastMapper {

    public DailyForecastDto mapDailyModelToDto(DailyForecastModel dailyModel) {
        return DailyForecastDto.builder()
          .days(extractDays(dailyModel.getDaily()))
          .build();
    }

    public HourlyForecastDto mapHourlyModelToDto(HourlyForecastModel hourlyModel) {
        return HourlyForecastDto.builder()
          .hours(extractHours(hourlyModel.getHourly()))
          .build();
    }

    private List<DayForecastDto> extractDays(List<DayModel> dayModels) {
        List<DayForecastDto> daysList = new ArrayList<>();
        for (DayModel model: dayModels) {
            DayForecastDto dayForecastDto = DayForecastDto.builder()
              .date(new Date(model.getDt() * 1000L))
              .minTemperature(model.getTemp().getMin())
              .maxTemperature(model.getTemp().getMax())
              .dayAverageTemperature(calculateAverageTemperature(model.getTemp()))
              .probabilityOfPrecipitation(model.getPop())
              .build();
            daysList.add(dayForecastDto);
        }
        return daysList;
    }

    private List<HourForecastDto> extractHours(List<HourModel> hourModels) {
        List<HourForecastDto> hoursList = new ArrayList<>();
        for (HourModel model: hourModels) {
            HourForecastDto hourForecastDto = HourForecastDto.builder()
              .date(getFormattedDate(model.getDt()))
              .temperature(model.getTemp())
              .precipitationVolume(getVolume(model))
              .build();
            hoursList.add(hourForecastDto);
        }
        return hoursList;
    }

    protected String getVolume(HourModel model) {
        if (model.getRain() == null) {
            return "unknown";
        } else {
            return String.valueOf(model.getRain().get_1h());
        }
    }

    protected String getFormattedDate(int timeStamp) {
        LocalDateTime localDateTime = Timestamp.from(Instant.ofEpochMilli(timeStamp * 1000L)).toLocalDateTime();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
        return localDateTime.format(formatter);
    }

    protected String calculateAverageTemperature(TemperatureModel temperatureModel) {
        DecimalFormat df = new DecimalFormat("#.##");
        Double result = (temperatureModel.getDay() + temperatureModel.getEve()
          + temperatureModel.getMorn() + temperatureModel.getNight()) / 4;
        return df.format(result);
    }
}
