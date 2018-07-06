
@user_registration
Feature: User registration
  As a non registered user of ABA English
  I want to create a user account
  So that I can start learning English


  Scenario: Sign up with google account
    Given I go to login page
    When I register with google credentials
    Then I should enter the campus for the first time
    When I log out
    Then I should be on the login page
    #And I sign in with email and password
    #Then I am signed in as a registered user
    

  