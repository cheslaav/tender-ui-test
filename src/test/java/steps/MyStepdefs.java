package steps;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import helpers.Helpers;
import io.cucumber.datatable.DataTable;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import pages.Approvals;
import pages.Basket;
import pages.Bidding;
import pages.Template;

import javax.naming.directory.SearchResult;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Pattern;

import static com.codeborne.selenide.CollectionCondition.sizeLessThanOrEqual;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static helpers.Const.*;
import static helpers.Helpers.*;

public class MyStepdefs {

    @Given("I logged in test as {string}")
    public void iLoggedInTest(String arg0){
        AuthByTestTpro(arg0);
    }



    @Given("^I open homepage$")
    public void iOpenHomepage() {
        Configuration.startMaximized = true;
        open(DEFAULT_URL);
    }

    @Given("^I open product page$")
    public void iOpenProductPage() {
        Configuration.startMaximized = true;
        open(PROD_PAGE);
    }

    @Given("^I open search product page$")
    public void iOpenSearchProductPage() {
        Configuration.startMaximized = true;
        open(SEARCH_PROD_PAGE);
    }

    @Given("^I open search procedures page$")
    public void iOpenSearchProceduresPage() {
        Configuration.startMaximized = true;
        open(SEARCH_PROCEDURES_PAGE);
    }

    @Given("^I open search company page$")
    public void iOpenSearchCompanyPage() {
        Configuration.startMaximized = true;
        open(SEARCH_COMPANY_PAGE);
    }

    @Given("^I open procedure page$")
    public void iOpenProcedurePage() {
        Configuration.startMaximized = true;
        open(PROCEDURE_PAGE);
    }

    @Given("^I open closed procedure page$")
    public void iOpenClosedProcedurePage() {
        Configuration.startMaximized = true;
        open(CLOSED_PROCEDURE_PAGE);
    }

    @Given("^I open company card page$")
    public void iOpenCompanyCardPage() {
        Configuration.startMaximized = true;
        open(COMPANY_PAGE);
    }


    @And("^I see \"([^\"]*)\" form$")
    public void iSeeForm(String arg0) {
        $(byAttribute("type", arg0));
    }

    @And("^I find item \"([^\"]*)\" and clicked on it$")
    public void iFindItemAndClickedOnIt(String arg0) {

        for (int i = 1; i <= 3; i++) {
            String str1 = $(".suggest--nav > span:nth-child(" + i + ")").getText();
            if (str1.equalsIgnoreCase(arg0)) {
                $(".suggest--nav > span:nth-child(" + i + ")").click();
                sleep(500);
            }
        }
        $(".iac--index > footer:nth-child(3)").click();
    }


    @Then("^I enter the \"([^\"]*)\" request \"([^\"]*)\" in the field$")
    public void iEnterTheRequestInTheField(String arg0, String arg1) {
        Helpers.targetElement(arg0);
        $(".lg > input:nth-child(1)").setValue(arg1).pressEnter();


    }

    @Then("^I uncheck checkbox \"([^\"]*)\"$")
    public void iUncheckCheckbox(String arg0) {
        $("div.iac--filter-block:nth-child(4) > div:nth-child(2) > div:nth-child(1) > label:nth-child(1)")
                .shouldHave(text(arg0)).click();
    }

    @Then("^I wait (\\d+)$")
    public void iWait(int arg0) {
        sleep(arg0);
    }

    @When("^I check that in every result \"([^\"]*)\" there is a word \"([^\"]*)\"$")
    //необходимо допилить. есть баг в выдаче
    public void iCheckThatInEveryResultThereIsAWord(String arg0, String arg1) {
        String str = arg0;
        char A = str.charAt(0);
        switch (A) {
            case 'Т': {
                Integer resultOnPage = $$("div.summary").size();
                for (int i = 1; i <= resultOnPage; i++) {
                    $("div.summary:nth-child(" + i + ") > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > a:nth-child(1)").shouldHave(text(arg1.substring(0, 4)));
                }
                break;
            }
            case 'П': {
                Integer resultOnPage = $$("div.summary").size();
                for (int i = 1; i <= resultOnPage; i++) {
                    $("div.summary:nth-child(" + i + ") > div:nth-child(1) > div:nth-child(1) " +
                            "> div:nth-child(2) > div:nth-child(1) > div:nth-child(1)")
                            .shouldHave(text(arg1.substring(0, 5)));
                }
                break;
            }
            case 'К': {
                Integer resultOnPage = $$("div.summary").size();
                for (int i = 1; i <= resultOnPage; i++) {
                    $("div.summary:nth-child(" + i + ") > div:nth-child(1) > div:nth-child(1) " +
                            "> div:nth-child(2) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1)")
                            .shouldHave(text(arg1.substring(0, 1)));
                }
                break;
            }
        }
    }


    @Then("^I check breadcrumbs block$")
    public void iCheckBreadcrumbsBlock() {
        $(".icon_spiner").shouldBe(disappear);
        $(".iac--breadcrumbs ").shouldBe(appear);
        $(".iac--breadcrumbs ").shouldBe(visible);
        System.out.println("Test check breadcrumbs passed \n");
    }

    @Then("^I check product header name$")
    public void iCheckProductHeaderName() {
        $(".iac--product__description-header > h3").shouldNotBe(empty);
        System.out.print("Test check product header name passed \n");
    }

    @Then("^I check product category$")
    public void iCheckProductCategory() {
        String str1 = $(".iac--link > span:nth-child(2)").getText();
        String str2 = $(".iac--breadcrumbs_item:nth-last-child(1)").getText();
        if ((str1).equals(str2)) {
            System.out.println("Test check product category passed \n");
        }
        ;
    }

    @Then("^set in field \"([^\"]*)\" the value \"([^\"]*)\" and action \"([^\"]*)\"")
    public void setInFieldTheValueAndClickCross(String arg0, String arg1, Integer i) {
        actionWithTargetElement(arg0, arg1, i);
        sleep(1000);
    }


    @Then("^I check footer link \"([^\"]*)\" and assert title page \"([^\"]*)\"$")
    public void iCheckFooterLinkAndAssertTitlePage(String arg0, String arg1) {
        $(".iac--index > footer:nth-child(3)").find(byText(arg0)).click();
        String title = title();
        if (title.equalsIgnoreCase(arg1)) {
            open(DEFAULT_URL);
        }
    }

    @When("^I select filter category$")
    public void iSelectFilterCategory() throws Throwable {
        $("div.iac--filter-block:nth-child(2) > div:nth-child(2) > div:nth-child(1) > div:nth-child(1)").click();
    }


    @Then("^I select \"([^\"]*)\"$")
    public void iSelect(String arg0) {
        $(byText(arg0)).shouldBe(appear).click();
        sleep(1000);

    }

    @And("^press button \"([^\"]*)\"$")
    public void pressButton(String arg0) {
        $(byText(arg0)).shouldBe(appear).click();
        sleep(1000);
    }

    @Then("^I clear select category$")
    public void iClearSelectCategory() {
        sleep(1000);
        $(".iac--filter-pick > .item > .clear").click();
    }

    @Then("^I press level up$")
    public void iPressLevelUp() {
        $("icon.iac--btn:nth-child(2)").shouldBe(appear).click();
        $(byText("Загрузка ...")).shouldHave(disappear);
    }

    @Then("^I press home$")
    public void iPressHome() {
        $("icon.iac--btn:nth-child(1)").click();
        $(byText("Загрузка ...")).shouldHave(disappear);
    }

    @Then("^I check catalog path \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\"$")
    public void iCheckCatalogPath(String arg0, String arg1, String arg2) {
        $("span.iac--breadcrumbs_item-label:nth-child(2)").shouldHave(text("Главная"));
        $("li.iac--breadcrumbs_item:nth-child(2) > a:nth-child(1) > span:nth-child(1)")
                .shouldHave(text("Каталог"));
        $("li.iac--breadcrumbs_item:nth-child(3) > a:nth-child(1) > span:nth-child(1)").shouldHave(text(arg0));
        $("li.iac--breadcrumbs_item:nth-child(4) > a:nth-child(1) > span:nth-child(1)").shouldHave(text(arg1));
        $("li.iac--breadcrumbs_item:nth-child(5) > a:nth-child(1) > span:nth-child(1)").shouldHave(text(arg2));
    }

    @And("^I check active selected category \"([^\"]*)\"$")
    public void iCheckActiveSelectedCategory(String arg0) {
        $("span.title:nth-child(2)")
                .shouldHave(text(arg0));
    }

    @When("^I select region$")
    public void iSelectRegion() {
        $("div.iac--filter-block:nth-child(3) > div:nth-child(2) > div:nth-child(1) > div:nth-child(1)")
                .click();
    }


    @And("^I clear select region$")
    public void iClearSelectRegion() throws Throwable {
        sleep(2000);
        $(".iac--filter-pick > .item > .clear").shouldBe(appear).click();
    }

    @Then("^I check region path \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\"$")
    public void iCheckRegionPath(String arg0, String arg1, String arg2) {
        $("span.iac--breadcrumbs_item-label:nth-child(2)").shouldHave(text("Главная"));
        $("li.iac--breadcrumbs_item:nth-child(2) > a:nth-child(1) > span:nth-child(1)")
                .shouldHave(text("Все регионы"));
        $("li.iac--breadcrumbs_item:nth-child(3) > a:nth-child(1) > span:nth-child(1)").shouldHave(text(arg0));
        $("li.iac--breadcrumbs_item:nth-child(4) > a:nth-child(1) > span:nth-child(1)").shouldHave(text(arg1));
        $("li.iac--breadcrumbs_item:nth-child(5) > a:nth-child(1) > span:nth-child(1)").shouldHave(text(arg2));
    }

    @When("^I searching in group \"([^\"]*)\" I enter \"([^\"]*)\"$")
    public void iSearchingInGroupIEnter(String arg0, String arg1) {
        $(".suggest--nav").find(byText(arg0)).click();
        sleep(100);
        $(".lg > input:nth-child(1)").setValue(arg1);
        sleep(500);
    }

    @Then("^click on the cross$")
    public void clickOnTheCross() {
        $("span.icon:nth-child(3)").click();
    }


    @And("^the search string clears the values \"([^\"]*)\"$")
    public void theSearchStringClearsTheValues(String arg0) throws Throwable {
        $(".suggest--nav").shouldNotBe(text(arg0));
    }

    @Then("^click on the icon search$")
    public void clickOnTheIconSearch() {
        $("span.icon:nth-child(4)").click();
    }

    @Then("^I see dropdown window \"([^\"]*)\"$")
    public void iSeeDropdownWindow(String arg0) {
        $(".list > .group").shouldHave(text(arg0));
    }

    @And("^I they should have size (\\d+)$")
    public void iTheyShouldHaveSize(int arg0) {
        Integer listSize = $$(".list > .item").size();
        if (listSize == arg0) {
            $("span.icon:nth-child(4)").click();
        }
        System.out.println("Expexted value " + arg0 + ". Present value: " + listSize);
    }

    @When("^I should see filter \"([^\"]*)\"$")
    public void iShouldSeeFilter(String arg0) {
        $(".iac--filter-panel").shouldHave(text(arg0));
    }

    @And("^I see each item contains a sale value$")
    public void iSeeEachItemContainsASaleValue() {
        sleep(1000);
        Integer item = $$(".iac--search__result > .summary-list > .summary").size();
        for (int i = 1; i < item - 1; ) {
            String presentValue = $("div.summary:nth-child(" + i + ") > div:nth-child(1) " +
                    "> div:nth-child(1) > div:nth-child(2) > footer:nth-child(4) > div:nth-child(1) " +
                    "> a:nth-child(2)").getText();
            int presentValueInt = Integer.parseInt(presentValue);
            if (presentValueInt >= 1) {
                i++;
            } else
                System.out.println("Значение отсутствует");
        }
    }

    @And("^I see each item contains a price value$")
    public void iSeeEachItemContainsAPriceValue() {
        Integer item = $$(".iac--search__result > .summary-list > .summary").size();
        for (int i = 1; i < item - 1; ) {
            String presentValue = $("div.summary:nth-child(" + 1 + ") > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > footer:nth-child(4) > div:nth-child(1) > a:nth-child(1)").shouldBe(appear).shouldBe(visible).getText();
            int presentValueInt = Integer.parseInt(presentValue);
            if (presentValueInt >= 1) {
                $("div.summary:nth-child(" + i + ") > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) ").click();
                i++;
            } else
                System.out.println("Значение отсутствует");
        }
    }

    @And("^The selected section has appeared in the filter and can be deleted$")
    public void theSelectedSectionHasAppearedInTheFilterAndCanBeDeleted() {
        sleep(1000);
        $(".iac--filter-pick > .item > .clear").shouldBe(appear).shouldBe(visible);
    }

    @When("^I select \"([^\"]*)\" in filter where search$")
    public void iSelectInFilterWhereSearch(String arg0) {
        $("div.iac--filter-block:nth-child(4) > div:nth-child(2)").find(byText(arg0)).click();
    }

    @And("^checkbox should be active$")
    public void checkboxShouldBeActive() {
        $("div.iac--filter-block:nth-child(5) > div:nth-child(2) > div:nth-child(1) " +
                "> label:nth-child(1) > input:nth-child(1)").shouldBe(checked);
    }


    @And("^item by number (\\d+) \"([^\"]*)\" should be active$")
    public void itemByNumberShouldBeActive(int arg0, String arg1) {
        $("li.iac--tabs_item:nth-child(" + arg0 + ")").shouldHave(text(arg1)).isSelected();
    }

    @And("^checkbox status only open should be active$")
    public void checkboxStatusOnlyOpenShouldBeActive() {
        $("div.iac--filter-block:nth-child(4) > div:nth-child(2) > div:nth-child(1) " +
                "> label:nth-child(1) > input:nth-child(1)").shouldBe(checked);
    }


    @When("^I check status \"([^\"]*)\" every item on page$")
    public void iCheckStatusEveryItemOnPage(String arg0) throws Throwable {
        Integer size = $$(".summary-list > .summary").size();
        for (int i = 1; i < size; i++) {
            String str = $$(".summary-list > .summary").get(i)
                    .find(byCssSelector(".content > .header > .status")).getText();
            if (str.equalsIgnoreCase(arg0)) {
                System.out.println("Заебись! \n\n");
            } else {
                System.out.println("ВСЁ ХУЁВО! \n\n");
            }

        }
    }

    @When("^I check the status of \"([^\"]*)\" for each item on the page$")
    public void iCheckTheStatusOfForEachItemOnThePage(String arg0) {
        for (int i = 1; i <= ELEMENT_ON_PAGE; i++) {
            $("div.summary:nth-child(" + i + ") > div:nth-child(1) > div:nth-child(1)" +
                    " > div:nth-child(2) > div:nth-child(1) > div:nth-child(2)").shouldBe(appear).shouldHave(text(arg0));
        }
    }

    @And("^checkbox status only open should not be active$")
    public void checkboxStatusOnlyOpenShouldNotBeActive() {
        $("div.iac--filter-block:nth-child(4) > div:nth-child(2) > div:nth-child(1) " +
                "> label:nth-child(1) > input:nth-child(1)").shouldNotBe(checked);
    }

    @And("^I see each item contains$")
    public void iSeeEachItemContains() {
        for (int i = 1; i <= ELEMENT_ON_PAGE; i++) {
            String str1 = $("div.summary:nth-child(" + i + ") > div:nth-child(1) > div:nth-child(1) > div:nth-child(2)" +
                    " > div:nth-child(1) > div:nth-child(1) > span:nth-child(1) > span:nth-child(1)").getText();
            checkProcedureNameSale(str1, i);
        }
    }

    @And("^result contains data$")
    public void resultContainsData() {

        for (int i = 1; i <= ELEMENT_ON_PAGE; i++) {
            String str1 = $("div.summary:nth-child(" + i + ") > div:nth-child(1) " +
                    "> div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div:nth-child(1) " +
                    "> span:nth-child(1) > span:nth-child(1)").getText();
            checkProcedureNamePurchase(str1, i);
        }
    }

    @When("^I select (\\d+) item in the list$")
    public void iSelectItemInTheList(int arg0) {
        $$("div.iac--item").get((arg0 - 1)).click();
    }

    @When("^I select filter region$")
    public void iSelectFilterRegion() {
        $("div.iac--filter-block:nth-child(3) > div:nth-child(2) > div:nth-child(1) > div:nth-child(1)").click();
    }


    @When("^I select filter company region$")
    public void iSelectFilterCompanyRegion() {
        $(".add").click();
    }

    @And("^I check product category and the last item of bread crumbs are the same$")
    public void iCheckProductCategoryAndTheLastItemOfBreadCrumbsAreTheSame() {
        String breadcrumbsName = $("li.iac--breadcrumbs_item:last-child").getText();
        String catName = $(".iac--link > span:nth-child(2)").getText();
        if (breadcrumbsName.contentEquals(catName)) {
            $(".iac--product__description-header").click();
        } else {
            $(".ошибка в тесте карточки товара").click();
        }

    }

    @And("^I check the name of the product$")
    public void iCheckTheNameOfTheProduct() {
        $("iac--product__description-header > h3").shouldNotBe(empty);
    }

    @When("^I check (\\d+) tab name \"([^\"]*)\" should be active$")
    public void iCheckTabNameShouldBeActive(int arg0, String arg1) {
        $(".iac--tabs_item:first-child").shouldHave(text(arg1)).isSelected();
    }

    @Then("^I check tab on product$")
    public void iCheckTabOnProduct() {
        $$(".iac--tabs_item").size();
        if ($$(".iac--tabs_item").size() > 0) {
            $$(".iac--tabs_item").get(0).click();
        }

    }

    @And("^I check field \"([^\"]*)\" presence$")
    public void iCheckFieldPresence(String arg0) {
        $("div.content-block:nth-child(1)").shouldBe(visible).shouldHave(text(arg0));
    }


    @And("^I check value field ID not empty$")
    public void iCheckValueFieldIDNotEmpty() {
        $("div.content-block:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2)")
                .shouldBe(visible).shouldNotBe(empty);
    }

    @And("^I check value field unit not empty$")
    public void iCheckValueFieldUnitNotEmpty() throws Throwable {
        $("div.content-block:nth-child(1) > div:nth-child(1) > div:nth-child(2) > div:nth-child(2)")
                .shouldBe(visible).shouldNotBe(empty);
    }

    @And("^check tab prod \"([^\"]*)\"$")
    public void checkTabProd(String arg0) throws Throwable {
        $("li.iac--tabs_item:nth-child(1) > a:nth-child(1)").isSelected();
        Integer aboutSize = $$("div.content-block:nth-child(1) > div:nth-child(1) > div").size();
        if (aboutSize > 1) {
            for (int j = 0; j < aboutSize; j++) {
                $$("div.content-block:nth-child(1) > div:nth-child(1) > div").get(j).shouldNotBe(empty).click();
                sleep(300);
            }
        }
    }

    @And("^check tab prod \"([^\"]*)\" if there is a page$")
    public void checkTabProdIfThereIsAPage(String arg0) {
        Integer tabSize = $$("li.iac--tabs_item").size();
        if (tabSize > 1) {
            for (int i = 1; i <= tabSize; i++) {
                String itemName = $$("li.iac--tabs_item").get(i).getText();
                String cutTabName = itemName.substring(0, 3);
                String cutExpectedTabName = arg0.substring(0, 3);
                if (cutTabName.equalsIgnoreCase(cutExpectedTabName)) {
                    switch (cutTabName) {
                        case "ПОК": {
                            itemChecking(itemName, i);
                            itemContentChecking();
                            i = i + 4;
                            break;
                        }
                        case "ПОС": {
                            itemChecking(itemName, i);
                            itemContentChecking();
                            i = i + 4;
                            break;
                        }
                        case "ПРО": {

                            i = i + 4;
                            break;
                        }
                    }
                }
            }
        } else {
            System.out.println("Вкладка " + arg0 + " на странице " + PROD_PAGE + " не найдена\n");
        }
    }

    @When("^I check result procedure on product page$")
    public void iCheckResultProcedureOnProductPage() {
        $(".iac--tabs").find(byAttribute("title", "Процедуры")).shouldBe(visible).click();
        Integer size = $$(".summary-list > .summary").size();
        for (int i = 1; i <= size; i++) { //цикл проверяет наличие значений тип, статус, название, компания у каждого элемента в выдаче
            $("div.summary:nth-child(" + i + ") > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) " +
                    "> div:nth-child(1) > div:nth-child(1) > span:nth-child(1) > span:nth-child(1)")
                    .shouldNotBe(empty);
            $("div.summary:nth-child(" + i + ") > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) " +
                    "> div:nth-child(1) > div:nth-child(2)").shouldNotBe(empty);
            $("div.summary:nth-child(" + i + ") > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) " +
                    "> div:nth-child(2) > div:nth-child(1) > div:nth-child(1) > a:nth-child(1)").shouldNotBe(empty);
            $("div.summary:nth-child(" + i + ") > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) " +
                    "> div:nth-child(2) > div:nth-child(1) > div:nth-child(1) > a:nth-child(1)").shouldNotBe(empty);

            //проверка даты открытия и закрытия
            String openData = $$(".summary-list > .summary").get(i).find(byCssSelector("div:nth-child(" + i + ") " +
                    "> div:nth-child(1) > div:nth-child(2) > div:nth-child(2) > div:nth-child(2) > div:nth-child(1) " +
                    "> div:nth-child(1) > span:nth-child(1) > span")).shouldBe(visible).getText();
            String closeData = $$(".summary-list > .summary").get(i).find(byCssSelector("div:nth-child(" + i + ") " +
                    "> div:nth-child(1) > div:nth-child(2) > div:nth-child(2) > div:nth-child(2) > div:nth-child(1) " +
                    "> div:nth-child(2) > span:nth-child(1) > span:nth-child(2)")).shouldBe(visible).getText();
            DateTimeFormatter inputParser = DateTimeFormatter.ofPattern("dd.MM.yyyy", Locale.ENGLISH);
            LocalDate open = LocalDate.parse(openData, inputParser);
            LocalDate close = LocalDate.parse(closeData, inputParser);
            if (open.isAfter(close)) {
                $("BUG!!!! STEP NOT PASSED Проверка таба ПРОЦЕДУРЫ у товара").click();
            } else {
                $$(".summary-list > .summary").get(i).shouldBe(visible);
            }

            //проверка кол-ва товаров
            String itemValue = $("div.summary:nth-child(1) > div:nth-child(" + i + ") > div:nth-child(1) " +
                    "> div:nth-child(2) > div:nth-child(2) > div:nth-child(2) > div:nth-child(1) > div:nth-child(1) " +
                    "> span:nth-child(2) > span:nth-child(2)").getText();
            Integer intItemValue = Integer.parseInt(itemValue);
            if (intItemValue >= 1) {
                $$(".summary-list > .summary").get(i).shouldBe(visible);
            } else {
                $("BUG!!!! STEP NOT PASSED Проверка таба ПРОЦЕДУРЫ у товара").click();
            }

            //проверка кол-ва участников
            $("div.summary:nth-child(1) > div:nth-child(" + i + ") > div:nth-child(1) > div:nth-child(2) " +
                    "> div:nth-child(2) > div:nth-child(2) > div:nth-child(1) > div:nth-child(2) > span:nth-child(2) " +
                    "> span:nth-child(2)").scrollTo().shouldBe(visible);
            String user = $("div.summary:nth-child(6) > div:nth-child(1) > div:nth-child(1) " +
                    "> div:nth-child(2) > div:nth-child(2) > div:nth-child(2) > div:nth-child(1) > div:nth-child(2) " +
                    "> span:nth-child(2) > span:nth-child(2)").getText();
            String data = user;
            boolean b1 = Pattern.matches("^\\d+$", data);
            boolean b2 = Pattern.matches("[0-9a-zA-Z([+-]?\\d*\\.+\\d*)]*", data);
            if (b1) {
                int j = Integer.parseInt(data);
                if (j > 0) {
                    $$(".summary-list > .summary").get(j).click();
                }
            } else if (b2) {
                $$(".summary-list > .summary").get(i).click();
                System.out.println("Пользователь предпочёл скрыть данные об участниках");
            }
        }
        //после правки включить код ниже в тело цикла с заменой get(0) на get("+ i +") и nth-child(1) на nth-child("+ i +")


    }

    @And("^I select procedure tab$")
    public void iSelectProcedureTab() {
        $("li.iac--tabs_item:nth-child(4) > a:nth-child(1) > span:nth-child(1)").click();
    }


    @And("^I check availability of company information block$")
    public void iCheckAvailabilityOfCompanyInformationBlock() {
        $(".iac--company > div:nth-child(1) > div:nth-child(1) > a:nth-child(1)").shouldBe(visible).shouldNotBe(empty);
        $(".iac--company > div:nth-child(1) > div:nth-child(2) > a:nth-child(1) > span:nth-child(2)").shouldBe(visible).shouldNotBe(empty);
        $("a.activate:nth-child(1)").shouldBe(visible).shouldNotBe(empty);
        $("a.link:nth-child(2)").shouldBe(visible).shouldNotBe(empty);
        $("a.link:nth-child(3)").shouldBe(visible).shouldNotBe(empty);
    }

    @And("^I check availability of the procedure description block$")
    public void iCheckAvailabilityOfTheProcedureDescriptionBlock() {
        $(".name").shouldBe(visible).shouldNotBe(empty);
        $(".content > div:nth-child(1) > div:nth-child(1)").shouldHave(text("Тип:"));
        $(".content > div:nth-child(1) > div:nth-child(2)").shouldHave(text("Открытый тендер на закупку"));
        $(".content > div:nth-child(2) > div:nth-child(1)").shouldHave(text("Статус:"));
        $(".content > div:nth-child(2) > div:nth-child(2)").shouldBe(visible).shouldNotBe(empty);
        $(".content > div:nth-child(3) > div:nth-child(1)").shouldHave(text("Валюта:"));
        $(".content > div:nth-child(3) > div:nth-child(2)").shouldBe(visible).shouldNotBe(empty);
        $(".content > div:nth-child(4) > div:nth-child(1)").shouldHave(text("Начало приема предложений:"));
        $(".content > div:nth-child(4) > div:nth-child(2)").shouldBe(visible).shouldNotBe(empty);
        $(".content > div:nth-child(5) > div:nth-child(1)").shouldHave(text("Окончание приема предложений:"));
        $(".content > div:nth-child(5) > div:nth-child(2)").shouldBe(visible).shouldNotBe(empty);
        $(".content > div:nth-child(8) > div:nth-child(1)").shouldHave(text("Куда:"));
        $(".content > div:nth-child(8) > div:nth-child(2)").shouldBe(visible).shouldNotBe(empty);
        $(".ui-btn").shouldHave(text("Подробнее")).click();
        $(".account-signin").shouldBe(appear);
        $(".account-signin").shouldBe(visible).shouldHave(text("Авторизация пользователя"));

    }

    @And("^I check the presence of a block of goods procedure$")
    public void iCheckThePresenceOfABlockOfGoodsProcedure() {
        Integer size = $$(".iac--product-item").size();
        for (int i = 1; i <= size; i++) {
            $("div.summary:nth-child(" + i + ") > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > a:nth-child(1)").scrollTo().shouldNotBe(empty);
            $("div.summary:nth-child(" + i + ") > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > div:nth-child(2)").scrollTo().shouldNotBe(empty);
            $("div.summary:nth-child(" + i + ") > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > footer:nth-child(3) > div:nth-child(1)").scrollTo().shouldNotBe(empty).click();
        }
    }

    @And("^I should see message \"([^\"]*)\"$")
    public void iShouldSeeMessage(String arg0) {
        $(".alert").shouldHave(text(arg0));
    }


    @And("^I should see company name$")
    public void iShouldSeeCompanyName() {
        $(".iac--company__description-header > h3:nth-child(1)").shouldBe(visible).shouldNotBe(empty);
    }

    @And("^I should see company city$")
    public void iShouldSeeCompanyCity() {
        $(".iac--link > span:nth-child(2)").shouldBe(visible).shouldNotBe(empty);
    }

    @Then("^I should see active tab \"([^\"]*)\"$")
    public void iShouldSeeActiveTab(String arg0) {
        $("li.iac--tabs_item:nth-child(1)").shouldHave(text(arg0)).isSelected();
    }

    @Then("^I check company tab$")
    public void iCheckCompanyTab(DataTable arg) {
        List<Map<String, String>> table = arg.asMaps(String.class, String.class);
        $$("li.iac--tabs_item").get(0).shouldBe(appear);
        Integer size = $$("li.iac--tabs_item").size();
        if (size > 1) {
            for (int i = 1; i < size; ) {
                String str = $$("li.iac--tabs_item").get(i).getText();
                String s = str.replaceAll("\\w*", "");
                String str1 = table.get(1).get("Name");
                String str2 = table.get(2).get("Name");
                String str3 = table.get(3).get("Name");
                if (s.equalsIgnoreCase(str1) || s.equalsIgnoreCase(str2)) {
                    $$("li.iac--tabs_item").get(i).click();
                    sleep(500);
                    companyTabContentChecking();
                    i++;
                } else if (s.equalsIgnoreCase(str3)) {
                    $$("li.iac--tabs_item").get(i).click();
                    companyTabProcedureChecking();
                    sleep(500);
                    i++;
                }
            }
        }
    }

    @Then("^I check description field$")
    public void iCheckDescriptionField(DataTable arg) {
        List<Map<String, String>> table = arg.asMaps(String.class, String.class);
        Integer size = $$("div.company-info > div > label").size();
        for (int i = 0; i < size; ) {
            String str = $$("div.company-info > div > label").get(i).getText();
            $$("div.company-info > div > label").get(i).click();
            String str1 = table.get(i).get("title");
            if (str.equalsIgnoreCase(str1)) {
                String stringVal = table.get(i).get("required");
                if (stringVal.equalsIgnoreCase("true")) {
                    String str3 = $("div.company-info:nth-child(1) > div:nth-child(" + (i + 1) + ") > div:nth-child(2)").getText();
                    $("div.company-info:nth-child(1) > div:nth-child(" + (i + 1) + ") > div:nth-child(2)").click();
                    if (str3.trim().length() > 0) {
                        i++;
                    } else {
                        $("BUG!!!! STEP NOT PASSED I check description field наличие вкладок компании").click();
                    }
                } else {
                    i++;
                }
            } else {
                $("BUG!!!! STEP NOT PASSED I check description field наличие вкладок компании").click();
            }
        }
    }

    @Then("^I check company tab value$")
    public void iCheckCompanyTabValue() {
        $("li.iac--tabs_item:nth-child(1) > a:nth-child(1)").shouldBe(appear).isSelected();
        sleep(1000);
        Integer size = $$("li.iac--tabs_item").size();
        if (size > 1) {
            for (int i = 1; i < size; ) {
                String strTabName = $$("li.iac--tabs_item").get(i).getText();
                String strTabValue = strTabName.replaceAll("\\D+", "");
                int tabVal = Integer.parseInt(strTabValue);
                if (tabVal > 0) {
                    $$("li.iac--tabs_item").get(i).click();
                    $$("li.iac--tabs_item").get(i).shouldBe(appear).isSelected();
                    Integer tabItemSize = $$(".summary-list > .summary").size();
                    if (tabItemSize >= ELEMENT_ON_PAGE) {
                        String strVal = $(".iac--pagination > li:last-child").scrollTo().getText();
                        int intVal = Integer.parseInt(strVal);
                        $(".iac--pagination > li:last-child").scrollTo().shouldBe(appear).click();
                        $(".summary-list > .summary:first-child").shouldBe(appear);
                        Integer lastPegeVal = $$(".summary-list > .summary").size();
                        $(".iac--pagination > li:last-child").scrollTo();
                        int realVal = $$(".iac--pagination > li").size();
                        String preVal = $$(".iac--pagination > li").get(realVal - 1).getText();
                        int intPreVal = Integer.parseInt(preVal);
                        int sum = ((intPreVal - 1) * ELEMENT_ON_PAGE) + lastPegeVal;
                        if (sum == tabVal) {
                            sleep(1000);
                            System.out.println("Всего элементов в табе " + strTabName + "  " + tabVal + "\n" + "Результат вычисления составил " + sum + "\n");
                            i++;
                        }
                    } else if (tabItemSize == tabVal) {
                        sleep(1000);
                        i++;
                    }
                }
            }
        }
    }

    @When("^I switch langauge \"([^\"]*)\" to \"([^\"]*)\"$")
    public void iSwitchLangaugeTo(String arg0, String arg1) {
        $(byText(arg0)).click();
        $(byText(arg1)).shouldBe(appear).click();
    }

    @Then("^I check the element names on the English home page$")
    public void iCheckTheElementNamesOnTheEnglishHomePage(DataTable arg) {
        swichLang(DEFAULT_URL);
        List<Map<String, String>> table = arg.asMaps(String.class, String.class);
        $("a.iac--btn").shouldHave(text(table.get(0).get("Name")));
        $(".suggest--nav > span:nth-child(1)").shouldBe(visible).shouldHave(text(table.get(1).get("Name")));
        $(".suggest--nav > span:nth-child(2)").shouldBe(visible).shouldHave(text(table.get(2).get("Name")));
        $(".suggest--nav > span:nth-child(3)").shouldBe(visible).shouldHave(text(table.get(3).get("Name")));
        $(".iac--index > footer:nth-child(3) > div:nth-child(1) > div:nth-child(1) > a:nth-child(1)").shouldBe(visible).shouldHave(text(table.get(2).get("Name")));
        $(".iac--index > footer:nth-child(3) > div:nth-child(1) > div:nth-child(1) > a:nth-child(2)").shouldBe(visible).shouldHave(text(table.get(1).get("Name")));
        $(".iac--index > footer:nth-child(3) > div:nth-child(1) > div:nth-child(1) > a:nth-child(3)").shouldBe(visible).shouldHave(text(table.get(4).get("Name")));
        $(".iac--index > footer:nth-child(3) > div:nth-child(1) > div:nth-child(2) > a:nth-child(1)").shouldBe(visible).shouldHave(text(table.get(5).get("Name")));
        $(".iac--index > footer:nth-child(3) > div:nth-child(1) > div:nth-child(2) > a:nth-child(2)").shouldBe(visible).shouldHave(text(table.get(6).get("Name")));
        $(".iac--index > footer:nth-child(3) > div:nth-child(1) > div:nth-child(2) > a:nth-child(3)").shouldBe(visible).shouldHave(text(table.get(7).get("Name")));
        $(".iac--index > footer:nth-child(3) > div:nth-child(1) > div:nth-child(3) > a:nth-child(1)").shouldBe(visible).shouldHave(text(table.get(8).get("Name")));
        $(".iac--index > footer:nth-child(3) > div:nth-child(1) > div:nth-child(3) > a:nth-child(2)").shouldBe(visible).shouldHave(text(table.get(9).get("Name")));
        $(".iac--index > footer:nth-child(3) > div:nth-child(1) > div:nth-child(3) > a:nth-child(3)").shouldBe(visible).shouldHave(text(table.get(10).get("Name")));
        $(".iac--index > footer:nth-child(3) > div:nth-child(1) > div:nth-child(4) > a:nth-child(1)").shouldBe(visible).shouldHave(text(table.get(11).get("Name")));
        $(".iac--index > footer:nth-child(3) > div:nth-child(1) > div:nth-child(4) > a:nth-child(2)").shouldBe(visible).shouldHave(text(table.get(12).get("Name")));
        $(".iac--index > footer:nth-child(3) > div:nth-child(1) > div:nth-child(4) > a:nth-child(3)").shouldBe(visible).shouldHave(text(table.get(13).get("Name")));
    }

    @Then("^I check the element names on the English product search page$")
    public void iCheckTheElementNamesOnTheEnglishProductSearchPage(DataTable arg) {
        swichLang(SEARCH_PROD_PAGE);
        List<Map<String, String>> table = arg.asMaps(String.class, String.class);
        $("li.iac--tabs_item:nth-child(1) > span:nth-child(1)").shouldHave(text(table.get(17).get("Name")));
        $(".iac--nav-bar__signin > span:nth-child(2)").shouldHave(text(table.get(0).get("Name")));
        $("div.iac--filter-block:nth-child(1) > div:nth-child(1) > div:nth-child(1)").shouldHave(text(table.get(1).get("Name")));
        $("button.ui-btn:nth-child(1)").shouldHave(text(table.get(2).get("Name")));
        $("button.ui-btn:nth-child(2)").shouldHave(text(table.get(3).get("Name")));
        $("button.ui-btn:nth-child(3)").shouldHave(text(table.get(4).get("Name")));
        $("div.iac--filter-block:nth-child(2) > div:nth-child(1) > div:nth-child(1)").shouldHave(text(table.get(5).get("Name")));
        $("div.iac--filter-block:nth-child(2) > div:nth-child(2) > div:nth-child(1) > div:nth-child(1)").shouldHave(text(table.get(6).get("Name")));
        $("div.iac--filter-block:nth-child(3) > div:nth-child(1) > div:nth-child(1)").shouldHave(text(table.get(7).get("Name")));
        $("div.iac--filter-block:nth-child(3) > div:nth-child(2) > div:nth-child(1) > div:nth-child(1)").shouldHave(text(table.get(6).get("Name")));
        $("div.iac--filter-block:nth-child(4) > div:nth-child(1) > div:nth-child(1)").shouldHave(text(table.get(8).get("Name")));
        $("div.iac--filter-block:nth-child(4) > div:nth-child(2) > div:nth-child(1) > div:nth-child(1) > label:nth-child(2)").shouldHave(text(table.get(9).get("Name")));
        $("div.iac--filter-block:nth-child(4) > div:nth-child(2) > div:nth-child(1) > div:nth-child(2) > label:nth-child(2)").shouldHave(text(table.get(10).get("Name")));
        $("div.iac--filter-block:nth-child(4) > div:nth-child(2) > div:nth-child(1) > div:nth-child(3) > label:nth-child(2)").shouldHave(text(table.get(11).get("Name")));
        $("div.summary:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > footer:nth-child(4) > div:nth-child(1) > div:nth-child(1)").shouldHave(attribute("title", table.get(13).get("Name")));
        $("div.summary:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > footer:nth-child(4) > div:nth-child(1) > a:nth-child(2)").shouldHave(attribute("title", table.get(14).get("Name")));
        $("div.summary:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > footer:nth-child(4) > div:nth-child(1) > div:nth-child(3)").shouldHave(attribute("title", table.get(15).get("Name")));
        $("div.iac--filter-block:nth-child(2) > div:nth-child(2) > div:nth-child(1) > div:nth-child(1)").click();
        $("div.iac--item:nth-child(1) > span:nth-child(2)").click();
        $(".form-actions > span:nth-child(1)").shouldBe(appear);
        $(".form-actions > span:nth-child(1)").shouldHave(text(table.get(12).get("Name")));
        $(".iac--dialog__modal").click();
        $("span.iac--btn:nth-child(1)").scrollTo().shouldHave(text(table.get(16).get("Name")));
    }

    @Then("^I check the element names on the English procedures search page$")
    public void iCheckTheElementNamesOnTheEnglishProceduresSearchPage(DataTable arg) {
        swichLang(SEARCH_PROCEDURES_PAGE);
        List<Map<String, String>> table = arg.asMaps(String.class, String.class);
        $("div.iac--filter-block:nth-child(4) > div:nth-child(1) > div:nth-child(1)").shouldBe(appear).shouldHave(text(table.get(0).get("Name")));
        $("div.iac--filter-block:nth-child(4) > div:nth-child(2) > div:nth-child(1) > label:nth-child(1)").shouldHave(text(table.get(1).get("Name")));
        $("div.summary:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div:nth-child(1) > span:nth-child(1) > span:nth-child(1)").shouldHave(attribute("title", table.get(2).get("Name")));
        $("div.summary:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > div:nth-child(2) > div:nth-child(2) > div:nth-child(1) > div:nth-child(1) > span:nth-child(1)").shouldHave(attribute("title", table.get(3).get("Name")));
        $("div.summary:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > div:nth-child(2) > div:nth-child(2) > div:nth-child(1) > div:nth-child(2) > span:nth-child(1)").shouldHave(attribute("title", table.get(4).get("Name")));
        $("div.summary:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > div:nth-child(2) > div:nth-child(2) > div:nth-child(1) > div:nth-child(1) > span:nth-child(2)").shouldHave(attribute("title", table.get(5).get("Name")));
        $("div.summary:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > div:nth-child(2) > div:nth-child(2) > div:nth-child(1) > div:nth-child(2) > span:nth-child(2)").shouldHave(attribute("title", table.get(6).get("Name")));
        $("span.iac--btn:nth-child(1)").scrollTo().shouldHave(text(table.get(7).get("Name")));
        $("li.iac--tabs_item:nth-child(2) > span:nth-child(1)").shouldHave(text(table.get(8).get("Name")));
    }

    @Then("^I check the element names on the English company search page$")
    public void iCheckTheElementNamesOnTheEnglishCompanySearchPage(DataTable arg) {
        swichLang(SEARCH_COMPANY_PAGE);
        List<Map<String, String>> table = arg.asMaps(String.class, String.class);
        $("li.iac--tabs_item:nth-child(3) > span:nth-child(1)").shouldBe(appear).shouldHave(text(table.get(0).get("Name")));
        $("div.title").shouldHave(text(table.get(1).get("Name")));
        $(".add").shouldHave(text(table.get(2).get("Name")));
        $("div.summary:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > footer:nth-child(2) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1)").shouldHave(attribute("title", table.get(3).get("Name")));
        $("div.summary:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > footer:nth-child(2) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2)").shouldHave(attribute("title", table.get(4).get("Name")));
        $("div.summary:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > footer:nth-child(2) > div:nth-child(1) > div:nth-child(1) > div:nth-child(3)").shouldHave(attribute("title", table.get(5).get("Name")));
        $("span.iac--btn:nth-child(1)").shouldHave(text(table.get(6).get("Name")));
    }

    @Then("^I check the element names on the English product page$")
    public void iCheckTheElementNamesOnTheEnglishProductPage(DataTable arg) {
        swichLang(PROD_PAGE);
        List<Map<String, String>> table = arg.asMaps(String.class, String.class);
        $("span.iac--breadcrumbs_item-label:nth-child(2)").shouldBe(appear).shouldHave(text(table.get(0).get("Name")));
        $("li.iac--breadcrumbs_item:nth-child(2) > a:nth-child(1) > span:nth-child(1)").shouldHave(text(table.get(1).get("Name")));
        $("li.iac--tabs_item:nth-child(1) > a:nth-child(1) > span:nth-child(1)").shouldHave(text(table.get(2).get("Name")));
        $("li.iac--tabs_item:nth-child(2) > a:nth-child(1) > span:nth-child(1)").shouldHave(text(table.get(3).get("Name")));
        $("li.iac--tabs_item:nth-child(3) > a:nth-child(1) > span:nth-child(1)").shouldHave(text(table.get(4).get("Name")));
        $("li.iac--tabs_item:nth-child(4) > a:nth-child(1) > span:nth-child(1)").shouldHave(text(table.get(5).get("Name")));
        $("div.content-block:nth-child(1) > div:nth-child(1) > div:nth-child(1) > label:nth-child(1)").shouldHave(text(table.get(6).get("Name")));
        $("div.content-block:nth-child(1) > div:nth-child(1) > div:nth-child(2) > label:nth-child(1)").shouldHave(text(table.get(7).get("Name")));
        $("div.content-block:nth-child(1) > div:nth-child(1) > div:nth-child(3) > label:nth-child(1)").shouldHave(text(table.get(8).get("Name")));
        $("li.iac--tabs_item:nth-child(2) > a:nth-child(1) > span:nth-child(1)").click();
        $("div.summary:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > a:nth-child(1) > span:nth-child(2)").shouldHave(attribute("title", table.get(9).get("Name")));
        $("div.summary:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > footer:nth-child(2) > div:nth-child(1) > div:nth-child(1)").shouldHave(attribute("title", table.get(10).get("Name")));
        $("li.iac--tabs_item:nth-child(3) > a:nth-child(1) > span:nth-child(1)").click();
        $("div.summary:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > footer:nth-child(2) > div:nth-child(1) > div:nth-child(1)").shouldHave(attribute("title", table.get(11).get("Name")));
        $("div.summary:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > a:nth-child(1) > span:nth-child(2)").shouldHave(attribute("title", table.get(9).get("Name")));
        $("li.iac--tabs_item:nth-child(4) > a:nth-child(1) > span:nth-child(1)").click();
        $("div.summary:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div:nth-child(1) > span:nth-child(1) > span:nth-child(1)").shouldBe(appear).shouldHave(attribute("title", table.get(12).get("Name")));
        $("div.summary:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > div:nth-child(2) > div:nth-child(2) > div:nth-child(1) > div:nth-child(1) > span:nth-child(1)").shouldBe(appear).shouldHave(attribute("title", table.get(13).get("Name")));
        $("div.summary:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > div:nth-child(2) > div:nth-child(2) > div:nth-child(1) > div:nth-child(2) > span:nth-child(1)").shouldHave(attribute("title", table.get(14).get("Name")));
        $("div.summary:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > div:nth-child(2) > div:nth-child(2) > div:nth-child(1) > div:nth-child(1) > span:nth-child(2)").shouldHave(attribute("title", table.get(15).get("Name")));
        $("div.summary:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > div:nth-child(2) > div:nth-child(2) > div:nth-child(1) > div:nth-child(2) > span:nth-child(2)").shouldHave(attribute("title", table.get(16).get("Name")));
    }

    @Then("^I check the element names on the English company card$")
    public void iCheckTheElementNamesOnTheEnglishCompanyCard(DataTable arg) {
        swichLang(COMPANY_PAGE);
        List<Map<String, String>> table = arg.asMaps(String.class, String.class);
        $("span.iac--breadcrumbs_item-label:nth-child(2)").shouldBe(appear).shouldHave(text(table.get(0).get("Name")));
        $("li.iac--breadcrumbs_item:nth-child(2) > a:nth-child(1) > span:nth-child(1)").shouldHave(text(table.get(1).get("Name")));
        $("li.iac--tabs_item:nth-child(1) > a:nth-child(1) > span:nth-child(1)").shouldHave(text(table.get(2).get("Name")));
        $("li.iac--tabs_item:nth-child(2) > a:nth-child(1) > span:nth-child(1)").shouldHave(text(table.get(3).get("Name")));
        $("li.iac--tabs_item:nth-child(3) > a:nth-child(1) > span:nth-child(1)").shouldHave(text(table.get(4).get("Name")));
        $("li.iac--tabs_item:nth-child(4) > a:nth-child(1) > span:nth-child(1)").shouldHave(text(table.get(5).get("Name")));
        $("div.company-info:nth-child(1) > div:nth-child(1) > label:nth-child(1)").shouldHave(text(table.get(6).get("Name")));
        $("div.company-info:nth-child(1) > div:nth-child(2) > label:nth-child(1)").shouldHave(text(table.get(7).get("Name")));
        $("div.company-info:nth-child(1) > div:nth-child(3) > label:nth-child(1)").shouldHave(text(table.get(8).get("Name")));
        $("div.company-info:nth-child(1) > div:nth-child(4) > label:nth-child(1)").shouldHave(text(table.get(9).get("Name")));
        $("div.company-info:nth-child(1) > div:nth-child(5) > label:nth-child(1)").shouldHave(text(table.get(10).get("Name")));
        $("div.company-info:nth-child(1) > div:nth-child(6) > label:nth-child(1)").shouldHave(text(table.get(11).get("Name")));
        $("div.company-info:nth-child(1) > div:nth-child(7) > label:nth-child(1)").shouldHave(text(table.get(12).get("Name")));
        $("div.company-info:nth-child(1) > div:nth-child(8) > label:nth-child(1)").shouldHave(text(table.get(13).get("Name")));
        $("div.company-info:nth-child(1) > div:nth-child(9) > label:nth-child(1)").shouldHave(text(table.get(14).get("Name")));
        $("li.iac--tabs_item:nth-child(2) > a:nth-child(1) > span:nth-child(1)").click();
        $("span.iac--btn").scrollTo();
        $("span.iac--btn").shouldHave(text(table.get(15).get("Name")));
        $("li.iac--tabs_item:nth-child(3) > a:nth-child(1) > span:nth-child(1)").scrollTo();
        $("li.iac--tabs_item:nth-child(4) > a:nth-child(1) > span:nth-child(1)").click();
        $("div.summary:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > span:nth-child(1) > span:nth-child(1)").shouldHave(attribute("title", table.get(16).get("Name")));
        $("div.summary:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > div:nth-child(2) > div:nth-child(1) > div:nth-child(1) > span:nth-child(1)").shouldHave(attribute("title", table.get(17).get("Name")));
        $("div.summary:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > div:nth-child(2) > div:nth-child(1) > div:nth-child(2) > span:nth-child(1)").shouldHave(attribute("title", table.get(18).get("Name")));
        $("div.summary:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > div:nth-child(2) > div:nth-child(1) > div:nth-child(1) > span:nth-child(2)").shouldHave(attribute("title", table.get(19).get("Name")));
        $("div.summary:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > div:nth-child(2) > div:nth-child(1) > div:nth-child(2) > span:nth-child(2)").shouldHave(attribute("title", table.get(20).get("Name")));
    }

    @Then("^I check the element names on the English procedure card$")
    public void iCheckTheElementNamesOnTheEnglishProcedureCard(DataTable arg) {
        swichLang(PROCEDURE_PAGE);
        List<Map<String, String>> table = arg.asMaps(String.class, String.class);
        $("span.iac--breadcrumbs_item-label:nth-child(2)").shouldBe(appear).shouldHave(text(table.get(0).get("Name")));
        $("li.iac--breadcrumbs_item:nth-child(2) > a:nth-child(1) > span:nth-child(1)").shouldHave(text(table.get(1).get("Name")));
        $(".iac--company > div:nth-child(1) > div:nth-child(2) > a:nth-child(1) > span:nth-child(2)").shouldHave(attribute("title", table.get(2).get("Name")));
        $("a.activate:nth-child(1)").shouldHave(attribute("title", table.get(3).get("Name")));
        $("a.link:nth-child(2)").shouldHave(attribute("title", table.get(4).get("Name")));
        $("a.link:nth-child(3)").shouldHave(attribute("title", table.get(5).get("Name")));
        $(".stat > div:nth-child(1)").shouldHave(attribute("title", table.get(6).get("Name")));
        $(".stat > div:nth-child(2)").shouldHave(attribute("title", table.get(7).get("Name")));
        $(".content > div:nth-child(1) > div:nth-child(1)").shouldHave(text(table.get(8).get("Name")));
        $(".content > div:nth-child(2) > div:nth-child(1)").shouldHave(text(table.get(9).get("Name")));
        $(".content > div:nth-child(3) > div:nth-child(1)").shouldHave(text(table.get(10).get("Name")));
        $(".content > div:nth-child(4) > div:nth-child(1)").shouldHave(text(table.get(11).get("Name")));
        $(".content > div:nth-child(5) > div:nth-child(1)").shouldHave(text(table.get(12).get("Name")));
        $(".content > div:nth-child(8) > div:nth-child(1)").shouldHave(text(table.get(13).get("Name")));
    }

    @When("^I select category$")
    public void iSelectCategory() {
        $("div.iac--filter-block:nth-child(2) > div:nth-child(2) > div:nth-child(1) > div:nth-child(1)").click();
    }

    @And("^I find button \"([^\"]*)\" and see (\\d+) result on page$")
    public void iFindButtonAndSeeResultOnPage(String arg0, int arg1) {
        $("span.iac--btn:nth-child(1)").scrollTo().shouldHave(text(arg0)).click();
        sleep(1000);
        int totalSizeOnPage = $$(".summary-list > .summary").size();
        if (totalSizeOnPage == arg1) {
            for (int i = 1; i <= totalSizeOnPage; ) {
                $("div.summary:nth-child(" + i + ")").click();
                i++;
            }

        }
    }

    @And("^I check value \"([^\"]*)\" in quick result$")
    public void iCheckValueInQuickResult(String arg0) {
        int size = $$(".list > .item").size();
        for (int i = 0; i >= size; i++) {
            $$(".list > .item").get(i).find(byCssSelector("div.item:nth-child(" + i + ")")).shouldHave(text(arg0));
            sleep(100);
        }
    }

    @And("^I check region in breadcrumbs and filter$")
    public void iCheckRegionInBreadcrumbsAndFilter() {
        String breadVal = $("li.iac--breadcrumbs_item:nth-child(3) > a:nth-child(1) > span:nth-child(1)").getText();
        String filterVal = $("span.title:nth-child(2)").getText();
        if (breadVal.equalsIgnoreCase(filterVal)) {
            $(".iac--filter-pick > .item > .clear").shouldBe(appear).click();
        }
    }

    @Given("^I open loginpage$")
    public void iOpenLoginpage() throws Throwable {
        Configuration.startMaximized = true;
        AuthByTpro();
        open(LOGIN_PAGE);
    }

    @And("^log in$")
    public void logIn() throws Throwable {
        AuthByTpro();
        $(".info > div:nth-child(1)").shouldHave(text("Кемеров Александр Иванович"));
    }

    @And("^Check section name tab \"([^\"]*)\"$")
    public void checkSectionNameTab(String arg0) throws Throwable {
        $(".title").shouldHave(text(arg0));
    }

    @And("^I see stub \"([^\"]*)\"$")
    public void iSeeStub(String arg0) throws Throwable {
        $(".alert").shouldHave(text(arg0));
    }

    @Given("^I logged in$")
    public void iLoggedIn() throws Throwable {
        Configuration.browser = "chrome";
        System.setProperty("selenide.browser", "chrome");
        //System.setProperty("webdriver.chrome.driver", "drivers/chromedriver");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("headless", "--no-sandbox");
        options.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR,
                UnexpectedAlertBehaviour.IGNORE);
        Configuration.startMaximized = true;
        AuthByTpro();
    }

    @Then("^I select tab \"([^\"]*)\"$")
    public void iSelectTab(String arg0) throws Throwable {
        $(byText(arg0)).click();
    }

    @Then("^Check list result$")
    public void checkListResult() throws Throwable {
        $$(".summary-list > .summary").shouldHave(sizeLessThanOrEqual(ELEMENT_ON_PAGE));

    }

    @When("^Check button \"([^\"]*)\"$")
    public void checkButton(String arg0) throws Throwable {
        $(".ui-btn").shouldBe(appear).shouldHave(text(arg0));
    }

    @And("^I see (\\d+) active tab \"([^\"]*)\"$")
    public void iSeeActiveTab(int arg0, String arg1) throws Throwable {
        $("li.iac--tabs_item:nth-child(" + arg0 + ") > a:nth-child(1)").shouldHave(text(arg1)).isSelected();
    }

    @When("^I click button \"([^\"]*)\"$")
    public void iClickButton(String arg0) throws Throwable {
        $(".ui-btn").shouldBe(appear).shouldHave(text(arg0)).click();
    }

    @And("^I see popup \"([^\"]*)\"$")
    public void iSeePopup(String arg0) throws Throwable {
        $(".iac--modal__header").shouldBe(appear).shouldHave(text(arg0));


    }

    @And("^I see message \"([^\"]*)\"$")
    public void iSeeMessage(String arg0) throws Throwable {
        $(".alert").shouldBe(visible).shouldHave(text(arg0));
    }

    @And("^I see field type procedure \"([^\"]*)\"$")
    public void iSeeFieldTypeProcedure(String arg0) throws Throwable {
        $(".control-label").shouldBe(visible).shouldHave(text(arg0));
    }

    @And("^I check name item$")
    public void iCheckNameItem(DataTable arg) throws Throwable {
        $("select.form-control").click();
        List<Map<String, String>> table = arg.asMaps(String.class, String.class);
        $("select.form-control > option:nth-child(1)").shouldHave(text(table.get(0).get("Name")));
        $("select.form-control > option:nth-child(2)").shouldBe(visible).shouldHave(text(table.get(1).get("Name")));
        $("select.form-control > option:nth-child(3)").shouldBe(visible).shouldHave(text(table.get(2).get("Name")));
        $("select.form-control > option:nth-child(4)").shouldBe(visible).shouldHave(text(table.get(3).get("Name")));
        $("select.form-control > option:nth-child(5)").shouldBe(visible).shouldHave(text(table.get(4).get("Name")));

    }

    @And("^I see field trash \"([^\"]*)\"$")
    public void iSeeFieldTrash(String arg0) throws Throwable {
        $("label.title").shouldHave(text(arg0));
    }

    @And("^I check popup trash$")
    public void iCheckPopupTrash() throws Throwable {
        $(".placeholder").shouldHave(text("Добавить")).click();
    }

    @When("^Title should have name \"([^\"]*)\"$")
    public void titleShouldHaveName(String arg0) throws Throwable {
        $(".object-select > div:nth-child(1)").shouldHave(text(arg0));
    }

    @And("^I see (\\d+) tab name \"([^\"]*)\"$")
    public void iSeeTabName(int arg0, String arg1) throws Throwable {
        $(".sm > li:nth-child(" + arg0 + ") > span:nth-child(1) > span:nth-child(1)")
                .shouldHave(text(arg1)).isSelected();

    }

    @And("^I see search field with text \"([^\"]*)\"$")
    public void iSeeSearchFieldWithText(String arg0) throws Throwable {
        $(byAttribute("placeholder", arg0));
    }

    @When("^I cancel change$")
    public void iCancelChange() throws Throwable {
        $(".iac--dialog_container1").click();
    }

    @Then("^I click brown button \"([^\"]*)\"$")
    public void iClickBrownButton(String arg0) throws Throwable {
        $(".ui-btn-secondary").shouldHave(text(arg0)).click();
    }

    @And("^I input request \"([^\"]*)\"$")
    public void iInputRequest(String arg0) throws Throwable {
        $(byAttribute("placeholder", "Найти компанию, процедуру или товар")).setValue(arg0);
    }


    @And("^I check group name$")
    public void iCheckGroupName(DataTable arg) throws Throwable {
        sleep(1000);
        $(byAttribute("placeholder", "Найти компанию, процедуру или товар")).click();
        List<Map<String, String>> table = arg.asMaps(String.class, String.class);
        $("div.group:nth-child(1)").shouldHave(text(table.get(0).get("Name")));
        $("div.group:nth-child(7)").shouldBe(visible).shouldHave(text(table.get(1).get("Name")));
        $("div.group:nth-child(13)").shouldBe(visible).shouldHave(text(table.get(2).get("Name")));
    }

    @And("^I check that each group elements contain text \"([^\"]*)\"$")
    public void iCheckThatEachGroupElementsContainText(String arg0) throws Throwable {
        Integer size = $$("div.list:nth-child(2) > div").size();
        if (size == 18) {
            for (int i = 2; i < 6; ) {
                String strItemName = $$("div.list:nth-child(2) > div").get(i).getText().toLowerCase();
                boolean isContain = strItemName.contains("лес");
                if (isContain == true) {
                    i++;
                }
            }
            for (int i = 8; i < 12; ) {
                String strItemName = $$("div.list:nth-child(2) > div").get(i).getText().toLowerCase();
                boolean isContain = strItemName.contains("лес");
                if (isContain == true) {
                    i++;
                }
            }
            for (int i = 14; i < 18; ) {
                String strItemName = $$("div.list:nth-child(2) > div").get(i).getText().toLowerCase();
                boolean isContain = strItemName.contains("лес");
                if (isContain == true) {
                    i++;
                }
            }
        }
    }

    @When("^I click button in window create \"([^\"]*)\"$")
    public void iClickButtonInWindowCreate(String arg0) throws Throwable {
        $("button.ui-btn:nth-child(2)").click();
    }

    @And("^I should see company name in page$")
    public void iShouldSeeCompanyNameInPage() {
        $(".iac--company > div:nth-child(1) > div:nth-child(1) > a:nth-child(1)").shouldBe(visible).shouldNotBe(empty);
    }

    @And("^I should see company city in page$")
    public void iShouldSeeCompanyCityInPage() {
        $(".iac--company > div:nth-child(1) > div:nth-child(2) > a:nth-child(1) > span:nth-child(2)").shouldBe(visible).shouldNotBe(empty);
    }

    @And("^I check operations stats block$")
    public void iCheckOperationsStatsBlock() throws Throwable {
        $("a.link:nth-child(1)").shouldBe(visible).shouldNotBe(empty);
        $("a.link:nth-child(2)").shouldBe(visible).shouldNotBe(empty);
        $("a.link:nth-child(3)").shouldBe(visible).shouldNotBe(empty);
    }

    @And("^I check basic procedure settings$")
    public void iCheckBasicProcedureSettings(DataTable arg) throws Throwable {
        List<Map<String, String>> table = arg.asMaps(String.class, String.class);
        $("div.sort-item:nth-child(1) > div:nth-child(2) > div:nth-child(1)").shouldHave(text(table.get(0).get("Name")));
        $("div.sort-item:nth-child(2) > div:nth-child(2) > div:nth-child(1)").shouldHave(text(table.get(1).get("Name")));
        $("div.sort-item:nth-child(3) > div:nth-child(2) > div:nth-child(1)").shouldHave(text(table.get(2).get("Name")));
        $("div.sort-item:nth-child(4) > div:nth-child(2) > div:nth-child(1)").shouldHave(text(table.get(3).get("Name")));
        $("div.sort-item:nth-child(5) > div:nth-child(2) > div:nth-child(1)").shouldHave(text(table.get(4).get("Name")));
        $("div.sort-item:nth-child(6) > div:nth-child(2) > div:nth-child(1)").shouldHave(text(table.get(5).get("Name")));
    }

    @And("^I click pencil on basic procedure settings$")
    public void iClickPencilOnBasicProcedureSettings() throws Throwable {
        $(".field-group > fieldset:nth-child(1) > legend:nth-child(1) > icon:nth-child(1)").click();
    }

    @And("^I see button \"([^\"]*)\"$")
    public void iSeeButton(String arg0) throws Throwable {
        $(".ui-btn-secondary").shouldBe(visible).shouldHave(text(arg0));
    }

    @And("^I check name element on tab for the participant$")
    public void iCheckNameElementOnTabForTheParticipant(DataTable arg) throws Throwable {
        List<Map<String, String>> table = arg.asMaps(String.class, String.class);
        $(".template > fieldset:nth-child(1) > legend:nth-child(1) > label:nth-child(1)").shouldHave(text(table.get(0).get("Name")));
        $(".template > fieldset:nth-child(1) > div:nth-child(2) > div:nth-child(1) > label:nth-child(1)").shouldHave(text(table.get(1).get("Name")));
        $(".template > fieldset:nth-child(1) > div:nth-child(2) > div:nth-child(2) > label:nth-child(1)").shouldHave(text(table.get(2).get("Name")));
        $(".template > fieldset:nth-child(2) > legend:nth-child(1) > label:nth-child(1)").shouldHave(text(table.get(3).get("Name")));
        $(".template > fieldset:nth-child(2) > div:nth-child(2) > div:nth-child(1) > label:nth-child(1)").shouldHave(text(table.get(4).get("Name")));
        $(".template > fieldset:nth-child(2) > div:nth-child(2) > div:nth-child(2) > label:nth-child(1)").shouldHave(text(table.get(5).get("Name")));
        $(".template > fieldset:nth-child(2) > div:nth-child(2) > div:nth-child(3) > label:nth-child(1)").shouldHave(text(table.get(6).get("Name")));
        $(".template > fieldset:nth-child(2) > div:nth-child(2) > div:nth-child(4) > label:nth-child(1)").shouldHave(text(table.get(7).get("Name")));
    }

    @And("^I check name element on tab customizable$")
    public void iCheckNameElementOnTabCustomizable(DataTable arg) throws Throwable {
        List<Map<String, String>> table = arg.asMaps(String.class, String.class);
        $("div.undefined:nth-child(2) > div:nth-child(2) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1)").shouldHave(text(table.get(0).get("Name")));
        $("div.undefined:nth-child(2) > div:nth-child(2) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1)").shouldHave(text(table.get(1).get("Name")));
        $("div.undefined:nth-child(2) > div:nth-child(2) > div:nth-child(1) > div:nth-child(1) > div:nth-child(3) > div:nth-child(1)").shouldHave(text(table.get(2).get("Name")));
        $("div.undefined:nth-child(2) > div:nth-child(2) > div:nth-child(1) > div:nth-child(1) > div:nth-child(4) > div:nth-child(1) > label:nth-child(1) > span:nth-child(2)").shouldHave(text(table.get(3).get("Name")));
        $("div.undefined:nth-child(2) > div:nth-child(2) > div:nth-child(1) > div:nth-child(1) > div:nth-child(5) > div:nth-child(1) > label:nth-child(1) > span:nth-child(2)").shouldHave(text(table.get(4).get("Name")));
    }

    @And("^I see button primary \"([^\"]*)\"$")
    public void iSeeButtonPrimary(String arg0) throws Throwable {
        $("button.ui-btn:nth-child(2)").shouldBe(visible).shouldHave(text(arg0));
    }

    @Then("^I see (\\d+) active tab in popup \"([^\"]*)\"$")
    public void iSeeActiveTabInPopup(int arg0, String arg1) throws Throwable {
        $("div.undefined:nth-child(2) > ul:nth-child(" + arg0 + ") > li:nth-child(1)").shouldHave(text(arg1));
    }

    @Then("^I type in field \"([^\"]*)\" text \"([^\"]*)\"$")
    public void iTypeInFieldText(String arg0, String arg1) throws Throwable {
        $(byAttribute("placeholder", arg0)).setValue(arg1);
    }

    @And("^I see status \"([^\"]*)\" and button \"([^\"]*)\"$")
    public void iSeeStatusAndButton(String arg0, String arg1) throws Throwable {
        String title = $(".workspace > div:nth-child(1) > div:nth-child(1) > h4:nth-child(2) > span:nth-child(1)").getText().toLowerCase();
        boolean isContain = title.contains(arg0);
        if (isContain) {
            $("button.ui-btn").shouldHave(text(arg1));
        }
    }

    @Then("^I create default procedure$")
    public void iCreateDefaultProcedure() {
        $(byText("Организуем")).click();
        $(".ui-btn").shouldBe(appear).shouldHave(text("Создать конкурс")).click();
        $(".iac--modal__header").shouldBe(appear).shouldHave(text("Новая процедура"));
        $("button.ui-btn:nth-child(2)").click();
    }

    @And("^I set in field \"([^\"]*)\" value \"([^\"]*)\"$")
    public void iSetInFieldValue(String arg0, String arg1) throws Throwable {
        $(byAttribute("placeholder", arg0)).setValue(arg1);
    }

    @And("^I select direction of the procedure \"([^\"]*)\"$")
    public void iSelectDirectionOfTheProcedure(String arg0) throws Throwable {
        $("div.sort-item:nth-child(2) > div:nth-child(2) > div:nth-child(2) > div:nth-child(1) > select:nth-child(1)").click();
        $("div.sort-item:nth-child(2) > div:nth-child(2) > div:nth-child(2) > div:nth-child(1) > select:nth-child(1) > option:nth-child(2)").click();
        $("div.sort-item:nth-child(2) > div:nth-child(2) > div:nth-child(2) > div:nth-child(1) > select:nth-child(1)").click();
        $("div.sort-item:nth-child(2) > div:nth-child(2) > div:nth-child(2) > div:nth-child(1) > select:nth-child(1) > option:nth-child(1)").click();
    }

    @When("^I should see block \"([^\"]*)\"$")
    public void iShouldSeeBlock(String arg0) throws Throwable {
        $(".field-group > fieldset:nth-child(1) > legend:nth-child(1) > span:nth-child(2)").shouldHave(text(arg0));
    }

    @And("^I select item \"([^\"]*)\"$")
    public void iSelectItem(String arg0) throws Throwable {
        $(".title").shouldHave(text(arg0)).click();
    }

    @And("^I select (\\d+) item on list$")
    public void iSelectItemOnList(int arg0) {
        sleep(4000);
        $(".iac--modal__header").shouldBe(appear).shouldHave(text("Товары"));
        $(".product_select > div:nth-child(1) > div:nth-child(" + arg0 + ") > div:nth-child(1)").click();
    }

    @And("^I set value \"([^\"]*)\" in field \"([^\"]*)\"$")
    public void iSetValueInField(String arg0, String arg1) throws Throwable {
        $(byAttribute("placeholder", arg1)).setValue(arg0);
    }

    @And("^I specify the unit$")
    public void iSpecifyTheUnit() {
        $("div.field:nth-child(2) > select:nth-child(1)").click();
        $("div.field:nth-child(2) > select:nth-child(1) > option:nth-child(1)").click();
    }

    @And("^I check fields with empty fields$")
    public void iCheckFieldsWithEmptyFields() {
        $(".workspace > div:nth-child(1) > div:nth-child(5) > div:nth-child(2) > label:nth-child(4)").click();
        $(".alert").shouldHave(text("Позиции отсутствуют"));
        $(".workspace > div:nth-child(1) > div:nth-child(5) > div:nth-child(2) > label:nth-child(4)").click();
    }

    @And("^I add lot$")
    public void iAddLot() {
        $(".form-actions > div:nth-child(1) > button:nth-child(1)").scrollTo().click(); //Добавить позицию
        $("textarea.form-control").setValue("Москва");// Добавить адрес
        $(".placeholder").click();//Выберите товар
        $(".product_select > div:nth-child(1) > div:nth-child(1) > div:nth-child(1)").click();//1 элемент списка
        $(".fields > div:nth-child(1) > input:nth-child(1)").setValue("2");//Объем организатора
        $("div.field:nth-child(2) > select:nth-child(1)").click();//тык по полю Единица измерения
        $("div.field:nth-child(2) > select:nth-child(1) > option:nth-child(1)").click();//шт
        $("div.field:nth-child(3) > input:nth-child(1)").setValue("1000000");//Цена организатора
    }

    @Then("^I create closed tender for sale$")
    public void iCreateClosedTenderForSale() {
        $(byText("Организуем")).click();
        $(".ui-btn").shouldBe(appear).shouldHave(text("Создать конкурс")).click();
        $(".iac--modal__header").shouldBe(appear).shouldHave(text("Новая процедура"));
        $("select.form-control").click();//клик по выпадашке
        $("select.form-control > option:nth-child(2)").click();//выбор Закрытый конкурс на продажу
        $("button.ui-btn:nth-child(2)").shouldHave(text("Создать")).click();//клик по кнопке создать
        sleep(2000);
    }

    @And("^I press add$")
    public void iPressAdd() {
        $(".lot-nav > ul:nth-child(1) > li:nth-child(2) > span:nth-child(1) > span:nth-child(1)").click();//тык по плюсику
    }

    @And("I click on {string}")
    public void iClickOn(String arg0) {
        $(byText(arg0)).shouldBe(appear).click();//поиск по тексту Торги. Д.б. виден. Клик
    }

    @And("I select close tender for sale")
    public void iSelectCloseTenderForSale() {
        $("select.form-control").click();//клик по дропдауну типа процедуры
        $("select.form-control > option:nth-child(2)")
                .shouldBe(visible)
                .shouldHave(text("Закрытый конкурс на продажу"))
                .click(); //выбор в выпадающем списке нужного конкурса, проверка его названия, клик для выбора
        $("button.ui-btn:nth-child(2)").shouldHave(text("Создать")).click();//клик по кнопке Создать
        sleep(2000);
    }

    @Given("I check id in url and compare tender name")
    public void iCheckIdInUrlAndCompareTenderName() throws InterruptedException {
        String procName = $(".workspace > div:nth-child(1) > div:nth-child(1) > h4:nth-child(1)")
                .shouldBe(appear)
                .getText();//взяли наименование процедуры
        String idProc = procName.replaceAll("[^0-9]", "");// извлекли цифры
        String urlName = WebDriverRunner.url();// взяли адрес URL
        String idSrt = urlName.replaceAll("[^0-9]", ""); // извлекли из него цифры айдишника

        int idInt = Integer.parseInt(idSrt);//преобразовали строку в число
        int procInt = Integer.parseInt(idProc);//преобразовали строку в число
        Assert.assertTrue(idInt == procInt); //сравнили 2 числа
        sleep(2000);
    }

    @Given("I check default status {string}")
    public void iCheckDefaultStatus(String arg0) {
        $(".workspace > div:nth-child(1) > div:nth-child(1) > h4:nth-child(2) > span:nth-child(1)")
                .shouldBe(visible)
                .shouldHave(text(arg0));//взяли значение статуса и сравнили со значением по умолчанию
    }

    @Given("I check the number of tabs {int}")
    public void iCheckTheNumberOfTabs(int arg0) {
        int tabNumber = $$(".iac--tabs_item-label").size();
        Assert.assertTrue(tabNumber == arg0);
    }

    @And("I check name Tab on page create procedure")
    public void iCheckNameTabOnPageCreateProcedure(DataTable arg) {
        List<Map<String, String>> table = arg.asMaps(String.class, String.class);
        $("li.iac--tabs_item:nth-child(1) > a:nth-child(1) > span:nth-child(1)").shouldBe(visible).shouldHave(text(table.get(0).get("Name")));
        $("li.iac--tabs_item:nth-child(2) > a:nth-child(1) > span:nth-child(1)").shouldBe(visible).shouldHave(text(table.get(1).get("Name")));
        $("li.iac--tabs_item:nth-child(3) > a:nth-child(1) > span:nth-child(1)").shouldBe(visible).shouldHave(text(table.get(2).get("Name")));
        $("li.iac--tabs_item:nth-child(4) > a:nth-child(1) > span:nth-child(1)").shouldBe(visible).shouldHave(text(table.get(3).get("Name")));
        $("li.iac--tabs_item:nth-child(5) > a:nth-child(1) > span:nth-child(1)").shouldBe(visible).shouldHave(text(table.get(4).get("Name")));
        $("li.iac--tabs_item:nth-child(6) > a:nth-child(1) > span:nth-child(1)").shouldBe(visible).shouldHave(text(table.get(5).get("Name")));
    }

    @And("I check tab {string} is active")
    public void iCheckTabIsActive(String arg0) {
        $("li.iac--tabs_item:nth-child(1)").shouldHave(text(arg0)).isSelected();
    }

    @Given("I see block about company")
    public void iSeeBlockAboutCompany() {
        $(".iac--company-item").shouldNotBe(empty).shouldBe(appear);

    }

    @And("I see company name {string}")
    public void iSeeCompanyName(String arg0) {
        $(".iac--company > div:nth-child(1) > div:nth-child(1) > a:nth-child(1)")
                .shouldBe(visible)
                .shouldHave(text(arg0));
    }

    @And("I see company city {string}")
    public void iSeeCompanyCity(String arg0) {
        $(".iac--company > div:nth-child(1) > div:nth-child(2) > a:nth-child(1) > span:nth-child(2)")
                .shouldBe(visible)
                .shouldHave(text(arg0));
    }

    @And("I see block active procedure and {int} item")
    public void iSeeBlockActiveProcedureAndItem(int arg0) {
        $$(".iac--link activate").shouldHave(sizeLessThanOrEqual(arg0));
    }

    @And("I see active company item buyer {string}")
    public void iSeeActiveCompanyItemBuyer(String arg0) {
        $("a.link:nth-child(1)").shouldHave(attribute("title", arg0));
    }

    @And("I see active company item supplier {string}")
    public void iSeeActiveCompanyItemSupplier(String arg0) {
        $("a.link:nth-child(2)").shouldHave(attribute("title", arg0));
    }

    @And("I see active company item auction open {string}")
    public void iSeeActiveCompanyItemAuctionOpen(String arg0) {
        $("a.link:nth-child(3)").shouldHave(attribute("title", arg0));
    }

    @And("I press button {string}")
    public void iPressButton(String arg0) {
        $(".ui-btn-primary").shouldHave(text(arg0)).click();
    }

    @And("I see error popup {string}")
    public void iSeeErrorPopup(String arg0) {
        $(".error").shouldHave(visible).shouldHave(text(arg0));
    }

    @And("I add addres")
    public void iAddAddres() {
        $("textarea.form-control").setValue("Москва");
    }

    @And("I set default settings")
    public void iSetDefaultSettings() {
        $(".workspace > div:nth-child(1) > div:nth-child(5) > div:nth-child(1) > button:nth-child(1)").shouldHave(text(" Настройки по умолчанию")).click();
    }

    @And("I check name element on pop up default settings lot")
    public void iCheckNameElementOnPopUpDefaultSettingsLot(DataTable arg) {
        List<Map<String, String>> table = arg.asMaps(String.class, String.class);
        $(".field-group > fieldset:nth-child(1) > legend:nth-child(1) > span:nth-child(2)").shouldBe(visible).shouldHave(text(table.get(0).get("Name")));
        $("div.sort-item:nth-child(1) > div:nth-child(2) > div:nth-child(1)").shouldBe(visible).shouldHave(text(table.get(1).get("Name")));
        $("div.sort-item:nth-child(2) > div:nth-child(2) > div:nth-child(1)").shouldBe(visible).shouldHave(text(table.get(2).get("Name")));
        $("div.sort-item:nth-child(3) > div:nth-child(2) > div:nth-child(1)").shouldBe(visible).shouldHave(text(table.get(3).get("Name")));
        $("div.sort-item:nth-child(4) > div:nth-child(2) > div:nth-child(1)").shouldBe(visible).shouldHave(text(table.get(4).get("Name")));
        $(".ui-btn-secondary").shouldBe(visible).shouldHave(text(table.get(5).get("Name")));
    }

    @And("I filled in the address field")
    public void iFilledInTheAddressField() {
        $("div.sort-item:nth-child(2) > div:nth-child(2) > div:nth-child(2) > textarea:nth-child(1)").setValue("Москва");
        $(".ui-btn-secondary").shouldHave(text("Закрыть")).click();
        sleep(1000);
    }

    @And("I select filter where find")
    public void iSelectFilterWhereFind() {
        $("div.iac--filter-block:nth-child(4) > div:nth-child(2) > div:nth-child(1) > div:nth-child(2) > label:nth-child(2)").click();
        sleep(1000);
    }

    @And("I check workspace")
    public void iCheckWorkspace() {
        open("https://new.tender.pro/workspace");

    }


    @And("I check login user {string}")
    public void iCheckLoginUser(String arg0) {
        $(".info > div:nth-child(2)").shouldBe(visible).shouldHave(text(arg0));
    }

    @Then("I check sidebar link")
    public void iCheckSidebarLink(DataTable arg) {
        List<Map<String, String>> table = arg.asMaps(String.class, String.class);
        $(byText(table.get(0).get("Name"))).shouldBe(visible).shouldHave(text(table.get(0).get("Name")));
        $(byText(table.get(1).get("Name"))).shouldBe(visible).shouldHave(text(table.get(1).get("Name")));
        $(byText(table.get(2).get("Name"))).shouldBe(visible).shouldHave(text(table.get(2).get("Name")));
        $(byText(table.get(3).get("Name"))).shouldBe(visible).shouldHave(text(table.get(3).get("Name")));
        $(byText(table.get(4).get("Name"))).shouldBe(visible).shouldHave(text(table.get(4).get("Name")));
        $(byText(table.get(5).get("Name"))).shouldBe(visible).shouldHave(text(table.get(5).get("Name")));
        $(byText(table.get(6).get("Name"))).shouldBe(visible).shouldHave(text(table.get(6).get("Name")));
        $(byText(table.get(7).get("Name"))).shouldBe(visible).shouldHave(text(table.get(7).get("Name")));
        $(byText(table.get(8).get("Name"))).shouldBe(visible).shouldHave(text(table.get(8).get("Name")));
        $(byText(table.get(9).get("Name"))).shouldBe(visible).shouldHave(text(table.get(9).get("Name")));
        $(byText(table.get(10).get("Name"))).shouldBe(visible).shouldHave(text(table.get(10).get("Name")));
        $(byText(table.get(11).get("Name"))).shouldBe(visible).shouldHave(text(table.get(11).get("Name")));
    }

    @And("I check sidebar link {string}")
    public void iCheckSidebarLink(String arg0) {
        $(By.linkText(arg0)).shouldHave(text(arg0)).click();
        $(".title").shouldHave(text(arg0));
    }

    @And("I check sidebar link employees {string}")
    public void iCheckSidebarLinkEmployees(String arg0){
        $(By.linkText(arg0)).shouldHave(text(arg0)).click();
        $(".template-edit > div:nth-child(1) > h3:nth-child(1)").shouldHave(text(arg0));
    }

    @And("I check sidebar link coordination {string}")
    public void iCheckSidebarLinkCoordination(String arg0){
        $(By.linkText(arg0)).shouldHave(text(arg0)).click();
        $(".procedure > h3:nth-child(1)").shouldHave(text(arg0));
    }

    @And("I check sidebar link patterns {string}")
    public void iCheckSidebarLinkPatterns(String arg0){
        $(By.linkText(arg0)).shouldHave(text(arg0)).click();
        $("[href=\"/workspace/agreement/template/new\"]").shouldHave(text("Создать"));
    }


    @And("check tab trash {string}, {string}, {string}")
    public void checkTabTrash(String arg0, String arg1, String arg2) {
        $(".ui-btn").shouldBe(visible).shouldHave(text("Сформировать процедуру"));
        $("li.iac--tabs_item:nth-child(1) > a:nth-child(1) > span:nth-child(1)").shouldHave(text(arg0)).isSelected();
        $(".summary-list > div:nth-child(1)").shouldHave(text("По вашему запросу ничего не найдено"));
        $("li.iac--tabs_item:nth-child(2) > a:nth-child(1)").click();
        $("li.iac--tabs_item:nth-child(2) > a:nth-child(1)").shouldHave(text(arg1)).isSelected();
        $(".summary-list > div:nth-child(1)").shouldHave(text("По вашему запросу ничего не найдено"));
        $("li.iac--tabs_item:nth-child(3) > a:nth-child(1) > span:nth-child(1)").click();
        $("li.iac--tabs_item:nth-child(3) > a:nth-child(1) > span:nth-child(1)").shouldHave(text(arg2)).isSelected();
        $(".summary-list > div:nth-child(1)").shouldHave(text("По вашему запросу ничего не найдено"));
    }

    @And("check tab tender {string}, {string}, {string}")
    public void checkTabTender(String arg0, String arg1, String arg2) {
        $(".ui-btn").shouldBe(visible).shouldHave(text("Создать конкурс"));
        $("li.iac--tabs_item:nth-child(1) > a:nth-child(1)").shouldHave(text(arg0)).isSelected();
        $("li.iac--tabs_item:nth-child(2) > a:nth-child(1) > span:nth-child(1)").click();
        $("li.iac--tabs_item:nth-child(2) > a:nth-child(1) > span:nth-child(1)").shouldHave(text(arg1)).isSelected();
        $("li.iac--tabs_item:nth-child(3) > a:nth-child(1)").click();
        $("li.iac--tabs_item:nth-child(3) > a:nth-child(1)").shouldHave(text(arg2)).isSelected();
    }

    @And("check tab contract {string}, {string}")
    public void checkTabContract(String arg0, String arg1) {
        $(".ui-btn").shouldBe(visible).shouldHave(text("Создать договор"));
        $("li.iac--tabs_item:nth-child(1) > a:nth-child(1)").shouldHave(text(arg0)).isSelected();
        $("li.iac--tabs_item:nth-child(2) > a:nth-child(1)").click();
        $("li.iac--tabs_item:nth-child(2) > a:nth-child(1) > span:nth-child(1)").shouldHave(text(arg1)).isSelected();


    }

    @And("check tab order {string}, {string}")
    public void checkTabOrder(String arg0, String arg1) {
        $(".ui-btn").shouldBe(visible).shouldHave(text("Создать заказ"));
        $("li.iac--tabs_item:nth-child(1) > a:nth-child(1)").shouldHave(text(arg0)).isSelected();
        $("li.iac--tabs_item:nth-child(2) > a:nth-child(1)").click();
        $("li.iac--tabs_item:nth-child(2) > a:nth-child(1) > span:nth-child(1)").shouldHave(text(arg1)).isSelected();
    }


    @And("check tab order product {string}")
    public void checkTabOrderProduct(String arg0){
        $(byClassName("title")).shouldHave(text("Товары"));
        $(byClassName("iac--tabs_item-label")).click();
    }

    @And("check tab coordination {string}, {string}, {string}, {string}")
    public void checkTabCoordination(String arg0, String arg1, String arg2, String arg3){
        $(".procedure > h3:nth-child(1)").shouldHave(text("Список согласования"));
        $(byLinkText(arg0)).click();
        $(byLinkText(arg0)).isSelected();
        $(byLinkText(arg1)).click();
        $(byLinkText(arg1)).isSelected();
        $(byLinkText(arg2)).click();
        $(byLinkText(arg2)).isSelected();
        $(byLinkText(arg3)).click();
        $(byLinkText(arg3)).isSelected();
    }

    @And("I check a random item from the list own {string}")
    public void iCheckARandomItemFromTheListOwn(String arg0) {
        $("li.iac--tabs_item:nth-child(1) > a:nth-child(1) > span:nth-child(1)").shouldHave(text(arg0));
        Integer size = $$(".summary-list").size();
        if (size >= 1) {
            String s1 = $("div.summary:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > span:nth-child(1) > span:nth-child(2)").getText();

            $("div.summary:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div:nth-child(1) > a:nth-child(1)").click();
            $(".workspace > div:nth-child(1) > div:nth-child(1) > h4:nth-child(1)").shouldNotBe(empty);
            String s2 = $(".workspace > div:nth-child(1) > div:nth-child(1) > h4:nth-child(1)").getText();
            String s3 = s2.replaceAll("[^-?0-9]+", " ");
            if (s1 == s3) {
                $(".workspace > div:nth-child(1) > div:nth-child(3)").sendKeys(Keys.BACK_SPACE);
            }
        } else {
            $(".summary-list > div:nth-child(1)").shouldHave(text("По вашему запросу ничего не найдено"));
        }
    }

    @And("I check a random item from the list party {string}")
    public void iCheckARandomItemFromTheListParty(String arg0) {
        $("li.iac--tabs_item:nth-child(2) > a:nth-child(1) > span:nth-child(1)").shouldHave(text(arg0));
        Integer size = $$(".summary-list").size();
        if (size >= 1) {
            String s1 = $("div.summary:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > span:nth-child(1) > span:nth-child(2)").getText();
            $("div.summary:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div:nth-child(1) > a:nth-child(1)").click();
            $(".workspace > div:nth-child(1) > div:nth-child(1) > h4:nth-child(1)").shouldNotBe(empty);
            String s2 = $(".id > span:nth-child(1)").getText();
            String s3 = s2.replaceAll("[^-?0-9]+", " ");
            if (s1 == s3) {
                $(".header").sendKeys(Keys.BACK_SPACE);
            }
        } else {
            $(".summary-list > div:nth-child(1)").shouldHave(text("По вашему запросу ничего не найдено"));
        }
    }

    @And("I check a random item from the list invitation {string}")
    public void iCheckARandomItemFromTheListInvitation(String arg0) {
        $("li.iac--tabs_item:nth-child(3) > a:nth-child(1) > span:nth-child(1)").shouldHave(text(arg0));
        Integer size = $$(".summary").size();
        if (size >= 1) {
            String s1 = $("div.summary:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div:nth-child(1) > span:nth-child(1) > span:nth-child(2)").getText();
            $("div.summary:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > div:nth-child(2) > div:nth-child(1) > div:nth-child(1) > a:nth-child(1)").click();
            $(".workspace > div:nth-child(1) > div:nth-child(1) > h4:nth-child(1)").shouldNotBe(empty);
            String s2 = $(".id > span:nth-child(1)").getText();
            String s3 = s2.replaceAll("[^-?0-9]+", " ");
            if (s1 == s3) {
                $(".header").sendKeys(Keys.BACK_SPACE);
            }
        } else {
            $(".summary-list > div:nth-child(1)").shouldHave(text("По вашему запросу ничего не найдено"));
        }
    }

    @And("I check a random item from the list contract own {string}")
    public void iCheckARandomItemFromTheListContractOwn(String arg0) {
        $("li.iac--tabs_item:nth-child(1) > a:nth-child(1) > span:nth-child(1)").shouldHave(text(arg0));
        Integer size = $$(".result > div").size();
        if (size >= 1) {
            String s1 = $(".result > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1)").getText();
            String s2 = s1.replaceAll("[^-?0-9]+", " ");
            $(".result > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div:nth-child(1) > a:nth-child(1)").click();
            $(".workspace > div:nth-child(1) > div:nth-child(1) > h4:nth-child(1)").shouldNotBe(empty);
            String s3 = $(".workspace > div:nth-child(1) > div:nth-child(1) > h4:nth-child(1)").getText();
            String s4 = s3.replaceAll("[^-?0-9]+", " ");
            if (s2 == s4) {
                $(".header").sendKeys(Keys.BACK_SPACE);
            }
        } else {
            $(".summary-list > div:nth-child(1)").shouldHave(text("По вашему запросу ничего не найдено"));
        }
    }

    @And("I check a random item from the list contract party {string}")
    public void iCheckARandomItemFromTheListContractParty(String arg0) {
        $("li.iac--tabs_item:nth-child(2) > a:nth-child(1) > span:nth-child(1)").shouldHave(text(arg0));
        Integer size = $$(".result > div").size();
        if (size >= 1) {
            String s1 = $(".result > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1)").getText();
            String s2 = s1.replaceAll("[^-?0-9]+", " ");
            $(".result > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div:nth-child(1) > a:nth-child(1)").click();
            $(".workspace > div:nth-child(1) > div:nth-child(1) > h4:nth-child(1)").shouldNotBe(empty);
            String s3 = $(".workspace > div:nth-child(1) > div:nth-child(1) > h4:nth-child(1)").getText();
            String s4 = s3.replaceAll("[^-?0-9]+", " ");
            if (s2 == s4) {
                $(".header").sendKeys(Keys.BACK_SPACE);
            }
        } else {
            $(".summary-list > div:nth-child(1)").shouldHave(text("По вашему запросу ничего не найдено"));
        }
    }

    @And("I check a random item from the list order own {string}")
    public void iCheckARandomItemFromTheListOrderOwn(String arg0) {
        $("li.iac--tabs_item:nth-child(1) > a:nth-child(1) > span:nth-child(1)").shouldHave(text(arg0));
        Integer size = $$(".result > div").size();
        if (size >= 1) {
            String s1 = $(".result > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1)").getText();
            String s2 = s1.replaceAll("[^-?0-9]+", " ");
            $(".result > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div:nth-child(1) > a:nth-child(1)").click();
            $(".workspace > div:nth-child(1) > div:nth-child(1) > h4:nth-child(1)").shouldNotBe(empty);
            sleep(1000);
            String s3 = $(withText("Заказ")).getText();
            String s4 = s3.replaceAll("[^-?0-9]+", " ");
            if (s2 == s4) {
                $(".header").sendKeys(Keys.BACK_SPACE);
            }
        } else {
            $(".summary-list > div:nth-child(1)").shouldHave(text("По вашему запросу ничего не найдено"));
        }
    }

    @And("I check a random item from the list order party {string}")
    public void iCheckARandomItemFromTheListOrderParty(String arg0) {

        $("li.iac--tabs_item:nth-child(2) > a:nth-child(1) > span:nth-child(1)").shouldHave(text(arg0));
        Integer size = $$("#app > div > div.iac--layout > div.iac--layout-main_wrapper > div > div.iac--scroll-up.iac--layout-main_content > div > div > div > div > div.iac--datasource > div.content > div").size();
        if (size >= 1) {
            String s1 = $(".result > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1)").getText();
            String s2 = s1.replaceAll("[^-?0-9]+", " ");

            $(".result > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div:nth-child(1) > a:nth-child(1)").click();
            $(".workspace > div:nth-child(1) > div:nth-child(1) > h4:nth-child(1)").shouldNotBe(empty);
            String s3 = $(".workspace > div:nth-child(1) > div:nth-child(1) > h4:nth-child(1)").getText();
            String s4 = s3.replaceAll("[^-?0-9]+", " ");

            if (s2 == s4) {
                $(".header").sendKeys(Keys.BACK_SPACE);
            }
        } else {
            $(".summary-list > div:nth-child(1)").shouldHave(text("По вашему запросу ничего не найдено"));
        }
    }

    @And("I check a random item from the list employees {string}")
    public void iCheckARandomItemFromTheListEmployees(String arg0) {
        $("#app > div > div.layout-2019 > div.body > div.page > div.content-wrapper > div > div > h3").shouldHave(text(arg0));
        // проверка существования списка сотрудников
        if (!$$(byClassName("result")).isEmpty()) {
            // сохраняю имя сотрудника
            String s = $(byClassName("iac--link")).getText();
            //Клик на первого сотрудника
            $(byClassName("item")).find(byClassName("iac--link")).click();
            // Проверка страницы сотрудника
            if ($(byText(s)).isDisplayed()) {
                $(byTitle("Выйти")).click();
            }

        } else {
            $(".summary-list > div:nth-child(1)").shouldHave(text("По вашему запросу ничего не найдено"));
        }
    }

    @And("I check a random item from the list orders {string}")
    public void iCheckARandomItemFromTheListOrders(String arg0) {
        $(byClassName("title")).shouldHave(text("Товары"));
        // проверка существования заказа
        if (!$$(byClassName("result")).get(0).getText().equals("Список пуст")) {
            // сохраняю имя заказа
            String s = $(byClassName("iac--link")).getText();
            //Клик на первого заказа
            $(byClassName("item")).find(byClassName("iac--link")).click();
            // Проверка страницы заказа
            if ($(byText(s)).isDisplayed()) {
                $(byTitle("Выйти")).click();
            }

        } else {
            $(byClassName("alert-warning")).shouldHave(text("Список пуст"));
        }
    }

    @And("I check a random item from the list all approvals {string}")
    public void iCheckARandomItemFromTheListAllApprovals(String arg0) {
        $(".procedure > h3:nth-child(1)").shouldHave(text("Список согласования"));
        //// проверка существования списка
        if (!$$(byClassName("result")).get(0).getText().equals("Список пуст")) {
            // Сохраняю ид соглашения и нажимаю
            String id = $("[href=\"/workspace/tender/details/361893/core\"]").getText().replaceAll("[^0-9]", "");
            $("[href=\"/workspace/tender/details/361893/core\"]").click();
           // беру ид открытой соглашения
            String id2 = $(".workspace > div:nth-child(1) > div:nth-child(1) > h4:nth-child(1)").getText().replaceAll("[^0-9]", "");

            if (id.equals(id2)) {
                $(byTitle("Выйти")).click();
            }
        } else {
            $(byClassName("alert-warning")).shouldHave(text("Список пуст"));
        }

    }

    @And("I check a random item from the list you are matching {string}")
    public void iCheckARandomItemFromTheListYouAreMarching(String arg0) {
        $(byText("Вы согласующий")).click();
        $(".procedure > h3:nth-child(1)").shouldHave(text("Список согласования"));
        if (!$(".alert").getText().equals("Список пуст")) {
            // Сохраняю ид соглашения и нажимаю
            String id = $("[href=\"/workspace/tender/details/361893/core\"]").getText().replaceAll("[^0-9]", "");
            $("[href=\"/workspace/tender/details/361893/core\"]").click();
            // беру ид открытой соглашения
            String id2 = $(".workspace > div:nth-child(1) > div:nth-child(1) > h4:nth-child(1)").getText().replaceAll("[^0-9]", "");

            if (id.equals(id2)) {
                $(byTitle("Выйти")).click();
            }

        } else {
            $(byClassName("alert-warning")).shouldHave(text("Список пуст"));
        }
    }

    @And("I check a random item from the list created by you {string}")
    public void iCheckARandomItemFromTheListCreatedByYou(String arg0) {
        $(byText("Созданные вами")).click();
        $(".procedure > h3:nth-child(1)").shouldHave(text("Список согласования"));
        if (!$$(byClassName("result")).get(0).getText().equals("Список пуст")) {
            // сохраняю имя заказа
            String id = $("[href=\"/workspace/tender/details/361893/core\"]").getText().replaceAll("[^0-9]", "");
            $("[href=\"/workspace/tender/details/361893/core\"]").click();
            String id2 = $(".workspace > div:nth-child(1) > div:nth-child(1) > h4:nth-child(1)").getText().replaceAll("[^0-9]", "");
            System.out.println(id);
            System.out.println(id2);
            if (id.equals(id2)) {
                $(byTitle("Выйти")).click();
            }
        } else {
            $(byClassName("alert-warning")).shouldHave(text("Список пуст"));
        }
    }


    @And("I check a random item from the list other {string}")
    public void iCheckARandomItemFromTheListOther (String arg0){
        $(byText("Прочее")).click();
        $(".procedure > h3:nth-child(1)").shouldHave(text("Список согласования"));
        if (!$$(".alert").get(0).getText().equals("Список пуст")) {
            // сохраняю ид
            String id = $("[href=\"/workspace/tender/details/155920/core\"]").getText().replaceAll("[^0-9]", "");
            $("[href=\"/workspace/tender/details/361893/core\"]").click();
            String id2 = $(".workspace > div:nth-child(1) > div:nth-child(1) > h4:nth-child(1)").getText().replaceAll("[^0-9]", "");
            System.out.println(id);
            System.out.println(id2);
            if (id.equals(id2)) {
                $(byTitle("Выйти")).click();
            }
        } else {
            $(byClassName("alert-warning")).shouldHave(text("Список пуст"));
        }

    }

    @And("I create a new pattern")
    public void iCreateANewPattern(){
        // Проверка титл страницы
        $("div.title > h3:nth-child(1)").shouldHave(text("Список с шаблонами"));
        // клик кнопку создать шаблон
        $(".ui-btn").click();

    }
    @Then("I create Contest")
    public void iCreateContest(){
        Bidding bidding = new Bidding();
        bidding.createContest();
        $(".logout").click();
    }

    @Given("I create new template")
    public void iCreateNewTemplate(){
        Template temp = new Template();
        temp.open();
        temp.createTemplate();
        $(".logout").click();
    }

    @Then("I change template")
    public void iChangeTemplate(){
        Template template = new Template();
        template.open();
        template.changeTemplate();
        $(".success").shouldHave(text("Шаблон был успешно сохранен"));
        $(".form-actions > button:nth-child(1)").click();
        $(".logout").click();
    }

    @Then("I launch approval")
    public void iLaunchApproval(){
        Bidding bidding = new Bidding();
        bidding.goToBidding();
        bidding.launchApproval();
        $(".logout").click();
    }
    @Then("I accept agreement")
    public void iAcceptAgreement(){
        Approvals approvals = new Approvals();
        approvals.open();
        approvals.concertContestAsEmployee();
        $(".logout").click();
    }

    @Then("I ignore agreement")
    public void iIgnoreAgreement(){
        Approvals approvals = new Approvals();
        approvals.open();
        approvals.ignoreContestAsEmployee();
        $(".logout").click();
    }

    @Then("I send offer")
    public void iSendOffer(){
        Bidding bidding = new Bidding();
        bidding.sendOffer();
    }

    @Then("I send contest to bidding")
    public void iSendContestToBidding(){
        new Bidding().sendToBidding();
    }

    @Then("I create contract based on contest")
    public void iCreateContractBasedOnContest(){
        new Bidding().createContractBasedOnContest();
    }

    @Then("I create agreement without basis")
    public void iCreateAgreementWithoutBasis(){
        new Basket().creatAgreementWithoutBasis();
    }


    @Then("I check download position from basic order")
    public void iCheckDownloadPositionFromBasicOrder(){
        new Basket().downloadPositionBasicOrder();
    }

    @Then("I check filling position from file")
    public void iCheckFillingPositionFromFile(){
        new Basket().fillFieldBasicOrderFromFile();
    }

    @Then("I update volume position from file")
    public void iUpdateVolumePositionFromFile(){
        new Basket().updateVolumeBasicOrderFromFile();
    }

}
