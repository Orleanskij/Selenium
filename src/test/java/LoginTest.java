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
import java.util.ArrayList;
import java.util.List;


public class LoginTest {
    WebDriver driver;

    @BeforeMethod(alwaysRun = true)
    public void preparingData() {
        WebDriverManager.getInstance(ChromeDriver.class).setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }

    @DataProvider(name = "authentication")
    public Object[][] dpMethod() {
        return new Object[][]{{Constants.LOGIN_USER, Constants.PASSWORD_USER}, {Constants.LOGIN_USER2, Constants.PASSWORD_USER2}};
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
        WebElement selectElement = driver.findElement(By.name("States"));
        Select select = new Select(selectElement);
        select.selectByValue("Florida");
        select.selectByValue("New Jersey");
        select.selectByValue("New York");
        List<WebElement> returnedOptions = select.getAllSelectedOptions();
        Assert.assertEquals(3, returnedOptions.size());
    }

    @Test
    public void javaScriptConfirmBoxOKTest() {
        driver.get(Constants.ALERTS_URL);
        WebElement clickMeButton = driver.findElement(By.xpath("//button[@class='btn btn-default btn-lg' and text() = 'Click me!']"));
        clickMeButton.click();
        driver.switchTo().alert().accept();
        WebElement resultMessage = driver.findElement(By.id("confirm-demo"));
        Assert.assertEquals("You pressed OK!", resultMessage.getText());
    }

    @Test
    public void javaScriptConfirmBoxCancelTest() {
        driver.get(Constants.ALERTS_URL);
        WebElement clickMeButton = driver.findElement(By.xpath("//button[@class='btn btn-default btn-lg' and text() = 'Click me!']"));
        clickMeButton.click();
        driver.switchTo().alert().dismiss();
        WebElement resultMessage = driver.findElement(By.id("confirm-demo"));
        Assert.assertEquals("You pressed Cancel!", resultMessage.getText());
    }

    @Test
    public void javaScriptAlertBoxTest() {
        driver.get(Constants.ALERTS_URL);
        WebElement clickMeButton = driver.findElement(By.xpath("//button[@class='btn btn-default btn-lg' and text() = 'Click for Prompt Box']"));
        clickMeButton.click();
        driver.switchTo().alert().sendKeys("Peter");
        driver.switchTo().alert().accept();
        WebElement resultMessage = driver.findElement(By.id("prompt-demo"));
        Assert.assertEquals("You have entered 'Peter' !", resultMessage.getText());
    }

    @Test
    public void getRandomUserTest() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        driver.get(Constants.USERS_URL);
        WebElement clickMeButton = driver.findElement(By.xpath("//button[@class='btn btn-default']"));
        clickMeButton.click();
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[@id='loading']/img"))));
        WebElement newUser = driver.findElement(By.xpath("//div[@id='loading']/img"));
        Assert.assertTrue(newUser.isDisplayed());
    }

    @Test
    public void downloadTest() {
        driver.get("https://demo.seleniumeasy.com/bootstrap-download-progress-demo.html");
        WebElement clickMeButton = driver.findElement(By.id("cricle-btn"));
        WebElement percentage = driver.findElement(By.xpath("//div[@class='percenttext']"));

        String parameter;
        int number = 0;
        clickMeButton.click();
        do {
            parameter = percentage.getText().substring(0, percentage.getText().length() - 1);
            number = Integer.parseInt(parameter);
        } while (number <= 50);
        driver.navigate().refresh();
    }

    @Test
    public void downloadTestx() {
        driver.get("https://demo.seleniumeasy.com/table-sort-search-demo.html");
        WebElement dropdown = driver.findElement(By.name("example_length"));
        Select s = new Select(dropdown);
        s.selectByValue("10");
        Assert.assertTrue(!showResultMethod(60, 400000).isEmpty());
    }

    public List<SortAndSearchDemo> showResultMethod(int x, int y) {
        List<SortAndSearchDemo> results = new ArrayList<>();
        List<WebElement> allElements = driver.findElements(By.xpath("//table[@id='example']/tbody/tr"));

        for (int row = 1; row <= allElements.size(); row++) {
            int age = Integer.parseInt(driver.findElement(By.xpath("//tbody//tr[" + row + "]/td[4]")).getText());
            if (age > x) {
                int salary = Integer.parseInt(driver.findElement(By.xpath("//tbody//tr[" + row + "]/td[6]")).getAttribute("data-order"));
                if (salary <= y) {
                    String name = driver.findElement(By.xpath("//tbody//tr[" + row + "]/td[1]")).getText();
                    String position = driver.findElement(By.xpath("//tbody//tr[" + row + "]/td[2]")).getText();
                    String office = driver.findElement(By.xpath("//tbody//tr[" + row + "]/td[3]")).getText();
                    SortAndSearchDemo sortAndSearchDemo = new SortAndSearchDemo(name, position, office);
                    results.add(sortAndSearchDemo);
                }
            }
        }
        return results;
    }

    @AfterMethod(alwaysRun = true)
    public void afterMethod() {
        driver.quit();
    }
}
