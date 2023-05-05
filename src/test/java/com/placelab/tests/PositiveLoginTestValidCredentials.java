package com.placelab.tests;

import com.placelab.utils.WebDriverSetup;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class PositiveLoginTestValidCredentials {
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
    public void loginWithValidCredentials(final String email, final String password) {

        driver.findElement(By.id("email")).sendKeys(email);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.cssSelector("#login_form > input.btn.large-btn[value = 'Log in']")).click();

        final String actualNewPageLink = driver.getCurrentUrl();
        final String expectedNewPageLink = "https://demo.placelab.com/dashboard/traffic";
        Assert.assertEquals(actualNewPageLink, expectedNewPageLink, "Validation if transferred to new page after login");

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
