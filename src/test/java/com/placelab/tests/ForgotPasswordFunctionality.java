package com.placelab.tests;

import com.placelab.utils.WebDriverSetup;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class ForgotPasswordFunctionality {

    private WebDriver driver;
    private final String browserName = System.getProperty("browser");

    @Parameters("browser")
    @BeforeTest
    private void setDriverAndCheckIfLoginFormIsDisplayed() {
        this.driver = WebDriverSetup.getWebDriver(browserName);
        this.driver.get("https://demo.placelab.com");
        final LoginFormDisplayed loginForm = new LoginFormDisplayed();
        loginForm.isLoginFormDisplayed(this.driver);
    }

    @Parameters({"email", "password"})
    @Test
    public void testForgetPasswordLink(final String email, final String password) {

        driver.findElement(By.xpath("//*[@id=\"password-area\"]/a")).click();

        final String actualForgetPasswordLink = driver.getCurrentUrl();
        final String expectedForgetPasswordLink = "https://demo.placelab.com/password/forgot";
        Assert.assertEquals(actualForgetPasswordLink, expectedForgetPasswordLink, "Validation if transferred to forget password page.");

        driver.findElement(By.id("email")).sendKeys(email);
        driver.findElement(By.cssSelector("#login_form > input.btn.large-btn[value = 'Continue']")).click();

        final String actualResetEmailPage = driver.getCurrentUrl();
        final String expectedResetEmailPage = "https://demo.placelab.com/email/sent";
        Assert.assertEquals(actualResetEmailPage, expectedResetEmailPage, "Validation if transferred to email sent page.");


        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @AfterTest
    public void tearDown() {
        driver.close();
    }

}
