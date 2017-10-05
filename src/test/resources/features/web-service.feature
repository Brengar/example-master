@all
Feature: web-service
    
Scenario: Check string
    When i send string "London is a copital of Great-Britan"
    Then the result is "copital,Britan"