@all
Feature: Проверка страницы поиска компаний
  Scenario: Работа фильтра Регион на странице поиска компаний
    Given I open search company page
    And item by number 3 "Компании" should be active
    When I select filter company region
    When I select "Россия"
    And I wait 1000
    And press button "Выбрать текущий раздел"
    And I clear select region