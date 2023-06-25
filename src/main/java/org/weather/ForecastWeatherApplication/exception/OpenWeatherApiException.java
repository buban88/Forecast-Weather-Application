package org.weather.ForecastWeatherApplication.exception;

import org.weather.ForecastWeatherApplication.model.error.Error;

import java.util.List;

public class OpenWeatherApiException extends RuntimeException {
    private final transient List<Error> errorTypes;

    public OpenWeatherApiException(List<Error> errorTypes){
        super();
        this.errorTypes = errorTypes;
    }

    public List<Error> getErrors(){return errorTypes;}
}
