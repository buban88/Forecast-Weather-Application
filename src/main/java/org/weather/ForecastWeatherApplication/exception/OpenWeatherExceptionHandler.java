package org.weather.ForecastWeatherApplication.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.weather.ForecastWeatherApplication.model.WeatherDataResponse;
import org.weather.ForecastWeatherApplication.model.error.Error;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;
import java.util.List;

@ControllerAdvice
public class OpenWeatherExceptionHandler {

    @ExceptionHandler(value = OpenWeatherApiException.class)
    public ResponseEntity<WeatherDataResponse> handleOpenWeatherApiException(
            HttpServletRequest request, OpenWeatherApiException openWeatherApiException) {
        WeatherDataResponse response =
                createResponse(request, openWeatherApiException.getErrors(), HttpStatus.INTERNAL_SERVER_ERROR);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = InvalidRequestParameterException.class)
    public ResponseEntity<WeatherDataResponse> handleOpenWeatherApiException(
            HttpServletRequest request, InvalidRequestParameterException openWeatherApiException) {
        WeatherDataResponse response =
                createResponse(request, openWeatherApiException.getErrors(), HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    private WeatherDataResponse createResponse(
            HttpServletRequest request, List<Error> errorTypes, HttpStatus errorType) {
        WeatherDataResponse response = new WeatherDataResponse();
        response.setTransactionId(request.getHeader("transactionId"));
        response.setTimeStamp(Instant.now().toString());
        response.setStatus(errorType.value());
        response.setErrorList(errorTypes);
        return response;
    }

}

