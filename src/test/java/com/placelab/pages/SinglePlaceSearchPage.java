package com.placelab.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.security.Key;
import java.sql.Driver;
import java.time.Duration;
import java.util.List;
import java.util.Random;

public class SinglePlaceSearchPage {
    // Using SPS as Single Place Search shortcut
    private final static String SPS_LINK = "https://demo.placelab.com/places/single_place_searches/new";
    private final static By REPORT_CONTENT_FORM = By.id("sp_query");
    private final static By REPORT_HEADER = By.cssSelector("div.report-header");
    private static final String EXPECTED_REPORT_NAME_FIELD_TEXT = "Enter report name...";
    private static final String EXPECTED_PLACE_NAME_FIELD_TEXT = "Place Name";
    private static final String EXPECTED_LOCATION_FIELD_TEXT = "e.g. W Illinois St, Chicago";
    private final static By REPORT_NAME_INPUT = By.id("name");
    private final static By REPORT_PLACE_NAME_INPUT = By.id("single_text");
    private final static By REPORT_PHONE_INPUT = By.id("single_phone");
    private final static By REPORT_CATEGORY_DROPDOWN_BUTTON = By.cssSelector("div.btn-group > button.multiselect > b.caret");
    private final static By REPORT_CATEGORY_LIST = By.cssSelector("ul > div.options-wrapper");
    private final static By REPORT_REQUIRED_LOCATION = By.id("location_name");
    private final static By REPORT_LATITUDE_INPUT = By.id("city_lat");
    private final static By REPORT_LONGITUDE_INPUT = By.id("city_lng");
    private final static By CREATE_REPORT_BUTTON = By.xpath("//button[contains(text(), 'Create Report')]");
    private final static By BUTTON_YES_FOR_CONFIRMING_ADDRESS = By.xpath("//button[contains(text(), 'Yes')]");
    private final static By SUGGESTED_ADDRESS_FROM_DROPDOWN = By.xpath("//ul[@class='typeahead dropdown-menu']//li[@class='active']");


    private WebDriver driver;

    public SinglePlaceSearchPage(final WebDriver webDriver) {
        this.driver = webDriver;
    }

    public void validateSinglePlaceSearchPageContent() {
        final WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        Assert.assertTrue(driver.findElement(REPORT_HEADER).isDisplayed(), "Validation if header is displayed.");

        Assert.assertTrue(driver.findElement(REPORT_CONTENT_FORM).isDisplayed(), "Validation if content form is displayed.");

        Assert.assertTrue(driver.findElement(REPORT_NAME_INPUT).isDisplayed(), "Validation if report name is displayed.");
        Assert.assertEquals(driver.findElement(REPORT_NAME_INPUT).getAttribute("placeholder"), EXPECTED_REPORT_NAME_FIELD_TEXT);

        Assert.assertTrue(driver.findElement(REPORT_PLACE_NAME_INPUT).isDisplayed(), "Validation if place name is displayed.");
        Assert.assertEquals(driver.findElement(REPORT_PLACE_NAME_INPUT).getAttribute("placeholder"), EXPECTED_PLACE_NAME_FIELD_TEXT);

        Assert.assertTrue(driver.findElement(REPORT_PHONE_INPUT).isDisplayed(), "Validation if phone is displayed.");

        Assert.assertTrue(driver.findElement(REPORT_REQUIRED_LOCATION).isDisplayed(), "Validation if required location is displayed.");
        Assert.assertEquals(driver.findElement(REPORT_REQUIRED_LOCATION).getAttribute("placeholder"), EXPECTED_LOCATION_FIELD_TEXT);

        Assert.assertTrue(driver.findElement(REPORT_LATITUDE_INPUT).isDisplayed(), "Validation if latitude is displayed.");
        Assert.assertTrue(driver.findElement(REPORT_LONGITUDE_INPUT).isDisplayed(), "Validation if longitude is displayed.");

        Assert.assertTrue(driver.findElement(CREATE_REPORT_BUTTON).isDisplayed(), "Validation if button is displayed.");
    }

    public void validateSinglePlaceSearchPageLink() {
        Assert.assertEquals(driver.getCurrentUrl(), SPS_LINK, "Validation if if the user is on Login page.");
    }

    public boolean isCreateReportButtonEnabled() {
        return driver.findElement(CREATE_REPORT_BUTTON).isEnabled();
    }

    public void fullyFillReportForm(final String reportName, final String placeName, final String phoneNumber, final String location) {
        final WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.findElement(REPORT_NAME_INPUT).sendKeys(reportName);
        driver.findElement(REPORT_PHONE_INPUT).sendKeys(phoneNumber);

        Assert.assertFalse(isCreateReportButtonEnabled(), "Validate that Create report button is not enabled after entering report name, place name and phone.");
        fillOnlyLocationAndName(location, placeName);
    }

    //Testing report creation with only required information
    public void fillOnlyLocationAndName(final String location, final String placeName) {
        final WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.findElement(REPORT_PLACE_NAME_INPUT).sendKeys(placeName);
        driver.findElement(REPORT_REQUIRED_LOCATION).sendKeys(location);
        driver.findElement(REPORT_REQUIRED_LOCATION).sendKeys(Keys.ENTER);


        wait.until(ExpectedConditions.visibilityOfElementLocated(SUGGESTED_ADDRESS_FROM_DROPDOWN));
        Assert.assertTrue(driver.findElement(SUGGESTED_ADDRESS_FROM_DROPDOWN).isDisplayed(), "Validate if suggested location section is displayed");
        driver.findElement(SUGGESTED_ADDRESS_FROM_DROPDOWN).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(BUTTON_YES_FOR_CONFIRMING_ADDRESS));
        Assert.assertTrue(driver.findElement(BUTTON_YES_FOR_CONFIRMING_ADDRESS).isDisplayed(), "Validate if location confirmation button is displayed");
        driver.findElement(BUTTON_YES_FOR_CONFIRMING_ADDRESS).click();

        Assert.assertTrue(driver.findElement(REPORT_CATEGORY_DROPDOWN_BUTTON).isDisplayed(), "Validate if category dropdown menu is displayed.");
        driver.findElement(REPORT_CATEGORY_DROPDOWN_BUTTON).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(selectRandomCategory()));
        this.driver.findElement(selectRandomCategory()).click();

        Assert.assertTrue(isCreateReportButtonEnabled(), "Validate that Create report button is not enabled after entering location.");
        driver.findElement(CREATE_REPORT_BUTTON).click();
    }

    //Used this method to select random category from Aldin as I didn't come up with better solution so far
    private By selectRandomCategory() {
        final List<String> listOfCategories = driver.findElement(REPORT_CATEGORY_LIST).getText().lines().toList();
        final Random randomNumber = new Random();
        return By.xpath("//label[contains(text(), '" + listOfCategories.get(randomNumber.nextInt(0, listOfCategories.size() - 1)) + "')]");
    }

}
