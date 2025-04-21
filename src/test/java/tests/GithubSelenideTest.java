package tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.DragAndDropOptions.to;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.actions;
import static com.codeborne.selenide.Selenide.open;

public class GithubSelenideTest {

    @BeforeAll
    static void setUp() {
        Configuration.browserSize = "1920x1080";
        Configuration.pageLoadStrategy = "eager";
        Configuration.browser = "chrome";
    }

    @Test
    @Disabled
    void shouldFindSelenideRepositoryAtTheTopTest() {
        open("https://github.com/");
        $(".input-button").click();
        $("#query-builder-test").setValue("selenide").pressEnter();
        $$(".Box-sc-g0xbh4-0").first().$("a").click();
        $("#repository-container-header").shouldHave(text("selenide / selenide"));//исправить

    }

    @Test
    void andreiSolntsevShouldBeTheFirstContributorTest() {
        open("https://github.com/selenide/selenide");
        $(".Layout-sidebar").$(byText("Contributors"))
                               .closest(".BorderGrid-cell").$$("ul li").first().hover();
        $(".Popover-message").shouldHave(text("Andrei Solntsev"));
    }

    @Test
    void findJUnit5CodeForSoftAssertionsInWikiTest() {
        open("https://github.com/selenide/selenide");
        $("#wiki-tab").click();
        $(".markdown-body").shouldHave(text("Soft assertions"));
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

    @Test
    void openEnterprizePageUsingTheHoverCommandTest () {
        open("https://github.com/");
        $$(".HeaderMenu-link").findBy(text ("Solutions")).hover();
        $("a[href='https://github.com/enterprise']").click();
        $("#hero-section-brand-heading").shouldHave(text("The AI-powered developer platform"));
    }

    @Test
    void dragAndDropSelenideActionTest () {
        open("https://the-internet.herokuapp.com/drag_and_drop");
        actions().moveToElement($("#column-a"))
                 .clickAndHold()
                 .moveToElement($("#column-b"))
                 .release()
                 .perform();
        $("#column-b header").shouldHave(text("A"));
        }

        @Test
        void dragAndDropTest() {
            open("https://the-internet.herokuapp.com/drag_and_drop");
            $("#column-b").dragAndDrop(to($("#column-a")));
            $("#column-a header").shouldHave(text("B"));
        }
}
