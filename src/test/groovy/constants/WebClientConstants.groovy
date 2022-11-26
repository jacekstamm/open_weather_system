package constants

import webClient.model.*

class WebClientConstants {

    static DailyForecastModel getDailyForecastModel() {
        return new DailyForecastModel([
                new DayModel(1669460400, new TemperatureModel(30.58, 34.85, 24.42, 24.42, 25.87, 34.76), 0.2),
                new DayModel(1669546800, new TemperatureModel(-24.39, -30.86, -24.06, -30.86, -28.88, -24.17), 0.38),
                new DayModel(1669633200, new TemperatureModel(-31.87, -33.72, -29.26, -33.72, -33.23, -29.58), 0.0),
                new DayModel(1669719600, new TemperatureModel(-23.48, -33.4, -16.65, -16.65, -19.35, -28.64), 1.0),
                new DayModel(1669806000, new TemperatureModel(-16.28, -19.55, -10.04, -10.04, -12.46, -17.84), 1.0),
                new DayModel(1669892400, new TemperatureModel(-14.21, -18.85, -8.72, -18.85, -17.43, -9.67), 1.0),
                new DayModel(1669978800, new TemperatureModel(-19.57, -25.03, -17.95, -25.03, -23.71, -17.95), 1.0),
                new DayModel(1670065200, new TemperatureModel(-29.26, -31.12, -27.02, -29.12, -31.12, -27.55), 0.0)])
    }

    static HourlyForecastModel getHourlyForecastModel() {
        List<HourModel> modelList = [new HourModel(1669467600, 28.51, null),
        new HourModel(1669471200, 28.33, new RainModel(0.37)),
        new HourModel(1669474800, 25.81, new RainModel(0.27)),
        new HourModel(1669478400, 26.94, new RainModel(0.65)),
        new HourModel(1669482000, 28.22, new RainModel(1.17))]
        for (int i = 5; i < 48; i++) {
            int timestamp = modelList.get(i - 1).dt
            modelList.add(i, new HourModel((timestamp + 3600), 0.0, null))
        }
        return new HourlyForecastModel(modelList)
    }
}
