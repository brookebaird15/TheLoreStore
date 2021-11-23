package com.example.thelorestore.DAOs;

import com.example.thelorestore.Pojo.Book;
import com.example.thelorestore.Pojo.Genre;

import java.util.ArrayList;

public interface BookDAO {
    /**
     * Method for creating a new entry to the Book Table
     * @param book
     */
    public void createBook(Book book, Genre genre);

    /**
     * Method for accessing all books in Book Table
     */
    public ArrayList<Book> getAllBooks();

    /**
     * Method for accessing one book in Book Table
     * @param isbn
     */
    public Book getBook(int isbn);

    /**
     * Method for updating book information in Book Table
     * @param book
     */
    public void updateBook(Book book);

    /**
     * Method for removing a book from the Book Table
     * @param book
     */
    public void deleteBook(Book book);
}
