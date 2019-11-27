@dockerTest
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
