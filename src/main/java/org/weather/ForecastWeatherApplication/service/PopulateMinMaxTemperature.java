package org.weather.ForecastWeatherApplication.service;

import org.springframework.stereotype.Component;
import org.weather.ForecastWeatherApplication.model.DateWiseData;
import org.weather.ForecastWeatherApplication.model.DayWiseDetails;

import java.util.Objects;

@Component
public class PopulateMinMaxTemperature {

    public DayWiseDetails populateMinMaxTemperature(DayWiseDetails dayWiseDetails, DateWiseData dateWiseData, String weatherForecastDate){
      if(Objects.isNull(dayWiseDetails)){
          dayWiseDetails = new DayWiseDetails();
          dayWiseDetails.setDay(weatherForecastDate);
      }
        dayWiseDetails.setMaxTemperature(Math.max(dayWiseDetails.getMaxTemperature(),dateWiseData.getMain().getTemp_max()));
        dayWiseDetails.setMinTemperature(Math.min(dayWiseDetails.getMinTemperature(),dateWiseData.getMain().getTemp_min()));
        if(!dayWiseDetails.getChancesOfRain()){
            dayWiseDetails.setChancesOfRain(!dateWiseData.getPop().equalsIgnoreCase("0"));

        }
        return dayWiseDetails;
    }

}