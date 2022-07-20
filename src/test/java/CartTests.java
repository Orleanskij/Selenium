import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Tag;
import shop.Cart;
import shop.RealItem;

public class CartTests {

    Cart cart;
    RealItem car;

    @Before
    public void beforeTest() {
        cart = new Cart("andrew-cart");
        car = new RealItem();
    }

    @Tag("Cart Test")
    @Test
    public void getTotalPriceTest() {
        car.setName("Audi");
        car.setPrice(32026.9);
        car.setWeight(1560);

        cart.addRealItem(car);
        Assert.assertNotEquals(cart.getTotalPrice(), 0.0);
    }

    @Tag("Cart Test")
    @Test
    public void getCartNameTest() {
        Assert.assertEquals(cart.getCartName(), "andrew-cart");
    }
}