package com.example.thelorestore.DAOs;

import com.example.thelorestore.Pojo.Author;
import com.example.thelorestore.Pojo.Book;

import java.util.ArrayList;

public interface BookAuthorDAO {
    public void createBookAuthorRelation(Book book, Author author);
    public ArrayList<Book> getAllBooksForAuthor(int authorId);
    public ArrayList<Author> getAllAuthorsForBook(int bookId);
//    public void updateBookAuthorRelation(Book book, Author author);
    public void removeAuthorRelation(Book book);
}
