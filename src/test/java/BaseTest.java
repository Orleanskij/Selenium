import driver.Driver;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.net.MalformedURLException;

import static util.Constants.AUTOMATION_PRACTICE_ACCOUNT_URL;

public class BaseTest {

    private WebDriver driver;

    @BeforeClass
    @Step("Navigate to Home page")
    void initializeDriver() throws MalformedURLException {
        Driver drv = Driver.getInstanceOfDriver();
        driver = drv.getDriver();
        driver.manage().window().maximize();

        driver.get(AUTOMATION_PRACTICE_ACCOUNT_URL);
    }

    @AfterClass
    public void afterMethod(ITestResult result) {
        driver.quit();
    }
}
