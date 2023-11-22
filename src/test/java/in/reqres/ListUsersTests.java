package in.reqres;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;

public class ListUsersTests extends TestBase {

    @Test
    void listUsersTest() {

        given()
                .log().uri()
                .log().method()
                .log().body()
                .contentType(JSON)
                .when()
                .get("/users?page=2")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("page", is(2)).
                body("total", is(12)).
                body("data", hasSize(6));
    }

    @Test
    void listSingleUserTest() {

        given()
                .log().uri()
                .log().method()
                .log().body()
                .contentType(JSON)
                .when()
                .get("/users/2")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("data.id", is(2))
                .body("data.email", is("janet.weaver@reqres.in"))
                .body("data.first_name", is("Janet"))
                .body("data.last_name", is("Weaver"));
    }

    @Test
    void singleUserNotFoundTest() {
        given()
                .log().uri()
                .log().method()
                .log().body()
                .contentType(JSON)
                .when()
                .get("/users/23")
                .then()
                .log().status()
                .log().body()
                .statusCode(404)
                .body("isEmpty()", Matchers.is(true));
    }
}
