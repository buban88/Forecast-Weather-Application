FROM openjdk:11

ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} forecastweatherapplication.jar

ENTRYPOINT ["java","-jar","/forecastweatherapplication.jar"]

EXPOSE 8081