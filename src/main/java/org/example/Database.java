package org.example;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class Database {
    private static Database instance;

    private Database() {

    }

    public static Database getInstance() {
        if(instance == null) {
            instance = new Database();
        }
        return instance;
    }
    public Connection getConnection() {
        File file = new File("/Users/serhiimischenko/IdeaProjects/Module04/src/main/resources/" +
                "properties.properties");
        Properties properties = new Properties();
        Connection conn = null;
        try {
            properties.load(new FileReader(file));
            String dbUrl = properties.getProperty("dbUrl");
            String dbUser = properties.getProperty("dbUser");
            String dbPass = properties.getProperty("dbPass");
            try{
                conn = DriverManager.getConnection(dbUrl, dbUser, dbPass);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return conn;
    }

    public String sqlResponseReader(String sqlResponse) {
        StringBuilder response = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(sqlResponse))){
            while (bufferedReader.ready()) {
                response.append(bufferedReader.readLine());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return response.toString();
    }
}