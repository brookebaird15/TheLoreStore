package com.example.thelorestore.Database;
import com.example.thelorestore.Panes.LoginPane;
import org.w3c.dom.ls.LSOutput;

import java.sql.*;

/**
 * Database class follows the Singleton Pattern
 */

public class Database {
    private static Database instance;
    private Connection connection = null;

    /**
     * If the connection is null, create a new database connection
     * Executes createTable for each table (Book, Author, Genre, Publisher)
     */
    private Database() {
        if(connection == null) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection("jdbc:mysql://localhost/" + DBConst.DB_NAME + "?serverTimezone=UTC", DBConst.DB_USER, DBConst.DB_PASS);
                System.out.println("Connection created");
                createTable(DBTableValues.AUTHOR_TABLE, DBTableValues.CREATE_AUTHOR_TABLE, connection);
                createTable(DBTableValues.GENRE_TABLE, DBTableValues.CREATE_GENRE_TABLE, connection);
                createTable(DBTableValues.PUBLISHER_TABLE, DBTableValues.CREATE_PUBLISHER_TABLE, connection);
                createTable(DBTableValues.STATUS_TABLE, DBTableValues.CREATE_STATUS_TABLE, connection);
                createTable(DBTableValues.BOOK_TABLE, DBTableValues.CREATE_BOOK_TABLE, connection);
                createTable(DBTableValues.BOOK_GENRE_TABLE, DBTableValues.CREATE_BOOK_GENRE_TABLE, connection);
                createTable(DBTableValues.BOOK_AUTHOR_TABLE, DBTableValues.CREATE_BOOK_AUTHOR_TABLE, connection);
                createTable(DBTableValues.BOOK_VIEW, DBTableValues.CREATE_BOOK_VIEW, connection);
                insertStatusItems(DBTableValues.INSERT_INTO_STATUS, connection);
            } catch (Exception e) {
                //display login error
                LoginPane.loginError.setVisible(true);
                //reset fields
                LoginPane.pwTextField.setText("");
                LoginPane.userTextField.setText("");
                LoginPane.dbTextField.setText("");
                try {
                    //end request to allow user another chance to input
                    connection.endRequest();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
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

    /**
     * createTable() adds tables to database
     * @param tableName
     * @param tableQuery
     * @param connection
     * @throws SQLException
     */
    private void createTable(String tableName, String tableQuery, Connection connection) throws SQLException {
        Statement createTable;
        DatabaseMetaData md = connection.getMetaData();
        ResultSet resultSet = md.getTables(DBConst.DB_NAME, null, tableName, null);
        if(resultSet.next()) {
            System.out.println(tableName + " table already exists");
        } else {
            createTable = connection.createStatement();
            createTable.execute(tableQuery);
            System.out.println("The " + tableName + " table has been inserted");
        }
    }

    private void insertStatusItems(String tableQuery, Connection connection) throws SQLException {
        String query = "SELECT * FROM " + DBTableValues.STATUS_TABLE;
        Statement checkItems = connection.createStatement();
        ResultSet data = checkItems.executeQuery(query);
        if(data.next()) {
            System.out.println("Status table already populated");
        } else {
            Statement insertItems = connection.createStatement();
            insertItems.executeUpdate(tableQuery);
            System.out.println("Status table items inserted");
        }
    }
}
