package tests.AllureReportsTests;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.openqa.selenium.By.linkText;

public class WebSteps {

    @Step("Открыть главную страницу")
public WebSteps openMainPage() {
        open("https://github.com/");
        return this;
    }

    @Step("С помощью поисковой строки найти репозиторий {repo}")
    public WebSteps searchRepository(String repo) {
        $(".search-input").click();
        $("#query-builder-test").setValue(repo).pressEnter();
        return this;
    }

    @Step("Открыть репозиторий и перейти в Issues")
    public WebSteps clickOnRepository(String repo) {
        $(linkText(repo)).click();
        $("#issues-tab").click();
        return this;
    }

    @Step("Проверить наличие {issue} в списке")
    public WebSteps checkIssue(String issue) {
        $(".IssueRow-module__row--XmR1f").shouldHave(text(issue));
        return this;
    }
}
