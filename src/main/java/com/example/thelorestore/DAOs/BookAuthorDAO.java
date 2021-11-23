package com.example.thelorestore.DAOs;

import com.example.thelorestore.Pojo.Author;
import com.example.thelorestore.Pojo.Book;
import com.example.thelorestore.Pojo.BookAuthor;

import java.util.ArrayList;

public interface BookAuthorDAO {
    public void createBookAuthorRelation(Book book, Author author);
    public ArrayList<Book> getAllBooksForAuthor(BookAuthor bookAuthor);
    public ArrayList<Author> getAllAuthorsForBook(BookAuthor bookAuthor);
}
