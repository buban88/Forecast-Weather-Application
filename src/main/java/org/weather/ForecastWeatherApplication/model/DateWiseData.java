package org.weather.ForecastWeatherApplication.model;

import java.util.List;

public class DateWiseData {
    private Long dt;
    private Temperature main;
    private List<WeatherDetails> weather;
    private Cloud clouds;
    private WindDetails wind;
    private String visibility;
    private String pop;
    private SysDetails sys;
    private String dt_txt;

    public DateWiseData() {
    }

    public Long getDt() {
        return dt;
    }

    public void setDt(Long dt) {
        this.dt = dt;
    }

    public Temperature getMain() {
        return main;
    }

    public void setMain(Temperature main) {
        this.main = main;
    }

    public List<WeatherDetails> getWeather() {
        return weather;
    }

    public void setWeather(List<WeatherDetails> weather) {
        this.weather = weather;
    }

    public Cloud getClouds() {
        return clouds;
    }

    public void setClouds(Cloud clouds) {
        this.clouds = clouds;
    }

    public WindDetails getWind() {
        return wind;
    }

    public void setWind(WindDetails wind) {
        this.wind = wind;
    }

    public String getVisibility() {
        return visibility;
    }

    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }

    public String getPop() {
        return pop;
    }

    public void setPop(String pop) {
        this.pop = pop;
    }

    public SysDetails getSys() {
        return sys;
    }

    public void setSys(SysDetails sys) {
        this.sys = sys;
    }

    public String getDt_txt() {
        return dt_txt;
    }

    public void setDt_txt(String dt_txt) {
        this.dt_txt = dt_txt;
    }

    @Override
    public String toString() {
        return "DateWiseData{" +
                "dt=" + dt +
                ", main=" + main +
                ", weather=" + weather +
                ", clouds=" + clouds +
                ", wind=" + wind +
                ", visibility='" + visibility + '\'' +
                ", pop='" + pop + '\'' +
                ", sys=" + sys +
                ", dt_txt='" + dt_txt + '\'' +
                '}';
    }
}
