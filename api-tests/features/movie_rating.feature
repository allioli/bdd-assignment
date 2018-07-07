
Feature: MovieDB API Movie rating

  Scenario: Rate The GodFather
    Given I create moviedb API request to rate movie with id "238"
    And I add parameter "rating" with value "8.5"
    When I perform POST request to movie-db API
    Then response code is "201"

  