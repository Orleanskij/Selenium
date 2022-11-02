import dto.User;
import io.qameta.allure.Description;
import io.qameta.allure.TmsLink;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.*;

import static util.Constants.*;
import static util.RandomFieldPopulator.*;
import static util.TestUtils.getUser;

@Listeners(TestListener.class)
public class AutomationPracticeTest extends BaseTest {
    private AccountPage accountPage;
    private WishListPage wishlistPage;
    private ProductPage productPage;
    private CartPage cartPage;
    private String wishListName = "orleanWishlist";

    @Test(groups = "registration test group")
    @TmsLink(value = "AP-001")
    @Description(value = "create account Test")
    public void createAccountTest() {
        User user = getUser(USA_USER);
        loginPage.fillEmailField(generateEmail());
        accountPage = loginPage.clickCreateAnAccount();
        accountPage.createUser(user);
        accountPage.clickRegisterButton();
        Assert.assertTrue(accountPage.isMyAccountLabelDisplayed());
    }

    @Test(groups = "Login test group")
    @TmsLink(value = "AP-002")
    @Description(value = "Log in Test")
    public void logIntoAccountTest() {
        User user = getUser(EXISTING_USER);
        loginPage.logIn(user.getEmail(), user.getPassword());
        loginPage.clickSignInButton();
        Assert.assertTrue(loginPage.isSignOutButtonDisplayed());
    }

    @Test(groups = "WishList group")
    @TmsLink(value = "AP-003")
    @Description(value = "Auto-Creating Wishlist Test")
    public void createAutoCreatedWishlistTest() {
        User user = getUser(EXISTING_USER);
        loginPage.logIn(user.getEmail(), user.getPassword());
        accountPage = loginPage.clickSignInButton();
        wishlistPage = accountPage.navigateToWishlistPage();
        wishlistPage.clearWishList();
        productPage = wishlistPage.NavigateToTSHIRTSCategory();
        productPage.openRandomProduct();
        productPage.clickAddToWishList();
        accountPage = productPage.navigateToAccountMenu();
        wishlistPage = accountPage.navigateToWishlistPage();
        Assert.assertTrue(wishlistPage.isWishListAdded());
    }

    @Test(groups = "WishList group")
    @TmsLink(value = "AP-004")
    @Description(value = "Creating Wishlist Test")
    public void createWishlistTest() {
        User user = getUser(EXISTING_USER);
        loginPage.logIn(user.getEmail(), user.getPassword());
        accountPage = loginPage.clickSignInButton();
        wishlistPage = accountPage.navigateToWishlistPage();
        wishlistPage.clearWishList();
        wishlistPage.createWishList(wishListName);
        productPage = wishlistPage.NavigateToTSHIRTSCategory();
        productPage.openRandomProduct();
        productPage.clickAddToWishList();
        accountPage = productPage.navigateToAccountMenu();
        wishlistPage = accountPage.navigateToWishlistPage();
        Assert.assertTrue(wishlistPage.doesWishListContain(wishListName));
    }

    @Test(groups = "ProductCart group")
    @TmsLink(value = "AP-005")
    @Description(value = "Adding product Test")
    public void addProductsToCartTest() {
        User user = getUser(USA_USER);
        loginPage.fillEmailField(generateEmail());
        accountPage = loginPage.clickCreateAnAccount();
        accountPage.createUser(user);
        accountPage.clickRegisterButton();
        productPage = accountPage.NavigateToDRESSCategory();
        productPage.addProductCart(3);
        cartPage = productPage.navigateToCart();
        Assert.assertEquals(3, cartPage.getItemsQty());
        Assert.assertTrue(cartPage.getCartTotal() == cartPage.getItemsTotal());
    }
}
