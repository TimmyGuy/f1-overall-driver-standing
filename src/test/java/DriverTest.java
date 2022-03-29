import Driver.Driver;
import com.mashape.unirest.http.exceptions.UnirestException;
import junit.framework.TestCase;

public class DriverTest extends TestCase {
    public void testWithWorkingDriverId() throws UnirestException {
        Driver d = Driver.getDriver("http://ergast.com/api/f1/", "alonso");
        assertEquals("alonso", d.getDriverId());
        assertEquals("Alonso", d.getFamilyName());
    }

    public void testWithNonExistingDriverId() throws UnirestException {
        Driver d = Driver.getDriver("http://ergast.com/api/f1/", "nonExistingDriver");
        assertNull(d);
    }

    public void testWithNullDriverId() throws UnirestException {
        Driver d = Driver.getDriver("http://ergast.com/api/f1/", null);
        assertNull(d);
    }
}
