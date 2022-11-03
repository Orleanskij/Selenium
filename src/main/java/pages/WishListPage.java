package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.section.Header;

import java.util.List;

import static util.TestUtils.isDisplayed;

public class WishListPage extends BasePage {

    public WishListPage() {
        super();
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//table[@class='table table-bordered']/tbody/tr")
    List<WebElement> myWishlistsTable;

    @FindBy(css = ".icon-remove")
    WebElement removeProductBtn;

    @FindBy(id = "submitWishlist")
    WebElement saveWishList;

    @FindBy(id = "name")
    WebElement wishListNameField;

    public void clearWishList() {
        if (isDisplayed(removeProductBtn)) {
            removeProductBtn.click();
            driver.switchTo().alert().accept();
        }
    }

    public boolean isWishListAdded() {
        return !myWishlistsTable.isEmpty();
    }

    public boolean doesWishListContain(String myWishlistName) {
        return myWishlistsTable.stream().anyMatch(element -> element.getText().contains(myWishlistName));
    }

    public void createWishList(String wishlistName) {
        wishListNameField.sendKeys(wishlistName);
        saveWishList.click();
    }

    public Header getHeader() {
        return new Header();
    }
}
