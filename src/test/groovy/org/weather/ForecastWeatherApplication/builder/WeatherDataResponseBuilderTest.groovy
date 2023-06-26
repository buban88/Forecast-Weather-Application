import org.weather.ForecastWeatherApplication.builder.WeatherDataResponseBuilder
import org.weather.ForecastWeatherApplication.model.DayWiseWeatherForecast
import spock.lang.Specification

class WeatherDataResponseBuilderTest extends Specification{

    private classUnderTest = new WeatherDataResponseBuilder()

    def "Test whether Weather Api Response is constructed"(){
        when:
        def weatherResponse = classUnderTest.responseBuilder(buildDayWiseData(),"122222","London","metric")
        then:
        weatherResponse.cityName == 'London'
        weatherResponse.dayWiseDetailsList.get(0).maxTemperature == buildDayWiseData().get(0).getMaxTemperature()
    }



    def buildDayWiseData(){
        return [

                    new DayWiseWeatherForecast(
                          day: "27-09-2023",
                            maxTemperature: 23.0,
                            minTemperature: 19.0,
                            chancesOfRain: true
                    ),
                    new DayWiseWeatherForecast(
                            day: "28-09-2023",
                            maxTemperature: 27.0,
                            minTemperature: 22.0,
                            chancesOfRain: false
                    )

        ]
    }

}