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

    @When("^I select register new user with Google credentials$")
    public void i_select_register_new_user_with_google_credentials() {

        loginPage.clickRegisterButton();
        registrationPage.clickSignInGoogleButton();
    }

    @When("^I submit registration$")
    public void i_submit_registration() {

        registrationPage.checkNoCommercialNotification();
        registrationPage.clickRegisterButton();
    }

    @When("^I skip level test$")
    public void i_skip_level_test() {

        campusPage.clickLevelContinueButton();
    }

    @When("^I enter Google credentials \"(.+)\" and \"(.+)\"$")
    public void i_enter_google_credentials_email_and_password(String email, String password) {

        googleSignInPopUp.switchToNewChildWindow();

        googleSignInPopUp.enterEmail(email);
        googleSignInPopUp.clickEmailSubmitButton();
        googleSignInPopUp.enterPassword(password);
        googleSignInPopUp.clickPasswordSubmitButton();

        googleSignInPopUp.switchToParentWindow();
    }

    @When("^I select sign in with Google credentials$")
    public void i_select_sign_in_with_google_credentials() {

        loginPage.clickGoogleLogin();
    }

    @When("^I log out$")
    public void i_log_out() {

        campusPage.clickMyAccountMenu();
        campusPage.clickLogoutButton();
    }

    @Then("^I should be on the login page$")
    public void i_should_be_on_the_login_page() {

        assertTrue("Missing expected elements in campus login page", loginPage.validatePage());
    }

    @Then("^I should enter the campus$")
    public void i_should_enter_the_campus() {

        campusPage.clickCloseSendPersonalInformationDialog();
        campusPage.clickNotNowInviteFriendsDialog();
        campusPage.clickStartCourse();

        // Click Home in order to skip the Adobe Flash panel
        campusPage.clickHomeButton();

        assertTrue("Missing expected elements in campus login page", campusPage.validatePage());
    }

}