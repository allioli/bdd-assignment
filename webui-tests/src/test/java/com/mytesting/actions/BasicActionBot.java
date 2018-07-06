package com.mytesting.actions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasicActionBot implements IActionBot {

    protected final WebDriver driver;
    protected final int defaultExplicitTimeoutInSeconds = 10;

    public BasicActionBot(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isElementVisible(WebElement element) {

        try {
            WebDriverWait wait = new WebDriverWait(driver, defaultExplicitTimeoutInSeconds);
            wait.until(d -> element.isDisplayed());
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean isElementEnabled(WebElement element) {

        try {
            WebDriverWait wait = new WebDriverWait(driver, defaultExplicitTimeoutInSeconds);
            wait.until(d -> element.isEnabled());
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean waitElementDisplayedAndClick(WebElement element) {

        if (!isElementVisible(element))
            return false;

        element.click();
        return true;
    }

    public boolean waitElementEnabledAndClick(WebElement element) {

        if (!isElementEnabled(element))
            return false;

        element.click();
        return true;
    }

    public boolean waitElementDisplayedAndEnterText(WebElement element, String text) {

        if (!isElementVisible(element))
            return false;

        element.sendKeys(text);
        return true;
    }

    public boolean waitElementEnabledAndEnterText(WebElement element, String text) {

        if (!isElementEnabled(element))
            return false;

        element.sendKeys(text);
        return true;
    }

}