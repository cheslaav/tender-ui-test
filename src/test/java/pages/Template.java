package pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Properties;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byClassName;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.sleep;

/**
 * Created by Rustam on 02.10.2019.
 */
public class Template {

    FileInputStream fis;
    Properties property = new Properties();
   public   Template(){
        try {
            fis = new FileInputStream("src/test/java/source/config.properties");
            property.load(new InputStreamReader(fis, Charset.forName("utf8")));
        }catch (IOException e){
            System.err.println("ОШИБКА: Файл свойств отсутствует!");
        }
    }

    //Открыт раздел "Шаблоны"
    public Template open(){
        $(byText("Шаблоны")).click();
        return this;
    }
    //Создать Шаблон
    public void createTemplate(){
        //Нажать на кнопку "Создать"
        $(byText("Создать")).click();
        //Ввод названия
        $(".disabled").setValue("avtotest");
        //Нажать на кнопку "Создать"
        $(".ui-btn").click();
        $("div.layout-group:nth-child(2) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div:nth-child(2) > textarea:nth-child(2)").setValue("avtotest");
        $(".open").click();
        //выбор участника
        List<SelenideElement> list = $(".open").findAll(By.className("item"));
        for(SelenideElement e : list){
            if(e.getText().equals(property.getProperty("nameEmployee"))){
               e.find(byClassName("action")).click();
            }
        }
        $(By.className("minus")).should(exist);
        $(".add > span:nth-child(2)").click();
        $(".ui-btn").click();
        $(".success").shouldHave(text("Шаблон был успешно сохранен"));
        $(".form-actions > button:nth-child(1)").click();
    }

    //Изменит Шаблон
    public void changeTemplate(){
        //нажать Редактироват у шаблона
        $(By.className("action")).click();
        $(".editor > h3:nth-child(1)").shouldBe(text("Шаблон:"));
        $(".disabled").setValue("avtotest change");
        $("div.layout-group:nth-child(2) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div:nth-child(2) > textarea:nth-child(2)").setValue("avtotest change");
        $(".open").click();
        //выбор участника
        List<SelenideElement> list = $(".open").findAll(By.className("item"));
        for(SelenideElement e : list){
            System.out.println(property.getProperty("nameEmployee"));
            if(e.getText().equals(property.getProperty("nameEmployee"))){
                e.find(By.className("action")).click();
            }
        }
        $(By.className("minus")).should(exist);
        $(By.className("field")).click();
        $(withText("Сохранить")).click();
    }

}
