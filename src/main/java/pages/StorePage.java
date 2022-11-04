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

    @FindBy(css = ".ajax_add_to_cart_button")
    List<WebElement> addToCartButtonList;

    @FindBy(css = ".continue")
    WebElement continueShoppingButton;

    @FindBy(xpath = "//a[@class='button lnk_view btn btn-default']")
    List<WebElement> moreBtn;

    public StorePage() {
        super();
        PageFactory.initElements(driver, this);
    }

    public void addProductCart(int countProducts) {
        int count = 0;

        for (WebElement product : items) {
            if (count < countProducts) {
                utils.moveToElement(product);
                waiter.waifForWebElementVisibility(addToCartButtonList.get(count));
                addToCartButtonList.get(count).click();
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
        utils.moveToElement(items.get(randomValue));
        moreBtn.get(randomValue).click();
        return new ProductPage();
    }

    public Header getHeader() {
        return new Header();
    }
}
