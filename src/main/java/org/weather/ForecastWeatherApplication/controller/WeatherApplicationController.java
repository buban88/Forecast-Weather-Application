package org.weather.ForecastWeatherApplication.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.weather.ForecastWeatherApplication.model.WeatherForecastApiResponse;
import org.weather.ForecastWeatherApplication.model.decorator.WeatherForecastResponse;
import org.weather.ForecastWeatherApplication.service.WeatherApplicationService;
import org.weather.ForecastWeatherApplication.service.WeatherForecastService;
import org.weather.ForecastWeatherApplication.service.WeatherForecastServiceWithRainDetails;

@RestController
@RequestMapping("/weather")
public class WeatherApplicationController {

    private final WeatherApplicationService weatherApplicationService;
    private final WeatherForecastService weatherForecastService;

    private final WeatherForecastServiceWithRainDetails weatherForecastServiceWithRainDetails;

    public WeatherApplicationController(WeatherApplicationService weatherApplicationService,WeatherForecastService weatherForecastService,WeatherForecastServiceWithRainDetails weatherForecastServiceWithRainDetails) {
        this.weatherApplicationService = weatherApplicationService;
        this.weatherForecastService = weatherForecastService;
        this.weatherForecastServiceWithRainDetails = weatherForecastServiceWithRainDetails;
    }

    @GetMapping("/hello")
    public String sayHello(){
        return "Hello";
    }

    @GetMapping("/weatherForecast")
    public ResponseEntity<WeatherForecastApiResponse> getTemperatureForecast(
            @RequestHeader String transactionId, @RequestParam String cityName, @RequestParam String metricUnit){

        return ResponseEntity.ok(weatherApplicationService.getTemperature(transactionId,cityName,metricUnit));
    }

    @GetMapping("/weatherFore")
    public ResponseEntity<WeatherForecastResponse> getForeCastedTemperature(
            @RequestHeader String transactionId, @RequestParam String cityName, @RequestParam String metricUnit){

        return ResponseEntity.ok(weatherForecastService.getTemperature(transactionId,cityName,metricUnit));
    }

    @GetMapping("/weatherForeWithRain")
    public ResponseEntity<WeatherForecastResponse> getForeCastedTemperatureAndRain(
            @RequestHeader String transactionId, @RequestParam String cityName, @RequestParam String metricUnit){

        return ResponseEntity.ok(weatherForecastServiceWithRainDetails.getTemperatureAndRain(transactionId,cityName,metricUnit));
    }


}
