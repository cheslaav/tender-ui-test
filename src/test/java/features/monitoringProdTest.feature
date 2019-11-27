@prodTest
Feature: Мониторинг кабинета на бою
  Scenario: Проверка работоспособности кабинета
    Given I logged in
    And I check login user "Jenkins - 1"
    And I check workspace
    And I see message "Раздел в разработке"
    Then I check sidebar link
      | Name         |
      | Конструктор процедур      |
      | Торги        |
      | Организуем   |
      | Участвуем    |
      | Приглашения  |
      | Договоры     |
      | Заказы       |
      | Сотрудники   |
      | Товары       |
      | Заказ товаров|
      | Согласования |
      | Шаблоны      |


    Scenario: Переход по ссылкам в сайдбаре
      Given I logged in
      And I check sidebar link "Конструктор процедур"
      And check tab trash "Товары", "Процедуры", "Компании"
      And I check sidebar link "Торги"
      And check tab tender "Организуем", "Участвуем", "Приглашения"
      And I check sidebar link "Договоры"
      And check tab contract "Исходящие", "Входящие"
      And I check sidebar link "Заказы"
      And check tab order "Исходящие", "Входящие"
      And I check sidebar link employees "Сотрудники"
      And I check sidebar link "Товары"
      And check tab order product "Заказ товаров"
      And I check sidebar link coordination "Согласования"
      And check tab coordination "Все согласования в системе", "Вы согласующий", "Созданные вами", "Прочее"
      And I check sidebar link patterns "Шаблоны"

    Scenario: Проверка элемента из раздела Торги -> Организуем
      Given I logged in
      And I select "Организуем"
      And I check a random item from the list own "Организуем"

    Scenario: Проверка элемента из раздела Торги -> Участвуем
      Given I logged in
      And I select "Участвуем"
      And I check a random item from the list party "Участвуем"

  Scenario: Проверка элемента из раздела Торги -> Приглашения
    Given I logged in
    And I select "Приглашения"
    And I check a random item from the list invitation "Приглашения"

  Scenario: Проверка элемента из раздела Договоры -> Исходящие
    Given I logged in
    And I select "Договоры"
    And I check a random item from the list contract own "Исходящие"

  Scenario: Проверка элемента из раздела Договоры -> Входящие
    Given I logged in
    And I select "Договоры"
    And I check a random item from the list contract party "Входящие"

  Scenario: Проверка элемента из раздела Заказы -> Исходящие
    Given I logged in
    And I select "Заказы"
    And I check a random item from the list order own "Исходящие"

  Scenario: Проверка элемента из раздела Заказы -> Входящие
    Given I logged in
    And I select "Заказы"
    And I check a random item from the list order party "Входящие"

  Scenario: Проверка элемента из раздела Сотрудники
    Given I logged in
    And I select "Сотрудники"
    And I check a random item from the list employees "Сотрудники"

  Scenario: Проверка элемента из раздела Товары -> Заказ товаров
    Given I logged in
    And I select "Товары"
    And I check a random item from the list orders "Заказ товаров"

  Scenario: Проверка элемента из раздела Согласования -> Все согласования в системе
    Given I logged in
    And I select "Согласования"
    And I check a random item from the list all approvals "Все согласования в системе"

  Scenario: Проверка элемента из раздела Согласования -> Вы согласующий
    Given I logged in
    And I select "Согласования"
    And I check a random item from the list you are matching "Вы согласующий"

  Scenario: Проверка элемента из раздела Согласования -> Созданные вами
    Given I logged in
    And I select "Согласования"
    And I check a random item from the list created by you "Созданные вами"

  Scenario: Проверка элемента из раздела Согласования -> Прочее
    Given I logged in
    And I select "Согласования"
    And I check a random item from the list other "Прочее"

  Scenario: Проверка раздела Шаблоны
    Given I logged in
    And I select "Шаблоны"
    And I create a new pattern

  Scenario: Проверка создания конкурса
    Given I logged in test as "admin"
    And I check sidebar link "Торги"
    Then I create Contest

  Scenario: Отправка предложения на конкурс
    Given  I logged in test as "admin2"
    And I check sidebar link "Торги"
    Then I send offer


  Scenario: Запуск соглашения
    Given I logged in test as "admin"
    Then I launch approval

  Scenario: Принятия соглашения
    Given I logged in test as "employee"
    Then I accept agreement

  Scenario: Отклонения соглашения
    Given I logged in test as "admin"
    And I check sidebar link "Торги"
    Then I create Contest
    Given I logged in test as "admin"
    Then I launch approval
    Given I logged in test as "employee"
    Then I ignore agreement

  Scenario: Проверка создания Шаблона
    Given I logged in test as "admin"
    And I check sidebar link patterns "Шаблоны"
    Then I create new template

  Scenario: Проверка изменение шаблона
    Given I logged in test as "admin"
    Then I change template

  Scenario: Проверка создания договора на основе конкурса
    Given I logged in test as "admin"
    And I check sidebar link "Торги"
    Then I create Contest
    Given  I logged in test as "admin2"
    And I check sidebar link "Торги"
    Then I send offer
    Given I logged in test as "admin"
    Then I send contest to bidding
    Given I logged in test as "Employee"
    Then I accept agreement
    Given I logged in test as "admin"
    Then I create contract based on contest

  Scenario: Создания Договора без основы и проверка изменения НДС
    Given I logged in test as "admin"
    And I check sidebar link "Конструктор процедур"
    Then I create agreement without basis

  Scenario: Проверка скачивания позиции при создания Базового заказа
    Given I logged in test as "admin"
    And I check sidebar link "Конструктор процедур"
    Then I check download position from basic order

  Scenario: Проверка заполнения позиции из файла при создания Базового заказа
    Given I logged in test as "admin"
    And I check sidebar link "Конструктор процедур"
    Then I check filling position from file

  Scenario: Проверка обновление объема позиций из файла
    Given I logged in test as "admin"
    And I check sidebar link "Конструктор процедур"
    Then I update volume position from file