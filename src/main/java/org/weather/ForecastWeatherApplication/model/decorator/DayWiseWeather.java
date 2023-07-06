package org.weather.ForecastWeatherApplication.model.decorator;

import java.util.ArrayList;
import java.util.List;

public class DayWiseWeather extends Weather{
    @Override
    public Double getMaxTemperature() {
        if(this.maxTemperature==null)
            return 0.0;
        return maxTemperature;
    }

    @Override
    public Double getMinTemperature() {
        if(this.minTemperature==null)
            return Double.MAX_VALUE;
        return minTemperature;
    }

    @Override
    public String getDay() {
        return day;
    }

    @Override
    public List<String> getAdditionalDetails() {
        if(this.additionalDetails == null)
            return new ArrayList<>();
        return this.additionalDetails;
    }


}
