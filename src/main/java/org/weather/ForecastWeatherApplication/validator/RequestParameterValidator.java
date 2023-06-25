package org.weather.ForecastWeatherApplication.validator;

import org.springframework.stereotype.Component;
import org.weather.ForecastWeatherApplication.constants.WeatherApplicationConstants;
import org.weather.ForecastWeatherApplication.exception.InvalidRequestParameterException;
import org.weather.ForecastWeatherApplication.model.error.Error;

import java.util.ArrayList;
import java.util.List;

@Component
public class RequestParameterValidator {
    public void validateParameter(String metricUnit){
        if(!metricUnit.equalsIgnoreCase("metric")&&!metricUnit.equalsIgnoreCase("standard")&&!metricUnit.equalsIgnoreCase("imperial")){
            throw new InvalidRequestParameterException(buildError());
        }
    }

    public List<Error> buildError() {
        List<Error> errorList = new ArrayList<>();
        Error error = new Error();
        error.setType(WeatherApplicationConstants.INVALID_PARAMETER);
        error.setMessage("The metric unit is invalid, kindly enter either metric,standard or imperial");
        errorList.add(error);
        return errorList;
    }
}
