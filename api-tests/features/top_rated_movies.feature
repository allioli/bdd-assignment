
Feature: MovieDB API Top Rated Movies

  Scenario: Default query
    When I perform GET request to movie-db API
    Then response code is "200"
    And response contains "page" equal to number "1"
    And response contains movie in position "0" with "id" equal to number "19404"
    And response contains movie in position "0" with "vote_average" equal to number "9.3"
    And movies are sorted by "vote_average" in descending order

  Scenario: Invalid API key
    Given I add parameter "custom_api_key" with value "555555"
    When I perform GET request to movie-db API
    Then response code is "401"
    And response contains "status_code" equal to number "7"
    And response contains "status_message" equal to "Invalid API key: You must be granted a valid key."

  Scenario: Invalid API resource 
    Given I add parameter "custom_api_resource" with value "top_scraped"
    When I perform GET request to movie-db API
    Then response code is "404"
    And response contains "status_code" equal to number "34"
    And response contains "status_message" equal to "The resource you requested could not be found."

  Scenario Outline: Test Region vs Language
    Given I add parameter "language" with value "<language>"
    And I add parameter "region" with value "<region>"
    When I perform GET request to movie-db API
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

   Scenario Outline: Paging results
    Given I add parameter "page" with value "<page>"
    When I perform GET request to movie-db API
    Then response code is "<response_code>"
    And response contains "page" equal to number "<expected_page>"
    And response contains "total_results" equal to number "7574"
    And response contains "total_pages" equal to number "379"
    And response contains "<results_per_page>" results
    And movies are sorted by "vote_average" in descending order

   Examples: Valid paging values
     | page  | response_code |  expected_page | results_per_page |        
     | 1     | 200           |  1             |        20        |
     | 378   | 200           |  378           |        20        |
     | 379   | 200           |  379           |        14        |
     | 380   | 200           |  380           |        0         |
     | 1000  | 200           |  1000          |        0         |                         

Scenario Outline: Paging errors
    Given I add parameter "page" with value "<page>"
    When I perform GET request to movie-db API
    Then response code is "<response_code>"
    And response contains error message "<error_message>"

   Examples: Invalid paging values
     | page  | response_code |  error_message                               |        
     | 0     | 422           |  page must be greater than 0                 |
     | 1001  | 422           |  page must be less than or equal to 1000     |
      