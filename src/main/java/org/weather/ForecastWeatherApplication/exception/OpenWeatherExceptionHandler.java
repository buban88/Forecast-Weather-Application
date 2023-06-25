package org.weather.ForecastWeatherApplication.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.weather.ForecastWeatherApplication.model.WeatherForecastApiResponse;
import org.weather.ForecastWeatherApplication.model.error.Error;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;
import java.util.List;

@ControllerAdvice
public class OpenWeatherExceptionHandler {

    @ExceptionHandler(value = OpenWeatherApiException.class)
    public ResponseEntity<WeatherForecastApiResponse> handleOpenWeatherApiException(
            HttpServletRequest request, OpenWeatherApiException openWeatherApiException) {
        WeatherForecastApiResponse response =
                createResponse(request, openWeatherApiException.getErrors(), HttpStatus.INTERNAL_SERVER_ERROR);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = InvalidRequestParameterException.class)
    public ResponseEntity<WeatherForecastApiResponse> handleOpenWeatherApiException(
            HttpServletRequest request, InvalidRequestParameterException openWeatherApiException) {
        WeatherForecastApiResponse response =
                createResponse(request, openWeatherApiException.getErrors(), HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    private WeatherForecastApiResponse createResponse(
            HttpServletRequest request, List<Error> errorTypes, HttpStatus errorType) {
        WeatherForecastApiResponse response = new WeatherForecastApiResponse();
        response.setTransactionId(request.getHeader("transactionId"));
        response.setTimeStamp(Instant.now().toString());
        response.setStatus(errorType.value());
        response.setErrorList(errorTypes);
        return response;
    }

}

