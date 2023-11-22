package in.reqres;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;

public class ListResourcesTests extends TestBase {

    @Test
    void listResourcesTest() {

        given()
                .log().uri()
                .log().method()
                .log().body()
                .contentType(JSON)
                .when()
                .get("/unknown")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("page", is(1))
                .body("per_page", is(6))
                .body("total", is(12))
                .body("total_pages", is(2))
                .body("data", hasSize(6));
    }

    @Test
    void listSingleResourceTest() {

        given()
                .log().uri()
                .log().method()
                .log().body()
                .contentType(JSON)
                .when()
                .get("/unknown/2")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("data.id", is(2))
                .body("data.name", is("fuchsia rose"))
                .body("data.year", is(2001))
                .body("data.color", is("#C74375"))
                .body("data.pantone_value", is("17-2031"));
    }

    @Test
    void singleResourceNotFoundTest() {
        given()
                .log().uri()
                .log().method()
                .log().body()
                .contentType(JSON)
                .when()
                .get("/unknown/23")
                .then()
                .log().status()
                .log().body()
                .statusCode(404)
                .body("isEmpty()", Matchers.is(true));
    }
}
