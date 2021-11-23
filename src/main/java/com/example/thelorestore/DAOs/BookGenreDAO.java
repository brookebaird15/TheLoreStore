package com.example.thelorestore.DAOs;

import com.example.thelorestore.Pojo.Book;
import com.example.thelorestore.Pojo.Genre;

public interface BookGenreDAO {
    public void createBookGenreRelation(Book book, Genre genre);
}
