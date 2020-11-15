import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTests {
    @Test
    public void testDistance() {
        Point p = new Point(10, 80, 15, 90);
        Assert.assertEquals(p.distance(), 11.180339887498949);
    }
}
