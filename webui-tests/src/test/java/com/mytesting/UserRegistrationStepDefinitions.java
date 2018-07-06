package com.mytesting;

import com.mytesting.pages.CampusPage;
import com.mytesting.pages.GoogleSignInPopUpPage;
import com.mytesting.pages.LoginPage;
import com.mytesting.pages.RegistrationPage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class UserRegistrationStepDefinitions {

    public WebDriver driver;
    private LoginPage loginPage;
    private RegistrationPage registrationPage;
    private GoogleSignInPopUpPage googleSignInPopUp;
    private CampusPage campusPage;
    
    public UserRegistrationStepDefinitions() {
        driver = Hooks.driver;
        loginPage = PageFactory.initElements(driver, LoginPage.class);
        registrationPage = PageFactory.initElements(driver, RegistrationPage.class);
        googleSignInPopUp = PageFactory.initElements(driver, GoogleSignInPopUpPage.class);
        campusPage = PageFactory.initElements(driver, CampusPage.class);
    }

    @Given("^I go to login page$")
    public void i_go_to_login_page() {
        
    	loginPage.navigate();
    }
    
    @When("^I create a user account with google credentials$")
    public void i_create_user_account_with_google_credentials() {
        
        loginPage.clickRegisterButton();

        registrationPage.clickSignInGoogleButton();
        registrationPage.switchToNewChildWindowAndStoreParentWindowHandle();

        googleSignInPopUp.enterEmail("joethecatwithdiamonds@gmail.com");
        googleSignInPopUp.clickEmailSubmitButton();
        googleSignInPopUp.enterPassword("joe123joe");
        googleSignInPopUp.clickPasswordSubmitButton();

        registrationPage.switchToParentWindow();
        //registrationPage.checkNoCommercialNotification();
        //registrationPage.clickRegisterButton();

        //campusPage.clickLevelContinueButton();
        campusPage.clickCloseSendPersonalInformationDialog();
        campusPage.clickNotNowInviteFriendsDialog();
        campusPage.clickCloseStartCourseDialog();
        campusPage.clickMyAccountMenu();
        campusPage.clickLogoutButton(); 
    	
    }
    
    @When("^I sign in with email and password$")
    public void i_sign_in_with_email_password() {
    	
    	/*loginPage.clickLoginButton();
    	loginPanel.clickLoginEmailButton();
    	loginPanel.enterEmail(world.getLoginEmail());
    	loginPanel.enterPassword(world.getLoginPassword());
    	loginPanel.clickEmailPasswordSubmitButton();*/
    	
    }
    
    @When("^I sign out$")
    public void i_sign_out() {
    	
    	//signedInHomePage.signOut();	
    }
    
    @Then("^I am signed in as a registered user$")
    public void i_am_signed_in_as_a_registeread_user() {
    	
    	/*signedInHomePage.waitForAvatarButton();
    	signedInHomePage.waitForPushNotificationLink();*/
    }
    
   
    
     
}