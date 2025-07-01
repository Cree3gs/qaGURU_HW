package pages.components;

import static com.codeborne.selenide.Selenide.$;

public class UploadPicture {
    public void uploadPicture(String classpath) {
        $("#uploadPicture").uploadFromClasspath(classpath);
    }
}
