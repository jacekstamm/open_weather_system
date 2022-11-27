# Open Weather Forecast App

## Requirements:

1) Java 11
2) Gradle

## How to build application

In terminal/console enter `gradle build`

## How to run application

You need to go to directory where you have application folder. In this place please open terminal/console and
enter `gradle run`

Other option is to open project in you favorite IDE and in terminal/console either `gradle run`

## How to run tests

Please in terminal/console paste `gradle test` to run all tests.

## How to use application

When application is running you will be need provide 4 arguments to System to get forecast result. You will need to
prepare:

1) API key - Provided on [OpenWeather website](https://openweathermap.org/)
2) Path to configuration file
3) Forecast type - `daily` or `hourly`
4) Name of the city - which is included in configuration file

Arguments need to be in order with comma separator! Example:
`63hff8561917746476285fab,config.txt,daily,Berlin`

After application return Forecast results you can choose between exit program or get next forecast.

## Configuration file

1) Need to be with .txt extension

Example of content:

````
Warsaw,52.2297,21.0122
Dublin,53.3498,-6.2603
Buenos Aires,-34.6037,-58.3816
````
