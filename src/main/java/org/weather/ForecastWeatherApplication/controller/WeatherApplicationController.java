package org.weather.ForecastWeatherApplication.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.weather.ForecastWeatherApplication.model.WeatherForecastApiResponse;
import org.weather.ForecastWeatherApplication.service.WeatherApplicationService;

@RestController
@RequestMapping("/weather")
public class WeatherApplicationController {

    private final WeatherApplicationService weatherApplicationService;

    public WeatherApplicationController(WeatherApplicationService weatherApplicationService) {
        this.weatherApplicationService = weatherApplicationService;
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

}
