@all

Feature: Study test. Check tabs

  Scenario: Profile tab
    Given I logged in
    And Check section name tab "Профиль"
    And I see stub "Раздел в разработке"

  Scenario: Bargaining tab
    Given I logged in
    Then I select tab "Организуем"
    And Check section name tab "Торги"
    And I see 1 active tab "Организуем"
    Then Check list result
    When Check button "Создать конкурс"

    Then I select tab "Участвуем"
    And Check section name tab "Торги"
    And I see 2 active tab "Участвуем"
    Then Check list result
    When Check button "Создать конкурс"

    Then I select tab "Приглашения"
    And Check section name tab "Торги"
    And I see 3 active tab "Приглашения"
    When Check button "Создать конкурс"

  Scenario:  Contract tab
    Given I logged in
    Then I select tab "Договоры"
    And Check section name tab "Договоры"
    And I see 1 active tab "Исходящие"
    Then Check list result
    When Check button "Создать договор"

    Then I select tab "Входящие"
    And Check section name tab "Договоры"
    And I see 2 active tab "Входящие"
    Then Check list result
    When Check button "Создать договор"

  Scenario: Order tab
    Given I logged in
    Then I select tab "Заказы"
    And Check section name tab "Заказы"
    And I see 1 active tab "Исходящие"
    Then Check list result
    When Check button "Создать заказ"

    Then I select tab "Входящие"
    And Check section name tab "Заказы"
    And I see 2 active tab "Входящие"
    Then Check list result
    When Check button "Создать заказ"