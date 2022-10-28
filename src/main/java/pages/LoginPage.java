package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {

    WebDriver driver;

    @FindBy(id = "SubmitCreate")
    WebElement createAnAccountButton;

    @FindBy(id = "email")
    WebElement emailField;

    @FindBy(id = "passwd")
    WebElement passwordField;

    @FindBy(id = "SubmitLogin")
    WebElement submitButton;

    @FindBy(xpath = "//a[@class= 'logout']")
    WebElement signOutButton;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void clickCreateAnAccount() {
        createAnAccountButton.click();
    }

    public void clickSignInButton() {
        submitButton.click();
    }

    public void logInTheSystem(String email, String password) {
        if (isSignOutButtonDisplayed()) {
            signOutButton.click();
        }
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(12));
        wait.until(ExpectedConditions.visibilityOfAllElements(emailField));
        emailField.sendKeys(email);
        passwordField.sendKeys(password);
    }

    public boolean isSignOutButtonDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfAllElements(signOutButton));
        return signOutButton.isDisplayed();
    }
}
