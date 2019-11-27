@prodTest
Feature: Загрузка позиций базовой процедуры из файла. Негативный тест.
  Background: Тест
    Given I logged in test as "admin"
    And I check sidebar link "Конструктор процедур"
    And I create "basic" order

  Scenario: Загрузка позиций с пустого файла
    When I upload "empty.xlsx" file for positions
    Then I should see error message for empty file

  Scenario: Загрузка позиций с неподдерживаемого файла
    When I upload "unsupported.jpg" file for positions
    Then I should see error message for unsupported file

  Scenario: Загрузка позиций с файла с не правильными типами данных
    When I upload "wrong_format.xlsx" file for positions
    Then I should see error message for wrong format file

  Scenario: Загрузка позиций с файла с не допустимыми значениями
    When I upload "wrong_value.xlsx" file for positions
    Then positions should be added
    And positions should contain error
