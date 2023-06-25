package org.weather.ForecastWeatherApplication.builder;

import org.weather.ForecastWeatherApplication.model.WeatherDataResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.weather.ForecastWeatherApplication.model.DayWiseDetails;

import java.time.Instant;
import java.util.List;

@Component
public class WeatherDataResponseBuilder {

    public WeatherDataResponse responseBuilder(List<DayWiseDetails> dayWiseDetailsList, String transactionId, String cityName, String metricUnit){
        WeatherDataResponse weatherDataResponse = new WeatherDataResponse();
        weatherDataResponse.setTransactionId(transactionId);
        weatherDataResponse.setStatus(HttpStatus.OK.value());
        weatherDataResponse.setTemperatureMeasurement(getMeasurementUnit(metricUnit));
        weatherDataResponse.setCityName(cityName);
        weatherDataResponse.setTimeStamp(Instant.now().toString());
        weatherDataResponse.setDayWiseDetailsList(dayWiseDetailsList);
        return weatherDataResponse;

    }

    private String getMeasurementUnit(String metricUnit){
        if(metricUnit.equalsIgnoreCase("metric")){
            return "Celsius";
        }else if(metricUnit.equalsIgnoreCase("imperial")){
            return "Fahrenheit ";
        }else
            return "Kelvin";
    }
}
