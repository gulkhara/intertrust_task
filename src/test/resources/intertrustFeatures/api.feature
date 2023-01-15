@APIFeatures
Feature: API Tests



  Scenario: Verify weather forecast through VisualCrossing API endpoint
    When Send GET request to the VisualCrossing API endpoint for "Tallinn"
    And Verify status code is 200
    Then Verify that forecast is given for the correct city
    And Verify that timezone is "Europe/Tallinn"
    And Verify that forecast is given for 15 days
