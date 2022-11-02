import driver.Driver;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;


import io.qameta.allure.Allure;
import org.openqa.selenium.Capabilities;

import org.openqa.selenium.remote.RemoteWebDriver;

import java.time.LocalDateTime;


public class TestListener implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {
        WebDriver driver;
        driver = Driver.getInstance().getDriver();
        takeScreenshot(driver);
        Capabilities caps = ((RemoteWebDriver) driver).getCapabilities();
        Allure.parameter("time", LocalDateTime.now());
        Allure.parameter(caps.getBrowserName(), caps.getBrowserVersion());
        Allure.parameter("platform", caps.getPlatformName());
    }

    @Attachment(value = "Page Screenshot", type = "image/png")
    private byte[] takeScreenshot(WebDriver driver) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }
}
