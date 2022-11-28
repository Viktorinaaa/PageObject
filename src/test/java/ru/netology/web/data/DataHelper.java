package ru.netology.web.data;

import lombok.Value;
import com.github.javafaker.Faker;



import java.util.Locale;

public class DataHelper {

    private DataHelper() {}

    @Value
    public static class AuthInfo {
        private String login;
        private String password;
    }

    public static AuthInfo getAuthInfo() {
        return new AuthInfo("vasya", "qwerty123");
    }

    public static AuthInfo getOtherAuthInfo(AuthInfo original) {
        return new AuthInfo("petya", "123qwerty");
    }

    @Value
    public static class VerificationCode {
        private String code;
    }

    public static VerificationCode getVerificationCodeFor(AuthInfo authInfo) {
        return new VerificationCode("12345");
    }

    @Value
    public static class InfoCard{
        private String card;
        private String id;
    }

    public static InfoCard getInfoBalanceCardFirst(){
        return new InfoCard("5559 0000 0000 0001", "92df3f1c-a033-48e6-8390-206f6b1f56c0");
    }
    public static InfoCard getInfoBalanceCardSecond(){
        return new InfoCard("5559 0000 0000 0002","0f3f5c2a-249e-4c3d-8287-09f7a039391d");
    }

    @Value
    public static class InfoTransferInit{
        private int sumInit;
        private String cardFrom;
    }

    public static InfoTransferInit getInfoTransferFromSecondInit(){
        return new InfoTransferInit(10000,"5559 0000 0000 0002");
    }
    public static InfoTransferInit getInfoTransferFromFirstInit(){
        return new InfoTransferInit(10000,"5559 0000 0000 0001");
    }


    @Value
    public static class InfoTransfer{
        private int sumTransfer;
    }

    public static InfoTransfer getInfoTransfer(int balanceCurrent){
        Faker faker = new Faker(new Locale("int"));
        int sumTransfer = faker.random().nextInt(1, balanceCurrent);
        return new InfoTransfer(sumTransfer);
    }


}
