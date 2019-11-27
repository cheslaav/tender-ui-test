@all
Feature: Проверка главной страницы
  Scenario: Кнопки в поле поиска
    Given I open homepage
    Then set in field "Товары" the value "Сапоги" and action "3"
    Then set in field "Процедуры" the value "Замена" and action "3"
    Then set in field "Компании" the value "Газ" and action "3"
    Then set in field "Товары" the value "Сапоги" and action "4"
    And I open homepage
    Then set in field "Процедуры" the value "Замена" and action "4"
    And I open homepage
    Then set in field "Компании" the value "Газ" and action "4"
    And I open homepage

  Scenario: step set value
    Given I open homepage
    Then set in field "Товары" the value "Труба" and action "3"
    And I check value "Труба" in quick result
    Then set in field "Товары" the value "Труба стальная" and action "3"
    And I check value "Труба стальная" in quick result
    Then set in field "Товары" the value "Труба стальная бесшовная" and action "3"
    And I check value "Труба стальная бесшовная" in quick result
    Then set in field "Товары" the value "Труба стальная бесшовная оребренная" and action "3"
    And I check value "Труба стальная бесшовная оребренная" in quick result



    Scenario: Работоспособность ссылок в правом нижнем углу
      Given I open homepage
      Then I check footer link "Процедуры" and assert title page "ТендерПро"
      Then I check footer link "Товары" and assert title page "ТендерПро"
      Then I check footer link "Организаторам" and assert title page "Организаторам · Аналитический центр Tender.Pro"
      Then I check footer link "Участникам" and assert title page "Участникам · Аналитический центр Tender.Pro"
      Then I check footer link "О системе" and assert title page "О системе · Аналитический центр Tender.Pro"
      Then I check footer link "Справка" and assert title page "Справочная система · Аналитический центр Tender.Pro"
      Then I check footer link "О компании" and assert title page "О компании · Аналитический центр Tender.Pro"
      Then I check footer link "Услуги" and assert title page "Услуги · Аналитический центр Tender.Pro"
      Then I check footer link "Тарифы" and assert title page "Тарифы · Аналитический центр Tender.Pro"
      Then I check footer link "Контакты" and assert title page "Контакты · Аналитический центр Tender.Pro"
      Then I check footer link "Адреса" and assert title page "Адреса и реквизиты · Аналитический центр Tender.Pro"
      Then I check footer link "Реквизиты" and assert title page "Адреса и реквизиты · Аналитический центр Tender.Pro"
