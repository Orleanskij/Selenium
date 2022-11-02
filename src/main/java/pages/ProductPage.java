package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import util.TestUtils;

import java.util.List;
import java.util.Random;

public class ProductPage extends BasePage {

    @FindBy(xpath = "//a[@class='product_img_link']")
    List<WebElement> productsIcon;

    @FindBy(xpath = "//span[@title='Continue shopping']")
    WebElement continueShoppingButton;

    @FindBy(xpath = "//a[@class='product_img_link']")
    List<WebElement> itemProducts;

    @FindBy(id = "wishlist_button")
    WebElement addToWishlistButton;

    @FindBy(xpath = "//a[@title='View my customer account']")
    WebElement accountMenuBtn;

    @FindBy(xpath = "//a[@title='View my shopping cart']")
    WebElement viewMyShoppingCartBtn;

    private By productIcon = By.xpath("//a[@class='product_img_link']/img");
    private By addToCartButton = By.xpath("//a[@title='Add to cart']");

    public ProductPage() {
        super();
        PageFactory.initElements(driver, this);
    }

    public void addProductCart(int countProducts) {
        int count = 0;
        while (count < countProducts) {
            for (WebElement product : productsIcon) {
                TestUtils helper = new TestUtils(driver);
                helper.moveToElement(product.findElement(productIcon));
                WebElement addToCart = product.findElement(addToCartButton);
                addToCart.click();
                waiter.waifForWebElementVisibility(continueShoppingButton);
                continueShoppingButton.click();
                count++;
            }
        }
    }

    public AccountPage navigateToAccountMenu() {
        accountMenuBtn.click();
        return new AccountPage();
    }

    public void openRandomProduct() {
        Random r = new Random();
        int randomValue = r.nextInt(itemProducts.size());
        itemProducts.get(randomValue).click();
    }

    public void clickAddToWishList() {
        addToWishlistButton.click();
    }

    public CartPage navigateToCart() {
        viewMyShoppingCartBtn.click();
        return new CartPage();
    }
}
