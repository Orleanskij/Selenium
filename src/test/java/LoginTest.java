import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.LoginPage;
import util.Constants;

import static util.Constants.LOGIN_USER;
import static util.Constants.PASSWORD_USER;


public class LoginTest {
    WebDriver driver;
    LoginPage loginPage;


    @BeforeMethod(alwaysRun = true)
    public void preparingData() {
        Driver drv = Driver.getInstanceOfDriver();
        driver = drv.getDriver();
        driver.get(Constants.YANDEX_URL);
        loginPage = new LoginPage(driver);
        loginPage.login(LOGIN_USER, PASSWORD_USER);
    }

    @Test()
    public void loginTest() {
        Assert.assertTrue(loginPage.isComposeButtonDisplayed());
    }

    @Test()
    public void logoutTest() {
        loginPage.logout();
        Assert.assertTrue(loginPage.isDisplayed());
    }

    @AfterMethod(alwaysRun = true)
    public void afterMethod() {
        driver.quit();
    }
}
