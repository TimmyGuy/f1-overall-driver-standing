package Race;

import Circuit.Circuit;
import Result.Result;
import java.util.ArrayList;

public class Race {
    private int season;
    private int round;
    private Circuit circuit;
    private String date;
    private String time;
    private ArrayList<Result> results;

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

    public static Race getRace() {
        return null;
    }
}
