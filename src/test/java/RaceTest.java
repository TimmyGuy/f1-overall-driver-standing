import Driver.Driver;
import Race.Race;
import com.mashape.unirest.http.exceptions.UnirestException;
import junit.framework.TestCase;

public class RaceTest extends TestCase {
    public void testAnExistingRace() throws UnirestException {
        Race race = Race.getRace("http://ergast.com/api/f1/", 2018, 5);

        System.out.println("Test if first person really got to the first place");
        assertEquals("1", race.getResults().get(0).getPosition());
        System.out.println("Test if the season was 2008");
        assertEquals(2018, race.getSeason());
    }

    public void testAnNonExistingRace() throws UnirestException {
        Race race = Race.getRace("http://ergast.com/api/f1/", 2028, 28);
        assertNull(race);
    }
}
