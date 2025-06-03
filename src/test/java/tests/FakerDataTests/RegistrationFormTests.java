package tests.FakerDataTests;

import org.junit.jupiter.api.Test;
import pages.RegistrationPage;
import tests.TestBase;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import static tests.FakerDataTests.TestData.*;

public class RegistrationFormTests extends TestBase {

    RegistrationPage registrationPage = new RegistrationPage();

    @Test
    public void successfulRegistrationFullFormTest() {
        registrationPage
                .openPage()
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
                .checkResult("Label","Values")
                .checkResult("Student Name", firstname + " " + lastName)
                .checkResult("Student Email",email)
                .checkResult("Gender",gender)
                .checkResult("Mobile",phoneNumber)
                .checkResult("Date of Birth", dayOfBirth+" "+monthOfBirth+","+yearOfBirth)
                .checkResult("Subjects",subjects)
                .checkResult("Hobbies",hobbies)
                .checkResult("Picture",photo)
                .checkResult("Address",address)
                .checkResult("State and City", state + " " + city);
    }

    @Test
    public void successfulRegistrationShortFormTest() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMMM,yyyy", new Locale("en"));
        String formattedDate = LocalDate.now().format(formatter);

        registrationPage
                .openPage()
                .setFirstName(firstname)
                .setLastName(lastName)
                .setGender(gender)
                .setPhoneNumber(phoneNumber)
                .clickSubmit()
                .checkResult("Label","Values")
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

    @Test
    public void registrationFormInvalidPhoneNumberTest() {
        registrationPage
                .openPage()
                .setFirstName(firstname)
                .setLastName(lastName)
                .setEmail(email)
                .setGender(gender)
                .setPhoneNumber("900000000")
                .clickSubmit()
                .checkUnsubmitedForm();
    }

    @Test
    public void registrationFormGenderNotSelectedTest() {
        registrationPage
                .openPage()
                .setFirstName(firstname)
                .setLastName(lastName)
                .setEmail(email)
                .setPhoneNumber(phoneNumber)
                .clickSubmit()
                .checkUnsubmitedForm();
    }

    @Test
    public void registrationFormInvalidEmailTest() {
        registrationPage
                .openPage()
                .setFirstName(firstname)
                .setLastName(lastName)
                .setEmail("782476r82736823")
                .setGender(gender)
                .setPhoneNumber(phoneNumber)
                .clickSubmit()
                .checkUnsubmitedForm();
    }

    @Test
    public void registrationFormMissFirsNameTest() {
        registrationPage
                .openPage()
                .setLastName(lastName)
                .setEmail(email)
                .setGender(gender)
                .setPhoneNumber(phoneNumber)
                .clickSubmit()
                .checkUnsubmitedForm();
    }

    @Test
    public void registrationFormMissLastNameTest() {
        registrationPage
                .openPage()
                .setFirstName(firstname)
                .setEmail(email)
                .setGender(gender)
                .setPhoneNumber(phoneNumber)
                .clickSubmit()
                .checkUnsubmitedForm();
    }
}
