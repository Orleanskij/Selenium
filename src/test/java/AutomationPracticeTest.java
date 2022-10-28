import io.qameta.allure.Description;
import io.qameta.allure.TmsLink;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;
import pages.*;


import java.net.MalformedURLException;

import static util.Constants.*;
import static util.RandomFieldPopulator.*;

@Listeners(TestListener.class)
public class AutomationPracticeTest {
    public WebDriver driver;


    @BeforeClass(alwaysRun = true)
    public void preparingData() throws MalformedURLException {
        Driver drv = Driver.getInstanceOfDriver();
        driver = drv.getDriver();
        driver.manage().window().maximize();
    }


    @Test(groups = "registration test group")
    @TmsLink(value = "AP-001")
    @Description(value = "create account Test")
    public void createAccountTest() {
        driver.get(AUTOMATION_PRACTICE_ACCOUNT_URL);
        LoginPage loginPage = new LoginPage(driver);
        AccountPage accountPage = new AccountPage(driver);
        accountPage.fillEmailField(generateEmail());
        loginPage.clickCreateAnAccount();
        accountPage.fillRequiredFields();
        accountPage.clickRegisterButton();

        Assert.assertTrue(accountPage.isMyAccountLabelDisplayed());
    }

    @Test(groups = "Login test group")
    @TmsLink(value = "AP-002")
    @Description(value = "Log in Test")
    public void logIntoAccountTest() {
        driver.get(AUTOMATION_PRACTICE_ACCOUNT_URL);
        LoginPage loginPage = new LoginPage(driver);
        loginPage.logInTheSystem(EMAIL_USER, PASSWORD_USER);
        loginPage.clickSignInButton();

        Assert.assertTrue(loginPage.isSignOutButtonDisplayed());
    }

    @Test(groups = "WishList group")
    @TmsLink(value = "AP-003")
    @Description(value = "Auto-Creating Wishlist Test")
    public void createAutoCreatedWishlistTest() {
        driver.get(AUTOMATION_PRACTICE_ACCOUNT_URL);
        LoginPage loginPage = new LoginPage(driver);
        loginPage.logInTheSystem(EMAIL_USER, PASSWORD_USER);
        loginPage.clickSignInButton();
        AccountPage accountPage = new AccountPage(driver);
        MenuPage menuPage = new MenuPage(driver);
        menuPage.navigateTo(MY_WISHLIST_URL);
        accountPage.clearWishList();
        menuPage.navigateTo(T_SHIRT_MENU_URL);
        menuPage.openFirstProduct();
        menuPage.clickToAddToWishList();
        menuPage.navigateTo(MY_WISHLIST_URL);

        Assert.assertTrue(accountPage.isAddedWishList());
    }

    @Test(groups = "WishList group")
    @TmsLink(value = "AP-004")
    @Description(value = "Creating Wishlist Test")
    public void createWishlistTest() {
        driver.get(AUTOMATION_PRACTICE_ACCOUNT_URL);
        LoginPage loginPage = new LoginPage(driver);
        loginPage.logInTheSystem(EMAIL_USER, PASSWORD_USER);
        loginPage.clickSignInButton();
        AccountPage accountPage = new AccountPage(driver);
        MenuPage menuPage = new MenuPage(driver);
        menuPage.navigateTo(MY_WISHLIST_URL);
        accountPage.clearWishList();
        String wishListName = "orleanWishlist";
        accountPage.createWishList(wishListName);
        menuPage.navigateTo(T_SHIRT_MENU_URL);
        menuPage.openFirstProduct();
        menuPage.clickToAddToWishList();
        menuPage.navigateTo(MY_WISHLIST_URL);

        Assert.assertTrue(accountPage.isProductAddedToWishList(wishListName));
    }

    @Test(groups = "ProductCart group")
    @TmsLink(value = "AP-005")
    @Description(value = "Adding product Test")
    public void addProductsToCartTest() {
        driver.get(AUTOMATION_PRACTICE_ACCOUNT_URL);
        LoginPage loginPage = new LoginPage(driver);
        loginPage.logInTheSystem(EMAIL_USER, PASSWORD_USER);
        loginPage.clickSignInButton();
        MenuPage menuPage = new MenuPage(driver);
        menuPage.navigateTo(SUMMER_DRESS_CATEGORY_URL);
        ProductPage productPage = new ProductPage(driver);
        productPage.addProductCart(3);
        int total = productPage.getTotal();
        menuPage.navigateTo(CART_URL);
        CartPage cartPage = new CartPage(driver);
        Assert.assertTrue(cartPage.isAllProductsInTheCart(3));
        Assert.assertTrue(cartPage.getTotalPrice() == total);
    }


    @AfterClass(alwaysRun = true)
    public void afterMethod(ITestResult result) {
        driver.quit();
    }
}
