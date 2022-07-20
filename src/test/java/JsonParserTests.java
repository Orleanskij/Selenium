import org.junit.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import parser.JsonParser;
import parser.NoSuchFileException;
import parser.Parser;
import shop.Cart;

import java.io.*;


public class JsonParserTests {

    private Cart cart;
    private File file;
    private final String cartName = "andrew-cart";


    @Before
    public void beforeTests() {
        file = new File("src/main/resources/" + cartName + ".json");
    }

    @Test
    public void writeToFileTest() {
        cart = new Cart(cartName);
        Parser parser = new JsonParser();
        parser.writeToFile(cart);
        Cart cartActual = parser.readFromFile(file);
        String actualName = cartActual.getCartName();
        Assert.assertEquals(actualName, cartName);
    }

    @Disabled("Disabled")
    @Test
    public void readFromFileTest() {
        Parser parser = new JsonParser();
        cart = parser.readFromFile(file);
        String name = cart.getCartName();
        Assert.assertEquals(name, cartName);
    }

    @ParameterizedTest
    @ValueSource(strings = {"andrew-cart2", "andrew-cart3", "andrew-cart4", "andrew-cart4", "andrew-cart5"})
    public void testForException(String fileName) {
        Parser parser = new JsonParser();
        Assert.assertThrows(NoSuchFileException.class, () -> parser.readFromFile(new File("src/main/resources/" + fileName + ".json")));
    }

    @AfterEach
    public void messages() {
        System.out.println("test was triggered");
    }
}