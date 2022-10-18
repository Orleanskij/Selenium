import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
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

//        caps.setBrowserName("chrome");
//        caps.setVersion("latest");
//        caps.setPlatform(Platform.WIN10);

//        caps.setCapability("browserName", "firefox");
//        caps.setVersion("39");
//        caps.setPlatform(Platform.WIN8_1);

        caps.setCapability("browserName", "chrome");
        caps.setVersion("40");
        caps.setPlatform(Platform.LINUX);

        URL url = new URL("https://orlean88:ec9bd097-9768-4317-b066-36e52ec4890f@ondemand.eu-central-1.saucelabs.com:443/wd/hub");

        driver = new RemoteWebDriver(url, caps);
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
