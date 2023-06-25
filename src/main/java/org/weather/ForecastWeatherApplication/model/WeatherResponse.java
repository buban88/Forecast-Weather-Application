package org.weather.ForecastWeatherApplication.model;

import java.util.List;

public class WeatherResponse {
    private String cod;
    private String message;
    private String cnt;
    private List<DateWiseData> list;

    public WeatherResponse() {
    }

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCnt() {
        return cnt;
    }

    public void setCnt(String cnt) {
        this.cnt = cnt;
    }

    public List<DateWiseData> getList() {
        return list;
    }

    public void setList(List<DateWiseData> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "WeatherResponse{" +
                "cod='" + cod + '\'' +
                ", message='" + message + '\'' +
                ", cnt='" + cnt + '\'' +
                ", list=" + list +
                '}';
    }
}
