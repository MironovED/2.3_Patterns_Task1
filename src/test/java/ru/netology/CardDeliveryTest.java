package ru.netology;

import com.codeborne.selenide.Condition;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;


public class CardDeliveryTest {
    private RegistrationByCardInfo user = DataGenerator.Registration.generateByCard("ru");
    private String invalidName = DataGenerator.getRandomInvalidName();
    private String invalidCity = DataGenerator.getRandomInvalidCity();


    @BeforeEach
    public void setUp() {
        open("http://localhost:9999");
    }

    @Test
    void checkForValidValues() {
        String planningDate = DataGenerator.generateDate(3);


        $("[data-test-id='city'] input").val(user.getCity());
        $("[class='menu-item__control']").click();
        $("[data-test-id=date] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id=date] input").sendKeys(planningDate);
        $("[data-test-id='name'] .input__control").val(user.getName());
        $("[data-test-id='phone'] .input__control").val(user.getPhone());
        $("[data-test-id='agreement']").click();
        $(".button").click();
        $(withText("Успешно!")).shouldBe(visible);
        $("[data-test-id='success-notification'] .notification__content").shouldHave(text("Встреча успешно запланирована на " + planningDate));

    }

    @Test
    void shouldGetErrorIfEmptyFields() {
        $(".button").click();
        $("[data-test-id='city'].input_invalid").shouldHave(Condition.text("Поле обязательно для заполнения"));

    }

    @Test
    void shouldGetErrorIfInvalidFieldCity() {
        String planningDate = DataGenerator.generateDate(3);


        $("[data-test-id='city'] input").val(invalidCity);
        $("[data-test-id=date] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id=date] input").sendKeys(planningDate);
        $("[data-test-id='name'] .input__control").val(user.getName());
        $("[data-test-id='phone'] .input__control").val(user.getPhone());
        $("[data-test-id='agreement']").click();
        $(".button").click();
        $("[data-test-id='city'].input_invalid").shouldHave(Condition.text("Доставка в выбранный город недоступна"));

    }

    @Test
    void shouldGetErrorIfDateInvalid() {
        String planningDate = DataGenerator.generateDate(2);


        $("[data-test-id='city'] input").val(user.getCity());
        $("[class='menu-item__control']").click();
        $("[data-test-id=date] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id=date] input").sendKeys(planningDate);
        $("[data-test-id='name'] .input__control").val(user.getName());
        $("[data-test-id='phone'] .input__control").val(user.getPhone());
        $("[data-test-id='agreement']").click();
        $(".button").click();
        $("[data-test-id=date] .input_invalid").shouldHave(Condition.text("Заказ на выбранную дату невозможен"));

    }

    @Test
    void shouldPassedIfDatePlus4DaysFromCurrentDay() {
        String planningDate = DataGenerator.generateDate(4);


        $("[data-test-id='city'] input").val(user.getCity());
        $("[class='menu-item__control']").click();
        $("[data-test-id=date] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id=date] input").sendKeys(planningDate);
        $("[data-test-id='name'] .input__control").val(user.getName());
        $("[data-test-id='phone'] .input__control").val(user.getPhone());
        $("[data-test-id='agreement']").click();
        $(".button").click();
        $(withText("Успешно!")).shouldBe(visible);

    }

    @Test
    void shouldGetErrorIfFieldNameEmpty() {
        String planningDate = DataGenerator.generateDate(3);


        $("[data-test-id='city'] input").val(user.getCity());
        $("[class='menu-item__control']").click();
        $("[data-test-id=date] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id=date] input").sendKeys(planningDate);
        $("[data-test-id='name'] .input__control").val("");
        $("[data-test-id='phone'] .input__control").val(user.getPhone());
        $("[data-test-id='agreement']").click();
        $(".button").click();
        $("[data-test-id='name'].input_invalid").shouldHave(Condition.text("Поле обязательно для заполнения"));

    }

    @Test
    void shouldGetErrorIfInvalidValuesFieldName1() {
        String planningDate = DataGenerator.generateDate(3);


        $("[data-test-id='city'] input").val(user.getCity());
        $("[class='menu-item__control']").click();
        $("[data-test-id=date] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id=date] input").sendKeys(planningDate);
        $("[data-test-id='name'] .input__control").val(invalidName);
        $("[data-test-id='phone'] .input__control").val(user.getPhone());
        $("[data-test-id='agreement']").click();
        $(".button").click();
        $("[data-test-id='name'].input_invalid").shouldHave(Condition.text("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));

    }

    @Test
    void shouldGetErrorIfInvalidValuesFieldName2() {
        String planningDate = DataGenerator.generateDate(3);


        $("[data-test-id='city'] input").val(user.getCity());
        $("[class='menu-item__control']").click();
        $("[data-test-id=date] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id=date] input").sendKeys(planningDate);
        $("[data-test-id='name'] .input__control").val(invalidName);
        $("[data-test-id='phone'] .input__control").val(user.getPhone());
        $("[data-test-id='agreement']").click();
        $(".button").click();
        $("[data-test-id='name'].input_invalid").shouldHave(Condition.text("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));

    }

    @Test
    void shouldGetErrorIfFieldPhoneEmpty() {
        String planningDate = DataGenerator.generateDate(3);


        $("[data-test-id='city'] input").val(user.getCity());
        $("[class='menu-item__control']").click();
        $("[data-test-id=date] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id=date] input").sendKeys(planningDate);
        $("[data-test-id='name'] .input__control").val(user.getName());
        $("[data-test-id='phone'] .input__control").val("");
        $("[data-test-id='agreement']").click();
        $(".button").click();
        $("[data-test-id='phone'].input_invalid .input__sub").shouldHave(Condition.text("Поле обязательно для заполнения"));

    }

    @Test
    void shouldGetErrorIfUncheckedCheckbox() {
        String planningDate = DataGenerator.generateDate(3);


        $("[data-test-id='city'] input").val(user.getCity());
        $("[class='menu-item__control']").click();
        $("[data-test-id=date] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id=date] input").sendKeys(planningDate);
        $("[data-test-id='name'] .input__control").val(user.getName());
        $("[data-test-id='phone'] .input__control").val(user.getPhone());
        $(".button").click();
        $("[data-test-id='agreement'].input_invalid").shouldHave(visible);

    }

    @Test
    void checksTheRecordConfirmationNotificationForANewDate() {
        String planningDate = DataGenerator.generateDate(3);
        String planningDateLast = DataGenerator.generateDate(5);


        $("[data-test-id='city'] input").val(user.getCity());
        $("[class='menu-item__control']").click();
        $("[data-test-id=date] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id=date] input").sendKeys(planningDate);
        $("[data-test-id='name'] .input__control").val(user.getName());
        $("[data-test-id='phone'] .input__control").val(user.getPhone());
        $("[data-test-id='agreement']").click();
        $(".button").click();
        $("[data-test-id='success-notification'] .notification__content").shouldBe(text("Встреча успешно запланирована на " + planningDate), Duration.ofSeconds(1));
        $("[data-test-id=date] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id=date] input").sendKeys(planningDateLast);
        $(".button").click();
        $("[data-test-id='replan-notification'] .notification__content").shouldHave(Condition.text("У вас уже запланирована встреча на другую дату. Перепланировать?"));
        $("[data-test-id='replan-notification'] .button__text").click();
        $("[data-test-id='success-notification'] .notification__content").shouldBe(text("Встреча успешно запланирована на " + planningDateLast), Duration.ofSeconds(2));

    }

}
