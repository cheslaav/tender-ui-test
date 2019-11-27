package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.proxy.FileDownloadFilter;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Action;

import java.io.File;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.value;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.sleep;
import static helpers.Helpers.uploadFileWithRobot;

/**
 * Created by Rustam on 18.10.2019.
 */
public class Basket {

    public void goToBasket(){
        $(byText("Корзина")).click();
    }

    public void creatAgreementWithoutBasis(){
        $(byText("Сформировать процедуру")).click();
        // Выбор тип процедуры
        $(byClassName("form-control")).click();
        $(byText("Базовый договор")).click();
        $(byText("Создать")).click();
        // Выбор комбинат заказчика
        $(".field-group > fieldset:nth-child(1) > div:nth-child(2) > div:nth-child(2) > div:nth-child(2) > div:nth-child(2) > div:nth-child(1) > div:nth-child(1)").click();
        $("div.item:nth-child(1) > div:nth-child(1)").click();
        // Выбор контрагента
        $(".placeholder").click();
        $("div.search > input:nth-child(1)").setValue("sa");
        $$(byClassName("list")).last().click();
        // Переход в вкладку Товары
        $(byText("Товары")).click();
        $(byText("Добавить приложение")).click();
        $("div.sort-item:nth-child(2) > div:nth-child(2) > div:nth-child(2) > textarea:nth-child(1)").setValue("avtotest");
        $("div.iac--dropdown:nth-child(2) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1)").hover();
        $(withText("Добавить")).click();
        $(".title").click();
        $(By.className("product_select")).shouldNotBe(Condition.empty);
        $$(By.className("product_select")).first().click();
        $(".new > tr:nth-child(3) > td:nth-child(2) > div:nth-child(1) > input:nth-child(1)").setValue("1");
        sleep(1000);
        $(".new > tr:nth-child(3) > td:nth-child(3) > div:nth-child(1) > select:nth-child(1)").click();
        $(".new > tr:nth-child(3) > td:nth-child(3) > div:nth-child(1) > select:nth-child(1) > option:nth-child(1)").click();
        sleep(1000);
        $(".new > tr:nth-child(3) > td:nth-child(4) > div:nth-child(1) > input:nth-child(1)").setValue("1");
        sleep(1000);
        $(byTitle("Ставка НДС")).shouldHave(value("20"));
        //$(".ui-btn-secondary").click();
        String id = $(".workspace > div:nth-child(1) > div:nth-child(1)").getText();
        id = id.replaceAll("\\D+","");
        $(byText("Опубликовать")).click();
        $(byText("Договоры")).click();
        sleep(50000);
        Selenide.refresh();
        $(".content > div > div:nth-child(1) > div  > div > div.header > div.type").shouldHave(text(id));
        $(".result > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div:nth-child(1) > a:nth-child(1)").click();
        // Проверка НДС
        $("li.iac--tabs_item:nth-child(2)").shouldBe(visible).click();
        $(".ui-btn-undefined").click();
        $(".price_with_nds").shouldHave(text("1.2"));
    }

    // Создания Базового заказа
    public void createBasicOrder(){
        //Нажать кнопку "Сформировать процедуру"
        $(".ui-btn").click();
        //Выбор тип процедуры
        $("select.form-control").click();
        $(withText("Базовый заказ")).click();
        $(byText("Создать")).click();
        //Ввод названия
        $(byClassName("form-control")).setValue("Автотест");
        //Ввод дата поставки
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.HOUR, 1);
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm");
        String strDate = sdf.format(cal.getTime());
        $("div.sort-item:nth-child(3) > div:nth-child(2) > div:nth-child(2) > div:nth-child(1) > input:nth-child(1)").setValue(strDate);
        $(byClassName("name")).click();
        //Выбор контрагента
        $("div.ref:nth-child(1) > div:nth-child(2) > div:nth-child(2) > div:nth-child(1) > div:nth-child(1)").click();
        $("div.search > input:nth-child(1)").setValue("sa");
        $$(byClassName("list")).last().click();
        //Переход в раздель Товары
        $(byText("Товары")).click();
        //Нажать Добавить приложения
        $(".ui-btn-primary").click();
        //Заполнение всех полей
        $("input.form-control").setValue("10");
        $("textarea.form-control").setValue("город автотест");
    }

    //Проверка скачивания в файл посици базового заказа
    public void downloadPositionBasicOrder(){
        createBasicOrder();
        $("div.iac--dropdown:nth-child(3) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1)").hover();
        $(byText("Добавить позицию")).click();
        $(byText("Выберите товар")).click();
        $(".product_select > div:nth-child(1) > div:nth-child(1) > div:nth-child(1)").click();
        //$$(byClassName("product_select")).first().click();
        $(byTitle("Объем организатора")).setValue("1");
        sleep(1000);
        $(byTitle("Цена организатора")).setValue("1");
        sleep(1000);
        $(byTitle("Объем заказа")).setValue("1");
        sleep(1000);
        $("div.iac--dropdown:nth-child(3) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1)").hover();
        $(withText("Скачать")).click();
        sleep(3000);

    }

    public void fillBasicOrderPositionsFromFile(String filePath){
        $("div.iac--dropdown:nth-child(3) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1)").hover();
        $(byText("Позиции из файла")).click();
        uploadFileWithRobot(filePath);
    }

    //Проверка заполнения поля товаров с файла
    public void fillFieldBasicOrderFromFile(){
        createBasicOrder();
        $("div.iac--dropdown:nth-child(3) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1)").hover();
        $(byText("Позиции из файла")).click();
        uploadFileWithRobot(Paths.get(System.getProperty("user.dir"),"template.xls").toFile().getAbsolutePath());
        System.out.println(Paths.get(System.getProperty("user.dir"),"template.xls").toFile().getAbsolutePath());
        $(byTitle("Объем организатора")).shouldHave(value("1"));
        $(byTitle("Цена организатора")).shouldHave(value("1"));
        $(byTitle("Объем заказа")).shouldHave(value("1"));
    }

    public void updateVolumeBasicOrderFromFile(){
        createBasicOrder();
        $("div.iac--dropdown:nth-child(3) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1)").hover();
        $(byText("Добавить позицию")).click();
        $(byTitle("Объем организатора")).setValue("2");
        $(byTitle("Цена организатора")).setValue("2");
        $(byTitle("Объем заказа")).setValue("2");
        $("div.iac--dropdown:nth-child(3) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1)").hover();
        $(withText("Обновление")).click();
        uploadFileWithRobot(Paths.get(System.getProperty("user.dir"),"template.xls").toFile().getAbsolutePath());
        $(byTitle("Объем заказа")).shouldHave(value("1"));
    }


}
