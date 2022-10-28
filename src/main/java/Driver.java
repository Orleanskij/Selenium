import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class Driver {

    private static Driver instanceOfDriver = null;
    private WebDriver driver;

    private Driver() throws MalformedURLException {
        String machine = "local";
        String browser = "chrome";
        String cloud = "sauce";
        if (machine.equals("local")) {
            getBrowser(browser);
        } else if (machine.equals("remote")) {
            driver = getRemote(cloud, getCapabilities(browser));
        }
    }

    public WebDriver getBrowser(String browser) {
        switch (browser) {
            case "chrome":
                WebDriverManager.getInstance(ChromeDriver.class).setup();
                driver = new ChromeDriver();
                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
                return driver;
            case "firefox":
                WebDriverManager.getInstance(FirefoxDriver.class).setup();
                driver = new FirefoxDriver();
                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
                return driver;
        }
        return null;
    }

    public Capabilities getCapabilities(String browser) {
        switch (browser) {
            case "chrome":
                ChromeOptions options = new ChromeOptions();
                options.setCapability("os", "Windows");
                return options;
            case "firefox":
                FirefoxOptions option = new FirefoxOptions();
                option.setCapability("os", "Windows");
                return option;
        }
        return null;
    }

    public RemoteWebDriver getRemote(String remote, Capabilities options) throws MalformedURLException {
        switch (remote) {
            case "grid":
                return new RemoteWebDriver(new URL("http://localhost:4444"), options);
            case "sauce":
                return new RemoteWebDriver(new URL("https://orlean88:ec9bd097-9768-4317-b066-36e52ec4890f@ondemand.eu-central-1.saucelabs.com:443/wd/hub"), options);
        }

        return null;
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
