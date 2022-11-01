import dto.User;
import io.qameta.allure.Description;
import io.qameta.allure.TmsLink;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.*;


import java.net.MalformedURLException;

import static util.Constants.*;
import static util.RandomFieldPopulator.*;
import static util.UserUtils.getUser;

@Listeners(TestListener.class)
public class AutomationPracticeTest extends BaseTest{

    @Test(groups = "registration test group")
    @TmsLink(value = "AP-001")
    @Description(value = "create account Test")
    public void createAccountTest() throws MalformedURLException {
        LoginPage loginPage = new LoginPage();
        AccountPage accountPage = new AccountPage();
        User user = getUser(USA_USER);
        accountPage.fillEmailField(generateEmail());
        loginPage.clickCreateAnAccount();
        accountPage.createUser(user);
        accountPage.clickRegisterButton();

        Assert.assertTrue(accountPage.isMyAccountLabelDisplayed());
    }

    @Test(groups = "Login test group")
    @TmsLink(value = "AP-002")
    @Description(value = "Log in Test")
    public void logIntoAccountTest() throws MalformedURLException {
        LoginPage loginPage = new LoginPage();
        User user = getUser(EXIST_USER);
        loginPage.logInTheSystem(user.getEmail(), user.getPassword());
        loginPage.clickSignInButton();

        Assert.assertTrue(loginPage.isSignOutButtonDisplayed());
    }

    @Test(groups = "WishList group")
    @TmsLink(value = "AP-003")
    @Description(value = "Auto-Creating Wishlist Test")
    public void createAutoCreatedWishlistTest() throws MalformedURLException{
        LoginPage loginPage = new LoginPage();
        User user = getUser(EXIST_USER);
        loginPage.logInTheSystem(user.getEmail(), user.getPassword());
        loginPage.clickSignInButton();
        AccountPage accountPage = new AccountPage();
        ItemPage menuPage = new ItemPage();
        menuPage.navigateTo(MY_WISHLIST_URL);
        accountPage.clearWishList();
        menuPage.navigateTo(T_SHIRT_MENU_URL);
        menuPage.openRandomProduct();
        menuPage.clickToAddToWishList();
        menuPage.navigateTo(MY_WISHLIST_URL);

        Assert.assertTrue(accountPage.isWishListAdded());
    }

    @Test(groups = "WishList group")
    @TmsLink(value = "AP-004")
    @Description(value = "Creating Wishlist Test")
    public void createWishlistTest() throws MalformedURLException {
        LoginPage loginPage = new LoginPage();
        User user = getUser(EXIST_USER);
        loginPage.logInTheSystem(user.getEmail(), user.getPassword());
        loginPage.clickSignInButton();
        AccountPage accountPage = new AccountPage();
        ItemPage menuPage = new ItemPage();
        menuPage.navigateTo(MY_WISHLIST_URL);
        accountPage.clearWishList();
        String wishListName = "orleanWishlist";
        accountPage.createWishList(wishListName);
        menuPage.navigateTo(T_SHIRT_MENU_URL);
        menuPage.openRandomProduct();
        menuPage.clickToAddToWishList();
        menuPage.navigateTo(MY_WISHLIST_URL);

        Assert.assertTrue(accountPage.isProductNameDisplayedUnWishList(wishListName));
    }

    @Test(groups = "ProductCart group")
    @TmsLink(value = "AP-005")
    @Description(value = "Adding product Test")
    public void addProductsToCartTest() throws MalformedURLException{
        LoginPage loginPage = new LoginPage();
        User user = getUser(EXIST_USER);
        loginPage.logInTheSystem(user.getEmail(), user.getPassword());
        loginPage.clickSignInButton();
        ItemPage menuPage = new ItemPage();
        menuPage.navigateTo(SUMMER_DRESS_CATEGORY_URL);
        ProductPage productPage = new ProductPage();
        productPage.addProductCart(3);
        menuPage.navigateTo(CART_URL);
        CartPage cartPage = new CartPage();
        Assert.assertTrue(cartPage.isAllProductsInTheCart(3));
        Assert.assertTrue(cartPage.getTotalPrice() == cartPage.getSumPricesInCart());
    }
}
