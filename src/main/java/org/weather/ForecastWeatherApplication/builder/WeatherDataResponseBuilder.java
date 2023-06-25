package org.weather.ForecastWeatherApplication.builder;

import org.weather.ForecastWeatherApplication.model.WeatherForecastApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.weather.ForecastWeatherApplication.model.DayWiseWeatherForecast;

import java.time.Instant;
import java.util.List;

@Component
public class WeatherDataResponseBuilder {

    public WeatherForecastApiResponse responseBuilder(List<DayWiseWeatherForecast> dayWiseWeatherForecastList, String transactionId, String cityName, String metricUnit){
        WeatherForecastApiResponse weatherForecastApiResponse = new WeatherForecastApiResponse();
        weatherForecastApiResponse.setTransactionId(transactionId);
        weatherForecastApiResponse.setStatus(HttpStatus.OK.value());
        weatherForecastApiResponse.setTemperatureMeasurement(getMeasurementUnit(metricUnit));
        weatherForecastApiResponse.setCityName(cityName);
        weatherForecastApiResponse.setTimeStamp(Instant.now().toString());
        weatherForecastApiResponse.setDayWiseDetailsList(dayWiseWeatherForecastList);
        return weatherForecastApiResponse;

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
