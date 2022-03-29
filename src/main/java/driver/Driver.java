package driver;

import circuit.Circuit;
import race.Race;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONArray;
import org.json.JSONObject;
import result.Result;

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

    public ArrayList<Race> getDrivenRaces() throws UnirestException {
        if(drivenRaces == null) {
            drivenRaces = new ArrayList<>();

            String query = String.format("drivers/" + driverId + "/results.json", "UTF-8");
            HttpResponse<JsonNode> response = Unirest.get("http://ergast.com/api/f1/" + query).asJson();

            JSONObject json = response.getBody().getObject();

            JSONArray races = json.getJSONObject("MRData").getJSONObject("RaceTable").getJSONArray("Races");

            for(int i = 0; i < races.length(); i++) {
                JSONObject race = races.getJSONObject(i);
                JSONObject c = race.getJSONObject("Circuit");
                JSONArray r = race.getJSONArray("Results");

                Circuit circuit = new Circuit(c.getString("circuitId"), c.getString("circuitName"), c.getJSONObject("Location").getString("country"));

                ArrayList<Result> results = new ArrayList<>();

                for (int j = 0; j < r.length(); j++) {
                    JSONObject result = r.getJSONObject(j);
                    results.add(
                            new Result(
                                    result.getString("number"),
                                    result.getString("position"),
                                    result.getString("points"),
                                    this,
                                    result.getString("status")
                            )
                    );
                }

                drivenRaces.add(new Race(
                        race.getInt("season"),
                        race.getInt("round"),
                        circuit,
                        race.getString("date"),
                        null,
                        results
                ));
            }
        }

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
