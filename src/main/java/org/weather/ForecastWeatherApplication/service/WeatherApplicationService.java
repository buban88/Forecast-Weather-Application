package org.weather.ForecastWeatherApplication.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.weather.ForecastWeatherApplication.builder.WeatherDataResponseBuilder;
import org.weather.ForecastWeatherApplication.model.DateWiseData;
import org.weather.ForecastWeatherApplication.model.DayWiseWeatherForecast;
import org.weather.ForecastWeatherApplication.model.WeatherForecastApiResponse;
import org.weather.ForecastWeatherApplication.model.WeatherResponse;
import org.weather.ForecastWeatherApplication.validator.RequestParameterValidator;

import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class WeatherApplicationService {

    private final PopulateMinMaxTemperature populateMinMaxTemperature;
    private final OpenWeatherApiCaller openWeatherApiCaller;
    private final WeatherDataResponseBuilder weatherDataResponseBuilder;

    private final RequestParameterValidator requestParameterValidator;

    @Value("${weather.forecast.min}")
    private Integer minRange;
    @Value("${weather.forecast.max}")
    private Integer maxRange;

    public WeatherApplicationService(OpenWeatherApiCaller openWeatherApiCaller,PopulateMinMaxTemperature populateMinMaxTemperature, WeatherDataResponseBuilder weatherDataResponseBuilder, RequestParameterValidator requestParameterValidator) {
        this.openWeatherApiCaller = openWeatherApiCaller;
        this.populateMinMaxTemperature = populateMinMaxTemperature;
        this.weatherDataResponseBuilder = weatherDataResponseBuilder;
        this.requestParameterValidator = requestParameterValidator;
    }

    public WeatherForecastApiResponse getTemperature(String transactionId, String cityName, String metricUnit) {
        requestParameterValidator.validateParameter(metricUnit);
        List<DayWiseWeatherForecast> dayWiseWeatherForecastList = new ArrayList<>();
        WeatherResponse weatherResponse = openWeatherApiCaller.getDataFromOpenWeatherApi(cityName, metricUnit);
        if (Objects.nonNull(weatherResponse)) {
            for (DateWiseData dateWiseData : weatherResponse.getList()) {
                LocalDate localDate = LocalDate.ofInstant(Instant.ofEpochSecond(dateWiseData.getDt()), ZoneId.of("UTC"));
                LocalDate localDate1 = LocalDate.now();
                Period age = Period.between(localDate1, localDate);
                if (age.getDays() >= minRange && age.getDays() < maxRange) {
                    String[] weatherForecastDateAndTime = dateWiseData.getDt_txt().split("\\s+");
                    String weatherForecastDate = weatherForecastDateAndTime[0];
                    setMinMaxTemperature(dayWiseWeatherForecastList, dateWiseData, weatherForecastDate);
                }
            }
        }
        return weatherDataResponseBuilder.responseBuilder(dayWiseWeatherForecastList, transactionId,cityName,metricUnit);
    }

    private void setMinMaxTemperature(List<DayWiseWeatherForecast> dayWiseWeatherForecastList, DateWiseData dateWiseData, String weatherForecastDate) {
        DayWiseWeatherForecast dayWiseWeatherForecastReference = null;
        for (DayWiseWeatherForecast dayWiseWeatherForecast : dayWiseWeatherForecastList) {
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

    }


}