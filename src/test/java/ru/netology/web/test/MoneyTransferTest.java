package ru.netology.web.test;

import com.codeborne.selenide.Configuration;
import lombok.Value;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.web.data.DataHelper;
import ru.netology.web.page.LoginPage;
import ru.netology.web.page.DashboardPage;
import ru.netology.web.page.MoneyTransferPage;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static com.codeborne.selenide.Selenide.open;

class MoneyTransferTest {

    @BeforeEach
    void setup() {
        Configuration.holdBrowserOpen = true;
        open("http://localhost:9999");
    }


    @Test
    void shouldTransferMoneyBetweenOwnCards() {
        var loginPage = open("http://localhost:9999", LoginPage.class);
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);

        var dashboardPage = verificationPage.validVerify(verificationCode);
        var dashboardCardSecond = DataHelper.getInfoTransferFromSecondInit().getSumInit();
        var dashboardCardFirst = DataHelper.getInfoTransferFromFirstInit().getSumInit();



        //var moneyTransferPage = dashboardPage.moneyTransferFirst();
        var cardFrom = DataHelper.getInfoTransferFromFirstInit(); //получаем сумму и номер 1 карты
        var moneyTransferPage = dashboardPage.selectCardToTransfer(cardFrom); //



        var balanceCurrentSecondCard = DataHelper.getInfoTransferFromSecondInit().getSumInit();
        var balanceCurrentFirstCard = DataHelper.getInfoTransferFromFirstInit().getSumInit();

        var transferSum = DataHelper.getInfoTransfer(balanceCurrentSecondCard);
        //var cardFrom = DataHelper.getInfoTransferFromSecondInit().getCardFrom();
        var infoTransferInit = DataHelper.getInfoTransferFromSecondInit();
        dashboardPage = moneyTransferPage.validTransfer(transferSum.toString(), infoTransferInit);


        var idCardFirst = DataHelper.getInfoBalanceCardFirst();
        var idCardSecond = DataHelper.getInfoBalanceCardSecond();

        int expectedSumCardFirst = balanceCurrentFirstCard + transferSum.getSumTransfer();
        int expectedSumCardSecond = balanceCurrentSecondCard - transferSum.getSumTransfer();


        //int actualSumCardFirst = dashboardPage.getCardBalance(idCardFirst);
        //int actualSumCardSecond = dashboardPage.getCardBalance(idCardSecond);
        int actualSumCardFirst = dashboardPage.getOneCardBalance();
        int actualSumCardSecond = dashboardPage.getTwoCardBalance();


        assertEquals(expectedSumCardFirst, actualSumCardFirst);
        assertEquals(expectedSumCardSecond,actualSumCardSecond);

    }


}
