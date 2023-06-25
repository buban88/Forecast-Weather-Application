package org.weather.ForecastWeatherApplication.model;

public class DayWiseDetails {
    private String day;
    private Double maxTemperature ;
    private Double minTemperature ;

    public DayWiseDetails() {
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public Double getMaxTemperature() {
        return maxTemperature;
    }

    public void setMaxTemperature(Double maxTemperature) {
        this.maxTemperature = maxTemperature;
    }

    public Double getMinTemperature() {
        return minTemperature;
    }

    public void setMinTemperature(Double minTemperature) {
        this.minTemperature = minTemperature;
    }

    @Override
    public String toString() {
        return "DayWiseDetails{" +
                "day='" + day + '\'' +
                ", maxTemperature=" + maxTemperature +
                ", minTemperature=" + minTemperature +
                '}';
    }
}
