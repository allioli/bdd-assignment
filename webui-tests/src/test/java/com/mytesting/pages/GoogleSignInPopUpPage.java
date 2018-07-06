package com.mytesting.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class GoogleSignInPopUpPage extends BasePage {

	@FindBy(how = How.ID, using = "identifierId")
	private WebElement loginInputEmail;

	@FindBy(how = How.CSS, using = "input[type=password]")
	private WebElement loginInputPassword;

	@FindBy(how = How.ID, using = "identifierNext")
	private WebElement submitEmailButton;

	@FindBy(how = How.ID, using = "passwordNext")
	private WebElement submitPasswordButton;

	public GoogleSignInPopUpPage(WebDriver driver) {
		super(driver);
	}

	public void clickEmailSubmitButton() {

		actionBot.waitToBeDisplayedAndClick(submitEmailButton);
	}

	public void clickPasswordSubmitButton() {

		actionBot.waitToBeDisplayedAndClick(submitPasswordButton);
	}

	public void enterEmail(String email) {

		actionBot.waitToBeDisplayedAndEnterText(loginInputEmail, email);
	}

	public void enterPassword(String password) {

		actionBot.waitToBeDisplayedAndEnterText(loginInputPassword, password);
	}
}