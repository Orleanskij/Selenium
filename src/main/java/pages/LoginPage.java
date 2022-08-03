package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.sql.Driver;
import java.util.concurrent.TimeUnit;

public class LoginPage {

    //    @FindBy(xpath = "//div[contains(@class,'x-border-layout-ct')]//span[text()='Error ID']")
//    private WebElement loginButton;
    private String loginBtn = "//div[@class='HeadBanner-ButtonsWrapper']/a[2]";
    private String submitBtn = "//button[@type='submit']";
    private String loginField = "//input[@name='login']";
    private String passwordField = "//input[@name='passwd']";
    private String logInBtn2 = "//button[@id='passp:sign-in']";
    private String composeButton = "//a[@href='#compose']";

//    @FindBy(xpath = "//button[@type='submit']")
//    private WebElement submitButton;
//
//    @FindBy(xpath = "//input[@name='login']")
//    private WebElement loginField;
//
//    @FindBy(xpath = "//input[@name='login']")
//    private WebElement passwordField;


    WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean login(String login, String password) {
        WebDriverWait wait = new WebDriverWait(driver, 10);

        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(loginBtn)));
        WebElement loginButtonOnStartPage = driver.findElement(By.xpath(loginBtn));
        wait.until(ExpectedConditions.elementToBeClickable(loginButtonOnStartPage));
        loginButtonOnStartPage.click();

        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(loginField)));
        WebElement loginInput = driver.findElement(By.xpath(loginField));
        loginInput.sendKeys(login);

        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(submitBtn)));
        WebElement submitButton = driver.findElement(By.xpath(submitBtn));
        wait.until(ExpectedConditions.elementToBeClickable(submitButton));
        submitButton.click();

        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(passwordField)));
        WebElement passwordF = driver.findElement(By.xpath(passwordField));
        passwordF.sendKeys(password);

        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(logInBtn2)));
        WebElement loginButton2 = driver.findElement(By.xpath(logInBtn2));
        wait.until(ExpectedConditions.elementToBeClickable(loginButton2));
        loginButton2.click();

        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(composeButton)));
        WebElement composeBtn = driver.findElement(By.xpath(composeButton));
        boolean result = composeBtn.isDisplayed();
        return result;
    }


}
