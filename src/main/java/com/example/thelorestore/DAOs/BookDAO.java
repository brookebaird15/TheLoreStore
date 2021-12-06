package com.example.thelorestore.DAOs;

import com.example.thelorestore.Pojo.Author;
import com.example.thelorestore.Pojo.Book;
import com.example.thelorestore.Pojo.Genre;

import java.util.ArrayList;

public interface BookDAO {
    /**
     * Method for creating a new entry to the Book Table
     * @param book
     */
    public Book createBook(Book book);

    /**
     * Method for updating book information in Book Table
     * @param book
     */
    public Book updateBook(Book book);

    /**
     * Method for removing a book from the Book Table
     * @param book
     */
    public void deleteBook(Book book);
}
