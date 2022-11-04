package pages;

import dto.User;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class RegistrationPage extends BasePage {

    @FindBy(id = "customer_firstname")
    WebElement firstNameField;

    @FindBy(id = "customer_lastname")
    WebElement lastNameField;

    @FindBy(id = "passwd")
    WebElement passwordField;

    @FindBy(id = "address1")
    WebElement addressField;

    @FindBy(id = "city")
    WebElement cityField;

    @FindBy(id = "postcode")
    WebElement postCodeField;

    @FindBy(id = "phone_mobile")
    WebElement phoneNumberField;

    @FindBy(id = "submitAccount")
    WebElement registerButton;

    @FindBy(id = "id_gender1")
    WebElement mrRadio;

    @FindBy(id = "id_state")
    WebElement stateDrd;

    public RegistrationPage() {
        super();
        PageFactory.initElements(driver, this);
    }

    public void createUser(User user) {
        mrRadio.click();
        firstNameField.sendKeys(user.getFirstname());
        lastNameField.sendKeys(user.getLastname());
        passwordField.sendKeys(user.getPassword());
        addressField.sendKeys(user.getAddress());
        cityField.sendKeys(user.getCity());
        postCodeField.sendKeys(user.getPostcode());
        selectState(user.getState());
        phoneNumberField.sendKeys(user.getPhoneNumber());
    }

    public void selectState(String state) {
        Select drpCountry = new Select(stateDrd);
        drpCountry.selectByVisibleText(state);
    }

    public AccountPage clickRegisterButton() {
        registerButton.click();
        return new AccountPage();
    }
}
