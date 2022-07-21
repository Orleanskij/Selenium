import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
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

    @Test
    public void testPriceCalculationWhenAddingItem() {
        car.setName("Audi");
        car.setPrice(32026.9);
        car.setWeight(1560);

        cart.addRealItem(car);
        Assertions.assertEquals(cart.getTotalPrice(), 38432.28);
    }

    @Test
    public void testPriceCalculationWhenDeletingItem() {
        car.setName("Audi");
        car.setPrice(32026.9);
        car.setWeight(1560);

        cart.addRealItem(car);
        cart.deleteRealItem(car);
        Assertions.assertNotEquals(cart.getTotalPrice(), 38432.28);
    }
}