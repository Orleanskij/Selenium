package util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import dto.User;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestUtils {

    public static final String USERS_PATH = "src/test/resources/users.json";
    private static List<User> users = new ArrayList<>();
    private WebDriver driver;

    public TestUtils(WebDriver driver) {
        this.driver = driver;
    }

    public void moveToElement(WebElement element) {
            Actions actions = new Actions(driver);
            actions.moveToElement(element).build().perform();
    }

    public static String generateEmail() {
        Faker faker = new Faker();
        String firstName = faker.name().firstName();
        String number = String.valueOf(faker.number().numberBetween(10000, 10000));
        return firstName + number + "@mail.com";
    }

    static {
        ObjectMapper mapper = new ObjectMapper();
        try {
            users = Arrays.asList(mapper.readValue(new File(USERS_PATH), User[].class));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static User getUser(String userNumber) {
        return users.get(Integer.parseInt(userNumber));
    }

    public static boolean isDisplayed(WebElement element) {
        try {
            return element.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}
