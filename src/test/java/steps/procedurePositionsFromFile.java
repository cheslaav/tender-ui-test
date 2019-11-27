package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

import pages.Basket;
import static helpers.Helpers.getResourceFileAbsolutePath;

public class procedurePositionsFromFile {


    @And("I create {string} order")
    public void iCreateOrder(String orderType){
        switch(orderType) {
            case "basic": {
                new Basket().createBasicOrder();
                break;
            }
            default: System.out.println("No such order type " + orderType);
        }
    }

    @When("I upload {string} file for positions")
    public void iUploadFileForPositions(String fileName) {
        String path = getResourceFileAbsolutePath(fileName);
        new Basket().fillBasicOrderPositionsFromFile(path);
    }

    @Then("I should see error message for empty file")
    public void iShouldSeeErrorMessageForEmptyFile() {
        $(".error.iac--modal__body").shouldHave(text("Internal error")); //Пустой файл
    }

    @Then("I should see error message for unsupported file")
    public void iShouldSeeErrorMessageForUnsupportedFile() {
        $(".error.iac--modal__body").shouldHave(text("Internal error")); //Неподдерживаемый файл
    }

    @Then("I should see error message for wrong format file")
    public void iShouldSeeErrorMessageForWrongFormatFile() {
        $(".iac--modal__header").shouldHave(text("Неверный формат файла"));
    }

    @Then("positions should be added")
    public void positionsShouldBeAdded() {
        $(".lot-items").findAll("tbody").shouldBe(sizeGreaterThan(0));
    }

    @And("positions should contain error")
    public void positionsShouldContainError() {
        $(".lot-items").findAll(".error.field.ui-number").shouldBe(sizeGreaterThan(0));
    }
}
