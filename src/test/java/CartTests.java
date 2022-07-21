import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import shop.Cart;
import shop.RealItem;

public class CartTests {

    Cart cart;
    RealItem car;

    @BeforeEach
    public void beforeTest() {
        cart = new Cart("andrew-cart");
        car = new RealItem();
        car.setName("Audi");
        car.setPrice(32026.9);
        car.setWeight(1560);
        cart.addRealItem(car);
    }

    @Test
    public void testPriceCalculationWhenAddingItem() {
        Assertions.assertEquals(cart.getTotalPrice(), 38432.28);
    }

    @Test
    public void testPriceCalculationWhenDeletingItem() {
        cart.deleteRealItem(car);
        Assertions.assertNotEquals(cart.getTotalPrice(), 38432.28);
    }
}