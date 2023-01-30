package org.example;

import org.flywaydb.core.Flyway;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class InitDb {

    public void initDbMethod () {
        File file = new File("/Users/serhiimischenko/IdeaProjects/Module04/src/main/resources/" +
                "properties.properties");
        Properties properties = new Properties();
        try {
            properties.load(new FileReader(file));
            String dbUrl = properties.getProperty("dbUrl");
            String dbUser = properties.getProperty("dbUser");
            String dbPass = properties.getProperty("dbPass");

            Flyway flyway = Flyway
                    .configure()
                    .dataSource(dbUrl, dbUser, dbPass)
                    .load();
            flyway.migrate();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
        public static void main(String[] args) {
        InitDb initDb = new InitDb();
        initDb.initDbMethod();
    }
}
