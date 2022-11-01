package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.net.MalformedURLException;
import java.util.List;
import java.util.Random;

public class ItemPage extends BasePage {

    @FindBy(xpath = "//a[@class='product_img_link']")
    List<WebElement> itemProducts;

    @FindBy(id = "wishlist_button")
    WebElement addToWishlistButton;

    public ItemPage() throws MalformedURLException {
        super();
        PageFactory.initElements(driver, this);
    }

    public void navigateTo(String url) {
        driver.get(url);
    }

    public void openRandomProduct() {
        Random r = new Random();
        int randomValue = r.nextInt(itemProducts.size());
        itemProducts.get(randomValue).click();
    }

    public void clickToAddToWishList() {
        addToWishlistButton.click();
    }
}
