package com.raywenderlich.android.runtracker;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseManager {

    // For Local PostgreSQL
    private final String host = "10.0.2.2";

    private final String database = "postgres";
    private final int port = 5431;
    private final String user = "postgres";
    private final String pass = "disha543@";
    private String url = "jdbc:postgresql://localost:5431/postgres";
Connection connection;

    {
        try {
            connection = DriverManager.getConnection(url,user,pass);
            System.out.println("Connection to PostgreSQL server");

        } catch (SQLException e) {
            System.out.println("Error in connecting to PostgreSQL server");
            e.printStackTrace();
        }
    }

}
