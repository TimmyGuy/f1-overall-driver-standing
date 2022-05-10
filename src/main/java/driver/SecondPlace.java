package driver;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONArray;
import org.json.JSONObject;

public class SecondPlace extends Driver{
    public SecondPlace(String driverId, String givenName, String familyName, String dateOfBirth, String nationality) {
        super(driverId, givenName, familyName, dateOfBirth, nationality);
    }

    @Override
    public String getPrice() {
        return "Zilver & 50.000 EUR";
    }

    public static SecondPlace getDriver(String apiHost, String driverId) throws UnirestException {
        JSONArray drivers = getDriversArray(apiHost, driverId);
        if (drivers == null) return null;
        JSONObject driver = drivers.getJSONObject(0);
        return new SecondPlace(
                driver.getString("driverId"),
                driver.getString("givenName"),
                driver.getString("familyName"),
                driver.getString("dateOfBirth"),
                driver.getString("nationality")
        );
    }
}
