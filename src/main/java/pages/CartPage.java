package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CartPage extends BasePage {

    @FindBy(id = "total_price")
    WebElement totalPrice;

    @FindBy(xpath = "//td[@class='cart_total']/span")
    List<WebElement> pricesInTheColumns;

    @FindBy(xpath = "//tbody/tr")
    List<WebElement> items;

    public CartPage() {
        super();
        PageFactory.initElements(driver, this);
    }

    public double getCartTotal() {
        return Double.parseDouble(totalPrice.getText().substring(1));
    }

    public int getItemsQty() {
        return items.size();
    }

    public double getItemsTotal() {
        double total = 0;
        for (WebElement price : pricesInTheColumns) {
            total = Double.parseDouble(price.getText().substring(1));
            total += total;
        }
        return total;
    }
}
