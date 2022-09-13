import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.LoginPage;
import util.Constants;

import java.io.File;
import java.io.IOException;

import static util.Constants.*;


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
    public void loginTest() throws IOException {
        Assert.assertTrue(loginPage.isComposeButtonDisplayed());
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(screenshot, new File("D:\\UnitTesting-master\\screenshots\\homepage.png"));
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
