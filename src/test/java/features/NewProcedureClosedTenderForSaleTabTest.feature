@all
Feature: Закрытый конкурс на продажу тест вкладок

  Background: создаём закрытый конкурс на продажу
    Given I logged in
    And I click on "Торги"
    And I click button "Создать конкурс"
    And I select close tender for sale

  Scenario:  Имя процедуры по умолчанию состоит из текста и айди
    Given I check id in url and compare tender name

  Scenario: Default status Формирование
    Given I check default status "Формирование"

  Scenario: Проверка кол-ва и имён вкладок
    Given I check the number of tabs 6
    And I check name Tab on page create procedure
      | Name           |
      | Условие        |
      | Товары         |
      | участники      |
      | Договоры       |
      | Заказы         |
      | Отчетность     |
    And I check tab "Условие" is active

  Scenario: Проверка блока о компании
    Given I see block about company
    And I see company name "ТендерПро-2"
    And I see company city "Новодвинск"
    And I see company logo
    And I see block active procedure and 3 item
    And I see active company item buyer "Закупка"
    And I see active company item supplier "Продажа"
    And I see active company item auction open "Процедуры"