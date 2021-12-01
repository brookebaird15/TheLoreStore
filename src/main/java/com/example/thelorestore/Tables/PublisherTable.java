package com.example.thelorestore.Tables;

import com.example.thelorestore.DAOs.PublisherDAO;
import com.example.thelorestore.Database.DBTableValues;
import com.example.thelorestore.Database.Database;
import com.example.thelorestore.Pojo.Publisher;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class PublisherTable implements PublisherDAO {
    Database db = Database.getInstance();
    ArrayList<Publisher> publishers;

    /**
     * createPublisher() adds a publisher to the Publisher table
     * @param publisher is the publisher being added
     */
    @Override
    public void createPublisher(Publisher publisher) {
        String query = "INSERT INTO " + DBTableValues.PUBLISHER_TABLE + "(" +
                DBTableValues.PUBLISHER_COMPANY_COLUMN + ") VALUES ('" +
                publisher.getCompanyName() + "')";
        try {
            db.getConnection().createStatement().execute(query);
            System.out.println("Publisher record inserted");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * getAllPublisher returns all publishers in Publisher table with associated ID and name
     * @return
     */

    @Override
    public ArrayList<Publisher> getAllPublishers() {
        String query = "SELECT * FROM " + DBTableValues.PUBLISHER_TABLE + " ORDER BY " + DBTableValues.PUBLISHER_COMPANY_COLUMN + " ASC";
        publishers = new ArrayList<>();

        //Database statement surrounded in try-catch, executing the query
        try {
            Statement getPublishers = db.getConnection().createStatement();
            ResultSet data = getPublishers.executeQuery(query);

            while(data.next()){
                publishers.add(new Publisher(
                        data.getInt(DBTableValues.PUBLISHER_ID_COLUMN),
                        data.getString(DBTableValues.PUBLISHER_COMPANY_COLUMN)
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return publishers;
    }

    /**
     * Publisher object returns the publisher at ID and returns null if no match
     * @param bookId
     * @return
     */
    @Override
    public Publisher getPublisher(int bookId) {
        String query = "SELECT * FROM " + DBTableValues.PUBLISHER_TABLE + " INNER JOIN " + DBTableValues.BOOK_TABLE
                + " ON " + DBTableValues.PUBLISHER_TABLE + "." + DBTableValues.PUBLISHER_ID_COLUMN
                + " = " + DBTableValues.BOOK_TABLE + "." + DBTableValues.BOOK_PUBLISHER_COLUMN
                + " WHERE " + DBTableValues.BOOK_TABLE + "." + DBTableValues.BOOK_ID_COLUMN + " = " + bookId;
        Statement getPublisher = null;

        //Statement being created and caught in try-catch
        try {
            getPublisher = db.getConnection().createStatement();
            ResultSet data = getPublisher.executeQuery(query);

            if (data.next()){
                Publisher publisher = new Publisher(
                        data.getInt(DBTableValues.PUBLISHER_ID_COLUMN),
                        data.getString(DBTableValues.PUBLISHER_COMPANY_COLUMN)
                );
                return publisher;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * updatePublisher() updates the publisher information to the values provided
     * @param publisher is the publisher being updated
     */
    @Override
    public void updatePublisher(Publisher publisher) {
        String query = "UPDATE " + DBTableValues.PUBLISHER_TABLE + " SET " +
                DBTableValues.PUBLISHER_COMPANY_COLUMN + " = '" + publisher.getCompanyName() +
                "' WHERE " + DBTableValues.PUBLISHER_ID_COLUMN + " = " + publisher.getId();
        try {
            Statement updatePublisher = db.getConnection().createStatement();
            updatePublisher.executeUpdate(query);
            System.out.println("Publisher updated");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
