package tests;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.CollectionCondition.itemWithText;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.open;

public class githubSelenideTest {

    @Test
    @Disabled
    void shouldFindSelenideRepositoryAtTheTopTest() {

        // открыть главную страницу
        open("https://github.com/");
        // тап оп поисковой строке
        $(".input-button").click();
        // ввести в поле поиска селенид
        $("#query-builder-test").setValue("selenide").pressEnter();
        // клик на первый из списка
        $$(".Box-sc-g0xbh4-0").first().$("a").click();
        // проверка: заголовок selenide/selenide
        // исправить
        $("#repository-container-header").shouldHave(text("selenide / selenide"));

    }

    @Test
    void andreiSolntsevShouldBeTheFirstContributorTest() {
        // открыть страницу селенид
        open("https://github.com/selenide/selenide");
        // подвести мышку к первому аватару
        $(".Layout-sidebar").$(byText("Contributors"))
                               .closest(".BorderGrid-cell").$$("ul li").first().hover();
                               //.closest("h2").sibling(0).$$("li").first().hover();
        // проверка текста во всплывающем окне
        $(".Popover-message").shouldHave(text("Andrei Solntsev"));
    }

    @Test
    void findJUnit5CodeForSoftAssertionsInWikiTest() {
        //Откройте страницу Selenide в Github
        open("https://github.com/selenide/selenide");
        //Перейдите в раздел Wiki проекта
        $("#wiki-tab").click();
        //Убедитесь, что в списке страниц (Pages) есть страница SoftAssertions
        $(".markdown-body").shouldHave(text("Soft assertions"));
        //Откройте страницу SoftAssertions, проверьте что внутри есть пример кода для JUnit5
        $(".markdown-body ul").find("a[href='/selenide/selenide/wiki/SoftAssertions']").click();
        $$(".markdown-heading")
                .findBy(text("Using JUnit5"))
                .sibling(0)
                .$("pre").shouldHave(text(
        "@ExtendWith({SoftAssertsExtension.class}) class Tests { " +
                "@Test void test() { " +
                "Configuration.assertionMode = SOFT; " +
                "open(\"page.html\"); " +
                "$(\"#first\").should(visible).click(); " +
                "$(\"#second\").should(visible).click(); " +
                "} " +
                "}"));
    }
}
