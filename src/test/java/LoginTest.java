import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.LoginPage;
import util.Constants;


public class LoginTest {
    WebDriver driver;

    @BeforeMethod(alwaysRun = true)
    public void preparingData() {
        WebDriverManager.getInstance(ChromeDriver.class).setup();
        driver = new ChromeDriver();
    }

    @Test
    public void loginTest() {
        driver.get(Constants.YANDEX_URL);
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(Constants.LOGIN_USER, Constants.PASSWORD_USER);
        Assert.assertTrue(loginPage.isComposeButtonDisplayed());
    }

    @AfterMethod(alwaysRun = true)
    public void afterMethod() {
        driver.quit();
    }
}
