package com.placelab.pages;

import org.openqa.selenium.WebDriver;

public class ReportCreationLoadingPage {
    private final static String LOADING_REPORT_CREATION_LINK = "https://demo.placelab.com/progress/";
    private WebDriver driver;
    public ReportCreationLoadingPage(final WebDriver webDriver) {
        this.driver = webDriver;
    }
    public boolean isUserRedirectedToProgressPage() {
        return driver.getCurrentUrl().contains(LOADING_REPORT_CREATION_LINK);
    }
}
