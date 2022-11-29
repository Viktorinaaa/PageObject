package ru.netology.web.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.Value;
import lombok.val;
import org.openqa.selenium.By;
import ru.netology.web.data.DataHelper;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class DashboardPage {

    private SelenideElement heading = $("[data-test-id=dashboard]");
    private ElementsCollection cards = $$(".list__item");
    private final String balanceStart = "баланс: ";
    private final String balanceFinish = " р.";
    private ElementsCollection buttonMoneyTransfer = $$("[data-test-id=action-deposit]");


    public DashboardPage() {

        heading.shouldBe(visible);
    }


    public int getCardBalance(String id) {
        String text = "";
        val idFirst = "92df3f1c-a033-48e6-8390-206f6b1f56c0";
        val idSecond = "0f3f5c2a-249e-4c3d-8287-09f7a039391d";

        for (SelenideElement element : cards) {
            if (id.equals(idFirst)) {
                text = cards.get(0).text();
            } else if (id.equals(idSecond)) {
                text = cards.get(1).text();
            }
        }
        return extractBalance(text);
    }

    public MoneyTransferPage moneyTransferFirst() {
        buttonMoneyTransfer.get(0).click();
        return new MoneyTransferPage();
    }

    public MoneyTransferPage moneyTransferSecond() {
        buttonMoneyTransfer.get(1).click();
        return new MoneyTransferPage();
    }

    private int extractBalance(String text) {
        val start = text.indexOf(balanceStart);
        val finish = text.indexOf(balanceFinish);
        val value = text.substring(start + balanceStart.length(), finish);
        return Integer.parseInt(value);
    }
}
