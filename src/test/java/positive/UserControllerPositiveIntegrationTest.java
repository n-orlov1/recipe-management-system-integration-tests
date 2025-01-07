package positive;

import entity.User;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.DatabaseUtils;

import static io.restassured.RestAssured.given;

public class UserControllerPositiveIntegrationTest {

    private static final String URL = "http://localhost:8080";

    @Test
    public void Should_RegisterNewUser_WhenEmailAndPasswordIsCorrect() {

        User user = new User();
        user.setEmail("test@test.com");
        user.setPassword("superstrongpassword");

        given().
                contentType(ContentType.JSON).
                body(user).
        when().
                post(URL + "/api/register").
        then().
                assertThat().
                statusCode(200);

    }

    @Test
    public void Should_RegisterNewUser_WhenEmailContainsRussianLettersAndPasswordIsCorrect() {

        User newUser = new User();
        newUser.setEmail("тестовый_пользователь@маил.ру");
        newUser.setPassword("superstrongpassword");

        given()
                .contentType(ContentType.JSON)
                .body(newUser).
        when()
                .post(URL + "/api/register").

        then()
                .statusCode(200);
    }

    @BeforeEach
    public void setUp() {
        DatabaseUtils.deleteTestUser();
    }
}
