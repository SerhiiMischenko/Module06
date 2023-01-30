package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseQueryService {
    public static void main(String[] args) {
        List<LongestProject> longestProjectList = new DatabaseQueryService().findLongestProject();
        System.out.println(longestProjectList);
        List<MaxProjectsClient> maxProjectsClientList = new DatabaseQueryService().findMaxProjectsClient();
        System.out.println(maxProjectsClientList);
        List<MaxSalaryWorker> maxSalaryWorkerList = new DatabaseQueryService().findMaxSalaryWorker();
        System.out.println(maxSalaryWorkerList);
        List<YoungestEldestWorkers> youngestEldestWorkersList = new DatabaseQueryService().findYoungestEldestWorkers();
        System.out.println(youngestEldestWorkersList);
        List<Project_prices> projectPricesList = new DatabaseQueryService().printProject_prices();
        System.out.println(projectPricesList);
    }

    public List<LongestProject> findLongestProject() {
        ResultSet resultSet;
        List<LongestProject> list = new ArrayList<>();

        Database database = Database.getInstance();
        Connection conn = database.getConnection();
        try (PreparedStatement preparedStatement = conn.prepareStatement(database.sqlResponseReader("sql/find_longest_project.sql"))) {
            resultSet = preparedStatement.executeQuery(database.sqlResponseReader("sql/find_longest_project.sql"));
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int month_count = resultSet.getInt("month_count");
                list.add(new LongestProject(id, month_count));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public List<MaxProjectsClient> findMaxProjectsClient() {
        ResultSet resultSet;
        List<MaxProjectsClient> list = new ArrayList<>();
        Database database = Database.getInstance();
        Connection conn = database.getConnection();
        try (PreparedStatement preparedStatement = conn.prepareStatement("sql/find_max_projects_client.sql")) {
            resultSet = preparedStatement.executeQuery(database.sqlResponseReader("sql/find_max_projects_client.sql"));
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                int month_count = resultSet.getInt("project_count");
                list.add(new MaxProjectsClient(name, month_count));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public List<MaxSalaryWorker> findMaxSalaryWorker() {
        ResultSet resultSet;
        List<MaxSalaryWorker> list = new ArrayList<>();
        Database database = Database.getInstance();
        Connection conn = database.getConnection();
        try (PreparedStatement preparedStatement = conn.prepareStatement("sql/find_max_salary_worker.sql")) {
            resultSet = preparedStatement.executeQuery(database.sqlResponseReader("sql/find_max_salary_worker.sql"));
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                int salary = resultSet.getInt("salary");
                list.add(new MaxSalaryWorker(name, salary));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public List<YoungestEldestWorkers> findYoungestEldestWorkers() {
        ResultSet resultSet;
        List<YoungestEldestWorkers> list = new ArrayList<>();
        Database database = Database.getInstance();
        Connection conn = database.getConnection();
        try (PreparedStatement preparedStatement = conn.prepareStatement("sql/find_youngest_eldest_workers.sql")) {
            resultSet = preparedStatement.executeQuery(database.sqlResponseReader("sql/find_youngest_eldest_workers.sql"));
            while (resultSet.next()) {
                String type = resultSet.getString("TYPE");
                String name = resultSet.getString("NAME");
                String birthDay = resultSet.getString("BIRTHDAY");
                list.add(new YoungestEldestWorkers(type, name, birthDay));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public List<Project_prices> printProject_prices() {
        ResultSet resultSet;
        List<Project_prices> list = new ArrayList<>();
        Database database = Database.getInstance();
        Connection conn = database.getConnection();
        try (PreparedStatement preparedStatement = conn.prepareStatement("sql/print_project_prices.sql")) {
            resultSet = preparedStatement.executeQuery(database.sqlResponseReader("sql/print_project_prices.sql"));
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int price = resultSet.getInt("PRICE");
                list.add(new Project_prices(id, price));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }
}
