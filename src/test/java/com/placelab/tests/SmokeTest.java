package com.placelab.tests;

import com.placelab.utilis.WebDriverSetup;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class SmokeTest {

    private WebDriver driver;
    final String browserName = "chrome"; //System.getproperty("browser") wasn't working


    @BeforeTest
    private void setDriver() {
        this.driver = WebDriverSetup.getWebDriver(browserName);
    }

    @Test
    public void openPage() {
        this.driver.get("https://demo.placelab.com");
        System.out.println("Opened browser: " + browserName);
        System.out.println("Page title: " + this.driver.getTitle());
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
