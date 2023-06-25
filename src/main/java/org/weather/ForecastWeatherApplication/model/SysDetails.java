package org.weather.ForecastWeatherApplication.model;

public class SysDetails {
    private String pod;

    public SysDetails() {
    }

    public String getPod() {
        return pod;
    }

    public void setPod(String pod) {
        this.pod = pod;
    }

    @Override
    public String toString() {
        return "SysDetails{" +
                "pod='" + pod + '\'' +
                '}';
    }
}
