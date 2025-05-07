package pages;

import com.codeborne.selenide.SelenideElement;
import pages.components.CalendarComponent;
import pages.components.ResultTable;
import pages.components.UploadPicture;

import static com.codeborne.selenide.Condition.empty;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

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

    public RegistrationPage openPage() {
        open("/automation-practice-form");
        return this;
    }

    public RegistrationPage setFirstName (String value) {
        firstNameInput.setValue(value);
        return this;
    }

    public RegistrationPage setLastName (String value) {
        lastNameInput.setValue(value);
        return this;
    }

    public RegistrationPage setEmail (String value) {
        emailInput.setValue(value);
        return this;
    }

    public RegistrationPage setGender (String value) {
        genderInput.$(byText(value)).click();
        return this;
    }

    public RegistrationPage setPhoneNumber (String value) {
        phoneNumberInput.setValue(value);
        return this;
    }

    public RegistrationPage setDateOfBirth (String day, String month, String year) {
        dateOfBirthInput.click();
        calendarComponent.setDate(day, month, year);
        return this;
    }

    public RegistrationPage setSubjects (String value) {
        subjectsInput.setValue(value).pressEnter();
        return this;
    }

    public RegistrationPage setHobbies (String value) {
        selectHobbies.$(byText(value)).click();
        return this;
    }

    public RegistrationPage uploadProfilePhoto (String classpath) {
        uploadPicture.uploadPicture(classpath);
        return this;
    }

    public RegistrationPage setAddress (String value) {
        addressInput.setValue(value);
        return this;
    }

    public RegistrationPage setState (String value) {
        $("#state").click();
        stateInput.setValue(value).pressEnter();
        return this;
    }

    public RegistrationPage setCity (String value) {
        $("#city").click();
        cityInput.setValue(value).pressEnter();
        return this;
    }

    public RegistrationPage clickSubmit () {
        submitButton.click();
        return this;
    }

    public RegistrationPage checkResult(String key, String value) {
        resultTable.checkResult(key, value);
        return this;
    }

    public RegistrationPage checkResultEmptyField (String key) {
        $("table").$(byText(key)).sibling(0).shouldBe(empty);
        return this;
    }

    public void checkUnsubmitedForm() {
        $("#app").shouldNotHave(text("Thanks for submitting the form"));
    }
}
