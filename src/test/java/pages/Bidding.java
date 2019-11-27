package pages;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.commands.Find;
import org.openqa.selenium.By;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byClassName;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;
import static org.junit.Assert.assertTrue;

/**
 * Created by Rustam on 01.10.2019.
 */
public class Bidding {
    public static String contestID;
    FileInputStream fis;
    Properties property = new Properties();

    public Bidding() {
        try {
            fis = new FileInputStream("src/test/java/source/config.properties");
            property.load(new InputStreamReader(fis, Charset.forName("windows-1251")));
        } catch (IOException e) {
            System.err.println("ОШИБКА: Файл свойств отсутствует!");
        }
    }


    //открыт раздел Торги
    public Bidding goToBidding() {
        $("[href=\"/workspace/tender\"]").click();
        return this;
    }

    //под раздел Приглашения
    public void goToInvetations() {
        $(By.linkText("Приглашения")).click();
    }

    //под раздел Организуем
    public void goToOrganize() {
        $(By.linkText("Организуем")).click();
    }

    //подраздел Участвуем
    public void goToParticipation() {
        $(By.linkText("Участвуем")).click();
    }

    //Создать конкурс
    public void createContest() {
        //клик кнопку "Создать конкурс"
        $(byText("Создать конкурс")).click();
        //выбор тип процедуры
        $(By.className("form-control")).click();
        $(By.cssSelector("select.form-control > option:nth-child(4)")).click();
        //нажать кнопку "Создать"
        $(byText("Создать")).click();
        //заполнения все поля Условия
        $(By.className("form-control")).setValue("avtotest");
        $(By.className("name")).click();
        $("textarea.form-control").setValue("avtotest");
        //Добавляю шаблон
        $("div.sort-item:nth-child(9) > div:nth-child(2) > div:nth-child(2) > div:nth-child(1) > div:nth-child(1) > span:nth-child(1)").click();
        $$(By.className("result")).first().click();
        //сохраняю ID процедуры
        String realID = $(".workspace > div:nth-child(1) > div:nth-child(1) > h4:nth-child(1)").getText();
        contestID = realID.replaceAll("\\D+", "");
        //заполнения все поля Товары
        $(byText("Товары")).click();
        $(byText("Добавить лот")).click();
        $("div.sort-item:nth-child(2) > div:nth-child(2) > div:nth-child(2) > textarea:nth-child(1)").setValue("avtotest");
        $("div.sort-item:nth-child(4) > div:nth-child(2) > div:nth-child(2) > textarea:nth-child(1)").setValue("avtotest");
        $(".iac--btn--secondary > div:nth-child(1) > div:nth-child(1) > div:nth-child(1)").doubleClick();
        $(withText("Добавить")).click();
        $(".title").click();
        $(By.className("product_select")).shouldNotBe(Condition.empty);
        $$(By.className("product_select")).first().click();
        $(".new > tr:nth-child(3) > td:nth-child(2) > div:nth-child(1) > input:nth-child(1)").setValue("1");
        sleep(1000);
        $(".new > tr:nth-child(3) > td:nth-child(4) > div:nth-child(1) > input:nth-child(1)").setValue("2");
        sleep(1000);
        //переход в вкладку Участники
        $(byText("участники")).click();
        //нажать "Опубликоват"

        $(byText("Опубликовать")).click();
        $(byText("Торги")).click();
        sleep(1000);

        $(byClassName("summary-list")).shouldHave(text(contestID));
    }

    //Отправка предложения
    public void sendOffer() {
        open("https://test.iac.tender.pro/tender/" + contestID);
        $(byText("Подробнее")).click();
        $(byText("Принять участие")).click();
        $(byText("Создать предложение")).click();
        $(byText("Товары")).click();
        $("input.form-control").setValue("2");
        sleep(1000);
        $(byText("Подать предложение")).click();
        $(".ui-btn-default").shouldHave(text("Отозвать предложение"));

    }

    public void sendToBidding() {
        open("https://test.iac.tender.pro/workspace/tender/details/" + contestID + "/core");
        $(byText("Отправить процедуру на согласование")).click();
        $(byText("К процедуре согласования")).click();
        $(byText("Запустить")).click();
    }

    //Запуск согласования
    public void launchApproval() {
        $(byText("Организуем")).click();
        open("https://test.iac.tender.pro/workspace/tender/details/" + Bidding.contestID + "/core");
        // Жду появления кнопки "Отправить процедуру на согласование"
        while (!$("button.ui-btn:nth-child(3)").exists()) {
            sleep(1000);
        }

        $(byText("Отправить процедуру на согласование")).click();
        Approvals approvals = new Approvals();
        // переход на раздел Согласования
        approvals.open();
        $(withText(Bidding.contestID)).click();
        $("button.ui-btn:nth-child(1)").click();
        $(".open").click();
        //выбор участника
        List<SelenideElement> list = $(".open").findAll(By.className("item"));
        for (SelenideElement e : list) {
            System.out.println(e.getText());
            if (e.getText().equals(property.getProperty("nameEmployee"))) {
                e.find(byClassName("plus")).click();
            }
        }

        $(byText("Запустить")).click();
        $(".success").shouldHave(text("Процедура успешно запущена"));
        $(".form-actions > button:nth-child(1)").click();
        //Проверка наличия логов
        $(By.className("result")).shouldNotBe(empty);
    }

    public void createContractBasedOnContest() {
        sleep(1000);
        open("https://test.iac.tender.pro/workspace/tender/details/" + contestID + "/participants");
        $(".summary-list > div > div > div.actions > div > div").hover();
        $(withText("Создать")).click();
        $(byClassName("form-control")).click();
        $(byText("Базовый договор")).click();
        $(byText("Создать")).click();
        String id = $(".workspace > div:nth-child(1) > div:nth-child(1)").getText();
        id = id.replaceAll("\\D+", "");
        System.out.println(id);
        int id1 = Integer.parseInt(id);
        $(byText("Опубликовать")).click();
        $(byText("Договоры")).click();
        //когда исправять баг #1416
        sleep(50000);
        Selenide.refresh();
        $(".content > div > div:nth-child(1) > div > div > div.header > div.type").shouldHave(text(Integer.toString(++id1)));
    }
}
