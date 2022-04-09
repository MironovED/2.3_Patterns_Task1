package ru.netology;

import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Locale;
import java.util.Random;


public class DataGenerator {
    private DataGenerator() {
    }

    public static class Registration {
        private Registration() {
        }

        public static RegistrationByCardInfo generateByCard(String locale) {
            Faker faker = new Faker(new Locale(locale));
            return new RegistrationByCardInfo(
                    faker.name().fullName(),
                    faker.phoneNumber().phoneNumber(),
                    getRandomElement()
            );
        }

    }

    public static String getRandomElement() {
        var random = new Random();
        var list = Arrays.asList("Ростов-на-Дону", "Москва", "Санкт-Петербург", "Тверь", "Новосибирск", "Владивосток", "Пермь", "Барнаул", "Казань", "Хабаровск", "Воронеж", "Краснодар");
        String randomCity = list.get(random.nextInt(list.size()));
        return randomCity;
    }

    public static String generateDate(int Days) {
        return LocalDate.now().plusDays(Days).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }


    public static String getRandomInvalidName() {
        var random = new Random();
        var list = Arrays.asList("+79008007060", "Alina_Fomina", "Olga", "Cветлана Виноградова", ";.,:%№!", "Анна Фоми6а");
        String randomCity = list.get(random.nextInt(list.size()));
        return randomCity;
    }

    public static String getRandomInvalidCity() {
        var random = new Random();
        var list = Arrays.asList("Сеул", "Tver", "Ростов на Дону", "Берлин", ";.,:%№!", "+79008007060");
        String randomCity = list.get(random.nextInt(list.size()));
        return randomCity;
    }

}
