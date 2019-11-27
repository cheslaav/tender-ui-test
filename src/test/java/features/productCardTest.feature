@all
Feature: Проверка карточки товара
  Scenario: Проверка базовых элементов карточки товара
    Given I open product page
    When I check breadcrumbs block
    And I check product category and the last item of bread crumbs are the same
    When I check the name of the product
    When I check 1 tab name "Описание" should be active

  Scenario: Проверка наличия табов у товара
    Given I open product page
    When I check tab on product
    And  I check field "ID" presence
    And  I check value field ID not empty
    And  I check field "Ед. Изм.:" presence
    And  I check value field unit not empty

  Scenario: Проверка таба ПОКУПАТЕЛИ у товара
    Given I open product page
    And check tab prod "Описание"
    And check tab prod "ПОКУПАТЕЛИ" if there is a page

  Scenario: Проверка таба ПОСТАВЩИКИ у товара
    Given I open product page
    And check tab prod "Описание"
    And check tab prod "ПОСТАВЩИКИ" if there is a page

  Scenario: Проверка таба ПРОЦЕДУРЫ у товара
    Given I open product page
    And check tab prod "Описание"
    When I check result procedure on product page