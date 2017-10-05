@all
Feature: jdbc
    
Scenario: Add book
    When i put new book in base "Effective Java,Joshua Bloch,2001"
    Then this book appears in base