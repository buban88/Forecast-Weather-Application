package org.weather.ForecastWeatherApplication.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.weather.ForecastWeatherApplication.builder.WeatherForecastResponseBuilder;
import org.weather.ForecastWeatherApplication.model.DateWiseData;
import org.weather.ForecastWeatherApplication.model.WeatherResponse;
import org.weather.ForecastWeatherApplication.model.decorator.DayWiseWeather;
import org.weather.ForecastWeatherApplication.model.decorator.WeatherForecastResponse;
import org.weather.ForecastWeatherApplication.validator.RequestParameterValidator;

import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class WeatherForecastServiceWithRainDetails {

    private final PopulateMinMaxTemperature populateMinMaxTemperature;
    private final OpenWeatherApiCaller openWeatherApiCaller;
    private final WeatherForecastResponseBuilder weatherForecastResponseBuilder;

    private final RequestParameterValidator requestParameterValidator;

    @Value("${weather.forecast.min}")
    private Integer minRange;
    @Value("${weather.forecast.max}")
    private Integer maxRange;

    public WeatherForecastServiceWithRainDetails(PopulateMinMaxTemperature populateMinMaxTemperature, OpenWeatherApiCaller openWeatherApiCaller, WeatherForecastResponseBuilder weatherForecastResponseBuilder, RequestParameterValidator requestParameterValidator) {
        this.populateMinMaxTemperature = populateMinMaxTemperature;
        this.openWeatherApiCaller = openWeatherApiCaller;
        this.weatherForecastResponseBuilder = weatherForecastResponseBuilder;
        this.requestParameterValidator = requestParameterValidator;
    }

    public WeatherForecastResponse getTemperatureAndRain(String transactionId, String cityName, String metricUnit) {
        requestParameterValidator.validateParameter(metricUnit);
        List<DayWiseWeather> dayWiseWeatherList = new ArrayList<>();
        WeatherResponse weatherResponse = openWeatherApiCaller.getDataFromOpenWeatherApi(cityName, metricUnit);
        if (Objects.nonNull(weatherResponse)) {
            for (DateWiseData dateWiseData : weatherResponse.getList()) {
                LocalDate localDate = LocalDate.ofInstant(Instant.ofEpochSecond(dateWiseData.getDt()), ZoneId.of("UTC"));
                LocalDate localDate1 = LocalDate.now();
                Period age = Period.between(localDate1, localDate);
                if (age.getDays() >= minRange && age.getDays() < maxRange) {
                    String[] weatherForecastDateAndTime = dateWiseData.getDt_txt().split("\\s+");
                    String weatherForecastDate = weatherForecastDateAndTime[0];
                    setMinMaxTemperature(dayWiseWeatherList, dateWiseData, weatherForecastDate);
                }
            }
        }
        return weatherForecastResponseBuilder.responseBuilder(dayWiseWeatherList, transactionId,cityName,metricUnit);
    }


    private void setMinMaxTemperature(List<DayWiseWeather> dayWiseWeatherForecastList, DateWiseData dateWiseData, String weatherForecastDate) {
        DayWiseWeather dayWiseWeatherForecastReference = null;
        for (DayWiseWeather dayWiseWeatherForecast : dayWiseWeatherForecastList) {
            if (dayWiseWeatherForecast.getDay().equalsIgnoreCase(weatherForecastDate)) {
                dayWiseWeatherForecastReference = dayWiseWeatherForecast;
                break;
            }
        }
        if(Objects.isNull(dayWiseWeatherForecastReference)){
            dayWiseWeatherForecastList.add(populateMinMaxTemperature.populateMinMaxTemperature(dayWiseWeatherForecastReference,dateWiseData,weatherForecastDate));
        }else{
            populateMinMaxTemperature.populateMinMaxTemperature(dayWiseWeatherForecastReference,dateWiseData,weatherForecastDate);
        }
        populateMinMaxTemperature.populateRainChances(dayWiseWeatherForecastReference,dateWiseData);
    }

}
