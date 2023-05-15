package com.placelab.pages;

import org.checkerframework.checker.index.qual.PolyUpperBound;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class HomePage {
    private WebDriver driver;
    private final By USER_ROLE = By.id("user-role");
    private static final By USER_DROPDOWN_MENU = By.cssSelector("div#user-name > i.icon-angle-down");
    private static final By SIGN_OUT_FIELD = By.linkText("Sign out");
    private final String EXPECTED_HOMEPAGE_LINK = "https://demo.placelab.com/dashboard/traffic";
    private final String EXPECTED_HOMEPAGE_TITLE = "PlaceLab - demo";
    private static final By CREATE_REPORT_DROPDOWN = By.cssSelector("a#create-menu > i.icon-angle-down");
    ;
    private static final By SINGLE_PLACE_SEARCH = By.id("singleplacesearch");

    public HomePage(final WebDriver webDriver) {
        this.driver = webDriver;
    }

    public void validateHomePageLink() {
        Assert.assertEquals(driver.getCurrentUrl(), EXPECTED_HOMEPAGE_LINK, "Validation if user is on Homepage link.");
    }

    public void validateHomePageTitle() {
        Assert.assertEquals(driver.getTitle(), EXPECTED_HOMEPAGE_TITLE, "Validation if correct title is displayed.");
    }

    public void validateUserRole(final String expectedUserRole) {
        final String actualUserRole = driver.findElement(USER_ROLE).getText();
        Assert.assertEquals(actualUserRole, expectedUserRole, "Validation if user role is equal to expected after login.");
    }

    public void clickOnUserDropdownMenu() {
        Assert.assertTrue(driver.findElement(USER_DROPDOWN_MENU).isDisplayed(), "Validate dropdown menu  arrow is displayed. ");
        driver.findElement(USER_DROPDOWN_MENU).click();
    }

    public void signOut() {
        this.clickOnUserDropdownMenu();
        Assert.assertTrue(driver.findElement(SIGN_OUT_FIELD).isDisplayed(), "Validate is Sign out button displayed.");
        this.driver.findElement(SIGN_OUT_FIELD).click();
    }

    public void createReportDropdownClick() {
        final WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(CREATE_REPORT_DROPDOWN));
        Assert.assertTrue(driver.findElement(CREATE_REPORT_DROPDOWN).isEnabled(), "Validate if Create report dropdown is displayed.");
        driver.findElement(CREATE_REPORT_DROPDOWN).click();
    }

    public void singlePlaceSearchReportClick() {
        final WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(SINGLE_PLACE_SEARCH));
        Assert.assertTrue(driver.findElement(SINGLE_PLACE_SEARCH).isDisplayed(), "Validate if Single Place Search option is displayed");
        driver.findElement(SINGLE_PLACE_SEARCH).click();
    }
}
