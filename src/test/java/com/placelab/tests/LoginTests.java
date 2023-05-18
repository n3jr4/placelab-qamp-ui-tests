package com.placelab.tests;

import com.placelab.pages.ForgetPasswordPage;
import com.placelab.pages.HomePage;
import com.placelab.pages.LoginPage;
import com.placelab.pages.SentEmailPage;
import com.placelab.utils.WebDriverSetup;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import java.time.Duration;

public class LoginTests {
    private WebDriver driver;
    private static LoginPage loginPage;
    private static HomePage homePage;
    private static ForgetPasswordPage forgetPasswordPage;
    private static SentEmailPage sentEmailPage;

    @Parameters("browser")
    @BeforeMethod(alwaysRun = true)
    private void setDriver(final String browser) {
        this.driver = WebDriverSetup.getWebDriver(browser);
        this.driver.get("https://demo.placelab.com");
        this.loginPage = new LoginPage(this.driver);
        this.homePage = new HomePage(this.driver);
        this.forgetPasswordPage = new ForgetPasswordPage(this.driver);
        this.sentEmailPage = new SentEmailPage(this.driver);
    }

    @Parameters({"email", "password"})
    @Test(priority = 1, groups = "positive", description = "Validate login with valid credentials.")
    public void testLoginWithValidCredentials(final String email, final String password) {
        loginPage.validateLoginPageContent();
        loginPage.enterCredentials(email, password);
        loginPage.clickSubmitLoginButton();

        homePage.validateHomePageLink();
        homePage.validateHomePageTitle();
        final String expectedUserRole = "Group Admin";
        homePage.validateUserRole(expectedUserRole);
        homePage.signOut();

        loginPage.validateLoginPageLink();
    }

    @Test(priority = 2, groups = "negative", description = "Validate login with empty fields.")
    public void testLoginWithEmptyFields() {
        loginPage.validateLoginPageContent();
        loginPage.clearLoginFormInput();
        loginPage.clickSubmitLoginButton();
        loginPage.validateErrorMessageOnLogin();
        loginPage.validateLoginPageLink();
    }

    @BeforeTest
    public static String generateRandomEmail() {
        String username = RandomStringUtils.randomAlphanumeric(10);
        String domain = RandomStringUtils.randomAlphanumeric(5) + ".com";
        String randomEmail = username + "@" + domain;
        return randomEmail;
    }

    @Parameters("password")
    @Test(priority = 3, groups = "negative", description = "Validate login with invalid email address.")
    public void testLoginWithInvalidEmail(final String password) {
        loginPage.validateLoginPageContent();
        loginPage.enterCredentials(generateRandomEmail(), password);
        loginPage.clickSubmitLoginButton();
        loginPage.validateErrorMessageOnLogin();
        loginPage.validateLoginPageLink();
    }

    @BeforeMethod
    public static String generateRandomPassword() {
        String randomPassword = RandomStringUtils.randomAlphanumeric(10);
        return randomPassword;
    }

    @Parameters("email")
    @Test(priority = 4, groups = "negative", description = "Validate login with invalid password.")
    public void testLoginWithInvalidPassword(final String email) {
        loginPage.validateLoginPageContent();
        loginPage.enterCredentials(email, generateRandomPassword());
        loginPage.clickSubmitLoginButton();
        loginPage.validateErrorMessageOnLogin();
        loginPage.validateLoginPageLink();

    }

    @Parameters("email")
    @Test(priority = 5, groups = "Positive", description = "Validating if forget password link leads to forget password page.")
    public void testForgetPasswordLink(final String email) {
        loginPage.validateLoginPageContent();
        loginPage.forgetPasswordLinkClick();

        forgetPasswordPage.validateForgetPasswordPageLink();
        forgetPasswordPage.enterEmail(email);
        forgetPasswordPage.clickContinueButton();

        sentEmailPage.validateSentEmailPageLink();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        driver.close();
    }

}
