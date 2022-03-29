package race;

import circuit.Circuit;
import driver.Driver;
import result.Result;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class Race {
    private final int season;
    private final int round;
    private final Circuit circuit;
    private final String date;
    private final String time;
    private final ArrayList<Result> results;

    public Race(int season, int round, Circuit circuit, String date, String time, ArrayList<Result> results) {
        this.season = season;
        this.round = round;
        this.circuit = circuit;
        this.date = date;
        this.time = time;
        this.results = results;
    }

    public int getSeason() {
        return season;
    }

    public int getRound() {
        return round;
    }

    public Circuit getCircuit() {
        return circuit;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public ArrayList<Result> getResults() {
        return results;
    }

    public static Race getRace(String apiHost, int season, int round) throws UnirestException {
        String query = String.format(season + "/" + round + "/results.json", "UTF-8");
        HttpResponse<JsonNode> response = Unirest.get(apiHost + query).asJson();

        JSONObject json = response.getBody().getObject();
        JSONArray races = json.getJSONObject("MRData").getJSONObject("RaceTable").getJSONArray("Races");

        if (races.length() == 0) return null;

        JSONObject race = races.getJSONObject(0);
        JSONObject c = race.getJSONObject("circuit");
        JSONArray r = race.getJSONArray("Results");

        Circuit circuit = new Circuit(c.getString("circuitId"), c.getString("circuitName"), c.getJSONObject("Location").getString("country"));

        ArrayList<Result> results = new ArrayList<>();

        for (int i = 0; i < r.length(); i++) {
            JSONObject result = r.getJSONObject(i);
            results.add(
                    new Result(
                            result.getString("number"),
                            result.getString("position"),
                            result.getString("points"),
                            Driver.getDriver(apiHost, result.getJSONObject("driver").getString("driverId")),
                            result.getString("status")
                    )
            );
        }

        return new Race(
                season,
                round,
                circuit,
                race.getString("date"),
                race.getString("time"),
                results
        );
    }

    @Override
    public String toString() {
        return "Race{" +
                "season=" + season +
                ", round=" + round +
                ", circuit=" + circuit +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
}
