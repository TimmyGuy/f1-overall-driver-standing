import org.junit.Assert;
import org.junit.Test;
import race.Race;
import com.mashape.unirest.http.exceptions.UnirestException;
import junit.framework.TestCase;

public class RaceTest {
    @Test
    public void getRace_checkIfRaceResultListIsCorrectlyOrdered_firstItemInArrayListShouldBePositionOne() {
        Race race = null;
        try {
            race = Race.getRace("https://ergast.com/api/f1/", 2018, 5);
        } catch (UnirestException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Test if first person really got to the first place");
        Assert.assertEquals("1", race.getResults().get(0).getPosition());
        System.out.println("Test if the season was 2008");
        Assert.assertEquals(2018, race.getSeason());
    }

    @Test
    public void getRace_checkIfNonExistentRace_shouldReturnNull() {
        Race race = null;
        try {
            race = Race.getRace("https://ergast.com/api/f1/", 2028, 28);
        } catch (UnirestException e) {
            throw new RuntimeException(e);
        }
        Assert.assertNull(race);
    }
}
