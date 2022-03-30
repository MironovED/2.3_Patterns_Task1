package ru.netology;

import com.codeborne.selenide.Condition;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;

public class CardDeliveryTest {
    private Faker faker;


    public String generateDate(int Days) {
        return LocalDate.now().plusDays(Days).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    @BeforeEach
    void setUpAll() {
        faker = new Faker(new Locale("ru"));
    }

    public void setUp() {
        open("http://0.0.0.0:9999");
    }


    @Test
    void checkForValidValues() {
        String planningDate = generateDate(3);

        $("[data-test-id='city'] input").val(DataGenerator.Registration.generateByCard("ru").getCity());
        $("[class='menu-item__control']").click();
        $("[data-test-id=date] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id=date] input").sendKeys(planningDate);
        $("[data-test-id='name'] .input__control").val(DataGenerator.Registration.generateByCard("ru").getName());
        $("[data-test-id='phone'] .input__control").val(DataGenerator.Registration.generateByCard("ru").getPhone());
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
        String planningDate = generateDate(3);

        String name = faker.name().fullName();
        String phone = faker.phoneNumber().phoneNumber();

        $("[data-test-id='city'] input").val("Сеул");
        $("[data-test-id=date] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id=date] input").sendKeys(planningDate);
        $("[data-test-id='name'] .input__control").val(name);
        $("[data-test-id='phone'] .input__control").val(phone);
        $("[data-test-id='agreement']").click();
        $(".button").click();
        $("[data-test-id='city'].input_invalid").shouldHave(Condition.text("Доставка в выбранный город недоступна"));

    }

    @Test
    void shouldGetErrorIfDateInvalid() {
        String planningDate = generateDate(2);

        String city = faker.address().city();
        String name = faker.name().fullName();
        String phone = faker.phoneNumber().phoneNumber();

        $("[data-test-id='city'] input").val(city);
        $("[class='menu-item__control']").click();
        $("[data-test-id=date] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id=date] input").sendKeys(planningDate);
        $("[data-test-id='name'] .input__control").val(name);
        $("[data-test-id='phone'] .input__control").val(phone);
        $("[data-test-id='agreement']").click();
        $(".button").click();
        $("[data-test-id=date] .input_invalid").shouldHave(Condition.text("Заказ на выбранную дату невозможен"));

    }

    @Test
    void shouldPassedIfDatePlus4DaysFromCurrentDay() {
        String planningDate = generateDate(4);

        String city = faker.address().city();
        String name = faker.name().fullName();
        String phone = faker.phoneNumber().phoneNumber();

        $("[data-test-id='city'] input").val(city);
        $("[class='menu-item__control']").click();
        $("[data-test-id=date] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id=date] input").sendKeys(planningDate);
        $("[data-test-id='name'] .input__control").val(name);
        $("[data-test-id='phone'] .input__control").val(phone);
        $("[data-test-id='agreement']").click();
        $(".button").click();
        $(withText("Успешно!")).shouldBe(visible);

    }

    @Test
    void shouldGetErrorIfFieldNameEmpty() {
        String planningDate = generateDate(3);

        String city = faker.address().city();
        String phone = faker.phoneNumber().phoneNumber();

        $("[data-test-id='city'] input").val(city);
        $("[class='menu-item__control']").click();
        $("[data-test-id=date] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id=date] input").sendKeys(planningDate);
        $("[data-test-id='name'] .input__control").val("");
        $("[data-test-id='phone'] .input__control").val(phone);
        $("[data-test-id='agreement']").click();
        $(".button").click();
        $("[data-test-id='name'].input_invalid").shouldHave(Condition.text("Поле обязательно для заполнения"));

    }

    @Test
    void shouldGetErrorIfInvalidValuesFieldName1() {
        String planningDate = generateDate(3);

        String city = faker.address().city();
        String phone = faker.phoneNumber().phoneNumber();

        $("[data-test-id='city'] input").val(city);
        $("[class='menu-item__control']").click();
        $("[data-test-id=date] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id=date] input").sendKeys(planningDate);
        $("[data-test-id='name'] .input__control").val(phone);
        $("[data-test-id='phone'] .input__control").val(phone);
        $("[data-test-id='agreement']").click();
        $(".button").click();
        $("[data-test-id='name'].input_invalid").shouldHave(Condition.text("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));

    }

    @Test
    void shouldGetErrorIfInvalidValuesFieldName2() {
        String planningDate = generateDate(3);

        String city = faker.address().city();
        String phone = faker.phoneNumber().phoneNumber();


        $("[data-test-id='city'] input").val(city);
        $("[class='menu-item__control']").click();
        $("[data-test-id=date] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id=date] input").sendKeys(planningDate);
        $("[data-test-id='name'] .input__control").val("Alina_Fomina");
        $("[data-test-id='phone'] .input__control").val(phone);
        $("[data-test-id='agreement']").click();
        $(".button").click();
        $("[data-test-id='name'].input_invalid").shouldHave(Condition.text("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));

    }

    @Test
    void shouldGetErrorIfFieldPhoneEmpty() {
        String planningDate = generateDate(3);

        String city = faker.address().city();
        String name = faker.name().fullName();

        $("[data-test-id='city'] input").val(city);
        $("[class='menu-item__control']").click();
        $("[data-test-id=date] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id=date] input").sendKeys(planningDate);
        $("[data-test-id='name'] .input__control").val(name);
        $("[data-test-id='phone'] .input__control").val("");
        $("[data-test-id='agreement']").click();
        $(".button").click();
        $("[data-test-id='phone'].input_invalid").shouldHave(Condition.text("Поле обязательно для заполнения"));

    }

    @Test
    void shouldGetErrorIfUncheckedCheckbox() {
        String planningDate = generateDate(3);

        String city = faker.address().city();
        String name = faker.name().fullName();
        String phone = faker.phoneNumber().phoneNumber();

        $("[data-test-id='city'] input").val(city);
        $("[class='menu-item__control']").click();
        $("[data-test-id=date] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id=date] input").sendKeys(planningDate);
        $("[data-test-id='name'] .input__control").val(name);
        $("[data-test-id='phone'] .input__control").val(phone);
        $(".button").click();
        $("[data-test-id='agreement'].input_invalid").shouldHave(visible);

    }


}
