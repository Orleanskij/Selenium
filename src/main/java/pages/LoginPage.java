package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class LoginPage {

    private static final By LOGIN_BTN = By.xpath("//div[@class='PSHeader-Right']/button");
    private static final By SUBMIT_BTN = By.cssSelector("[type=submit]");
    private static final By LOGIN_FLD = By.name("login");
    private static final By PASSWORD_FLD = By.xpath("//input[@name='passwd']");
    private static final By SIG_IN_BTN = By.id("passp:sign-in");
    private static final By COMPOSE_BTN = By.xpath("//a[@href='#compose']");
    private static final By USER_MENU = By.xpath("//div[@class='PSHeader-User']/div");
    private static final By LOGOUT_BUTTON = By.xpath("//li/a[contains(@class, 'exit')]");
    private static final By LOGIN_BUTTON = By.xpath("//div[@class='PSHeader-Right']/button");

    WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void login(String login, String password) {
        WebElement loginButtonOnStartPage = driver.findElement(LOGIN_BTN);
        loginButtonOnStartPage.click();
        WebElement loginInput = driver.findElement(LOGIN_FLD);
        loginInput.sendKeys(login);
        WebElement submitButton = driver.findElement(SUBMIT_BTN);
        submitButton.click();
        WebElement passwordF = driver.findElement(PASSWORD_FLD);
        passwordF.sendKeys(password);
        WebElement loginButton2 = driver.findElement(SIG_IN_BTN);
        loginButton2.click();
    }

    public void logout() {
        WebElement user_menu = driver.findElement(USER_MENU);
        user_menu.click();
        WebElement logout = driver.findElement(LOGOUT_BUTTON);
        logout.click();
    }

    public boolean isDisplayed() {
        WebElement btn = driver.findElement(LOGIN_BUTTON);
        return btn.isDisplayed();
    }

    public boolean isComposeButtonDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy((COMPOSE_BTN)));
        WebElement composeBtn = driver.findElement(COMPOSE_BTN);
        return composeBtn.isDisplayed();
    }
}
