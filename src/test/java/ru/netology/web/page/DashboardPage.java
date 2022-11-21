package ru.netology.web.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.val;
import org.openqa.selenium.By;
import ru.netology.web.data.DataHelper;

import static com.codeborne.selenide.Selenide.*;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class DashboardPage {
    private SelenideElement heading = $("[data-test-id=dashboard]");
    private ElementsCollection cards = $$(".list__item div");
    private final String balanceStart = "баланс: ";
    private final String balanceFinish = " р.";

    private SelenideElement cardFirst = $("[data-test-id=92df3f1c-a033-48e6-8390-206f6b1f56c0]");
    private SelenideElement cardSecond= $("[data-test-id=0f3f5c2a-249e-4c3d-8287-09f7a039391d]");
    private ElementsCollection buttonMoneyTransfer = $$("[data-test-id=action-deposit]");


    public DashboardPage() {
        heading.shouldBe(visible);
    }


    public int getCardBalance(DataHelper.InfoCard id) {
        String text = new String();
        //String text = null;
        for (SelenideElement element : cards) {
            val idFirst = (element.find(DataHelper.getInfoBalanceCardFirst().getId()));
            val idSecond = (element.find(DataHelper.getInfoBalanceCardSecond().getId()));
            if (text == null)
                if (id.equals(idFirst)){
                    text = element.getText();
                }else if (id.equals(idSecond)){
                    text = element.getText();
                }
            else
                return Integer.parseInt(text);
        }
        return extractBalance(text);
    }


    private int extractBalance(String text) {
        val start = text.indexOf(balanceStart);
        val finish = text.indexOf(balanceFinish);
        val value = text.substring(start + balanceStart.length(), finish);
        return Integer.parseInt(value);
    }


    public MoneyTransferPage moneyTransferFirst(){
        buttonMoneyTransfer.get(0).click();
        return new MoneyTransferPage();
    }

    public MoneyTransferPage moneyTransferSecond(){
        buttonMoneyTransfer.get(1).click();
        return new MoneyTransferPage();
    }

}
