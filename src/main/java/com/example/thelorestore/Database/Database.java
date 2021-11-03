package com.example.thelorestore.Database;
import java.sql.*;

/**
 * Database class follows the Singleton Pattern
 */

public class Database {
    private static Database instance;
    private Connection connection = null;

    /**
     * If the connection is null, create a new database connection
     */
    private Database() {
        if(connection == null) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection("jdbc:mysql://localhost/" + DBConst.DB_NAME + "?serverTimezone=UTC", DBConst.DB_USER, DBConst.DB_PASS);
                System.out.println("Connection created");
                //TODO - create tables
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * getInstance() checks if the database connection is already active
     */
    public static Database getInstance() {
        if(instance == null) {
            instance = new Database();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }

    /**
     * close() closes the database connection
     */
    public void close() {
        System.out.println("Closing connection");
        try {
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //TODO - add create table method
}
