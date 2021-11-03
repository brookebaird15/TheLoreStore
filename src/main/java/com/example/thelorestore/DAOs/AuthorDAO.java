package com.example.thelorestore.DAOs;

import com.example.thelorestore.Pojo.Author;

import java.util.ArrayList;

public interface AuthorDAO {
    /**
     * Methods for accessing Author Table
     */
    public ArrayList<Author> getAllAuthors();
    public Author getAuthor(int id);
}
