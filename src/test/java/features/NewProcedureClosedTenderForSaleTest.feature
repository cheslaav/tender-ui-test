@all
Feature: New Procedure Closed Tender For Sale

  Scenario: Procedure creation window

    Given I logged in
    Then I select tab "Организуем"
    When I click button "Создать конкурс"
    And I see popup "Новая процедура"
    And I see message "Данный функционал работает в тестовом режиме. Пожалуйста не проводите реальные процедуры"
    And I see field type procedure "Тип процедуры"
    And I check name item
      | Name                             |
      | По умолчанию                     |
      | Закрытый конкурс на продажу      |
      | Закрытый конкурс на покупку      |
      | Открытый конкурс на продажу      |
      | Открытый конкурс на покупку      |
    And I see field trash "Корзиночка"
    And I check popup trash
    When Title should have name "Объекты"
    And I see 1 tab name "Искать"
    And I see search field with text "Найти компанию, процедуру или товар"
    Then I select tab "Процедура"
    And I see 2 tab name "Процедура"
    Then I select tab "Товар"
    And I see 3 tab name "Товар"
    When I cancel change
    Then I click brown button "Отмена"

  Scenario: search in trash
    Given I logged in
    Then I select tab "Организуем"
    When I click button "Создать конкурс"
    And I see popup "Новая процедура"
    And I check popup trash
    And I see search field with text "Найти компанию, процедуру или товар"
    And I input request "Лес"
    And I check group name
      | Name         |
      | Компания     |
      | Процедура    |
      | Товар        |
    And I check that each group elements contain text "лес"

  Scenario: Default procedure Basic procedure settings
    Given I logged in
    Then I select tab "Организуем"
    When I click button "Создать конкурс"
    And I see popup "Новая процедура"
    When I click button in window create "Создать"
    Then I see 1 active tab "Условие"
    And I should see company name in page
    And I should see company city in page
    And I check operations stats block
    And I check basic procedure settings
      | Name                                     |
      | Наименование процедуры:                  |
      | Направление процедуры:                   |
      | Дата и время активации процедуры:        |
      | Дата и время окончания процедуры:        |
      | Правило участия:                         |
      | Комментарий организатора:                |

  Scenario: Add custom field
    Given I logged in
    Then I select tab "Организуем"
    When I click button "Создать конкурс"
    And I see popup "Новая процедура"
    When I click button in window create "Создать"
    And I click pencil on basic procedure settings
    And I see popup "Добавление дополнительных полей"
    Then I see 1 active tab in popup "Для организатора"
    And I see stub "Каталог шаблонов для данного раздела пуст"
    And I see button "Закрыть"
    Then I select tab "Для участника"
    And I check name element on tab for the participant
      | Name                                                           |
      | Опыт работы на рынке                                           |
      | Опыт поставок данной группы                                    |
      | Крупные покупатели                                             |
      | Референс-лист                                                  |
      | Наименование организации                                       |
      | ИНН                                                            |
      | ФИО, Должность и Контакты единоличного исполнительного органа  |
      | Количество лет на рынке                                        |
    And I see button "Закрыть"
    Then I select tab "Настраиваемое"
    And I check name element on tab customizable
      | Name                      |
      | Раздел                    |
      | Наименование              |
      | Тип                       |
      | Заполняет участник        |
      | Приватное поле            |
    And I see button "Отмена"
    And I see button primary "Создать"

  Scenario: Create basic procedure
    Given I logged in
    Then I select tab "Организуем"
    When I click button "Создать конкурс"
    And I see popup "Новая процедура"
    When I click button in window create "Создать"
    And I wait 2000
    And I see status "формирование" and button "Опубликовать"
    And I wait 2000
    Then I type in field "Наименование процедуры" text "Автотестовая процедура 1"
    And I wait 2000
    And I click button "Опубликовать"
    And I wait 2000
    And I see status "открыт" and button "Вернуть на Формирование"
    And I wait 5000

  Scenario: Create procedure
    Given I logged in
    Then I create default procedure
    And I set in field "Наименование процедуры" value "Тестовая процедура 2"
    And I select direction of the procedure "Продажа"
    When I select tab "Товары"
    And press button "Добавить лот"
    When I should see block "Настройки по умолчанию для всех лотов"
    Then press button "Добавить позицию"
    And I select item "Выберите товар"
    And I select 3 item on list
    And I set value "200" in field "Объем организатора"
    And I specify the unit
    And I set value "5000" in field "Цена организатора"
    And I wait 1000
    And I check fields with empty fields
    And press button "Опубликовать"
    And I wait 2000
    And press button "Вернуть на Формирование"
    And I press add
    And I add lot
    And I wait 1000
    And I check fields with empty fields
    And press button "Опубликовать"
    And I wait 2000

    Scenario: Closed tender for sale
      Given I logged in
      Then I create closed tender for sale
      And I set in field "Наименование процедуры" value "Тестовая процедура 2"
      And I select direction of the procedure "Продажа"
      When I select tab "Товары"
      And I add lot
      And I wait 1000
      And I check fields with empty fields
      And press button "Опубликовать"