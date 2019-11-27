@all
Feature: Проверка страницы поиска товаров
  Scenario: Работа фильтра тип
    Given I open search product page
    And item by number 1 "Товары" should be active
    When I should see filter "Тип"
    When I select "Продажа"
    And I see each item contains a sale value
    When I select "Закупка"
    And I see each item contains a price value
    When I select "Любой"

  Scenario: Простой выбор категории
    Given I open search product page
    And item by number 1 "Товары" should be active
    And I should see filter "Категория"
    When I select category
    When I select "Аудио. Видео. Фото"
    And I wait 1000
    And press button "Выбрать текущий раздел"
    And The selected section has appeared in the filter and can be deleted
    Then I clear select category


  Scenario: Выбор категории с переходами по навигации
    Given I open search product page
    And item by number 1 "Товары" should be active
    And I should see filter "Категория"
    When I select category
    When I select "Аудио. Видео. Фото"
    Then I press level up
    When I select "Аудио. Видео. Фото"
    When I select "Аудиотехника"
    Then I press home
    When I select "Аудио. Видео. Фото"
    When I select "Аудиотехника"
    When I select "Микрофоны"
    And The selected section has appeared in the filter and can be deleted
    Then I check catalog path "Аудио. Видео. Фото" "Аудиотехника" "Микрофоны"
    And I check active selected category "Микрофоны"
    Then I clear select category

  Scenario: Простой выбор региона
    Given I open search product page
    And item by number 1 "Товары" should be active
    When I select region
    When I select "Россия"
    And press button "Выбрать текущий раздел"
    And The selected section has appeared in the filter and can be deleted
    And I clear select region

  Scenario: Выбор региона с переходами по навигации
    Given I open search product page
    And item by number 1 "Товары" should be active
    When I select region
    When I select "Россия"
    Then I press level up
    When I select "Россия"
    When I select "Уральский"
    Then I press home
    When I select "Россия"
    When I select "Северо-Западный"
    When I select "Новгородская область"
    And I wait 1000
    And press button "Выбрать текущий раздел"
    And The selected section has appeared in the filter and can be deleted
    Then I check region path "Россия" "Северо-Западный" "Новгородская область"
    And I check active selected category "Новгородская область"
    And I clear select region

  Scenario: Работа фильтра Где искать?
    Given I open search product page
    And item by number 1 "Товары" should be active
    And I should see filter "Где искать?"
    When I select "Среди объявлений" in filter where search
    When I select "Среди процедур" in filter where search
    And I should see filter "Статус"
    And checkbox should be active
    And I select "Только активные"
    When I select "Везде" in filter where search


