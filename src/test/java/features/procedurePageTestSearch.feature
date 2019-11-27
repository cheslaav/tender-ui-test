@all
Feature: Проверка страницы поиска процедур
  Scenario: Работа фильтра тип на странице процедур
    Given I open search procedures page
    When I wait 1000
    And item by number 2 "Процедуры" should be active
    When I should see filter "Тип"
    When I select "Продажа"
    When I wait 1000
    And I see each item contains
    When I select "Закупка"
    And result contains data
    When I wait 1000
    When I select "Любой"

  Scenario: Проверка статуса только открытые на странице процедур
    Given I open search procedures page
    And item by number 2 "Процедуры" should be active
    And checkbox status only open should be active
    When I check the status of "ОТКРЫТ" for each item on the page

  Scenario: Проверка статуса с не активным чекбоксом Только активные на странице процедур
    Given I open search procedures page
    And item by number 2 "Процедуры" should be active
    And checkbox status only open should be active
    Then I select "Только активные"
    And checkbox status only open should not be active
    When I check the status of "ОТКРЫТ" for each item on the page

  Scenario: Работа фильтра Категория на странице процедур
    Given I open search procedures page
    And item by number 2 "Процедуры" should be active
    When I select filter category
    When I select 1 item in the list
    And I wait 1000
    And press button "Выбрать текущий раздел"
    And I wait 1000
    And The selected section has appeared in the filter and can be deleted
    Then I clear select category

  Scenario: Работа фильтра Регионы на странице процедур
    Given I open search procedures page
    And item by number 2 "Процедуры" should be active
    When I select filter region
    When I select 1 item in the list
    And I wait 1000
    And press button "Выбрать текущий раздел"
    And I check region in breadcrumbs and filter