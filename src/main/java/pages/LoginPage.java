package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends BasePage {

    @FindBy(id = "SubmitCreate")
    WebElement createAnAccountButton;

    @FindBy(id = "email")
    WebElement loginEmail;

    @FindBy(id = "passwd")
    WebElement loginPassword;

    @FindBy(id = "SubmitLogin")
    WebElement submitButton;

    @FindBy(id = "email_create")
    WebElement emailFieldCreateUser;

    public LoginPage() {
        super();
        PageFactory.initElements(driver, this);
    }

    public RegistrationPage clickCreateAnAccount() {
        createAnAccountButton.click();
        return new RegistrationPage();
    }

    public AccountPage clickSignInButton() {
        submitButton.click();
        return new AccountPage();
    }

    public void fillEmailField(String email) {
        emailFieldCreateUser.sendKeys(email);
    }

    public void logIn(String email, String password) {
        loginEmail.sendKeys(email);
        loginPassword.sendKeys(password);
    }
}
