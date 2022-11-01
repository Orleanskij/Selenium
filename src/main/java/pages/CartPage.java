package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.net.MalformedURLException;
import java.util.List;

public class CartPage extends BasePage {


    @FindBy(id = "total_price")
    WebElement totalPrice;

    @FindBy(xpath = "//td[@class='cart_total']/span")
    List<WebElement> pricesInTheColumns;

    @FindBy(xpath = "//tbody/tr")
    List<WebElement> countProducts;


    public CartPage() throws MalformedURLException {
        super();
        PageFactory.initElements(driver, this);
    }

    public double getTotalPrice() {
        return Double.parseDouble(totalPrice.getText().substring(1));
    }

    public boolean isAllProductsInTheCart(int countProductsShouldBe) {
        return countProductsShouldBe == countProducts.size();
    }

    public double getSumPricesInCart() {
        double sumPrices = 0;
        for (WebElement price : pricesInTheColumns) {
            sumPrices = Double.parseDouble(price.getText().substring(1));
            sumPrices += sumPrices;
        }
        return sumPrices;
    }
}
