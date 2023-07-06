package org.weather.ForecastWeatherApplication.model.decorator;

import java.util.ArrayList;
import java.util.List;

public abstract class Weather {
    String day;
    Double maxTemperature ;
    Double minTemperature ;

    List<String> additionalDetails;

    public void setDay(String day) {
        this.day = day;
    }

    public void setMaxTemperature(Double maxTemperature) {
        this.maxTemperature = maxTemperature;
    }

    public void setMinTemperature(Double minTemperature) {
        this.minTemperature = minTemperature;
    }

    public void setAdditionalDetails(String additionalDetails) {
        if(this.additionalDetails == null){
            this.additionalDetails = new ArrayList<>();
            this.additionalDetails.add(additionalDetails);
        }
        this.getAdditionalDetails().add(additionalDetails);
    }

    public abstract Double getMaxTemperature();
   public abstract Double getMinTemperature();
   public abstract String getDay();

   public abstract List<String> getAdditionalDetails();
}
