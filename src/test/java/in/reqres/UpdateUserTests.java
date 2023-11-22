package in.reqres;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.is;

public class UpdateUserTests extends TestBase{

    @Test
    void fullUpdateUserTest() {
        String userData = "{ \"name\": \"morpheus\", \"job\": \"leader\" }"; // BAD PRACTICE

        given()
                .log().uri()
                .log().method()
                .log().body()
                .contentType(JSON)
                .body(userData)
                .when()
                .put("/users/2")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("name", is("morpheus"))
                .body("job", is("leader"));
    }

    @Test
    void partialUpdateUserTest() {
        String userData = "{ \"name\": \"morpheus\", \"job\": \"leader\" }"; // BAD PRACTICE

        given()
                .log().uri()
                .log().method()
                .log().body()
                .contentType(JSON)
                .body(userData)
                .when()
                .patch("/users/2")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("name", is("morpheus"))
                .body("job", is("leader"));
    }
}
