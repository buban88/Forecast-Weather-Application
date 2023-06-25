package org.weather.ForecastWeatherApplication.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.weather.ForecastWeatherApplication.model.WeatherDataResponse;
import org.weather.ForecastWeatherApplication.service.WeatherApplicationService;

@RestController
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
    public ResponseEntity<WeatherDataResponse> getTemperatureForecast(
            @RequestHeader String transactionId, @RequestParam String cityName, @RequestParam String metricUnit){

        return ResponseEntity.ok(weatherApplicationService.getTemperature(transactionId,cityName,metricUnit));
    }

}
