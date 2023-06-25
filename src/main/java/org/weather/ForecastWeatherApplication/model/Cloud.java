package org.weather.ForecastWeatherApplication.model;

public class Cloud {
    private int all;

    public Cloud() {
    }

    public int getAll() {
        return all;
    }

    public void setAll(int all) {
        this.all = all;
    }

    @Override
    public String toString() {
        return "Cloud{" +
                "all=" + all +
                '}';
    }
}
