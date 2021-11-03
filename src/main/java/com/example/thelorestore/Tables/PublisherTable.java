package com.example.thelorestore.Tables;

import com.example.thelorestore.DAOs.PublisherDAO;
import com.example.thelorestore.Database.DBTableValues;
import com.example.thelorestore.Pojo.Publisher;

import java.util.ArrayList;

public class PublisherTable implements PublisherDAO {


    @Override
    public ArrayList<Publisher> getAllPublishers() {
        String query = "SELECT * FROM " + DBTableValues.PUBLISHER_ID_COLUMN;
    }

    @Override
    public Publisher getPublisher(int publisherID) {
        return null;
    }
}
