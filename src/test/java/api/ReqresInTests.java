package api;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.matchesRegex;
import static org.hamcrest.Matchers.notNullValue;



@Tag("api")
public class ReqresInTests {

    @BeforeAll
    static void setup() {
        RestAssured.baseURI = "https://reqres.in";
        RestAssured.basePath = "/api";
    }

    @Test
    void getListUsersTest (){
        given()
                .log().all()
                .header("x-api-key", "reqres-free-v1")
                .queryParam("page", "2")
                .get("/users")
                .then()
                .log().all()
                .statusCode(200)
                .assertThat().body(matchesJsonSchemaInClasspath("GetListUsers.json"));
    }

    @Test
    void getSingleUserTest (){
        given()
                .log().all()
                .header("x-api-key", "reqres-free-v1")
                .get("/users/2")
                .then()
                .log().all()
                .statusCode(200)
                .body("data.id", equalTo(2));
    }

    @Test
    void getListTest (){
        given()
                .log().all()
                .header("x-api-key", "reqres-free-v1")
                .get("/unknown")
                .then()
                .log().all()
                .statusCode(200)
                .body("data.year", contains(2000, 2001, 2002, 2003, 2004, 2005));
    }

    @Test
    void postCreateTest (){
        String jsonData = "{\"name\": \"morpheus\", \"job\": \"leader\"}";
        given()
                .body(jsonData)
                .contentType(JSON)
                .log().all()
                .when()
                .header("x-api-key", "reqres-free-v1")
                .post("/users")
                .then()
                .log().all()
                .statusCode(201)
                .body("name", equalTo("morpheus"))
                .body("job", equalTo("leader"))
                .body("id", notNullValue())
                .body("createdAt", matchesRegex("\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}.\\d{3}Z"));
    }

    @Test
    void patchUpdateTest (){
        String jsonData = "{\"name\": \"morpheus\", \"job\": \"zion resident\"}";
        given()
                .body(jsonData)
                .contentType(JSON)
                .log().all()
                .when()
                .header("x-api-key", "reqres-free-v1")
                .patch("/users/2")
                .then()
                .log().all()
                .statusCode(200)
                .body("name", equalTo("morpheus"))
                .body("job", equalTo("zion resident"))
                .body("updatedAt", matchesRegex("\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}.\\d{3}Z"));
    }

}
