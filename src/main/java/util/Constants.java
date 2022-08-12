package util;

import org.openqa.selenium.By;

public class Constants {


    public static final String YANDEX_URL = "https://mail.yandex.com/";
    public static final String LOGIN_USER = "petrorlyansky";
    public static final String PASSWORD_USER = "Ctktybev";

    /**
     * Locators
     */
    public static final By LOGIN_BUTTON = By.xpath("//div[@class='HeadBanner-ButtonsWrapper']/a[2]");
    public static final By SUBMIT_BUTTON = By.cssSelector("[type=submit]");
    public static final By LOGIN_FIELD = By.name("login");
    public static final By PASSWORD_FIELD = By.xpath("//input[@name='passwd']");
    public static final By SIG_IN_BUTTON = By.id("passp:sign-in");
    public static final By COMPOSE_BUTTON = By.xpath("//a[@href='#compose']");
    public static final By LOCATOR = By.linkText("Light version");
    public static final By LOCATOR2 = By.partialLinkText("support");
    public static final By LOCATOR3 = By.className("HeadBanner-ButtonsWrapper");
    public static final By LOCATOR4 = By.tagName("style");
}
