package org.example;

import java.sql.*;

public class ClientService {
    public static void main(String[] args) {
        ClientService clientService = new ClientService();
        //System.out.println(clientService.create("Alex Mikulin"));
        //System.out.println(clientService.getById(101));
        clientService.setName(104, "Serhii Mikulin");
    }

    public long create(String name) {
        long id = -1;
        ResultSet resultSet;
        Database database = Database.getInstance();
        try (Connection conn = database.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO `mydb`.`client` (`name`) VALUES (?)")) {
            preparedStatement.setString(1, name);
            preparedStatement.execute();
            resultSet = preparedStatement.executeQuery("SELECT id FROM mydb.client where name = " + "'" + name + "'");
            while (resultSet.next()) {
                id = resultSet.getLong("id");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return id;
    }

    public String getById(long id) {
        String name = "";
        ResultSet resultSet;
        Database database = Database.getInstance();
        try (Connection conn = database.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM mydb.client where id = ?")) {
            preparedStatement.setLong(1, id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                name = resultSet.getString("name");
                name += " " + resultSet.getString("id");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return name;
    }
}
