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
    private RegistrationPage registrationPage;
    private WishListPage wishlistPage;
    private ProductPage productPage;
    private StorePage storePage;
    private CartPage cartPage;
    private String wishListName = "orleanWishlist";

    @Test(groups = "registration test group")
    @TmsLink(value = "AP-001")
    @Description(value = "create account Test")
    public void createAccountTest() {
        User user = getUser(USA_USER);
        loginPage.fillEmailField(generateEmail());
        registrationPage = loginPage.clickCreateAnAccount();
        registrationPage.createUser(user);
        accountPage = registrationPage.clickRegisterButton();
        Assert.assertTrue(accountPage.isMyAccountLabelDisplayed());
    }

    @Test(groups = "Login test group")
    @TmsLink(value = "AP-002")
    @Description(value = "Log in Test")
    public void logIntoAccountTest() {
        User user = getUser(EXISTING_USER);
        loginPage.logIn(user.getEmail(), user.getPassword());
        accountPage = loginPage.clickSignInButton();
        Assert.assertTrue(accountPage.isSignOutButtonDisplayed());
    }

    @Test(groups = "WishList group")
    @TmsLink(value = "AP-003")
    @Description(value = "Auto-Creating Wishlist Test")
    public void createAutoCreatedWishlistTest() {
        User user = getUser(EXISTING_USER);
        loginPage.logIn(user.getEmail(), user.getPassword());
        accountPage = loginPage.clickSignInButton();
        wishlistPage = accountPage.getHeader().navigateToWishlistPage();
        wishlistPage.clearWishList();
        storePage = wishlistPage.getHeader().navigateToCategory(WOMEN_CATEGORY);
        productPage = storePage.openRandomProduct();
        productPage.clickAddToWishList();
        accountPage = productPage.getHeader().navigateToAccountMenu();
        wishlistPage = accountPage.getHeader().navigateToWishlistPage();
        Assert.assertTrue(wishlistPage.isWishListAdded());
    }

    @Test(groups = "WishList group")
    @TmsLink(value = "AP-004")
    @Description(value = "Creating Wishlist Test")
    public void createWishlistTest() {
        User user = getUser(EXISTING_USER);
        loginPage.logIn(user.getEmail(), user.getPassword());
        accountPage = loginPage.clickSignInButton();
        wishlistPage = accountPage.getHeader().navigateToWishlistPage();
        wishlistPage.clearWishList();
        wishlistPage.createWishList(wishListName);
        storePage = wishlistPage.getHeader().navigateToCategory(WOMEN_CATEGORY);
        productPage = storePage.openRandomProduct();
        productPage.clickAddToWishList();
        accountPage = productPage.getHeader().navigateToAccountMenu();
        wishlistPage = accountPage.getHeader().navigateToWishlistPage();
        Assert.assertTrue(wishlistPage.doesWishListContain(wishListName));
    }

    @Test(groups = "ProductCart group")
    @TmsLink(value = "AP-005")
    @Description(value = "Adding product Test")
    public void addProductsToCartTest() {
        User user = getUser(USA_USER);
        loginPage.fillEmailField(generateEmail());
        registrationPage = loginPage.clickCreateAnAccount();
        registrationPage.createUser(user);
        accountPage = registrationPage.clickRegisterButton();
        storePage = accountPage.getHeader().navigateToCategory(WOMEN_CATEGORY);
        storePage.addProductCart(3);
        cartPage = storePage.getHeader().navigateToCart();
        Assert.assertEquals(3, cartPage.getItemsQty());
        Assert.assertEquals(cartPage.getCartTotal(), cartPage.getItemsTotal());
    }
}
