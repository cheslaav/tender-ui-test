@all
Feature: Проверка страницы результатов
  Scenario: Соответствие надписи в пустом поле выбранной вкладке.
    Given I open homepage
    And I see "search" form
    And I find item "Товары" and clicked on it
    And I find item "Процедуры" and clicked on it
    And I find item "Компании" and clicked on it

  Scenario: Функция очистки поля поиска
    Given I open homepage
    When I searching in group "Товары" I enter "Самосвал"
    Then click on the cross
    And the search string clears the values "Самосвал"

  Scenario: Функция поиска по кнопке
    Given I open homepage
    When I searching in group "Товары" I enter "Самосвал"
    Then click on the icon search

  Scenario: Выпадающий список быстрого поиска при последовательном введении слова
    Given I open homepage
    When I searching in group "Товары" I enter "Металл"
    Then I see dropdown window "Товар"
    And I they should have size 5


