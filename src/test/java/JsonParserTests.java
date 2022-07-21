import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import parser.JsonParser;
import parser.NoSuchFileException;
import parser.Parser;
import shop.Cart;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class JsonParserTests {

    private Cart cart;
    private File file;
    private Parser parser;
    private final String cartName = "andrew-cart6";
    public static final String EXPECTED_CART_RESULT = "{\"cartName\":\"andrew-cart6\",\"realItems\":[],\"virtualItems\":[],\"total\":0.0}";


    @BeforeEach
    public void beforeTests() {
        file = new File("src/main/resources/" + cartName + ".json");
        parser = new JsonParser();
    }

    @Disabled("Disabled")
    @Test
    public void testReadFromFile() {
        cart = parser.readFromFile(file);
        Assertions.assertAll(
                () -> Assertions.assertEquals(cart.getCartName(), cartName),
                () -> Assertions.assertEquals(cart.getTotalPrice(), 38445.479999999996));
    }

    @Test
    public void testWriteToFile() throws IOException {
        cart = new Cart(cartName);
        parser.writeToFile(cart);
        String actualResult = Files.readString(Path.of("src/main/resources/andrew-cart6.json"));
        Assertions.assertEquals(EXPECTED_CART_RESULT, actualResult);
    }

    @ParameterizedTest
    @ValueSource(strings = {"andrew-cart2", "andrew-cart3", "andrew-cart4", "andrew-cart4", "andrew-cart5"})
    public void testForException(String fileName) {
        Parser parser = new JsonParser();
        Assertions.assertThrows(NoSuchFileException.class, () -> parser.readFromFile(new File("src/main/resources/" + fileName + ".json")));
    }

    @AfterEach
    public void deleteFile() {
        try {
            File file = new File("src/main/resources/" + cartName + ".json");
            file.deleteOnExit();
        } catch (Exception ignore) {}
    }
}