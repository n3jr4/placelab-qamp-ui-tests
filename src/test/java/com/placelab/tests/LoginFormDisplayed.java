package com.placelab.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class LoginFormDisplayed {
    public void isLoginFormDisplayed(WebDriver driver) {
        final boolean isLoginFormDisplayed = driver.findElement(By.id("login_form")).isDisplayed();
        Assert.assertTrue(isLoginFormDisplayed, "Validation if login form is displayed.");

        final boolean isEmailFieldDisplayed = driver.findElement(By.id("email")).isDisplayed();
        Assert.assertTrue(isEmailFieldDisplayed, "Validation if email field is displayed.");
        final boolean isEmailFieldEnabled = driver.findElement(By.id("email")).isEnabled();
        Assert.assertTrue(isEmailFieldEnabled, "Validation if email field is set to receive input.");

        final boolean isPasswordFieldDisplayed = driver.findElement(By.id("password")).isDisplayed();
        Assert.assertTrue(isPasswordFieldDisplayed, "Validation if password field is displayed.");
        final boolean isPasswordFieldEnabled = driver.findElement(By.id("password")).isEnabled();
        Assert.assertTrue(isPasswordFieldEnabled, "Validation if password field is set to receive input.");

        final boolean isForgetPasswordLinkDisplayed = driver.findElement(By.cssSelector("#password-area > a")).isDisplayed();
        Assert.assertTrue(isForgetPasswordLinkDisplayed, "Validation if forget password link is displayed.");
        final boolean isForgetPasswordLinkClickable = driver.findElement(By.cssSelector("#password-area > a")).isEnabled();
        Assert.assertTrue(isForgetPasswordLinkClickable, "Validation if forget password is clickable.");

        final boolean isLoginButtonDisplayed = driver.findElement(By.cssSelector("#login_form > input.btn.large-btn[value = 'Log in']")).isDisplayed();
        Assert.assertTrue(isLoginButtonDisplayed, "Validation if login button is displayed.");
        final boolean isLoginButtonClickable = driver.findElement(By.cssSelector("#login_form > input.btn.large-btn[value = 'Log in']")).isEnabled();
        Assert.assertTrue(isLoginButtonClickable, "Validation if login button is clickable.");
    }
}
