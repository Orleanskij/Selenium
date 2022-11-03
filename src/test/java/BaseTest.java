import driver.Driver;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pages.LoginPage;


import static driver.Driver.closeInstance;
import static driver.Driver.getInstance;
import static util.Constants.AUTOMATION_PRACTICE_ACCOUNT_URL;

public class BaseTest {

    private WebDriver driver;
    protected LoginPage loginPage;

    @BeforeMethod
    @Step("Navigate to Home page")
    void initializeDriver() {
        driver = Driver.getInstance().getDriver();
        driver.get(AUTOMATION_PRACTICE_ACCOUNT_URL);
        loginPage = new LoginPage();
    }

    @AfterMethod
    public void closeBrowser() {
        driver.quit();
        closeInstance();
    }
}
