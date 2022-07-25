import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import parser.JsonParser;
import parser.NoSuchFileException;
import parser.Parser;
import shop.Cart;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class JsonParserTests {

    private Cart cart;
    private Parser parser;
    private final String cartName = "andrew-cart";
    public static final String EXPECTED_CART_RESULT = "{\"cartName\":\"testCart\",\"realItems\":[],\"virtualItems\":[],\"total\":0.0}";
    public static final String TEST_CART_PATH = "src/main/resources/testCart.json";


    @BeforeMethod(groups = {"JSON", "beforegroup"})
    public void beforeTests() {
        parser = new JsonParser();
    }

    @Ignore
    @Test(groups = {"JSON", "read_write"})
    public void testReadFromFile() {
        cart = parser.readFromFile(new File("src/main/resources/" + cartName + ".json"));
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(cart.getCartName(), cartName);
        softAssert.assertEquals(cart.getTotalPrice(), 38445.479999999996);
        softAssert.assertAll();
    }

    @Test(groups = {"JSON", "read_write"})
    public void testWriteToFile() throws IOException {
        cart = new Cart("testCart");
        parser.writeToFile(cart);
        String actualResult = Files.readString(Path.of(TEST_CART_PATH));
        Assert.assertEquals(EXPECTED_CART_RESULT, actualResult);
    }

    @DataProvider(name = "testForException")
    public Object[][] dpMethod() {
        return new Object[][]{{"andrew-cart2"}, {"andrew-cart3"}, {"andrew-cart4"}, {"andrew-cart4"}, {"andrew-cart5"}};
    }

    @Test(groups = {"JSON", "exceptionGroup"}, dataProvider = "testForException")
    public void testForException(String fileName) {
        Assert.assertThrows(NoSuchFileException.class, () -> parser.readFromFile(new File("src/main/resources/" + fileName + ".json")));
    }

    @AfterTest
    public void deleteFile() {
        try {
            File file = new File(TEST_CART_PATH);
            file.deleteOnExit();
        } catch (Exception ignore) {
        }
    }
}