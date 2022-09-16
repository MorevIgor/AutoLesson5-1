package ru.netology.delivery.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import ru.netology.delivery.data.DataGenerator;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

class DeliveryTest {


    @BeforeEach
    void setup() {
        open("http://localhost:9999");
    }

    @Test
    @DisplayName("Should successful plan and replan meeting")
    void shouldSuccessfulPlanAndReplanMeeting() {
        var validUser = DataGenerator.Registration.generateUser("ru");
        var daysToAddForFirstMeeting = 4;
        var firstMeetingDate = DataGenerator.generateDate(daysToAddForFirstMeeting);
        var daysToAddForSecondMeeting = 7;
        var secondMeetingDate = DataGenerator.generateDate(daysToAddForSecondMeeting);
        $x("//*[@data-test-id=\"city\"]//self::input").doubleClick().sendKeys(Keys.DELETE);
        $x("//*[@data-test-id=\"city\"]//self::input").setValue(validUser.getCity());
        $x("//*[@data-test-id=\"date\"]//self::input").doubleClick().sendKeys(Keys.DELETE, firstMeetingDate);
        $x("//*[@data-test-id=\"name\"]//self::input").doubleClick().sendKeys(Keys.DELETE);
        $x("//*[@data-test-id=\"name\"]//self::input").setValue(validUser.getName());
        $x("//*[@data-test-id=\"phone\"]//self::input").doubleClick().sendKeys(Keys.DELETE);
        $x("//*[@data-test-id=\"phone\"]//self::input").setValue(validUser.getPhone());
        $x("//*[@class=\"button__text\"]").click();
        String invalidCity = $x("//*[@data-test-id=\"city\"]//child::span[@class=\"input__sub\"]").getText();
        String invalidC = "Доставка в выбранный город недоступна";
        if (invalidCity.equals(invalidC)) {              //перегенерация данных при не валид городе
            shouldSuccessfulPlanAndReplanMeeting();
        }
        String invalidName = $x("//*[@data-test-id=\"name\"]//child::span[@class=\"input__sub\"]").getText();
        String invalidN = "Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.";
        if (invalidName.equals(invalidN)) {              //перегенерация данных при не валид Имени, хотя пропускает проверку на "ё"
            shouldSuccessfulPlanAndReplanMeeting();
        }
        String invalidPhone = $x("//*[@data-test-id=\"phone\"]//child::span[@class=\"input__sub\"]").getText();
        String invalidP = "Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.";
        if (invalidPhone.equals(invalidP)) {            // перегенерация кода при не валид номере телефона
            shouldSuccessfulPlanAndReplanMeeting();
        }
        $x("//*[@data-test-id=\"agreement\"]").click();
        $x("//*[@class=\"button__text\"]").click();
        $x("//*[@data-test-id=\"date\"]//self::input").doubleClick().sendKeys(Keys.DELETE, secondMeetingDate);
        $x("//*[@class=\"button__text\"]").click();
        $x("//*[@data-test-id=\"replan-notification\"]").click();
        String text = $x("//*[@data-test-id=\"success-notification\"]").should(visible, Duration.ofSeconds(15)).getText();
        assertEquals("Успешно!\n" + "Встреча успешно запланирована на " + secondMeetingDate, text);

    }
}
