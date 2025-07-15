package tests.ApiTest;

import io.restassured.RestAssured;
import models.lombok.createUser.RequestCreateUserBodyModel;
import models.lombok.createUser.ResponseCreateUserBodyModel;
import models.lombok.list.ResponseDataList;
import models.lombok.list.ResponseList;
import models.lombok.updateUser.RequestUpdateUserBodyModel;
import models.lombok.updateUser.ResponseUpdateUserBodyModel;
import models.lombok.user.UserResponse;
import models.lombok.users.UsersResponse;
import org.junit.jupiter.api.*;

import java.util.List;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static specs.BaseSpecs.baseRequestSpec;
import static specs.BaseSpecs.baseResponseSpec;


@Tag("api")
class ReqresInTests {

    @BeforeAll
    static void setup() {
        RestAssured.baseURI = "https://reqres.in";
        RestAssured.basePath = "/api";
    }

    @DisplayName("Проверка параметра total в GET запросе api/users")
    @Test
    void getListUsersTest() {
        UsersResponse response = step("Отправляем GET запрос", () ->
                given()
                        .spec(baseRequestSpec)
                        .queryParam("page", 2)
                        .when()
                        .get("/users")
                        .then()
                        .spec(baseResponseSpec(200))
                        .extract().as(UsersResponse.class)
        );

        step("Проверяем ответ", () -> assertThat(response.getTotal())
                .isEqualTo(12)
        );
    }

    @DisplayName("Проверка параметра id в GET запросе api/users/2")
    @Test
    void getSingleUserTest() {
        UserResponse response = step("Отправляем GET запрос", () ->
                given()
                        .spec(baseRequestSpec)
                        .get("/users/2")
                        .then()
                        .spec(baseResponseSpec(200))
                        .extract().as(UserResponse.class)
        );
        step("Проверяем ответ", () -> {
            int id = response.getData().getId();
            assertThat(id)
                    .isEqualTo(2);
        });
    }

    @DisplayName("Проверка параметра year в GET запросе api/unknown")
    @Test
    void getListTest() {
        ResponseList response = step("Отправляем GET запрос", () ->
                given()
                        .spec(baseRequestSpec)
                        .get("/unknown")
                        .then()
                        .spec(baseResponseSpec(200))
                        .extract().as(ResponseList.class)
        );


        step("Проверяем ответ", () -> {

            List<ResponseDataList> year = response.getData();
            assertThat(year.contains("2000"));
        });
    }

    @DisplayName("Проверка создания записи о пользователе POST запросом")
    @Test
    void postCreateTest() {
        RequestCreateUserBodyModel requestBody = step("Подготавливаем тестовые данные", () -> {
            RequestCreateUserBodyModel data = new RequestCreateUserBodyModel();
            data.setName("morpheus");
            data.setJob("leader");
            return data;
        });

        ResponseCreateUserBodyModel response = step("Отправляем POST запрос", () ->
                given()
                        .spec(baseRequestSpec)
                        .body(requestBody)
                        .when()
                        .post("/users")
                        .then()
                        .spec(baseResponseSpec(201))
                        .extract().as(ResponseCreateUserBodyModel.class)
        );

        step("Проверяем ответ", () -> assertAll(
                () -> assertEquals("morpheus", response.getName(), "Имя не совпадает"),
                () -> assertEquals("leader", response.getJob(), "Должность не совпадает"),
                () -> assertNotNull(response.getId(), "ID не должен быть null"),
                () -> assertNotNull(response.getCreatedAt(), "Дата создания не должена быть null"),
                () -> assertThat(response.getCreatedAt()).matches("^\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}\\.\\d{3}Z$")
        ));
    }

    @DisplayName("Проверка изменение записи о пользователе PATCH запросом")
    @Test
    void patchUpdateTest() {
        RequestUpdateUserBodyModel requestBody = step("Подготавливаем тестовые данные", () -> {
            RequestUpdateUserBodyModel data = new RequestUpdateUserBodyModel();
            data.setName("morpheus");
            data.setJob("zion resident");
            return data;
        });

        ResponseUpdateUserBodyModel response = step("Отправляем PATCH запрос", () ->
                given()
                        .spec(baseRequestSpec)
                        .body(requestBody)
                        .when()
                        .post("/users/2")
                        .then()
                        .spec(baseResponseSpec(201))
                        .extract().as(ResponseUpdateUserBodyModel.class)
        );

        step("Проверяем ответ", () -> assertAll(
                () -> assertEquals("morpheus", response.getName(), "Имя не совпадает"),
                () -> assertEquals("zion resident", response.getJob(), "Должность не совпадает"),
                () -> assertNotNull(response.getCreatedAt(), "Дата создания не должена быть null"),
                () -> assertThat(response.getCreatedAt()).matches("^\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}\\.\\d{3}Z$")
        ));
    }
}