package org.weather.ForecastWeatherApplication.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.weather.ForecastWeatherApplication.builder.WeatherDataResponseBuilder;
import org.weather.ForecastWeatherApplication.model.DateWiseData;
import org.weather.ForecastWeatherApplication.model.DayWiseDetails;
import org.weather.ForecastWeatherApplication.model.WeatherDataResponse;
import org.weather.ForecastWeatherApplication.model.WeatherResponse;

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

    @Value("${weather.forecast.min}")
    private Integer minRange;
    @Value("${weather.forecast.max}")
    private Integer maxRange;

    public WeatherApplicationService(OpenWeatherApiCaller openWeatherApiCaller,PopulateMinMaxTemperature populateMinMaxTemperature, WeatherDataResponseBuilder weatherDataResponseBuilder) {
        this.openWeatherApiCaller = openWeatherApiCaller;
        this.populateMinMaxTemperature = populateMinMaxTemperature;
        this.weatherDataResponseBuilder = weatherDataResponseBuilder;
    }

    public WeatherDataResponse getTemperature(String transactionId, String cityName, String metricUnit) {
        List<DayWiseDetails> dayWiseDetailsList = new ArrayList<>();
        WeatherResponse weatherResponse = openWeatherApiCaller.getDataFromOpenWeatherApi(cityName, metricUnit);
        if (Objects.nonNull(weatherResponse)) {
            for (DateWiseData dateWiseData : weatherResponse.getList()) {
                LocalDate localDate = LocalDate.ofInstant(Instant.ofEpochSecond(dateWiseData.getDt()), ZoneId.of("UTC"));
                LocalDate localDate1 = LocalDate.now();
                Period age = Period.between(localDate1, localDate);
                if (age.getDays() >= minRange && age.getDays() < maxRange) {
                    String[] weatherForecastDateAndTime = dateWiseData.getDt_txt().split("\\s+");
                    String weatherForecastDate = weatherForecastDateAndTime[0];
                    setMinMaxTemperature(dayWiseDetailsList, dateWiseData, weatherForecastDate);
                }
            }
        }
        return weatherDataResponseBuilder.responseBuilder(dayWiseDetailsList, transactionId,cityName,metricUnit);
    }

    private void setMinMaxTemperature(List<DayWiseDetails> dayWiseDetailsList, DateWiseData dateWiseData, String weatherForecastDate) {
        DayWiseDetails dayWiseDetailsReference = null;
        for (DayWiseDetails dayWiseDetails : dayWiseDetailsList) {
            if (dayWiseDetails.getDay().equalsIgnoreCase(weatherForecastDate)) {
                dayWiseDetailsReference = dayWiseDetails;
                break;
            }
        }
        if (Objects.isNull(dayWiseDetailsReference)) {
            dayWiseDetailsReference = new DayWiseDetails();
            dayWiseDetailsReference.setDay(weatherForecastDate);
            dayWiseDetailsReference.setMaxTemperature(dateWiseData.getMain().getTemp_max());
            dayWiseDetailsReference.setMinTemperature(dateWiseData.getMain().getTemp_min());
            dayWiseDetailsList.add(dayWiseDetailsReference);
        } else {
            populateMinMaxTemperature.populateMinMaxTemperature(dayWiseDetailsReference, dateWiseData.getMain().getTemp_min(), dateWiseData.getMain().getTemp_max());
        }
    }


}