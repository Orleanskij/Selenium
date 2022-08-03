
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import shop.Cart;
import shop.RealItem;

public class CartTest {

    Cart cart;
    RealItem car;

    @BeforeMethod(alwaysRun = true)
    public void beforeTest() {
        cart = new Cart("andrew-cart");
        car = new RealItem();
        car.setName("Audi");
        car.setPrice(32026.9);
        car.setWeight(1560);
        cart.addRealItem(car);
    }

    @Test(groups = {"calculation", "smoke"})
    public void testPriceCalculationWhenAddingItem() {
        Assert.assertEquals(cart.getTotalPrice(), 38432.28);
    }

    @Test(groups = {"calculation", "regression"})
    public void testPriceCalculationWhenDeletingItem() {
        cart.deleteRealItem(car);
        Assert.assertNotEquals(cart.getTotalPrice(), 38432.28);
    }
}