package ru.netology.delivery.data;

import com.github.javafaker.Faker;
import lombok.Value;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class DataGenerator {
    private DataGenerator() {
    }

    static Faker faker = new Faker(new Locale("ru_RU"));

    public static String cities() {
        List<String> cities = new ArrayList<>();
        cities.add("Москва");
        cities.add("Санкт-Петербург");
        cities.add("Майкоп");
        cities.add("Горно-Алтайск");
        cities.add("Уфа");
        cities.add("Улан-Удэ");
        cities.add("Махачкала");
        cities.add("Магас");
        cities.add("Нальчик");
        cities.add("Элиста");
        cities.add("Черкесск");
        cities.add("Петрозаводск");
        cities.add("Сыктывкар");
        cities.add("Симферополь");
        cities.add("Йошкар-Ола");
        cities.add("Саранск");
        cities.add("Якутск");
        cities.add("Владикавказ");
        cities.add("Казань");
        cities.add("Кызыл");
        cities.add("Ижевск");
        cities.add("Абакан");
        cities.add("Грозный");
        cities.add("Чебоксары");
        cities.add("Барнаул");
        cities.add("Чита");
        cities.add("Петропавловск-Камчатский");
        cities.add("Краснодар");
        cities.add("Пермь");
        cities.add("Владивосток");
        cities.add("Ставрополь");
        cities.add("Хабаровск");
        cities.add("Благовещенск");
        cities.add("Архангельск");
        cities.add("Астрахань");
        cities.add("Белгород");
        cities.add("Брянск");
        cities.add("Владимир");
        cities.add("Волгоград");
        cities.add("Вологда");
        cities.add("Воронеж");
        cities.add("Иваново");
        cities.add("Иркутск");
        cities.add("Калининград");
        cities.add("Калуга");
        cities.add("Кемерово");
        cities.add("Киров");
        cities.add("Кострома");
        cities.add("Курган");
        cities.add("Курск");
        cities.add("Гатчина");
        cities.add("Липецк");
        cities.add("Магадан");
        cities.add("Красногорск");
        cities.add("Мурманск");
        cities.add("Нижний Новгород");
        cities.add("Великий Новгород");
        cities.add("Новосибирск");
        cities.add("Омск");
        cities.add("Оренбург");
        cities.add("Орёл");
        cities.add("Пенза");
        cities.add("Псков");
        cities.add("Ростов-на-Дону");
        cities.add("Рязань");
        cities.add("Самара");
        cities.add("Саратов");
        cities.add("Южно-Сахалинск");
        cities.add("Екатеринбург");
        cities.add("Смоленск");
        cities.add("Тамбов");
        cities.add("Тверь");
        cities.add("Томск");
        cities.add("Тула");
        cities.add("Тюмень");
        cities.add("Ульяновск");
        cities.add("Челябинск");
        cities.add("Ярославль");
        cities.add("Севастополь");
        cities.add("Биробиджан");
        cities.add("Нарьян-Мар");
        cities.add("Ханты-Мансийск");
        cities.add("Анадырь");
        cities.add("Салехард");

        Random r = new Random();
        int x = r.nextInt(87) + 1;

        String citiesOut = cities.get(x);

        return citiesOut;
    }

    public static String generateDate(int day) {
        return LocalDate.now().plusDays(day).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    public static String generateCity() {
        return cities();
    }

    public static String generateName(String locale) {
        return faker.name().fullName();
    }

    public static String generatePhone(String locale) {
        return faker.phoneNumber().phoneNumber();
    }

    public static class Registration {
        private Registration() {
        }

        public static UserInfo generateUser(String locale) {
            return new UserInfo(generateName("ru_RU"), generatePhone("ru_RU"));
        }
    }

    @Value
    public static class UserInfo {
        String name;
        String phone;
    }
}



