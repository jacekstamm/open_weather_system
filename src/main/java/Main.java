import dto.DailyForecastDto;
import dto.HourlyForecastDto;
import java.util.Scanner;
import mapper.ForecastMapper;
import service.FileReaderService;
import service.WeatherService;
import webClient.OpenWeatherWebClient;

public class Main {
    final WeatherService weatherService = new WeatherService(new OpenWeatherWebClient(), new FileReaderService(), new ForecastMapper());

    public static void main(String[] args) {
        Main main = new Main();
        System.out.println("Welcome to OpenWeather forecast handler.");
        main.forecast();
    }

    private void forecast() {
        Scanner scan = new Scanner(System.in);
        initPrint();
        String requestString = scan.nextLine();
        String[] requestArray = requestString.split(",");
        weatherService.validateForecastRequest(requestArray);
        if (requestArray[2].equals("daily")) {
            DailyForecastDto result = weatherService.getDailyForecast(requestArray[0], requestArray[1], requestArray[3]);
            System.out.println(result);
        } else {
            HourlyForecastDto result = weatherService.getHourlyForecast(requestArray[0], requestArray[1], requestArray[3]);
            System.out.println(result);
        }
        nextForecastInit(scan);
    }

    private void nextForecastInit(Scanner scan) {
        System.out.println("Do you want to check next forecast?");
        System.out.println("If <YES> write Y, if <NO> type whatever you want to exit.");
        String onceAgain = scan.next();
        if (onceAgain.equals("Y")) {
            forecast();
        }
    }

    private static void initPrint() {
        System.out.println("Please enter 4 arguments with comma separator");
        System.out.println("Right now you can choose between 2 types of forecast. Daily and Hourly");
        System.out.println("API Key,Config file name,forecast type,city name");
    }
}