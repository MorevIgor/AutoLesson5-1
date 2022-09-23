package ru.netology.delivery.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import ru.netology.delivery.data.DataGenerator;

import java.time.Duration;

import static com.codeborne.selenide.Condition.exactText;
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
        $x("//*[@data-test-id=\"city\"]//self::input").sendKeys(Keys.LEFT_CONTROL + "A" + Keys.DELETE);
        $x("//*[@data-test-id=\"city\"]//self::input").setValue(DataGenerator.generateCity());
        $x("//*[@data-test-id=\"date\"]//self::input").doubleClick().sendKeys(Keys.DELETE, firstMeetingDate);
        $x("//*[@data-test-id=\"name\"]//self::input").sendKeys(Keys.LEFT_CONTROL + "A" + Keys.DELETE);
        $x("//*[@data-test-id=\"name\"]//self::input").setValue(validUser.getName());
        $x("//*[@data-test-id=\"phone\"]//self::input").sendKeys(Keys.LEFT_CONTROL + "A" + Keys.DELETE);
        $x("//*[@data-test-id=\"phone\"]//self::input").setValue(validUser.getPhone());
        $x("//*[@data-test-id=\"agreement\"]").click();
        $x("//*[@class=\"button__text\"]").click();
        $x("//*[@data-test-id=\"success-notification\"]").shouldHave(exactText("Успешно!\n" + "Встреча успешно запланирована на " + firstMeetingDate));
        $x("//*[@data-test-id=\"date\"]//self::input").doubleClick().sendKeys(Keys.DELETE, secondMeetingDate);
        $x("//*[@class=\"button__text\"]").click();
        $x("//*[@data-test-id=\"replan-notification\"]").click();
        $x("//*[@data-test-id=\"success-notification\"]").shouldHave(exactText("Успешно!\n" + "Встреча успешно запланирована на " + secondMeetingDate));
    }
}
