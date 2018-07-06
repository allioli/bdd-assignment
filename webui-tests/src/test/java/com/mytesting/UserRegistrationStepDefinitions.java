package com.mytesting;

import static org.junit.Assert.assertTrue;

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

    @When("^I register with google credentials$")
    public void i_register_with_google_credentials() {

        loginPage.clickRegisterButton();

        registrationPage.clickSignInGoogleButton();
        registrationPage.switchToNewChildWindow();

        googleSignInPopUp.enterEmail("joethecatwithdiamonds@gmail.com");
        googleSignInPopUp.clickEmailSubmitButton();
        googleSignInPopUp.enterPassword("joe123joe");
        googleSignInPopUp.clickPasswordSubmitButton();

        registrationPage.switchToParentWindow();
        // registrationPage.checkNoCommercialNotification();
        // registrationPage.clickRegisterButton();
    }

    @When("^I sign in with email and password$")
    public void i_sign_in_with_email_password() {

        /*
         * loginPage.clickLoginButton(); loginPanel.clickLoginEmailButton();
         * loginPanel.enterEmail(world.getLoginEmail());
         * loginPanel.enterPassword(world.getLoginPassword());
         * loginPanel.clickEmailPasswordSubmitButton();
         */

    }

    @When("^I log out$")
    public void i_log_out() {

        campusPage.clickMyAccountMenu();
        campusPage.clickLogoutButton();
    }

    @Then("^I should enter the campus for the first time$")
    public void i_should_enter_campus_for_the_first_time() {

        // campusPage.clickLevelContinueButton();
        campusPage.clickCloseSendPersonalInformationDialog();
        campusPage.clickNotNowInviteFriendsDialog();
        campusPage.clickStartCourse();
        campusPage.clickHomeButton();
    }

    @Then("^I should be on the login page$")
    public void i_should_be_on_the_login_page() {

        assertTrue("Missing expected elements from campus login page", loginPage.validatePage());
    }

}