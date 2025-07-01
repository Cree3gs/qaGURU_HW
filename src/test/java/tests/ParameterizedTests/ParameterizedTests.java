package tests.ParameterizedTests;

import data.SortTabs;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class ParameterizedTests {


    @ValueSource(strings = {
            "Джойстик",
            "Телевизор",
            "Настольная игра"
    })
    @ParameterizedTest(name = "Проверка работы поисковой строки. Поиск товара {0}")
    void selenideСheckingTheSearchWorks(String searchQuery) {
        open("https://market.yandex.ru/");
        $("#header-search").setValue(searchQuery).pressEnter();
        $("._38ab0").shouldHave(text(searchQuery));
    }


    @EnumSource(SortTabs.class)
    @ParameterizedTest(name = "Проверка работы фильтра. Поиск товара {0}")
    void selenideSortTabsShouldBeVisible(SortTabs sortTab) {
        open("https://market.yandex.ru/catalog--odezhda-obuv-i-aksessuary/54432/");
        $$("._2Ios3").findBy(text(sortTab.title)).shouldBe(visible);
    }

    @MethodSource()
    @ParameterizedTest(name = "Каталог {0} должен содержать ссылку {1}")
    void selenideProductCategoryShouldHaveLink(String productCategoryName, String categoryLink) {
        open("https://market.yandex.ru/");
        $(".button-focus-ring").click();
        $("._2GpS3").$(byText(productCategoryName)).parent().shouldHave(href(categoryLink));
    }


    static Stream<Arguments> selenideProductCategoryShouldHaveLink() {
        return Stream.of(
                Arguments.of("Одежда и обувь", "https://market.yandex.ru/special/fashion_dep"),
                Arguments.of("Детские товары", "https://market.yandex.ru/special/kids_dep"),
                Arguments.of("Бытовая техника", "https://market.yandex.ru/special/technic_dep"),
                Arguments.of("Строительство и ремонт", "https://market.yandex.ru/special/remont"),
                Arguments.of("Товары ИКЕА", "https://market.yandex.ru/catalog--tovary-kak-u-ikea/38679690")
        );
    }
}