package driver;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONArray;
import org.json.JSONObject;

public class Winner extends Driver {
    public Winner(String driverId, String givenName, String familyName, String dateOfBirth, String nationality) {
        super(driverId, givenName, familyName, dateOfBirth, nationality);
    }

    @Override
    public String getGivenName() {
        return "**" + this.givenName + "**";
    }

    public static Winner getDriver(String apiHost, String driverId) throws UnirestException {
        String query = String.format("drivers/" + driverId + ".json", "UTF-8");
        HttpResponse<JsonNode> response = Unirest.get(apiHost + query).asJson();

        JSONObject json = response.getBody().getObject();
        JSONArray drivers = json.getJSONObject("MRData").getJSONObject("DriverTable").getJSONArray("Drivers");

        if(drivers.length() == 0) {
            return null;
        }
        JSONObject driver = drivers.getJSONObject(0);
        return new Winner(
                driver.getString("driverId"),
                driver.getString("givenName"),
                driver.getString("familyName"),
                driver.getString("dateOfBirth"),
                driver.getString("nationality")
        );
    }
}
