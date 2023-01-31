package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientService {
    public static void main(String[] args) {
        ClientService clientService = new ClientService();
        System.out.println(clientService.create("Alex Mikulin"));
        System.out.println(clientService.getById(101));
        clientService.setName(104, "Yakov Kovalyov");
        clientService.deleteById(104);
        System.out.println(clientService.listAll());
    }

    public long create(String name) {
        long id = -1;
        ResultSet resultSet;
        Database database = Database.getInstance();
        if(name.length() > 100 || name.isBlank() ||name.isEmpty()){
            try {
                throw new Exception();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        try (Connection conn = database.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO `mydb`.`client` (`name`) VALUES (?)")) {
            preparedStatement.setString(1, name);
            preparedStatement.execute();
            resultSet = preparedStatement.executeQuery("SELECT id FROM mydb.client where name = " + "'" + name + "'");
            while (resultSet.next()) {
                id = resultSet.getLong("id");
            }
        } catch (SQLException e) {
            System.out.println("Wrong data input");
        }
        return id;
    }

    public String getById(long id) {
        String name = "";
        ResultSet resultSet;
        if(id < 0) {
            try {
                throw new Exception();
            } catch (Exception e) {
                System.out.println("Wrong data input");
            }
        }
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

    public void setName(long id, String name) {
        if(id < 0) {
            try {
                throw new Exception();
            } catch (Exception e) {
                System.out.println("Wrong data input");
            }
        }
        if(name.length() > 100 || name.isBlank() ||name.isEmpty()){
            try {
                throw new Exception();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        Database database = Database.getInstance();
        try (Connection conn = database.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(
                     "UPDATE `mydb`.`client` SET `name` = ? WHERE (`id` = ?)")) {
            preparedStatement.setString(1, name);
            preparedStatement.setLong(2, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

   public void deleteById(long id) {
       if(id < 0) {
           try {
               throw new Exception();
           } catch (Exception e) {
               System.out.println("Wrong data input");
           }
       }
       Database database = Database.getInstance();
       try (Connection conn = database.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(
                    "DELETE FROM `mydb`.`client` WHERE (`id` = ?)")) {
           preparedStatement.setLong(1, id);
           preparedStatement.execute();
       } catch (SQLException e) {
           throw new RuntimeException(e);
       }
   }

    public List<Client> listAll() {
        List<Client> clientList = new ArrayList<>();
        ResultSet resultSet;
        Database database = Database.getInstance();
        try (Connection conn = database.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM mydb.client")) {
            preparedStatement.execute();
            resultSet = preparedStatement.executeQuery("SELECT * FROM mydb.client");
            while (resultSet.next()) {
                clientList.add(new Client(resultSet.getLong("id"),
                        resultSet.getString("name")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return clientList;
    }
}
