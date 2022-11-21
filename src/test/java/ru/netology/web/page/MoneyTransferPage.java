package ru.netology.web.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.netology.web.data.DataHelper;
import static com.codeborne.selenide.Condition.visible;

import static com.codeborne.selenide.Selenide.$;

public class MoneyTransferPage {
    private SelenideElement sum = $("[data-test-id=amount] input");
    private SelenideElement cardFrom = $("[data-test-id=from] input");
    private SelenideElement buttonTransfer = $("[data-test-id=action-transfer]");

    public MoneyTransferPage(){
        sum.shouldBe(visible);
        cardFrom.shouldBe(visible);
    }

    public DashboardPage validTransfer(String sumTransfer, DataHelper.InfoTransferInit infoTransferInit){
        sum.setValue(sumTransfer);
        cardFrom.setValue(infoTransferInit.getCardFrom());
        buttonTransfer.click();
        return new DashboardPage();
    }

}
