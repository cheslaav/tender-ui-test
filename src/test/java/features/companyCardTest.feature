@all
Feature: Проверка карточки Компании
  Scenario: наличие блока информации о компании
    Given I open company card page
    And I should see company name
    And I should see company city

  Scenario: наличие вкладок компании тест
    Given I open company card page
    Then I should see active tab "Описание"
    Then I check description field
        | title                | required |
        | ID:                  | true     |
        | Полное наименование: | true     |
        | Сфера деятельности:  | false    |
        | Юридический адрес:   | true     |
        | Почтовый адрес:      | true     |
        | ИНН/КПП:             | true     |
        | Телефон:             | true     |
        | Сайт:                | false    |
        | Дата регистрации:    | true     |

  Scenario: наличие вкладок компании
    Given I open company card page
    Then I check company tab
      | Name          |
      | ОПИСАНИЕ      |
      | ПЛАН ЗАКУПОК  |
      | ПРАЙС-ЛИСТ    |
      | ПРОЦЕДУРЫ     |

  Scenario: Соответствие значения у вкладок реальному значению элементов
    Given I open company card page
    Then I check company tab value