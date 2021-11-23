package com.example.thelorestore.DAOs;

import com.example.thelorestore.Pojo.Author;
import com.example.thelorestore.Pojo.Book;

public interface BookAuthorDAO {
    public void createBookAuthorRelation(Book book, Author author);
}
