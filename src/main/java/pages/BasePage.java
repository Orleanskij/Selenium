package pages;

import driver.Driver;
import org.openqa.selenium.WebDriver;
import util.Waiter;

import java.net.MalformedURLException;

public class BasePage {
    protected WebDriver driver;
    protected Waiter waiter;

    public BasePage() throws MalformedURLException {
        Driver drv = Driver.getInstanceOfDriver();
        driver = drv.getDriver();
        waiter = new Waiter(driver);
    }
}
