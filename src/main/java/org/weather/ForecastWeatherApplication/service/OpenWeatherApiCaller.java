package org.weather.ForecastWeatherApplication.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.weather.ForecastWeatherApplication.constants.WeatherApplicationConstants;
import org.weather.ForecastWeatherApplication.exception.OpenWeatherApiException;
import org.weather.ForecastWeatherApplication.model.WeatherDataResponse;
import org.weather.ForecastWeatherApplication.model.WeatherResponse;
import org.weather.ForecastWeatherApplication.model.error.Error;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Slf4j
public class OpenWeatherApiCaller {

    private final RestTemplate restTemplate;
    private final RetryTemplate retryTemplate;

    @Value("${openWeather.api.url}")
    private String openWeatherUrl;

    @Value("${openWeather.api.key}")
    private String openWeatherApiKey;

    private Map<String, WeatherDataResponse> weatherDataResponseMap ;

    public OpenWeatherApiCaller(RestTemplate restTemplate, RetryTemplate retryTemplate) {
        this.restTemplate = restTemplate;
        this.retryTemplate = retryTemplate;
    }

    public WeatherResponse getDataFromOpenWeatherApi(String cityName, String metricUnit) {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> request = new HttpEntity<>(headers);
        Map<String, String> uriParams = new HashMap<>();
        uriParams.put(WeatherApplicationConstants.CITY_NAME, cityName);
        uriParams.put(WeatherApplicationConstants.APP_ID, openWeatherApiKey);
        uriParams.put(WeatherApplicationConstants.COUNT, "100");
        uriParams.put(WeatherApplicationConstants.METRIC_UNIT, metricUnit);
        ResponseEntity<WeatherResponse> weatherResponseResponseEntity = fetchDataByCallingApi(request, uriParams);
        return weatherResponseResponseEntity.getBody();
    }

    public ResponseEntity<WeatherResponse> fetchDataByCallingApi(HttpEntity<String> request, Map<String, String> uriParams) {
        try {
            return retryTemplate.execute(
                    retryContext -> restTemplate.exchange(openWeatherUrl, HttpMethod.GET, request, WeatherResponse.class, uriParams));
        } catch (Exception exception) {
            log.info("Exception Happened while calling API" + exception.getMessage());
            throw new OpenWeatherApiException(buildError());
        }
    }


    public List<Error> buildError() {
        List<Error> errorList = new ArrayList<>();
        Error error = new Error();
        error.setType(WeatherApplicationConstants.INVALID_KEY);
        error.setMessage("The Key is invalid, kindly check the key");
        errorList.add(error);
        return errorList;
    }
}
