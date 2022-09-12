import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class Driver {

    private static Driver instanceOfDriver = null;
    private WebDriver driver;

    public Driver() {
        WebDriverManager.getInstance(ChromeDriver.class).setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    public static Driver getInstanceOfDriver() {
        if (instanceOfDriver == null) {
            instanceOfDriver = new Driver();
        }
        return instanceOfDriver;
    }

    public WebDriver getDriver() {
        return driver;
    }
}
