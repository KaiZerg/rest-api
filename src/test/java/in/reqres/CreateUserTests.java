package in.reqres;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.is;

public class CreateUserTests extends TestBase{

    @Test
    void successfulUserCreationTest() {
        String userData = "{ \"name\": \"morpheus\", \"job\": \"leader\" }"; // BAD PRACTICE

        given()
                .log().uri()
                .log().method()
                .log().body()
                .contentType(JSON)
                .body(userData)
                .when()
                .post("/users")
                .then()
                .log().status()
                .log().body()
                .statusCode(201)
                .body("name", is("morpheus"))
                .body("job", is("leader"));
    }
}
