package org.weather.ForecastWeatherApplication.service

import org.weather.ForecastWeatherApplication.model.DateWiseData
import org.weather.ForecastWeatherApplication.model.DayWiseWeatherForecast
import org.weather.ForecastWeatherApplication.model.Temperature
import org.weather.ForecastWeatherApplication.model.WeatherDetails
import spock.lang.Specification

class PopulateMinMaxTemperatureTest extends Specification {

    def classUnderTest = new PopulateMinMaxTemperature()

    def"Test when this is the first item"(){
        when:
        def dayWiseWeatherForecast = classUnderTest.populateMinMaxTemperature(null,buildDayWiseData(),"26-09-2023")
        then:
        dayWiseWeatherForecast.maxTemperature == 20.0
        dayWiseWeatherForecast.minTemperature == 19.0
    }

    def"Test when this is the consecutive item"(){
        when:
        def dayWiseWeatherForecast = classUnderTest.populateMinMaxTemperature(buildDayWiseWeatherForecast(),buildDayWiseData(),"26-09-2023")
        then:
        dayWiseWeatherForecast.maxTemperature == 23.0
        dayWiseWeatherForecast.minTemperature == 15.0
    }



    def buildDayWiseData(){
        new DateWiseData(
                weather: [
                        new WeatherDetails(

                        )
                ],
                main: new Temperature(
                        temp_max: 20.0,
                        temp_min: 19.0
                ),
                pop: 0
        )
    }

    def buildDayWiseWeatherForecast(){
        new DayWiseWeatherForecast(
                day: "26-09-2023",
                maxTemperature: 23.0,
                minTemperature: 15.0,
                chancesOfRain: true
        )
    }

}
