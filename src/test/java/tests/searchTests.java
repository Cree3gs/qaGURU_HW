package tests;

import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class searchTests {
    @Test
    void duckDuckGoSearchTest3() {
        open("https://duckduckgo.com/");
        $(".searchbox_input__rnFzM").setValue("selenide").pressEnter();
        $(".Wo6ZAEmESLNUuWBkbMxx").shouldHave(text("https://selenide.org"));
    }

    @Test
    void rumblerSearchTest4() {
        open("https://www.rambler.ru/");
        $(".rc__86etrn").setValue("selenide").pressEnter();
        $(".SmartCaptcha__title--yQxdI").shouldHave(text("Вы не робот?"));
    }

}
