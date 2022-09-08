package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static util.Constants.*;


public class LoginPage {

    private static final By LOGIN_BTN = By.xpath("//div[@class='HeadBanner-ButtonsWrapper']/a[2]");
    private static final By SUBMIT_BTN = By.cssSelector("[type=submit]");
    private static final By LOGIN_FLD = By.name("login");
    private static final By PASSWORD_FLD = By.xpath("//input[@name='passwd']");
    private static final By SIG_IN_BTN = By.id("passp:sign-in");
    private static final By COMPOSE_BTN = By.xpath("//a[@href='#compose']");


    WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void login(String login, String password) throws InterruptedException {
        Thread.sleep(1000);//Explicit wait
        WebElement loginButtonOnStartPage = driver.findElement(LOGIN_BUTTON);
        loginButtonOnStartPage.click();
        WebElement loginInput = driver.findElement(LOGIN_FIELD);
        loginInput.sendKeys(login);
        WebElement submitButton = driver.findElement(SUBMIT_BUTTON);
        submitButton.click();
        WebElement passwordF = driver.findElement(PASSWORD_FIELD);
        passwordF.sendKeys(password);
        WebElement loginButton2 = driver.findElement(SIG_IN_BUTTON);
        loginButton2.click();
    }

    public boolean isComposeButtonDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy((COMPOSE_BUTTON)));
        WebElement composeBtn = driver.findElement(COMPOSE_BUTTON);
        return composeBtn.isDisplayed();
    }
}
