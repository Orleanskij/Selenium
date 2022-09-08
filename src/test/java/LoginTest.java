import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.LoginPage;
import util.Constants;

import java.time.Duration;
import java.util.*;
import java.util.stream.Collectors;

import static util.Constants.DOWNLOAD2_URL;
import static util.Constants.DOWNLOAD_URL;


public class LoginTest {
    WebDriver driver;

    public static final By CLICK_ME_BUTTON = By.xpath("//button[@class='btn btn-default btn-lg' and text() = 'Click me!']");
    public static final By CLICK_ALERT_BUTTON = By.xpath("//button[contains(text(), 'Prompt Box')]");
    public static final By CLICK_ME_RANDOM_BUTTON = By.xpath("//button[@class='btn btn-default']");
    public static final By PERCENTAGE = By.xpath("//div[@class='percenttext']");
    public static final By CLICK_PERCENT_BUTTON = By.id("cricle-btn");
    public static final By NEW_USER = By.xpath("//div[@id='loading']/img");
    public static final By RESULT_MESSAGE = By.id("confirm-demo");
    public static final By RESULT_MESSAGE_ALERT = By.id("prompt-demo");
    public static final By STATES = By.name("States");


    @BeforeMethod(alwaysRun = true)
    public void preparingData() {
        WebDriverManager.getInstance(ChromeDriver.class).setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }

    @DataProvider(name = "authentication")
    public Object[][] dpMethod() {
        return new Object[][]{
                {Constants.LOGIN_USER, Constants.PASSWORD_USER},
                {Constants.LOGIN_USER2, Constants.PASSWORD_USER2}};
    }

    @Test(dataProvider = "authentication")
    public void loginTest(String username, String password) throws InterruptedException {
        driver.get(Constants.YANDEX_URL);
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(username, password);
        Assert.assertTrue(loginPage.isComposeButtonDisplayed());
    }

    @Test
    public void multiSelectTest() {
        driver.get(Constants.MULTI_SELECT_URL);
        WebElement selectElement = driver.findElement(STATES);
        Select select = new Select(selectElement);

        List<String> expectedOptions = getRandomElements(3, select.getOptions().stream().map(WebElement::getText).collect(Collectors.toList()));
        expectedOptions.forEach(select::selectByVisibleText);
        List<String> selectedOptions = (select.getAllSelectedOptions().stream().map(WebElement::getText).collect(Collectors.toList()));

        Collections.sort(selectedOptions);
        Collections.sort(expectedOptions);

        Assert.assertEquals(selectedOptions, expectedOptions);
    }

    public List<String> getRandomElements(int amount, List<String> list) {
        return new Random().ints(amount, 0, list.size()).boxed()
                .map(i -> list.get(i)).collect(Collectors.toList());
    }

    @Test
    public void javaScriptConfirmBoxOKTest() {
        driver.get(Constants.ALERTS_URL);
        WebElement clickMeButton = driver.findElement(CLICK_ME_BUTTON);
        clickMeButton.click();
        driver.switchTo().alert().accept();
        WebElement resultMessage = driver.findElement(RESULT_MESSAGE);
        Assert.assertEquals("You pressed OK!", resultMessage.getText());
    }

    @Test
    public void javaScriptConfirmBoxCancelTest() {
        driver.get(Constants.ALERTS_URL);
        WebElement clickMeButton = driver.findElement(CLICK_ME_BUTTON);
        clickMeButton.click();
        driver.switchTo().alert().dismiss();
        WebElement resultMessage = driver.findElement(RESULT_MESSAGE);
        Assert.assertEquals("You pressed Cancel!", resultMessage.getText());
    }

    @Test
    public void javaScriptAlertBoxTest() {
        driver.get(Constants.ALERTS_URL);
        WebElement clickMeButton = driver.findElement(CLICK_ALERT_BUTTON);
        clickMeButton.click();
        driver.switchTo().alert().sendKeys("Peter");
        driver.switchTo().alert().accept();
        WebElement resultMessage = driver.findElement(RESULT_MESSAGE_ALERT);
        Assert.assertEquals("You have entered 'Peter' !", resultMessage.getText());
    }

    @Test
    public void getRandomUserTest() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get(Constants.USERS_URL);
        WebElement clickMeButton = driver.findElement(CLICK_ME_RANDOM_BUTTON);
        clickMeButton.click();
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(NEW_USER)));
        WebElement newParticipant = driver.findElement(NEW_USER);
        Assert.assertTrue(newParticipant.isDisplayed());
    }

    @Test
    public void downloadTest() {
        driver.get(DOWNLOAD_URL);
        WebElement clickMeButton = driver.findElement(CLICK_PERCENT_BUTTON);
        WebElement percent = driver.findElement(PERCENTAGE);

        clickMeButton.click();
        new WebDriverWait(driver, Duration.ofSeconds(15), Duration.ofMillis(100))
                .until(ExpectedConditions.textToBePresentInElement(percent, "50%"));
        driver.navigate().refresh();
    }

    @Test
    public void downloadTestx() {
        driver.get(DOWNLOAD2_URL);
        WebElement dropdown = driver.findElement(By.name("example_length"));
        Select s = new Select(dropdown);
        s.selectByValue("10");
        Assert.assertTrue(!getResultList(60, 400000).isEmpty());
    }

    public List<Employee> getResultList(int minAge, int maxSalary) {
        List<Employee> results = new ArrayList<>();

        WebElement nextBtn = driver.findElement(By.id("example_next"));
        while (!nextBtn.getAttribute("class").contains("disabled")) {
            results.addAll(getEmployee(minAge, maxSalary));
            nextBtn.click();
            nextBtn = driver.findElement(By.id("example_next"));
        }
        results.addAll(getEmployee(minAge, maxSalary));
        return results;
    }

    public List<Employee> getEmployee(int minAge, int maxSalary) {
        List<Employee> results = new ArrayList<>();
        List<WebElement> rows = driver.findElements(By.xpath("//table[@id='example']/tbody/tr"));
        for (WebElement row : rows) {
            int age = Integer.parseInt(row.findElement(By.xpath(".//td[4]")).getText());
            int salary = Integer.parseInt(row.findElement(By.xpath(".//td[6]")).getAttribute("data-order"));

            if (age > minAge && salary <= maxSalary) {
                String name = row.findElement(By.xpath(".//td[1]")).getText();
                String position = row.findElement(By.xpath(".//td[2]")).getText();
                String office = row.findElement(By.xpath(".//td[3]")).getText();
                Employee employee = new Employee(name, position, office);
                results.add(employee);
            }
        }
        return results;
    }

    @AfterMethod(alwaysRun = true)
    public void afterMethod() {
        driver.quit();
    }
}
