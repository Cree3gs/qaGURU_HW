package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import pages.components.CalendarComponent;
import pages.components.ResultTable;
import pages.components.UploadPicture;

import static com.codeborne.selenide.Condition.empty;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class RegistrationPage {

    private final SelenideElement
            firstNameInput = $("#firstName"),
            lastNameInput = $("#lastName"),
            emailInput = $("#userEmail"),
            genderInput = $("#genterWrapper"),
            phoneNumberInput = $("#userNumber"),
            dateOfBirthInput = $("#dateOfBirthInput"),
            subjectsInput = $("#subjectsInput"),
            selectHobbies = $("#hobbiesWrapper"),
            addressInput = $("#currentAddress"),
            stateInput = $("#react-select-3-input"),
            cityInput = $("#react-select-4-input"),
            submitButton = $(".text-right");

    CalendarComponent calendarComponent = new CalendarComponent();
    ResultTable resultTable = new ResultTable();
    UploadPicture uploadPicture = new UploadPicture();

    @Step("Открыть automation-practice-form страницу")
    public RegistrationPage openPage() {
        open("/automation-practice-form");
        return this;
    }

    @Step("Закрыть баннеры")
    public RegistrationPage closeBanners() {
        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('footer').remove()");
        return this;
    }

    @Step("Ввести имя")
    public RegistrationPage setFirstName(String value) {
        firstNameInput.setValue(value);
        return this;
    }

    @Step("Ввести фамилию")
    public RegistrationPage setLastName(String value) {
        lastNameInput.setValue(value);
        return this;
    }

    @Step("Ввести адрес электронной почты")
    public RegistrationPage setEmail(String value) {
        emailInput.setValue(value);
        return this;
    }

    @Step("Выбрать пол")
    public RegistrationPage setGender(String value) {
        genderInput.$(byText(value)).click();
        return this;
    }

    @Step("Ввести номер телефона")
    public RegistrationPage setPhoneNumber(String value) {
        phoneNumberInput.setValue(value);
        return this;
    }

    @Step("Выбрать дату рождения")
    public RegistrationPage setDateOfBirth(String day, String month, String year) {
        dateOfBirthInput.click();
        calendarComponent.setDate(day, month, year);
        return this;
    }

    @Step("Выбрать предметы")
    public RegistrationPage setSubjects(String value) {
        subjectsInput.setValue(value).pressEnter();
        return this;
    }

    @Step("Выбрать хобби")
    public RegistrationPage setHobbies(String value) {
        selectHobbies.$(byText(value)).click();
        return this;
    }

    @Step("Загрузить фото")
    public RegistrationPage uploadProfilePhoto(String classpath) {
        uploadPicture.uploadPicture(classpath);
        return this;
    }

    @Step("Ввести адрес")
    public RegistrationPage setAddress(String value) {
        addressInput.setValue(value);
        return this;
    }

    @Step("Выбрать штат")
    public RegistrationPage setState(String value) {
        $("#state").click();
        stateInput.setValue(value).pressEnter();
        return this;
    }

    @Step("Выбрать город")
    public RegistrationPage setCity(String value) {
        $("#city").click();
        cityInput.setValue(value).pressEnter();
        return this;
    }

    @Step("Нажать кнопку отправки формы")
    public RegistrationPage clickSubmit() {
        submitButton.click();
        return this;
    }

    @Step("Проверка заполнения формы")
    public RegistrationPage checkResult(String key, String value) {
        resultTable.checkResult(key, value);
        return this;
    }

    @Step("Проверка полей, которые должны оставаться пустыми")
    public RegistrationPage checkResultEmptyField(String key) {
        $("table").$(byText(key)).sibling(0).shouldBe(empty);
        return this;
    }

    @Step("Проверка ошибки отправки формы (поле формы не заполнено)")
    public void checkUnsubmitedForm() {
        $("#app").shouldNotHave(text("Thanks for submitting the form"));
    }
}
