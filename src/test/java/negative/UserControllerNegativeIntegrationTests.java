package negative;

import entity.User;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class UserControllerNegativeIntegrationTests {

    private static final String URL = "http://localhost:8080";

    @Test
    public void Should_FailToRegisterNewUser_WhenEmailIsNotCorrect() {

        User newUser = new User();
        newUser.setEmail("wrongemailaddress.com");
        newUser.setPassword("superstrongpassword");

        given()
                .contentType(ContentType.JSON)
                .body(newUser).
        when()
                .post(URL + "/api/register").
        then()
                .statusCode(400);
    }

    @Test
    public void Should_FailToRegisterNewUser_WhenPasswordIsBlank() {

        User newUser = new User();
        newUser.setEmail("testuser@test.com");
        newUser.setPassword("");

        given()
                .contentType(ContentType.JSON)
                .body(newUser).
        when()
                .post(URL + "/api/register").
        then()
                .statusCode(400);

    }

    @Test
    public void Should_FailToRegisterNewUser_WhenPasswordLengthIsLessThanEightCharacters() {

        User newUser = new User();
        newUser.setEmail("testuser@test.com");
        newUser.setPassword("testpas");

        given()
                .contentType(ContentType.JSON)
                .body(newUser).
        when()
                .post(URL + "/api/register").
        then()
                .statusCode(400);

    }

    @Test
    public void Should_FailToRegisterNewUser_WhenEmailIsNotCorrectAndStartsWithDot() {

        User newUser = new User();
        newUser.setEmail(".testuser@test.com");
        newUser.setPassword("superstrongpassword");

        given()
                .contentType(ContentType.JSON)
                .body(newUser).
        when()
                .post(URL + "/api/register").
        then()
                .statusCode(400);

    }
}
