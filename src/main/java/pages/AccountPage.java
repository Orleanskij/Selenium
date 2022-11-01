package pages;

import dto.User;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.net.MalformedURLException;
import java.util.List;

public class AccountPage extends BasePage {

    @FindBy(id = "email_create")
    WebElement emailField;

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

    @FindBy(xpath = "//h1[contains(text(), 'My account')]")
    WebElement myAccountLabel;

    @FindBy(xpath = "//table[@class='table table-bordered']/tbody/tr")
    List<WebElement> myWishlistsTable;

    @FindBy(id = "name")
    WebElement wishListNameField;

    @FindBy(id = "submitWishlist")
    WebElement saveWishList;

    private By closeButtonForWishList = By.xpath("//a[@class= 'icon']");

    public AccountPage() throws MalformedURLException {
        super();
        PageFactory.initElements(driver, this);
    }

    public void fillEmailField(String email) {
        emailField.sendKeys(email);
    }

    public void clickRegisterButton() {
        registerButton.click();
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

    public boolean isMyAccountLabelDisplayed() {
        waiter.waifForWebElementVisibility(myAccountLabel);
        try {
            return myAccountLabel.isDisplayed();
        } catch (
                NoSuchElementException e) {
            return false;
        }
    }

    public void clearWishList() {
        while (myWishlistsTable.size() != 0) {
            for (WebElement element : myWishlistsTable) {
                WebElement deleteWishList = element.findElement(closeButtonForWishList);
                deleteWishList.click();
                driver.switchTo().alert().accept();
            }
        }
    }

    public void createWishList(String wishlistName) {
        wishListNameField.sendKeys(wishlistName);
        saveWishList.click();
    }

    public boolean isWishListAdded() {
        return !myWishlistsTable.isEmpty();
    }

    public boolean isProductNameDisplayedUnWishList(String myWishlistName) {
        return myWishlistsTable.stream().anyMatch(element -> element.getText().contains(myWishlistName));
    }
}
