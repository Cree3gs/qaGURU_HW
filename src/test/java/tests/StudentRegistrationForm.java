package tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.open;

public class StudentRegistrationForm {

    @BeforeAll
    static void beforeAll() {
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.pageLoadStrategy = "eager";
    }

    @Test
    public void studentRegistrationFormTest() {
        open("/automation-practice-form");

        $("#firstName").setValue("Владимир");
        $("#lastName").setValue("Солнышко");
        $("#userEmail").setValue("killer123@ya.ru");
        $("#genterWrapper").$(byText("Male")).click();
        $("#userNumber").setValue("9000000000");

        //дата рождения
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOption("December");
        $(".react-datepicker__year-select").selectOption("1990");
        $(".react-datepicker__day--031").click();

        //Subjects
        $("#subjectsInput").setValue("ph").pressEnter();
        $("#subjectsInput").setValue("mat").pressEnter();
        $("#subjectsInput").setValue("ec").pressEnter();

        //Хобби
        $("#hobbiesWrapper").$(byText("Reading")).click();
        $("#hobbiesWrapper").$(byText("Music")).click();

        //Загрузка картинки
        $("#uploadPicture").uploadFromClasspath("photo.jpg");

        //Адрес
        $("#currentAddress").setValue("Платформа 9 и 3/4");
        $("#state").click();
        $("#react-select-3-input").setValue("Rajasthan").pressEnter();
        $("#city").click();
        $("#react-select-4-input").setValue("Jaipur").pressEnter();

        //Отправка анкеты
        $(".text-right").click();

        //Проверки
        $$("table").findBy(text("Label")).shouldHave(text("Values"));
        $$("table").findBy(text("Student Name")).shouldHave(text("Владимир Солнышко"));
        $$("table").findBy(text("Student Email")).shouldHave(text("killer123@ya.ru"));
        $$("table").findBy(text("Gender")).shouldHave(text("Male"));
        $$("table").findBy(text("Mobile")).shouldHave(text("9000000000"));
        $$("table").findBy(text("Date of Birth")).shouldHave(text("31 December,1990"));
        $$("table").findBy(text("Subjects")).shouldHave(text("Physics, Maths, Economics"));
        $$("table").findBy(text("Hobbies")).shouldHave(text("Reading, Music"));
        $$("table").findBy(text("Picture")).shouldHave(text("photo.jpg"));
        $$("table").findBy(text("Address")).shouldHave(text("Платформа 9 и 3/4"));
        $$("table").findBy(text("State and City")).shouldHave(text("Rajasthan Jaipur"));

    }
}
