package Driver;

import Race.Race;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Driver {
    private String driverId;
    private String givenName;
    private String familyName;
    private String dateOfBirth;
    private String nationality;
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
        JSONObject driver = json.getJSONObject("Drivers");

        System.out.println(response.getBody());

        return null;
    }
}
