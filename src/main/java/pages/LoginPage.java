package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {

    WebDriver driver;

    @FindBy(xpath = "//div[@class='PSHeader-User']/div")
    WebElement userMenu;

    @FindBy(xpath = "//li/a[contains(@class, 'exit')]")
    WebElement logOut;

    @FindBy(xpath = "//div[@class='PSHeader-Right']/button")
    WebElement loginButton;

    @FindBy(name = "login")
    WebElement loginField;

    @FindBy(css = "[type=submit]")
    WebElement submitButton;

    @FindBy(xpath = "//input[@name='passwd']")
    WebElement passwordField;

    @FindBy(id = "passp:sign-in")
    WebElement sigInButton;

    @FindBy(xpath = "//a[@href='#compose']")
    WebElement composeButton;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void login(String login, String password) {
        loginButton.click();
        loginField.sendKeys(login);
        submitButton.click();
        passwordField.sendKeys(password);
        sigInButton.click();
    }

    public void logout() {
        userMenu.click();
        logOut.click();
    }

    public boolean isButtonDisplayed(By button) {
        WebElement btn = driver.findElement(button);
        return btn.isDisplayed();
    }

    public boolean isComposeButtonDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfAllElements(composeButton));
        return composeButton.isDisplayed();
    }
}
