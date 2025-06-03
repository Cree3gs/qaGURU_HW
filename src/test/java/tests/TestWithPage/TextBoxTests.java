package tests.TestWithPage;

import org.junit.jupiter.api.Test;
import pages.TextBoxPage;
import tests.TestBase;

public class TextBoxTests extends TestBase {

    TextBoxPage textBoxPage = new TextBoxPage();
    String fullName = "Диана Арбенина";
    String email = "Rembo123@yahoo.com";
    String currentAddress = "Амстердам, бондюэль, бонпари, сильвупле";
    String permanentAddress = "г. Москва, Битцевский лес, 3я- береза справа, прикоп";

    @Test
    public void fillFormTest(){
        textBoxPage.openPage()
                .setUserName(fullName)
                .setUserEmail(email)
                .setCurrentAddress(currentAddress)
                .setPermanentAddress(permanentAddress)
                .clickSubmit()
                .checkUserName(fullName)
                .checkUserEmail(email)
                .checkCurrentAddress(currentAddress)
                .checkPermanentAddress(permanentAddress);
    }
}
