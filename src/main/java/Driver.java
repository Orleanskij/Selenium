import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Allure;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class Driver {

    private static Driver instanceOfDriver = null;
    private WebDriver driver;

    private Driver() throws MalformedURLException {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setBrowserName("chrome");
        caps.setPlatform(Platform.WIN10);
        driver = new RemoteWebDriver(new URL("http://localhost:4444"), caps);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    public static Driver getInstanceOfDriver() throws MalformedURLException {
        if (instanceOfDriver == null) {
            instanceOfDriver = new Driver();
        }
        return instanceOfDriver;
    }

    public WebDriver getDriver() {
        return driver;
    }
}
