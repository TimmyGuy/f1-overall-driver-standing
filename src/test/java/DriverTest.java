import com.mashape.unirest.http.exceptions.UnirestException;
import driver.Driver;
import org.junit.Assert;
import org.junit.Test;

public class DriverTest {

    @Test
    public void getDriver_getADriverWithAnExistingId_shouldReturnDriverInformation() {
        Driver d = null;
        try {
            d = Driver.getDriver("https://ergast.com/api/f1/", "alonso");
        } catch (UnirestException e) {
            throw new RuntimeException(e);
        }
        Assert.assertEquals("alonso", d.getDriverId());
        Assert.assertEquals("Alonso", d.getFamilyName());
    }

    @Test
    public void getDriver_getADriverWithANonExistingId_shouldReturnNull() {
        Driver d = null;
        try {
            d = Driver.getDriver("https://ergast.com/api/f1/", "nonExistingDriver");
        } catch (UnirestException e) {
            throw new RuntimeException(e);
        }
        Assert.assertNull(d);
    }

    @Test
    public void getDriver_getADriverWithoutFillingInAnId_shouldReturnNull() {
        Driver d = null;
        try {
            d = Driver.getDriver("https://ergast.com/api/f1/", null);
        } catch (UnirestException e) {
            throw new RuntimeException(e);
        }
        Assert.assertNull(d);
    }
}
