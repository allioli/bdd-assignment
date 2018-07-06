package com.mytesting.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class CampusPage extends BasePage { 

	@FindBy(how=How.ID, using="levelTest-cta")
    private WebElement levelContinueButton; 

    @FindBy(how=How.CSS, using=".ui-dialog-titlebar-close")
    private WebElement closeStartCourseDialog;
    
    @FindBy(how=How.CSS, using=".ui-icon-closethick")
    private WebElement closeSendPersonalInformationDialog;
    
    @FindBy(how=How.ID, using="my_account")
    private WebElement myAccountMenu; 

    @FindBy(how=How.ID, using="aLinkHeadLogout")
    private WebElement logoutButton;

    @FindBy(how=How.ID, using="popup-closeAction")
    private WebElement doNotInviteFriendsNowButton;
   
	public CampusPage(WebDriver driver) {
		super(driver);
	}
	
	public void clickLevelContinueButton() {
		
		actionBot.waitToBeDisplayedAndClick(levelContinueButton);
    }
    
    public void clickCloseStartCourseDialog() {
		
		actionBot.waitToBeDisplayedAndClick(closeStartCourseDialog);
    }

    public void clickCloseSendPersonalInformationDialog() {
		
		actionBot.waitToBeDisplayedAndClick(closeSendPersonalInformationDialog);
    }

    public void clickNotNowInviteFriendsDialog() {
		
		actionBot.waitToBeDisplayedAndClick(doNotInviteFriendsNowButton);
    }
    
    public void clickMyAccountMenu() {
		
		actionBot.waitToBeDisplayedAndClick(myAccountMenu);
    }
    
    public void clickLogoutButton() {
		
		actionBot.waitToBeDisplayedAndClick(logoutButton);
	}

	 
}