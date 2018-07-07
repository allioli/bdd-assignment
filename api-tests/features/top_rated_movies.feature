
Feature: MovieDB API Top Rated Movies

  Scenario: Default query
    Given I create moviedb API request for "top_rated"
    When I perform request to movie-db API
    Then response code is "200"
    And response contains "page" equal to number "1"
    And response contains movie in position "0" with "id" equal to number "19404"
    And response contains movie in position "0" with "vote_average" equal to number "9.3"
    And movies are sorted by "vote_average" in descending order

  Scenario: Invalid API key
    Given I create moviedb API request for "top_rated"
    And I add parameter "custom_api_key" with value "555555"
    When I perform request to movie-db API
    Then response code is "401"
    And response contains "status_code" equal to number "7"
    And response contains "status_message" equal to "Invalid API key: You must be granted a valid key."

  Scenario: Invalid API resource
    Given I create moviedb API request for "top_scraped"
    When I perform request to movie-db API
    Then response code is "404"
    And response contains "status_code" equal to number "34"
    And response contains "status_message" equal to "The resource you requested could not be found."

                                   
