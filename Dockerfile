FROM openjdk:11

ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} ForecastWeatherApplication.jar

ENTRYPOINT ["java","-jar","/ForecastWeatherApplication.jar"]

EXPOSE 8761