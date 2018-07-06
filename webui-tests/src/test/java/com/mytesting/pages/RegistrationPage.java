package com.mytesting.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class RegistrationPage extends BasePage {
    
    @FindBy(how=How.CSS, using=".button.ui.google.plus.button.google.plus-button-social.button-social")
	private WebElement signInGoogleButton; 
	
	@FindBy(how=How.CSS, using="input[type='radio'][value='0']")
	private WebElement noCommercialNotificationCheckbox; 

	@FindBy(how=How.ID, using="button-register")
	private WebElement registerButton; 
	
	   
	public RegistrationPage(WebDriver driver) {
		super(driver);
	}
	
	public void clickSignInGoogleButton() {
		
		actionBot.waitToBeDisplayedAndClick(signInGoogleButton);
	}

	public void checkNoCommercialNotification(){

		actionBot.waitToBeEnabledAndClick(noCommercialNotificationCheckbox);
	}

	public void clickRegisterButton(){

		actionBot.waitToBeDisplayedAndClick(registerButton);
	}
}