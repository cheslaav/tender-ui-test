package pages;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byClassName;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;

/**
 * Created by Rustam on 02.10.2019.
 */
public class Approvals {
    public Approvals open(){
        $(byText("Согласования")).click();
        return this;
    }

    public Approvals goToYouApprove(){
        $(byText("Вы согласующий")).click();
        return this;
    }

    public Approvals goToYouCreate(){
        $(byText("Созданные вами")).click();
        return this;
    }

    public Approvals goToOthers(){
        $(byText("Прочее")).click();
        return this;
    }

    public void concertContestAsEmployee(){
        $(withText(Bidding.contestID)).click();
        $(byText("Победители")).click();
        $(".iac--modal__body > div > div.item > label > input[type=checkbox]").click();
        $(byText("Закрыть")).click();
        $(byText("Согласовать")).click();
        $(".field > textarea:nth-child(2)").setValue("avtotest");
        $(byText("Принять")).click();
        $(byText("Завершить")).click();
        $(".iac--layout-main_content > div > div > div > div:nth-child(1) > h4:nth-child(2) > span:nth-child(1)").shouldHave(text("Закрыт"));
    }

    public void ignoreContestAsEmployee(){
        $(withText(Bidding.contestID)).click();
        $(byText("Согласовать")).click();
        $(byText("Отклонить")).click();
        $(byText("Согласовать")).click();
        $("div.item:nth-child(2) > div:nth-child(1) > div:nth-child(2)").shouldHave(text("Честный Работяга: не согласовано"));
    }

}
