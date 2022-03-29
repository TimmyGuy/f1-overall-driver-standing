package Result;

import Driver.Driver;

public class Result {
    private String number;
    private String position;
    private String points;
    private Driver driver;
    private String status;

    public Result(String number, String position, String points, Driver driver, String status) {
        this.number = number;
        this.position = position;
        this.points = points;
        this.driver = driver;
        this.status = status;
    }

    public String getNumber() {
        return number;
    }

    public String getPosition() {
        return position;
    }

    public String getPoints() {
        return points;
    }

    public Driver getDriver() {
        return driver;
    }

    public String getStatus() {
        return status;
    }
}
