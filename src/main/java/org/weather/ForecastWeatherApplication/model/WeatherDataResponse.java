package org.weather.ForecastWeatherApplication.model;

import org.weather.ForecastWeatherApplication.model.error.Error;

import java.util.List;

public class WeatherDataResponse {
    private String transactionId;
    private Integer status;
    private String timeStamp;
    private String temperatureMeasurement;
    private String cityName;
    private List<DayWiseDetails> dayWiseDetailsList;
    private List<Error> errorList;

    public WeatherDataResponse() {
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getTemperatureMeasurement() {
        return temperatureMeasurement;
    }

    public void setTemperatureMeasurement(String temperatureMeasurement) {
        this.temperatureMeasurement = temperatureMeasurement;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public List<DayWiseDetails> getDayWiseDetailsList() {
        return dayWiseDetailsList;
    }

    public void setDayWiseDetailsList(List<DayWiseDetails> dayWiseDetailsList) {
        this.dayWiseDetailsList = dayWiseDetailsList;
    }

    public List<Error> getErrorList() {
        return errorList;
    }

    public void setErrorList(List<Error> errorList) {
        this.errorList = errorList;
    }

    @Override
    public String toString() {
        return "WeatherDataResponse{" +
                "transactionId='" + transactionId + '\'' +
                ", status=" + status +
                ", timeStamp='" + timeStamp + '\'' +
                ", temperatureMeasurement='" + temperatureMeasurement + '\'' +
                ", cityName='" + cityName + '\'' +
                ", dayWiseDetailsList=" + dayWiseDetailsList +
                ", errorList=" + errorList +
                '}';
    }
}
