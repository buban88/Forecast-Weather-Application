package org.weather.ForecastWeatherApplication.service;

import org.springframework.stereotype.Component;
import org.weather.ForecastWeatherApplication.model.DateWiseData;
import org.weather.ForecastWeatherApplication.model.DayWiseWeatherForecast;
import org.weather.ForecastWeatherApplication.model.decorator.DayWiseWeather;

import java.util.Objects;

@Component
public class PopulateMinMaxTemperature {

    public DayWiseWeatherForecast populateMinMaxTemperature(DayWiseWeatherForecast dayWiseWeatherForecast, DateWiseData dateWiseData, String weatherForecastDate){
      if(Objects.isNull(dayWiseWeatherForecast)){
          dayWiseWeatherForecast = new DayWiseWeatherForecast();
          dayWiseWeatherForecast.setDay(weatherForecastDate);
      }
        dayWiseWeatherForecast.setMaxTemperature(Math.max(dayWiseWeatherForecast.getMaxTemperature(),dateWiseData.getMain().getTemp_max()));
        dayWiseWeatherForecast.setMinTemperature(Math.min(dayWiseWeatherForecast.getMinTemperature(),dateWiseData.getMain().getTemp_min()));
        if(!dayWiseWeatherForecast.getChancesOfRain()){
            dayWiseWeatherForecast.setChancesOfRain(!dateWiseData.getPop().equalsIgnoreCase("0"));

        }
        return dayWiseWeatherForecast;
    }

    public DayWiseWeather populateMinMaxTemperature(DayWiseWeather dayWiseWeatherForecast, DateWiseData dateWiseData, String weatherForecastDate){
        if(Objects.isNull(dayWiseWeatherForecast)){
            dayWiseWeatherForecast = new DayWiseWeather();
            dayWiseWeatherForecast.setDay(weatherForecastDate);
        }
        dayWiseWeatherForecast.setMaxTemperature(Math.max(dayWiseWeatherForecast.getMaxTemperature(),dateWiseData.getMain().getTemp_max()));
        dayWiseWeatherForecast.setMinTemperature(Math.min(dayWiseWeatherForecast.getMinTemperature(),dateWiseData.getMain().getTemp_min()));
        return dayWiseWeatherForecast;
    }

    public void populateRainChances(DayWiseWeather dayWiseWeatherForecast, DateWiseData dateWiseData){
       if(dateWiseData.getPop().equalsIgnoreCase("0")){
           dayWiseWeatherForecast.setAdditionalDetails("Please carry an Umbrella");
       }
    }

}