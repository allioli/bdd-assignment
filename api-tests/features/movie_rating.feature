
Feature: MovieDB API Movie rating

  Scenario: Rate The GodFather
    Given I create moviedb API request to rate movie with id "238"
    And I add parameter "rating" with value "8.5"
    When I perform POST request to movie-db API
    Then response code is "201"
    And response contains "status_code" equal to number "12"
    And response contains "status_message" equal to "The item/record was updated successfully." 

  Scenario: Invalid API key
    Given I create moviedb API request to rate movie with id "238"
    And I add parameter "rating" with value "8.5"
    And  I add parameter "custom_api_key" with value "98834534534"
    When I perform POST request to movie-db API
    Then response code is "401"
    And response contains "status_code" equal to number "7"
    And response contains "status_message" equal to "Invalid API key: You must be granted a valid key." 

  Scenario: Invalid guest session id 
    Given I create moviedb API request to rate movie with id "238"
    And I add parameter "rating" with value "8.5"
    And  I add parameter "custom_guest_session_id" with value "00000000000000"
    When I perform POST request to movie-db API
    Then response code is "401"
    And response contains "status_code" equal to number "3"
    And response contains "status_message" equal to "Authentication failed: You do not have permissions to access the service."

  Scenario: Non-existing movie id 
    Given I create moviedb API request to rate movie with id "0"
    And I add parameter "rating" with value "8.5"
    When I perform POST request to movie-db API
    Then response code is "404"
    And response contains "status_code" equal to number "34"
    And response contains "status_message" equal to "The resource you requested could not be found."

   Scenario Outline: Test Rating values
    Given I create moviedb API request to rate movie with id "238"
    And I add parameter "rating" with value "<rating_value>"
    When I perform POST request to movie-db API
    Then response code is "<response_code>"
    And response contains "status_code" equal to number "<status_code>"
    And response contains "status_message" equal to "<status_message>" 

    Examples: Several rating values 
     | rating_value | response_code | status_code | status_message                                         |
     | 0            | 400           | 18          | Value too low: Value must be greater than 0.0.         |
     | 0.5          | 201           | 12          | The item/record was updated successfully.              |
     | 5.2          | 400           | 18          | Value invalid: Values must be a multiple of 0.50.      |
     | 10           | 201           | 12          | The item/record was updated successfully.              |
     | 10.5         | 400           | 18          | Value too high: Value must be less than, or equal to 10.0.|
     | Poor         | 400           | 18          | Value too low: Value must be greater than 0.0.            |