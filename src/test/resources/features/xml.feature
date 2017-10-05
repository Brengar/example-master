@all
Feature: xml

  Scenario: Add book
    When i put new book in catalog "Incredible book about Java,Saburov Anton,2015,0-06-999999-9,Java-Course publisher,499,RUB"
    Then this book apperas in catalog
