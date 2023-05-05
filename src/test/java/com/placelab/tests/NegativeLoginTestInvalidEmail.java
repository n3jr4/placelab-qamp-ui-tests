package com.placelab.tests;

import java.time.Duration;

import com.placelab.utils.WebDriverSetup;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class NegativeLoginTestInvalidEmail {
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

    @Parameters("password")
    @Test
    public void loginWithValidCredentials(final String password) {

        driver.findElement(By.id("email")).sendKeys("nejraskandro@gmal.com");
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.cssSelector("#login_form > input.btn.large-btn[value = 'Log in']")).click();

        final Duration timeout = Duration.ofSeconds(10);
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#login > div.row > div > div")));
        final boolean isErrorMessageDisplayed = errorMessage.isDisplayed();
        Assert.assertTrue(isErrorMessageDisplayed, "Validation if error message is displayed.");

        final String actualErrorMessageDisplayed = driver.findElement(By.cssSelector("#login > div.row > div > div")).getText();
        final String expectedErrorMessageDisplayed = "Invalid credentials!";
        Assert.assertEquals(actualErrorMessageDisplayed, expectedErrorMessageDisplayed, "Validation if error message is indicating wrong credentials.");

        final String actualNewPageLink = driver.getCurrentUrl();
        final String expectedNewPageLink = "https://demo.placelab.com/";
        Assert.assertEquals(actualNewPageLink, expectedNewPageLink, "Validation if user stayed on the log in page after attempt to login with false credentials");

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
