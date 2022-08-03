package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class LoginPage {

    private By loginBtn = By.xpath("//div[@class='HeadBanner-ButtonsWrapper']/a[2]");
    private By submitBtn = By.xpath("//button[@type='submit']");
    private By loginField = By.xpath("//input[@name='login']");
    private By passwordField = By.xpath("//input[@name='passwd']");
    private By sigInBtn = By.xpath("//button[@id='passp:sign-in']");
    private By composeButton = By.xpath("//a[@href='#compose']");


    WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean login(String login, String password) {
        WebDriverWait wait = new WebDriverWait(driver, 10);

        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(loginBtn));
        WebElement loginButtonOnStartPage = driver.findElement(loginBtn);
        wait.until(ExpectedConditions.elementToBeClickable(loginButtonOnStartPage));
        loginButtonOnStartPage.click();

        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(loginField));
        WebElement loginInput = driver.findElement(loginField);
        loginInput.sendKeys(login);

        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(submitBtn));
        WebElement submitButton = driver.findElement(submitBtn);
        wait.until(ExpectedConditions.elementToBeClickable(submitButton));
        submitButton.click();

        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(passwordField));
        WebElement passwordF = driver.findElement(passwordField);
        passwordF.sendKeys(password);

        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(sigInBtn));
        WebElement loginButton2 = driver.findElement(sigInBtn);
        wait.until(ExpectedConditions.elementToBeClickable(loginButton2));
        loginButton2.click();

        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(composeButton));
        WebElement composeBtn = driver.findElement(composeButton);
        return composeBtn.isDisplayed();
    }


}
