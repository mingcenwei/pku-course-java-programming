package pku.course.java;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.DriverManager;

public class sample {

    public static final String serverUrl = "localhost:1433";
    public static final String databaseName = "DatabaseCourse";
    public static final String username = "SA";
    public static final String password = "<YourStrong!Passw0rd>";

    public static void main(String[] args) {

        //Update the username and password below
        final String connectionUrl = "jdbc:sqlserver://" + serverUrl +
            ";databaseName=" + databaseName + ";user=" + username +
            ";password=" + password;

        try {
            // Load SQL Server JDBC driver and establish connection.
            try (Connection connection = DriverManager.getConnection(connectionUrl)) {
                System.out.println("Done.");

                // Create a Table and insert some sample data
                String sql = new StringBuilder().append("USE SampleDB; ").append("CREATE TABLE Employees ( ")
                        .append(" Id INT IDENTITY(1,1) NOT NULL PRIMARY KEY, ").append(" Name NVARCHAR(50), ")
                        .append(" Location NVARCHAR(50) ").append("); ")
                        .append("INSERT INTO Employees (Name, Location) VALUES ").append("(N'Jared', N'Australia'), ")
                        .append("(N'Nikita', N'India'), ").append("(N'Tom', N'Germany'); ").toString();
                try (Statement statement = connection.createStatement()) {
                    statement.executeUpdate(sql);
                    System.out.println("Done.");
                }

                // INSERT demo
                sql = new StringBuilder().append("INSERT Employees (Name, Location) ").append("VALUES (?, ?);")
                        .toString();
                try (PreparedStatement statement = connection.prepareStatement(sql)) {
                    statement.setString(1, "Jake");
                    statement.setString(2, "United States");
                    int rowsAffected = statement.executeUpdate();
                    System.out.println(rowsAffected + " row(s) inserted");
                }

                // UPDATE demo
                String userToUpdate = "Nikita";
                System.out.print("Updating 'Location' for user '" + userToUpdate + "', press ENTER to continue...");
                System.in.read();
                sql = "UPDATE Employees SET Location = N'United States' WHERE Name = ?";
                try (PreparedStatement statement = connection.prepareStatement(sql)) {
                    statement.setString(1, userToUpdate);
                    int rowsAffected = statement.executeUpdate();
                    System.out.println(rowsAffected + " row(s) updated");
                }

                // DELETE demo
                String userToDelete = "Jared";
                sql = "DELETE FROM Employees WHERE Name = ?;";
                try (PreparedStatement statement = connection.prepareStatement(sql)) {
                    statement.setString(1, userToDelete);
                    int rowsAffected = statement.executeUpdate();
                    System.out.println(rowsAffected + " row(s) deleted");
                }

                // READ demo
                sql = "SELECT Id, Name, Location FROM Employees;";
                try (Statement statement = connection.createStatement();
                        ResultSet resultSet = statement.executeQuery(sql)) {
                    while (resultSet.next()) {
                        System.out.println(
                                resultSet.getInt(1) + " " + resultSet.getString(2) + " " + resultSet.getString(3));
                    }
                }
                connection.close();
            }
        }
        catch (Exception e)
        {
            System.out.println();
            e.printStackTrace();
        }
    }
}