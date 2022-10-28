package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static util.RandomFieldPopulator.*;

public class AccountPage {
    WebDriver driver;

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

    @FindBy(xpath = "//span[contains(text(), 'My wishlists')]")
    WebElement myWishlists;

    @FindBy(xpath = "//table[@class='table table-bordered']/tbody/tr")
    List<WebElement> myWishlistsTable;

    @FindBy(id = "name")
    WebElement wishListNameField;

    @FindBy(id = "submitWishlist")
    WebElement saveWishList;


    public AccountPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void fillEmailField(String email) {
        emailField.click();
        emailField.sendKeys(email);
    }

    public void fillPasswordField(String password) {
        emailField.click();
        emailField.sendKeys(password);
    }

    public void clickRegisterButton() {
        registerButton.click();
    }

    public void fillRequiredFields() {
        mrRadio.click();
        firstNameField.sendKeys(generateFirstName());
        lastNameField.sendKeys(generateLastName());
        passwordField.sendKeys(generatePassword());
        addressField.sendKeys("morozov street 64");
        cityField.sendKeys("Brest");
        postCodeField.sendKeys("12345");
        selectState("Alabama");
        phoneNumberField.sendKeys("291111111");
    }

    public void selectState(String state) {
        Select drpCountry = new Select(stateDrd);
        drpCountry.selectByVisibleText(state);
    }

    public boolean isMyAccountLabelDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfAllElements(myAccountLabel));
        return myAccountLabel.isDisplayed();
    }

    public void clickMyWishList() {
        myWishlists.click();
    }

    public void clearWishList() {
        while (myWishlistsTable.size() != 0) {
            for (WebElement element : myWishlistsTable) {
                WebElement deleteWishList = element.findElement(By.xpath("//a[@class= 'icon']"));
                deleteWishList.click();
                driver.switchTo().alert().accept();
            }
        }
    }

    public void createWishList(String wishlistName) {
        wishListNameField.sendKeys(wishlistName);
        saveWishList.click();
    }

    public boolean isAddedWishList() {
        return !myWishlistsTable.isEmpty();
    }

    public boolean isProductAddedToWishList(String myWishlistName) {
        int count = 0;
        if (myWishlistsTable.size() != 0) {
            for (WebElement element : myWishlistsTable) {
                WebElement WishListNameColumn = element.findElement(By.xpath("//tbody/tr/td[1]/a"));
                if (myWishlistName.contains(WishListNameColumn.getText())) {
                    WebElement productWishListColumn = element.findElement(By.xpath("//tbody/tr/td[@class='bold align_center']"));
                    count = Integer.parseInt(productWishListColumn.getText());
                }
            }
        }
        return count == 1;
    }
}
