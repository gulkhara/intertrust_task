@UIFeatures
Feature: UI Tests


  Scenario: Verify weather forecast is shown in VisualCrossing for your current city
    Given Open VisualCrossing URL
    And Select the “Weather Data” menu
    Then Enter your current city "Tallinn" in the text field and press “Search” button
    And Verify that weather forecast is shown for the entered city
    
