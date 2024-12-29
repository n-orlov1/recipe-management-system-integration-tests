package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseUtils {

    public static void deleteTestUser() {
        try(Connection pgConnection = DriverManager
                .getConnection("jdbc:postgresql://localhost:5432/recipe_dev","recipe_dev_user","recipe_dev_user")) {

            Statement statement = pgConnection.createStatement();
            statement.execute("DELETE from public.\"user\" where email = 'test@test.com' " +
                    "or email = 'тестовый_пользователь@маил.ру'");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
