FROM openjdk:11

ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} ForecastWeatherApplication.jar

ENTRYPOINT ["java","-jar","/forecastWeatherApplication.jar"]

EXPOSE 8761