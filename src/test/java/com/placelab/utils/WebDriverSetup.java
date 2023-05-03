package com.placelab.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class WebDriverSetup {
    public static WebDriver getWebDriver(final String browserName) {
        switch (browserName.toUpperCase()) {
            case "CHROME":
                return chromeDriver();
            case "FIREFOX":
                return firefoxDriver();
            case "EDGE":
                return edgeDriver();
            default:
                throw new IllegalArgumentException(browserName + " is not supported!");
        }
    }

    private static WebDriver chromeDriver() {
        WebDriverManager.chromedriver().setup();
        return new ChromeDriver();
    }

    private static WebDriver firefoxDriver() {
        WebDriverManager.firefoxdriver().setup();
        return new FirefoxDriver();
    }

    private static WebDriver edgeDriver() {
        WebDriverManager.edgedriver().setup();
        return new EdgeDriver();
    }
}
