package org.weather.ForecastWeatherApplication.model;

public class DayWiseWeatherForecast {
    private String day;
    private Double maxTemperature ;
    private Double minTemperature ;

    private Boolean chancesOfRain;

    public DayWiseWeatherForecast() {
    }

    public Boolean getChancesOfRain() {
        if(this.chancesOfRain == null)
             return false;
        return chancesOfRain;
    }

    public void setChancesOfRain(Boolean chancesOfRain) {
        this.chancesOfRain = chancesOfRain;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public Double getMaxTemperature() {
        if(this.maxTemperature==null)
            return 0.0;
        return maxTemperature;
    }

    public void setMaxTemperature(Double maxTemperature) {
        this.maxTemperature = maxTemperature;
    }

    public Double getMinTemperature() {
        if(this.minTemperature==null)
            return Double.MAX_VALUE;
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
