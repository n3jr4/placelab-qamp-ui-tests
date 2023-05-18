package com.placelab.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class ForgetPasswordPage {
    private final static String FORGET_PASSWORD_PAGE_LINK = "https://demo.placelab.com/password/forgot";
    private final static By EMAIL_INPUT = By.id("email");
    private final static By CONTINUE_BUTTON = By.cssSelector("#login_form > input.btn.large-btn[value = 'Continue']");
    private WebDriver driver;

    public ForgetPasswordPage(final WebDriver webDriver) {
        this.driver = webDriver;
    }

    public void validateForgetPasswordPageLink() {
        Assert.assertEquals(driver.getCurrentUrl(), FORGET_PASSWORD_PAGE_LINK, "Validation if user is on Forget password page.");
    }

    public void enterEmail(final String email) {
        driver.findElement(EMAIL_INPUT).sendKeys(email);
    }

    public void clickContinueButton() {
        driver.findElement(CONTINUE_BUTTON).click();
    }
}
