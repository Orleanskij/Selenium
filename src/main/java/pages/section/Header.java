package pages.section;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.*;

import java.util.List;

public class Header extends BasePage {

    @FindBy(css = ".menu-content>li>a")
    List<WebElement> categoryTabs;

    @FindBy(xpath = "//a[@title='My wishlists']")
    WebElement wishlistButton;

    @FindBy(xpath = "//a[@title='View my shopping cart']")
    WebElement viewMyShoppingCartBtn;

    @FindBy(xpath = "//a[@title='View my customer account']")
    WebElement accountMenuBtn;

    public Header() {
        super();
        PageFactory.initElements(driver, this);
    }

    public StorePage navigateToCategory(String category) {
        for (WebElement element : categoryTabs) {
            if (element.getText().contains(category)) {
                element.click();
                break;
            }
        }
        return new StorePage();
    }

    public WishListPage navigateToWishlistPage() {
        wishlistButton.click();
        return new WishListPage();
    }

    public CartPage navigateToCart() {
        viewMyShoppingCartBtn.click();
        return new CartPage();
    }

    public AccountPage navigateToAccountMenu() {
        accountMenuBtn.click();
        return new AccountPage();
    }
}
