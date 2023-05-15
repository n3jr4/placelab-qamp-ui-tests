package com.placelab.tests;

import com.github.javafaker.Faker;
import com.placelab.pages.*;
import com.placelab.utils.WebDriverSetup;
import org.openqa.selenium.DeviceRotation;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.Random;

public class SinglePlaceSearchSmokeTest {
    private WebDriver driver;
    private static LoginPage loginPage;
    private static HomePage homePage;
    private static SinglePlaceSearchPage singlePlaceSearchPage;
    private static ReportCreationLoadingPage reportCreationLoadingPage;

    @Parameters("browser")
    @BeforeMethod(alwaysRun = true)
    private void setDriver(final String browser) {
        this.driver = WebDriverSetup.getWebDriver(browser);
        this.driver.get("https://demo.placelab.com");
        this.loginPage = new LoginPage(driver);
        this.homePage = new HomePage(driver);
        this.singlePlaceSearchPage = new SinglePlaceSearchPage(driver);
        this.reportCreationLoadingPage = new ReportCreationLoadingPage(driver);
    }

    @Parameters({"email", "password"})
    @Test(priority = 1, description = "Validate if it is possible to create report.")
    public void createSinglePlaceSearchReport(final String email, final String password) {
        loginPage.validateLoginPageContent();
        loginPage.enterCredentials(email, password);
        loginPage.clickSubmitLoginButton();

        homePage.validateHomePageLink();
        homePage.validateHomePageTitle();
        homePage.createReportDropdownClick();
        homePage.singlePlaceSearchReportClick();

        singlePlaceSearchPage.validateSinglePlaceSearchPageLink();
        singlePlaceSearchPage.validateSinglePlaceSearchPageContent();

        Faker faker = new Faker();
        final String location = faker.country().capital();
        final String placeName = faker.country().name();
        final String reportName = location.toString() + " " + faker.funnyName().name().toString();
        final String phoneNumber = faker.phoneNumber().phoneNumber();
        singlePlaceSearchPage.fullyFillReportForm(reportName, placeName, phoneNumber, location);
        //singlePlaceSearchPage.fillOnlyLocationAndName(location, placeName);

        reportCreationLoadingPage.isUserRedirectedToProgressPage();
        homePage.signOut();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        driver.close();
    }
}
