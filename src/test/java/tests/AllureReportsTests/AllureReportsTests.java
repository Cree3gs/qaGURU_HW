package tests.AllureReportsTests;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;
import static org.openqa.selenium.By.linkText;

public class AllureReportsTests {

    private static final String REPOSITORY = "Cree3gs/qaGURU_HW";
    private static final String ISSUE_NAME = "HW-10(TEST)";

    @Test
    @DisplayName("Построение Allure отчета с помощью Listener для теста - Поиск Issue в проекте")
    public void issueSearchTestWithListener() {
        SelenideLogger.addListener("allure", new AllureSelenide());

        open("https://github.com/");
        $(".search-input").click();
        $("#query-builder-test").setValue(REPOSITORY).pressEnter();
        $(linkText(REPOSITORY)).click();
        $("#issues-tab").click();
        $(".IssueRow-module__row--XmR1f").shouldHave(text(ISSUE_NAME));
    }

    @Test
    @DisplayName("Построение Allure отчета с помощью Lambda steps для теста - Поиск Issue в проекте")
    public void issueSearchTestWithLambda() {
        step("Открыть главную страницу", () ->
        open("https://github.com/"));

        step("С помощью поисковой строки найти репозиторий" + REPOSITORY, () -> {
        $(".search-input").click();
        $("#query-builder-test").setValue(REPOSITORY).pressEnter();
        });

        step("Открыть репозиторий и перейти в Issues", () -> {
                 $(linkText(REPOSITORY)).click();
                 $("#issues-tab").click();
             });

        step("Проверить наличие HW-10(TEST) в списке", () ->
        $(".IssueRow-module__row--XmR1f").shouldHave(text(ISSUE_NAME)));
    }

    @Test
    @DisplayName("Построение Allure отчета с помощью Annotated steps для теста - Поиск Issue в проекте")
    public void issueSearchTestWithAnnotatedSteps() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        WebSteps steps = new WebSteps();
        steps.openMainPage()
             .searchRepository(REPOSITORY)
             .clickOnRepository(REPOSITORY)
             .checkIssue(ISSUE_NAME);
    }
}
