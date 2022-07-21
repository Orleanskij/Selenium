import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import shop.RealItem;

public class RealItemTest {

    @Test
    public void testRealItemWeight() {
        RealItem realItem = new RealItem();
        realItem.setWeight(1560);
        Assertions.assertTrue(realItem.toString().contains("1560"));
    }
}