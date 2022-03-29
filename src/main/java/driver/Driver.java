package driver;

import race.Race;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class Driver {
    private final String driverId;
    private final String givenName;
    private final String familyName;
    private final String dateOfBirth;
    private final String nationality;
    private ArrayList<Race> drivenRaces;

    public Driver(String driverId, String givenName, String familyName, String dateOfBirth, String nationality) {
        this.driverId = driverId;
        this.givenName = givenName;
        this.familyName = familyName;
        this.dateOfBirth = dateOfBirth;
        this.nationality = nationality;
    }

    public String getDriverId() {
        return driverId;
    }

    public String getGivenName() {
        return givenName;
    }

    public String getFamilyName() {
        return familyName;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public String getNationality() {
        return nationality;
    }

    public ArrayList<Race> getDrivenRaces() {
        return drivenRaces;
    }

    public static Driver getDriver(String apiHost, String driverId) throws UnirestException {
        String query = String.format("drivers/" + driverId + ".json", "UTF-8");
        HttpResponse<JsonNode> response = Unirest.get(apiHost + query).asJson();

        JSONObject json = response.getBody().getObject();
        JSONArray drivers = json.getJSONObject("MRData").getJSONObject("DriverTable").getJSONArray("Drivers");

        if(drivers.length() == 0) {
            return null;
        }
        JSONObject driver = drivers.getJSONObject(0);
        return new Driver(
                driver.getString("driverId"),
                driver.getString("givenName"),
                driver.getString("familyName"),
                driver.getString("dateOfBirth"),
                driver.getString("nationality")
        );
    }

    @Override
    public String toString() {
        return "Driver{" +
                "driverId='" + driverId + '\'' +
                ", givenName='" + givenName + '\'' +
                ", familyName='" + familyName + '\'' +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                ", nationality='" + nationality + '\'' +
                ", drivenRaces=" + drivenRaces +
                '}';
    }
}
