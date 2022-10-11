import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.net.MalformedURLException;


public class TestListener implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {
        WebDriver driver = null;
        try {
            driver = Driver.getInstanceOfDriver().getDriver();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        takeScreenshot(driver);
    }

    @Attachment(value = "Page Screenshot", type = "image/png")
    private byte[] takeScreenshot(WebDriver driver) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }
}
