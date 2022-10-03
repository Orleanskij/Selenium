import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.qameta.allure.TmsLink;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.LoginPage;
import util.Constants;

import java.io.IOException;

import java.io.File;

import static util.Constants.*;

@Listeners(TestListener.class)
public class LoginTest {
    public WebDriver driver;
    LoginPage loginPage;


    @BeforeMethod(alwaysRun = true)
    public void preparingData() {
        Driver drv = Driver.getInstanceOfDriver();
        driver = drv.getDriver();
        driver.get(Constants.YANDEX_URL);
        loginPage = new LoginPage(driver);
        loginPage.login(LOGIN_USER, PASSWORD_USER);
    }

    @Test(groups = "Login test group")
    @TmsLink(value = "ID-001")
    @Description(value = "Log in Test")
    public void loginTest() throws IOException {
        Assert.assertTrue(loginPage.isComposeButtonDisplayed());
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(screenshot, new File("D:\\UnitTesting-master\\screenshots\\homepage.png"));
    }

    @Test(groups = "Log in/out test group")
    @TmsLink(value = "ID-002")
    @Description(value = "Log out Test")
    public void logoutTest() {
        loginPage.logout();
        Assert.assertTrue(loginPage.isDisplayed());
    }

    @AfterMethod(alwaysRun = true)
    public void afterMethod(ITestResult result) {
        driver.quit();
    }
}
