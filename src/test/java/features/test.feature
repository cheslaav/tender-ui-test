@all
Feature: Home page test

  This test open home page and test search form on page
  @test
  Scenario: Page opened and there is a search form on it
    Given I open homepage
    And I see "search" form
    And I find item "товары" and clicked on it
    And I find item "процедуры" and clicked on it
    And I find item "Компании" and clicked on it

  Scenario: Search in the goods section
    Given I open homepage
    Then I enter the "Товары" request "Сапоги" in the field
    And I wait 500
    When I check that in every result "Товары" there is a word "Сапоги"
    And I find button "Показать еще" and see 50 result on page

  Scenario: Checking the search operation in the procurement
    Given I open homepage
    Then I enter the "Процедуры" request "Закупка" in the field
    Then I uncheck checkbox "Только активные"
    When I check that in every result "Процедуры" there is a word "Закупка"

  Scenario: Check search companies
    Given I open homepage
    Then I enter the "Компании" request "Металл" in the field
    And I wait 500
    When I check that in every result "Компании" there is a word "Металл"