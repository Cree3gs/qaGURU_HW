package pages.components;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$$;

public class ResultTable {
    public void checkResult(String key, String value) {
        $$("table").findBy(text(key)).shouldHave(text(value));
    }
}
