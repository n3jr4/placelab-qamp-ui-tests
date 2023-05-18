package com.placelab.pages;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class SentEmailPage {
    private final static String SENT_EMAIL_PAGE_LINK = "https://demo.placelab.com/email/sent";
    private WebDriver driver;

    public SentEmailPage(final WebDriver webDriver) {
        this.driver = webDriver;
    }

    public void validateSentEmailPageLink() {
        Assert.assertEquals(driver.getCurrentUrl(), SENT_EMAIL_PAGE_LINK, "Validation if user is on Forget password page.");
    }

}
