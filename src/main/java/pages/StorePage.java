package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.section.Header;

import java.util.List;
import java.util.Random;

public class StorePage extends BasePage {

    @FindBy(css = ".product_img_link")
    List<WebElement> items;

    @FindBy(xpath = "//a[@class='product_img_link']")
    List<WebElement> test;

    @FindBy(css = ".ajax_add_to_cart_button")
    WebElement addToCartButton;

    @FindBy(css = ".continue")
    WebElement continueShoppingButton;

    public StorePage() {
        super();
        PageFactory.initElements(driver, this);
    }

    public void addProductCart(int countProducts) {
        int count = 0;
        while (count < countProducts) {
            for (WebElement product : items) {
                utils.moveToElement(product);
                waiter.waifForWebElementVisibility(addToCartButton);
                addToCartButton.click();
                waiter.waifForWebElementVisibility(continueShoppingButton);
                continueShoppingButton.click();
                waiter.waifForWebElementInVisibility(continueShoppingButton);
                count++;
            }
        }
    }

    public ProductPage openRandomProduct() {
        Random r = new Random();
        int randomValue = r.nextInt(items.size());
        items.get(randomValue).click();
        return new ProductPage();
    }

    public Header getHeader() {
        return new Header();
    }
}
