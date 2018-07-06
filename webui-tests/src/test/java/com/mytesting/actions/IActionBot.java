package com.mytesting.actions;

import org.openqa.selenium.WebElement;

public interface IActionBot {

    public boolean isElementVisible(WebElement element);

	public boolean isElementEnabled(WebElement element);

    public boolean waitElementDisplayedAndClick(WebElement element);
    
    public boolean waitElementEnabledAndClick(WebElement element);
      
	public boolean waitElementDisplayedAndEnterText(WebElement element, String text);

	public boolean waitElementEnabledAndEnterText(WebElement element, String text);

}