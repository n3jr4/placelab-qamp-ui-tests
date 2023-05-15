package com.placelab.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class LoginPage {
    private static String EXPECTED_LOGINPAGE_TITLE = "PlaceLab";
    private static String EXPECTED_ERROR_LOGIN_MESSAGE = "Invalid credentials!";
    private final String LOGIN_PAGE_LINK = "https://demo.placelab.com/";
    private static final By LOGIN_FORM = By.id("login_form");
    private static final By EMAIL_INPUT = By.id("email");
    private static final By PASSWORD_INPUT = By.id("password");
    private static final By FORGET_PASSWORD_LINK = By.cssSelector("#password-area > a");
    private static final By LOGIN_BUTTON = By.cssSelector("#login_form > input.btn.large-btn[value = 'Log in']");
    private static final By ERROR_LOGIN_MESSAGE = By.cssSelector("div.span12 > div.error-area");
    private WebDriver driver;

    public LoginPage(final WebDriver webDriver) {
        this.driver = webDriver;
    }

    public void validateLoginPageContent() {
        final String actualPageTitle = driver.getTitle();
        Assert.assertEquals(actualPageTitle, EXPECTED_LOGINPAGE_TITLE, "Validating page title text.");
        final boolean isLoginFormDisplayed = driver.findElement(LOGIN_FORM).isDisplayed();
        Assert.assertTrue(isLoginFormDisplayed, "Validation if login form is displayed.");
        final boolean isEmailFieldDisplayed = driver.findElement(EMAIL_INPUT).isDisplayed();
        Assert.assertTrue(isEmailFieldDisplayed, "Validation if email field is displayed.");
        final boolean isEmailFieldEnabled = driver.findElement(EMAIL_INPUT).isEnabled();
        Assert.assertTrue(isEmailFieldEnabled, "Validation if email field is set to receive input.");
        final boolean isPasswordFieldDisplayed = driver.findElement(PASSWORD_INPUT).isDisplayed();
        Assert.assertTrue(isPasswordFieldDisplayed, "Validation if password field is displayed.");
        final boolean isPasswordFieldEnabled = driver.findElement(PASSWORD_INPUT).isEnabled();
        Assert.assertTrue(isPasswordFieldEnabled, "Validation if password field is set to receive input.");

        final boolean isForgetPasswordLinkDisplayed = driver.findElement(FORGET_PASSWORD_LINK).isDisplayed();
        Assert.assertTrue(isForgetPasswordLinkDisplayed, "Validation if forget password link is displayed.");
        final boolean isForgetPasswordLinkClickable = driver.findElement(FORGET_PASSWORD_LINK).isEnabled();
        Assert.assertTrue(isForgetPasswordLinkClickable, "Validation if forget password is clickable.");

        final boolean isLoginButtonDisplayed = driver.findElement(LOGIN_BUTTON).isDisplayed();
        Assert.assertTrue(isLoginButtonDisplayed, "Validation if login button is displayed.");
        final boolean isLoginButtonClickable = driver.findElement(LOGIN_BUTTON).isEnabled();
        Assert.assertTrue(isLoginButtonClickable, "Validation if login button is clickable.");
    }

    public void enterCredentials(final String email, final String password) {
        driver.findElement(EMAIL_INPUT).sendKeys(email);
        driver.findElement(PASSWORD_INPUT).sendKeys(password);
    }

    public void validateLoginPageLink() {
        Assert.assertEquals(driver.getCurrentUrl(), LOGIN_PAGE_LINK, "Validation if if the user is on Login page.");
    }

    public void clearLoginFormInput() {
        driver.findElement(EMAIL_INPUT).clear();
        driver.findElement(PASSWORD_INPUT).clear();
    }

    public void clickSubmitLoginButton() {
        driver.findElement(LOGIN_BUTTON).click();
    }

    public void clickLoginButtonByEnter() {
        driver.findElement(PASSWORD_INPUT).sendKeys(Keys.ENTER);
    }

    public void forgetPasswordLinkClick() {
        driver.findElement(FORGET_PASSWORD_LINK).click();
    }

    public void validateErrorMessageOnLogin() {
        final String actualLoginErrorMessage = driver.findElement(ERROR_LOGIN_MESSAGE).getText();
        Assert.assertEquals(actualLoginErrorMessage, EXPECTED_ERROR_LOGIN_MESSAGE, "Validation if correct error message is displayed.");
    }
}
