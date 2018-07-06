package com.mytesting.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class LoginPage extends BasePage {
	
    private final String url = "https://campus.abaenglish.com/en/login";
    
    @FindBy(how=How.ID, using="registerForm_collapse")
    private WebElement registerButton;   
	   
	public LoginPage(WebDriver driver) {
		super(driver);
	}
	
	public void navigate() {
		
		 driver.get(url);
	}
	
	public void clickRegisterButton() {
		
		actionBot.waitToBeDisplayedAndClick(registerButton);
	}
}