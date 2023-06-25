package org.weather.ForecastWeatherApplication.model.error;

public class Error {
    private String type;
    private String message;

    public Error() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Error{" +
                "type='" + type + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
