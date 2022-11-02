package driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;
import java.time.Duration;

import static util.Constants.GRID_URL;
import static util.Constants.SAUCE_URL;

public class Driver {

    private static final String BROWSER = StringUtils.defaultString(System.getProperty("browser"), "chrome");
    private static final String REMOTE_URL = StringUtils.defaultString(System.getProperty("remote"), "local");

    private static Driver instanceOfDriver = null;
    private WebDriver driver;

    private Driver() {
        if (REMOTE_URL.equals("local")) {
            setDriver();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
            driver.manage().window().maximize();
        } else {
            driver = getRemote(REMOTE_URL, getCapabilities());
        }
    }

    private WebDriver setDriver() {
        if (BROWSER.equals("chrome")) {
            WebDriverManager.getInstance(ChromeDriver.class).setup();
            driver = new ChromeDriver();
        } else {
            WebDriverManager.getInstance(FirefoxDriver.class).setup();
            driver = new FirefoxDriver();
        }
        return driver;
    }

    public Capabilities getCapabilities() {
        if (BROWSER.equals("chrome")) {
            ChromeOptions option = new ChromeOptions();
            option.setCapability("os", "Windows");
            return option;
        } else {
            FirefoxOptions option = new FirefoxOptions();
            option.setCapability("os", "Windows");
            return option;
        }
    }

    @SneakyThrows
    public RemoteWebDriver getRemote(String remote, Capabilities options) {
        String url;
        if (remote.contains("saucelabs")) {
            url = SAUCE_URL;
        } else {
            url = GRID_URL;
        }
        return new RemoteWebDriver(new URL(url), options);

    }

    public static Driver getInstance() {
        if (instanceOfDriver == null) {
            instanceOfDriver = new Driver();
        }
        return instanceOfDriver;
    }

    public WebDriver getDriver() {
        return driver;
    }
}
