package org.weather.ForecastWeatherApplication.service;

import org.springframework.stereotype.Component;
import org.weather.ForecastWeatherApplication.model.DateWiseData;
import org.weather.ForecastWeatherApplication.model.DayWiseWeatherForecast;

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

}