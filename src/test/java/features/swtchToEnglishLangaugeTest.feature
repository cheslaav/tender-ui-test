@all
 Feature: Переключение языка
   Scenario: Проверка главной страницы после переключения на английский язык
     Given I open homepage
     When I switch langauge "Русский" to "English"
     Then I check the element names on the English home page
      | Name            |
      | Log in          |
      | Products        |
      | Procedures      |
      | Companies       |
      | For buyers      |
      | For sellers     |
      | About system    |
      | Help            |
      | About company   |
      | Services        |
      | Tariff          |
      | Contacts        |
      | Addresses       |
      | Requisites      |

   Scenario: Проверка страницы поиска товаров
     Given I open search product page
     Then I check the element names on the English product search page
       | Name                    |
       | Log in                  |
       | Type                    |
       | Any                     |
       | Sell                    |
       | Purchase                |
       | Category                |
       | Choose                  |
       | Region                  |
       | Where to search?        |
       | Everywhere              |
       | Among the ads           |
       | Among the processes     |
       | Select current section  |
       | Buyers                  |
       | Suppliers               |
       | Procedures              |
       | Show more               |
       | Products                |

   Scenario: Проверка страницы поиска процедур
     Given I open search procedures page
     Then I check the element names on the English procedures search page
       | Name               |
       | Status             |
       | Only active        |
       | operation kind     |
       | Bidding of offers  |
       | End of offers      |
       | Products           |
       | participants       |
       | Show more          |
       | Processes          |

   Scenario: Проверка страницы поиска компаний
    Given I open search company page
     Then I check the element names on the English company search page
       | Name               |
       | Companies          |
       | Region             |
       | Choose             |
       | Purchase           |
       | Sell               |
       | Procedures         |
       | Show more          |

   Scenario: Проверка страницы карточки товара
     Given I open product page
     Then I check the element names on the English product page
       | Name               |
       | Main               |
       | Catalog            |
       | Description        |
       | Buyers             |
       | Suppliers          |
       | Procedures         |
       | ID:                |
       | Unit:              |
       | GOST:              |
       | Region             |
       | Volume             |
       | Price              |
       | operation kind     |
       | Bidding of offers  |
       | End of offers      |
       | Products           |
       | participants       |

   Scenario: Проверка страницы карточки компании
     Given I open company card page
     Then I check the element names on the English company card
       | Name                        |
       | Main                        |
       | All regions                 |
       | Description                 |
       | Purhase list                |
       | Price list                  |
       | Procedures                  |
       | ID:                         |
       | Full name:                  |
       | Type of company activity:   |
       | Legal address:              |
       | Postal address:             |
       | ITN/IEC:                    |
       | Phone:                      |
       | Site:                       |
       | Registration date:          |
       | Show more                   |
       | operation kind              |
       | Bidding of offers           |
       | End of offers               |
       | Products                    |
       | participants                |

   Scenario: Проверка страницы карточки процедуры
     Given I open procedure page
     Then I check the element names on the English procedure card
       | Name                        |
       | Main                        |
       | All regions                 |
       | Region                      |
       | Purchase                    |
       | Sell                        |
       | Procedures                  |
       | Products                    |
       | participants                |
       | Type:                       |
       | Status:                     |
       | Currency:                   |
       | Bidding of offers:          |
       | End of offers:              |
       | To:                         |



