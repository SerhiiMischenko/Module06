package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabasePopulateService {

    public static void main(String[] args) {
        Database database = Database.getInstance();
        String[] splitedResponse = database.sqlResponseReader("sql/populate_db.sql").split(";");
        Connection conn = database.getConnection();
        for (String st : splitedResponse) {
            try (PreparedStatement preparedStatement = conn.prepareStatement(st)) {
                preparedStatement.execute(st);

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
