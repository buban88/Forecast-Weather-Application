package org.weather.ForecastWeatherApplication.service;

import org.springframework.stereotype.Component;
import org.weather.ForecastWeatherApplication.model.DayWiseDetails;

@Component
public class PopulateMinMaxTemperature {

    public void populateMinMaxTemperature(DayWiseDetails dayWiseDetails, Double minTemperature, Double maxTemperature){
        dayWiseDetails.setMaxTemperature(Math.max(dayWiseDetails.getMaxTemperature(),maxTemperature));
        dayWiseDetails.setMinTemperature(Math.min(dayWiseDetails.getMinTemperature(),minTemperature));
    }

}