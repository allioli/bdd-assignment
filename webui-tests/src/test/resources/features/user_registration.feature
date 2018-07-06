
@user_registration
Feature: User registration
  As a non registered user of ABA English
  I want to create a user account
  So that I can start learning English


  Scenario: Sign up with google account
    Given I go to login page
    When I select register new user with Google credentials
    And I enter Google credentials "joethecatwithdiamonds@gmail.com" and "joe123joe"
    And I submit registration
    And I skip level test
    Then I should enter the campus
    When I log out
    Then I should be on the login page
    
  Scenario: Log in with google account
    Given I go to login page
    When I select sign in with Google credentials
    And I enter Google credentials "joethecatwithdiamonds@gmail.com" and "joe123joe"
    Then I should enter the campus
  