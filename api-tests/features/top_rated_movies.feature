
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

  Scenario Outline: Test Region vs Language
    Given I create moviedb API request for "top_rated"
    And I add parameter "language" with value "<language>"
    And I add parameter "region" with value "<region>"
    When I perform request to movie-db API
    Then response code is "200"
    And response contains "page" equal to number "1"
    And response contains movie in position "2" with "id" equal to number "238"
    And response contains movie in position "2" with "vote_average" equal to number "8.6"
    And response contains movie in position "2" with "title" equal to "<title>"
    And response contains movie in position "2" with "original_title" equal to "<original_title>"
    And response contains movie in position "2" with "original_language" equal to "<original_language>"
    And response contains movie in position "2" with "release_date" equal to "<release_date>"

   Examples: How GodFather properties change across language, region
     | language  | region |  title         | release_date | original_title  | original_language |
     | en-US     | US     |  The Godfather | 1972-03-15   | The Godfather   |  en               |
     | en-US     | ES     |  The Godfather | 1972-10-20   | The Godfather   |  en               |
     | es-SP     | ES     |  El Padrino    | 1972-10-20   | The Godfather   |  en               |
     | es-SP     | US     |  El Padrino    | 1972-03-15   | The Godfather   |  en               |                                 
