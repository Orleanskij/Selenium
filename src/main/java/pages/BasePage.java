package pages;

import driver.Driver;
import org.openqa.selenium.WebDriver;
import util.Waiter;

public class BasePage {
    protected WebDriver driver;
    protected Waiter waiter;

    public BasePage() {
        driver = Driver.getInstance().getDriver();
        waiter = new Waiter(driver);
    }
}
