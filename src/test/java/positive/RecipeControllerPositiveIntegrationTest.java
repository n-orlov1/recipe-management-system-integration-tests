package positive;

import entity.Recipe;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class RecipeControllerPositiveIntegrationTest {

    private static final String URL = "http://localhost:8080";

    @Test
    public void getRecipeById() {
        Recipe recipe = given()
                .auth()
                .basic("coookkkk@somewhere.com","$recipe_in_binary")
                .when()
                .contentType(ContentType.JSON)
                .get(URL + "/api/recipe/1")
                .then()
                .extract()
                .body()
                .as(Recipe.class);

        assertEquals("Fresh Mint Tea",recipe.getName());
        assertEquals("beverage", recipe.getCategory());
        assertEquals(LocalDateTime.parse("2024-04-30T22:34:58"), recipe.getDate());
    }

}
