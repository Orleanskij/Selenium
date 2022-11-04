package pages;

import driver.Driver;
import org.openqa.selenium.WebDriver;
import util.TestUtils;
import util.Waiter;

public class BasePage {
    protected WebDriver driver;
    protected Waiter waiter;
    protected TestUtils utils;

    public BasePage() {
        driver = Driver.getInstance().getDriver();
        waiter = new Waiter(driver);
        utils = new TestUtils(driver);
    }
}
