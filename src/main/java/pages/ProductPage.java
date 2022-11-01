package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import util.Helper;

import java.net.MalformedURLException;
import java.util.List;

public class ProductPage extends BasePage{

    @FindBy(xpath = "//a[@class='product_img_link']")
    List<WebElement> productsIcon;

    @FindBy(xpath = "//span[@title='Continue shopping']")
    WebElement continueShoppingButton;

    private By productIcon = By.xpath("//a[@class='product_img_link']/img");
    private By addToCartButton = By.xpath("//a[@title='Add to cart']");

    public ProductPage() throws MalformedURLException {
        super();
        PageFactory.initElements(driver, this);
    }

    public void addProductCart(int countProducts) {
        int count = 0;
        while (count < countProducts) {
            for (WebElement product : productsIcon) {
                Helper helper = new Helper(driver);
                helper.moveToElement(product.findElement(productIcon));
                WebElement addToCart = product.findElement(addToCartButton);
                addToCart.click();
                waiter.waifForWebElementVisibility(continueShoppingButton);
                continueShoppingButton.click();
                count++;
            }
        }
    }
}
