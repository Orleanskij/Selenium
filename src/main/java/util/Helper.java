package util;

import driver.Driver;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.net.MalformedURLException;

public class Helper {

    private Actions actions;
    private WebDriver driver;

    public Helper(WebDriver driver) {
        this.driver = driver;
        actions = new Actions(driver);
    }

    public void moveToElement(WebElement element) {
        if (System.getProperty("browser").equals("firefox")) {

            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        } else {
            actions.moveToElement(element).build().perform();
        }
    }
}
