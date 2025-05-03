package com.charity2.Service;

import com.charity2.entities.WeatherData;
import com.charity2.repository.WeatherDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.beans.factory.annotation.Value;
import java.time.LocalDate;
import java.util.Map;

@Service
public class WeatherDataService {

    @Autowired
    private WeatherDataRepository weatherDataRepository;

    @Value("${openweathermap.api.key}")
    private String apiKey;

    // Corrected API URL.  Using location ID.
    private final String WEATHER_API_URL = "https://api.openweathermap.org/data/2.5/forecast?id={locationId}&appid={apiKey}&units=metric";
    @Scheduled(fixedRate = 5000)
    public void fetchWeatherData4() {
        int locationId = 2643743;
        System.out.println("Location ID: " + locationId);
        //...
    }

    @Scheduled(fixedRate = 5000) // Runs every 5 seconds (5000 milliseconds)
    public void fetchWeatherData() {
        //  Use Location IDs, not names.
        int[] locationIds = {2643743, 2643743, 2643743}; // Example: Tunis, Sousse, Sfax.  REPLACE WITH CORRECT IDs

        for (int locationId : locationIds) {
            String url = WEATHER_API_URL.replace("{locationId}", String.valueOf(locationId)).replace("{apiKey}", apiKey);
            RestTemplate restTemplate = new RestTemplate();
            Map<String, Object> response = restTemplate.getForObject(url, Map.class);

            if (response != null && response.containsKey("list")) {
                java.util.List<Map<String, Object>> forecastList = (java.util.List<Map<String, Object>>) response.get("list");
                for (Map<String, Object> forecast : forecastList) {
                    long dt = ((Number) forecast.get("dt")).longValue();
                    LocalDate date = java.time.Instant.ofEpochSecond(dt).atZone(java.time.ZoneId.systemDefault()).toLocalDate();

                    Map<String, Object> main = (Map<String, Object>) forecast.get("main");
                    double temp = ((Number) main.get("temp")).doubleValue();

                    java.util.List<Map<String, Object>> weatherList = (java.util.List<Map<String, Object>>) forecast.get("weather");
                    String condition = (String) weatherList.get(0).get("description");

                    //check if the data already exists
                    if(!weatherDataRepository.existsByDateAndLocation(date, String.valueOf(locationId))){
                        WeatherData weatherData = new WeatherData();
                        weatherData.setDate(date);
                        weatherData.setLocation(String.valueOf(locationId));
                        weatherData.setWeatherCondition(condition);
                        weatherData.setTemperature(temp);
                        weatherDataRepository.save(weatherData);
                    }
                    // Print the weather data to the console
                    System.out.println("Location ID: " + locationId + ", Date: " + date + ", Condition: " + condition + ", Temperature: " + temp);
                }
            }
        }
    }

    public WeatherData getWeatherData(LocalDate date, String location) {
        return weatherDataRepository.findByDateAndLocation(date, location);
    }
}
