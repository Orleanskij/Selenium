package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CartPage {
    WebDriver driver;


    @FindBy(id = "total_price")
    WebElement totalPrice;

    @FindBy(xpath = "//tbody/tr")
    List<WebElement> countProducts;


    public CartPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public double getTotalPrice() {
        return Double.parseDouble(totalPrice.getText().substring(1));
    }

    public boolean isAllProductsInTheCart(int countProductsShouldBe) {
        return countProductsShouldBe == countProducts.size();
    }
}
