package com.mytesting.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class LoginPage extends BasePage {
	
    private final String url = "https://campus.abaenglish.com/en/login";
    
    @FindBy(how=How.ID, using="registerForm_collapse")
	private WebElement registerButton;
	
	@FindBy(how=How.ID, using="btnStartSession")
	private WebElement loginButton;

	@FindBy(how=How.ID, using="LoginForm_email")
	private WebElement emailTextInput;

	@FindBy(how=How.ID, using="LoginForm_password")
	private WebElement passwordTextInput;

	@FindBy(how=How.CSS, using=".btn-login-facebook")
	private WebElement facebookLoginButton;

	@FindBy(how=How.CSS, using=".btn-login-google")
	private WebElement googleLoginButton;
	   
	public LoginPage(WebDriver driver) {
		super(driver);
	}
	
	public void navigate() {
		
		 driver.get(url);
	}
	
	public void clickRegisterButton() {
		
		actionBot.waitElementDisplayedAndClick(registerButton);
	}

	public boolean validatePage(){

        if( actionBot.isElementVisible(registerButton) &&
			actionBot.isElementVisible(emailTextInput) &&
			actionBot.isElementVisible(passwordTextInput) &&
			actionBot.isElementVisible(facebookLoginButton) &&
			actionBot.isElementVisible(googleLoginButton))	

            return true;

        return false;

}
}