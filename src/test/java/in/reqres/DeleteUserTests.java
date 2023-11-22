package in.reqres;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class DeleteUserTests extends TestBase{

    @Test
    void successfulDeleteUserTest() {

        given()
                .log().uri()
                .log().method()
                .when()
                .delete("/users/2")
                .then()
                .log().status()
                .statusCode(204);
    }
}
