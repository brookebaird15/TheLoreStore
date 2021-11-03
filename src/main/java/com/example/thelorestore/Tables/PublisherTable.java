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
     * getAllPublisher returns all publishers in Publisher table with associated ID and name
     * @return
     */

    @Override
    public ArrayList<Publisher> getAllPublishers() {
        String query = "SELECT * FROM " + DBTableValues.PUBLISHER_ID_COLUMN;
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
     * @param publisherID
     * @return
     */
    @Override
    public Publisher getPublisher(int publisherID) {
        String query = "SELECT * FROM " + DBTableValues.PUBLISHER_ID_COLUMN + " WHERE "
                + DBTableValues.PUBLISHER_ID_COLUMN + " = "
                + publisherID;
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
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
