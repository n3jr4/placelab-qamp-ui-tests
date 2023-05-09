package com.placelab.tests;

import com.placelab.utils.WebDriverSetup;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class UIDisplayedElementsAndValues {
    private WebDriver driver;
    final String browserName = System.getProperty("browser");

    @BeforeTest
    private void setDriver() {
        this.driver = WebDriverSetup.getWebDriver(browserName);
        this.driver.get("https://demo.placelab.com");
    }

    @Test
    public void allElementsDisplayedWithCorrectValues() {
        // title
        final String actualPageTitle = driver.getTitle();
        final String expectedPageTitle = "PlaceLab";
        Assert.assertEquals(actualPageTitle, expectedPageTitle, "Validating page title text.");

        // logo
        final boolean isLogoDisplayed = driver.findElement(By.cssSelector("#login > img")).isDisplayed();
        Assert.assertTrue(isLogoDisplayed, "Validation if logo is displayed.");
        final String actualLinkToLogoImage = driver.findElement(By.cssSelector("#login > img")).getAttribute("src");
        final String expectedLinkToLogoImage = "https://demo.placelab.com/assets/logo-526ea19604d26801aca90fe441f7df4775a24a5d74ae273dbc4af85f42241259.png";
        Assert.assertEquals(actualLinkToLogoImage, expectedLinkToLogoImage, "Validation if correct logo is displayed.");

        //text underLogo
        final boolean isTestUnderLogoDisplayed = driver.findElement(By.xpath("//*[@id=\"login\"]/p")).isDisplayed();
        Assert.assertTrue(isTestUnderLogoDisplayed, "Validation if text under logo is displayed.");
        final String actualTestUnderLogo = "Turn your data into information, information into insight and insight into business decisions.";
        final String expectedTestUnderLogo = driver.findElement(By.xpath("//*[@id=\"login\"]/p")).getText();
        Assert.assertEquals(actualTestUnderLogo, expectedTestUnderLogo, "Validating the text under logo");

        //login form
        final LoginFormDisplayed loginForm = new LoginFormDisplayed();
        loginForm.isLoginFormDisplayed(this.driver);

        //side elements - will write it in details when I finish everything else
        final boolean isPlaceLabOverviewDisplayed = driver.findElement(By.cssSelector("#login > div.span5.pull-right")).isDisplayed();
        Assert.assertTrue(isPlaceLabOverviewDisplayed, "Validation if overview of Placelab is displayed.");

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
