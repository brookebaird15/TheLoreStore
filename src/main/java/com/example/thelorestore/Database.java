package com.example.thelorestore;
import java.sql.*;

public class Database {
    private static Database instance;
    private Connection connection = null;

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

    public static Database getInstance() {
        if(instance == null) {
            instance = new Database();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }

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
