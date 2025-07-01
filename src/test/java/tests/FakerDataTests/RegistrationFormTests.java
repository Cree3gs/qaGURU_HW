package tests.FakerDataTests;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.RegistrationPage;
import tests.TestBase;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import static tests.FakerDataTests.TestData.*;

@Tag("demoqa")
public class RegistrationFormTests extends TestBase {


    RegistrationPage registrationPage = new RegistrationPage();

    @DisplayName("Успешная отправка формы регистрации со всеми заполненными полями")
    @Test
    public void successfulRegistrationFullFormTest() {
        registrationPage
                .openPage()
                .closeBanners()
                .setFirstName(firstname)
                .setLastName(lastName)
                .setEmail(email)
                .setGender(gender)
                .setPhoneNumber(phoneNumber)
                .setDateOfBirth(dayOfBirth, monthOfBirth, yearOfBirth)
                .setSubjects(subjects)
                .setHobbies(hobbies)
                .uploadProfilePhoto(photo)
                .setAddress(address)
                .setState(state)
                .setCity(city)
                .clickSubmit()
                .checkResult("Label", "Values")
                .checkResult("Student Name", firstname + " " + lastName)
                .checkResult("Student Email", email)
                .checkResult("Gender", gender)
                .checkResult("Mobile", phoneNumber)
                .checkResult("Date of Birth", dayOfBirth + " " + monthOfBirth + "," + yearOfBirth)
                .checkResult("Subjects", subjects)
                .checkResult("Hobbies", hobbies)
                .checkResult("Picture", photo)
                .checkResult("Address", address)
                .checkResult("State and City", state + " " + city);
    }

    @DisplayName("Успешная отправка формы регистрации с заполненными обязательными полями")
    @Test
    public void successfulRegistrationShortFormTest() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMMM,yyyy", new Locale("en"));
        String formattedDate = LocalDate.now().format(formatter);

        registrationPage
                .openPage()
                .closeBanners()
                .setFirstName(firstname)
                .setLastName(lastName)
                .setGender(gender)
                .setPhoneNumber(phoneNumber)
                .clickSubmit()
                .checkResult("Label", "Values")
                .checkResult("Student Name", firstname + " " + lastName)
                .checkResultEmptyField("Student Email")
                .checkResult("Gender", gender)
                .checkResult("Mobile", phoneNumber)
                .checkResult("Date of Birth", formattedDate)
                .checkResultEmptyField("Subjects")
                .checkResultEmptyField("Hobbies")
                .checkResultEmptyField("Picture")
                .checkResultEmptyField("Address")
                .checkResultEmptyField("State and City");
    }

    @DisplayName("Попытка отправить форму с некорректным номером телефона")
    @Test
    public void registrationFormInvalidPhoneNumberTest() {
        registrationPage
                .openPage()
                .closeBanners()
                .setFirstName(firstname)
                .setLastName(lastName)
                .setEmail(email)
                .setGender(gender)
                .setPhoneNumber("900000000")
                .clickSubmit()
                .checkUnsubmitedForm();
    }

    @DisplayName("Попытка отправить форму с без выбранного пола")
    @Test
    public void registrationFormGenderNotSelectedTest() {
        registrationPage
                .openPage()
                .closeBanners()
                .setFirstName(firstname)
                .setLastName(lastName)
                .setEmail(email)
                .setPhoneNumber(phoneNumber)
                .clickSubmit()
                .checkUnsubmitedForm();
    }

    @DisplayName("Попытка отправить форму с без выбранного пола")
    @Test
    public void registrationFormInvalidEmailTest() {
        registrationPage
                .openPage()
                .closeBanners()
                .setFirstName(firstname)
                .setLastName(lastName)
                .setEmail("782476r82736823")
                .setGender(gender)
                .setPhoneNumber(phoneNumber)
                .clickSubmit()
                .checkUnsubmitedForm();
    }

    @DisplayName("Попытка отправить форму без заполнения имени")
    @Test
    public void registrationFormMissFirsNameTest() {
        registrationPage
                .openPage()
                .closeBanners()
                .setLastName(lastName)
                .setEmail(email)
                .setGender(gender)
                .setPhoneNumber(phoneNumber)
                .clickSubmit()
                .checkUnsubmitedForm();
    }

    @DisplayName("Попытка отправить форму без заполнения фамилии")
    @Test
    public void registrationFormMissLastNameTest() {
        registrationPage
                .openPage()
                .closeBanners()
                .setFirstName(firstname)
                .setEmail(email)
                .setGender(gender)
                .setPhoneNumber(phoneNumber)
                .clickSubmit()
                .checkUnsubmitedForm();
    }
}
