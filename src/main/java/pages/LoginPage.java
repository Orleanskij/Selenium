package pages;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.net.MalformedURLException;

public class LoginPage extends BasePage {

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

    public LoginPage() throws MalformedURLException {
        super();
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
        waiter.waifForWebElementVisibility(emailField);
        emailField.sendKeys(email);
        passwordField.sendKeys(password);
    }

    public boolean isSignOutButtonDisplayed() {
        waiter.waifForWebElementVisibility(signOutButton);
        try {
            return signOutButton.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}
