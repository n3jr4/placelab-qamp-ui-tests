package com.placelab.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ReportCreationLoadingPage {
    private final static String LOADING_REPORT_CREATION_LINK = "https://demo.placelab.com/progress/";
    private WebDriver driver;
    private static WebDriverWait wait;

    public ReportCreationLoadingPage(final WebDriver webDriver) {
        this.driver = webDriver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(60));
    }

    public boolean isUserRedirectedToProgressPage() {
        wait.until(ExpectedConditions.urlToBe(LOADING_REPORT_CREATION_LINK));
        return driver.getCurrentUrl().contains(LOADING_REPORT_CREATION_LINK);
    }

    public String getReportID() {
        wait.until(ExpectedConditions.urlContains(LOADING_REPORT_CREATION_LINK));
        return driver.getCurrentUrl().substring(driver.getCurrentUrl().lastIndexOf("/") + 1);
    }
}
