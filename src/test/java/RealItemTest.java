import org.testng.Assert;
import org.testng.annotations.Test;
import shop.RealItem;

public class RealItemTest {

    @Test
    public void testRealItemWeight() {
        RealItem realItem = new RealItem();
        realItem.setWeight(1560);
        Assert.assertTrue(realItem.toString().contains("1560"));
    }
}