package tests;

import org.junit.jupiter.api.Test;
import pages.RegistrationPage;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class RegistrationWithPageObjectsTests extends TestBase {

    RegistrationPage registrationPage = new RegistrationPage();

    String name = "Владимир";
    String lastName = "Солнышко";
    String email = "killer123@ya.ru";
    String userSex = "Male";
    String userNumber = "9000000000";

    @Test
    public void successfulRegistrationFullFormTest() {
        registrationPage
                .openPage()
                .setFirsName(name)
                .setLastName(lastName)
                .setEmail(email)
                .setGender(userSex)
                .setPhoneNumber(userNumber)
                .setDateOfBirth("31", "December", "1990")
                .setSubjects("ph")
                .setSubjects("mat")
                .setSubjects("ec")
                .setHobbies("Reading")
                .setHobbies("Music")
                .uploadProfilePhoto("photo.jpg")
                .setAddress("Платформа 9 и 3/4")
                .setState("Rajasthan")
                .setCity("Jaipur")
                .clickSubmit()
                .checkResult("Label","Values")
                .checkResult("Student Name",name + " " + lastName)
                .checkResult("Student Email",email)
                .checkResult("Gender",userSex)
                .checkResult("Mobile",userNumber)
                .checkResult("Date of Birth","31 December,1990")
                .checkResult("Subjects","Physics, Maths, Economics")
                .checkResult("Hobbies","Reading, Music")
                .checkResult("Picture","photo.jpg")
                .checkResult("Address","Платформа 9 и 3/4")
                .checkResult("State and City","Rajasthan Jaipur");
    }

    @Test
    public void successfulRegistrationShortFormTest() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMMM,yyyy", new Locale("en"));
        String formattedDate = LocalDate.now().format(formatter);

        registrationPage
                .openPage()
                .setFirsName(name)
                .setLastName(lastName)
                .setGender(userSex)
                .setPhoneNumber(userNumber)
                .clickSubmit()
                .checkResult("Label","Values")
                .checkResult("Student Name",name + " " + lastName)
                .checkResultEmptyField("Student Email")
                .checkResult("Gender",userSex)
                .checkResult("Mobile",userNumber)
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
                .setFirsName(name)
                .setLastName(lastName)
                .setEmail(email)
                .setGender(userSex)
                .setPhoneNumber("900000000")
                .clickSubmit()
                .checkUnsubmitedForm();
    }

    @Test
    public void registrationFormGenderNotSelectedTest() {
        registrationPage
                .openPage()
                .setFirsName(name)
                .setLastName(lastName)
                .setEmail(email)
                .setPhoneNumber(userNumber)
                .clickSubmit()
                .checkUnsubmitedForm();
    }

    @Test
    public void registrationFormInvalidEmailTest() {
        registrationPage
                .openPage()
                .setFirsName(name)
                .setLastName(lastName)
                .setEmail("782476r82736823")
                .setGender(userSex)
                .setPhoneNumber(userNumber)
                .clickSubmit()
                .checkUnsubmitedForm();
    }

    @Test
    public void registrationFormMissFirsNameTest() {
        registrationPage
                .openPage()
                .setLastName(lastName)
                .setEmail(email)
                .setGender(userSex)
                .setPhoneNumber(userNumber)
                .clickSubmit()
                .checkUnsubmitedForm();
    }

    @Test
    public void registrationFormMissLastNameTest() {
        registrationPage
                .openPage()
                .setFirsName(name)
                .setEmail(email)
                .setGender(userSex)
                .setPhoneNumber(userNumber)
                .clickSubmit()
                .checkUnsubmitedForm();
    }
}
