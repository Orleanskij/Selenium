import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.LoginPage;
import util.Constants;

import static util.Constants.*;


public class LoginTest {
    WebDriver driver;


    @BeforeMethod(alwaysRun = true)
    public void preparingData() {
        Driver drv = Driver.getInstanceOfDriver();
        driver = drv.getDriver();
    }

    @DataProvider(name = "authentication")
    public Object[][] dpMethod() {
        return new Object[][]{
                {Constants.LOGIN_USER, Constants.PASSWORD_USER}
        };
    }

    @Test(dataProvider = "authentication")
    public void loginTest(String username, String password) throws InterruptedException {
        driver.get(Constants.YANDEX_URL);
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(username, password);
        Assert.assertTrue(loginPage.isComposeButtonDisplayed());
    }

    @Test(dataProvider = "authentication")
    public void logoutTest(String username, String password) throws InterruptedException {
        driver.get(Constants.YANDEX_URL);
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(username, password);
        loginPage.logout();
        Assert.assertTrue(loginPage.isButtonDisplayed(LOGIN_BUTTON));
    }

    @AfterMethod(alwaysRun = true)
    public void afterMethod() {
        driver.quit();
    }
}
