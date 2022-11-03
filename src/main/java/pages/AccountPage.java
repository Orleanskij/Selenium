package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.section.Header;

import static util.TestUtils.isDisplayed;

public class AccountPage extends BasePage {

    @FindBy(xpath = "//h1[contains(text(), 'My account')]")
    WebElement myAccountLabel;

    @FindBy(xpath = "//a[@class= 'logout']")
    WebElement signOutButton;

    public AccountPage() {
        super();
        PageFactory.initElements(driver, this);
    }

    public boolean isMyAccountLabelDisplayed() {
        return isDisplayed(myAccountLabel);
    }

    public boolean isSignOutButtonDisplayed() {
        return isDisplayed(signOutButton);
    }

    public Header getHeader() {
        return new Header();
    }
}
