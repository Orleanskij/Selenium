package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;


public class LoginPage {

    private By loginBtn = By.xpath("//div[@class='HeadBanner-ButtonsWrapper']/a[2]");
    private By submitBtn = By.cssSelector("[type=submit]");
    private By loginField = By.name("login");
//    private By passwordField = By.className("Textinput-Control");
//    I didn't use it, just for display , also I didn't use By.tagNme because the test became instability
    private By passwordField = By.xpath("//input[@name='passwd']");
    private By sigInBtn = By.id("passp:sign-in");
    private By composeButton = By.xpath("//a[@href='#compose']");


    WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void login(String login, String password) {
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        WebElement loginButtonOnStartPage = driver.findElement(loginBtn);
        loginButtonOnStartPage.click();
        WebElement loginInput = driver.findElement(loginField);
        loginInput.sendKeys(login);
        WebElement submitButton = driver.findElement(submitBtn);
        submitButton.click();
        WebElement passwordF = driver.findElement(passwordField);
        passwordF.sendKeys(password);
        WebElement loginButton2 = driver.findElement(sigInBtn);
        loginButton2.click();
    }

    public boolean isComposeButtonDisplayed(){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(composeButton));
        WebElement composeBtn = driver.findElement(composeButton);
        return composeBtn.isDisplayed();
    }


}
