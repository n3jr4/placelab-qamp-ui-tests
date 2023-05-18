package com.placelab.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

public class QueriesPage {
    private final static String QUERIES_PAGE_LINK = "https://demo.placelab.com/queries";
    private final static By SEARCH_QUERIES_BY_REPORTNAME = By.id("query_search_name");
    private final static By CHECKBOX_FOR_REPORTNAME = By.cssSelector("#table_queries > tbody > tr > td.large > div");
    private final static By DELETE_BUTTON_ON_QUERIES = By.cssSelector("#action-delete > a > i");
    ;
    private final static By CONFIRM_DELETE_BUTTON = By.xpath("//*[@id='confirm-link']");
    private WebDriver driver;
    private static WebDriverWait wait;

    public QueriesPage(final WebDriver webDriver) {
        this.driver = webDriver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(60));
    }

    public void deleteReport(final String reportName) {
        wait.until(ExpectedConditions.urlToBe(QUERIES_PAGE_LINK));
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(SEARCH_QUERIES_BY_REPORTNAME))).click();
        driver.findElement(SEARCH_QUERIES_BY_REPORTNAME).sendKeys(reportName);
        driver.findElement(SEARCH_QUERIES_BY_REPORTNAME).sendKeys(Keys.ENTER);
        wait.until(ExpectedConditions.elementToBeClickable(CHECKBOX_FOR_REPORTNAME)).click();
        wait.until(ExpectedConditions.elementToBeClickable((DELETE_BUTTON_ON_QUERIES))).click();
        wait.until(ExpectedConditions.elementToBeClickable((CONFIRM_DELETE_BUTTON))).click();
    }

}
