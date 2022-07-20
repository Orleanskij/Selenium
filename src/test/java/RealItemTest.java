import org.junit.Assert;
import org.junit.Test;
import shop.RealItem;

public class RealItemTest {

    @Test
    public void realItemWeightTest() {
        RealItem realItem = new RealItem();
        realItem.setName("Audi");
        realItem.setPrice(32026.9);
        realItem.setWeight(1560);
        Assert.assertNotNull(realItem.getWeight());
    }
}