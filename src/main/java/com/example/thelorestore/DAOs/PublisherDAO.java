package com.example.thelorestore.DAOs;

import com.example.thelorestore.Pojo.Publisher;

import java.util.ArrayList;

public interface PublisherDAO {
    public void createPublisher(Publisher publisher);
    public ArrayList<Publisher> getAllPublishers();
    public Publisher getPublisher (int publisherID);
    public void updatePublisher(Publisher publisher);
}
