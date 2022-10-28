package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MenuPage {
    WebDriver driver;

    @FindBy(xpath = "//a[@class='product_img_link'][1]")
    WebElement firstProduct;

    @FindBy(id = "wishlist_button")
    WebElement addToWishlistButton;


    public MenuPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void navigateTo(String url) {
        driver.get(url);
    }

    public void openFirstProduct() {
        firstProduct.click();
    }

    public void clickToAddToWishList() {
        addToWishlistButton.click();
    }
}
