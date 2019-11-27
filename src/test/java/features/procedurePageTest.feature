@all
Feature: Проверка карточки Процедуры
  Scenario: Тип открытый аукцион
    Given I open procedure page
    And I check availability of company information block
    And I check availability of the procedure description block
    And I check the presence of a block of goods procedure

  Scenario: Тип закрытый аукцион
    Given I open closed procedure page
    And I should see message "Эта процедура является закрытой. Доступ к участию предоставляется только по приглашению компании-организатора."