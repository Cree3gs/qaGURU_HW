package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class TextBoxPage {

    private final SelenideElement
            userNameInput = $("#userName"),
            userEmailInput = $("#userEmail"),
            currentAddressInput = $("#currentAddress"),
            permanentAddressInput = $("#permanentAddress"),
            submitButton = $("#submit"),
    userNameOutput = $("#output #name"),
  userEmailOutput = $("#output #email"),
            currentAddressOutput = $("#output #currentAddress"),
            permanentAddressOutput = $("#output #permanentAddress");

    public TextBoxPage openPage() {
        open("/text-box");
        return this;
    }

    public TextBoxPage setUserName (String value) {
        userNameInput.setValue(value);
        return this;
    }

    public TextBoxPage setUserEmail (String value) {
        userEmailInput.setValue(value);
        return this;
    }

    public TextBoxPage setCurrentAddress (String value) {
        currentAddressInput.setValue(value);
        return this;
    }

    public TextBoxPage setPermanentAddress (String value) {
        permanentAddressInput.setValue(value);
        return this;
    }

    public TextBoxPage clickSubmit () {
        submitButton.click();
        return this;
    }

    public TextBoxPage checkUserName (String value) {
        userNameOutput.shouldHave(text(value));
        return this;
    }

    public TextBoxPage checkUserEmail (String value) {
        userEmailOutput.shouldHave(text(value));
        return this;
    }

    public TextBoxPage checkCurrentAddress (String value) {
     currentAddressOutput.shouldHave(text(value));
        return this;
    }

    public TextBoxPage checkPermanentAddress (String value) {
     permanentAddressOutput.shouldHave(text(value));
        return this;
    }
}
