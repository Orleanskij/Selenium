package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.section.Header;

public class ProductPage extends BasePage {

    @FindBy(xpath = "//a[@title='Add to my wishlist']")
    WebElement addToWishlistButton;

    @FindBy(xpath = "//a[@title='Close']")
    WebElement closePopUp;

    public ProductPage() {
        super();
        PageFactory.initElements(driver, this);
    }

    public void clickAddToWishList() {
        addToWishlistButton.click();
        waiter.waifForWebElementVisibility(closePopUp);
        closePopUp.click();
    }

    public Header getHeader() {
        return new Header();
    }
}
