package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class ProductPage {
    WebDriver driver;
    int total;

    @FindBy(xpath = "//a[@class='product_img_link']")
    List<WebElement> productsIcon;

    @FindBy(xpath = "//span[@title='Continue shopping']")
    WebElement continueShoppingButton;

    public ProductPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public int getTotal() {
        return total;
    }

    public void addProductCart(int countProducts) {
        int count = 0;
        total = 0;
        while (count < countProducts) {
            for (WebElement product : productsIcon) {
                Actions action = new Actions(driver);
                if (System.getProperty("browser").equals("firefox")) {
                    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", product.findElement(By.xpath("//a[@class='product_img_link']/img")));
                } else {
                    action.moveToElement(product.findElement(By.xpath("//a[@class='product_img_link']/img"))).build().perform();
                }

                WebElement addToCart = product.findElement(By.xpath("//a[@title='Add to cart']"));
                WebElement price = product.findElement(By.xpath("//span[@class='price product-price']"));
                total += Integer.parseInt(price.getText());
                addToCart.click();
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
                wait.until(ExpectedConditions.visibilityOfAllElements(continueShoppingButton));
                continueShoppingButton.click();
                count++;
            }
        }
    }
}
