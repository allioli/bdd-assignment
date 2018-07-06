package com.mytesting.pages;

import java.util.Set;

import org.openqa.selenium.WebDriver;

public class BasePage {
	
  protected WebDriver driver;
  protected ActionBot actionBot;
  protected String windowHandle;
  
  public BasePage(WebDriver driver) {
    this.driver = driver;
    this.actionBot = new ActionBot(driver);
  }

  public void switchToNewChildWindowAndStoreParentWindowHandle(){

    String parentWindowHandle="";  
    String childWindowHandle=""; 

    Set <String>handles = driver.getWindowHandles();
    parentWindowHandle = driver.getWindowHandle();
    handles.remove(parentWindowHandle);
    this.windowHandle = parentWindowHandle;

    childWindowHandle=handles.iterator().next();
    driver.switchTo().window(childWindowHandle);
  }

  public void switchToParentWindow(){

    driver.switchTo().window(this.windowHandle);
  }
  
}
