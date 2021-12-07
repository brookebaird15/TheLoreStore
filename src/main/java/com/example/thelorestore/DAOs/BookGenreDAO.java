package com.example.thelorestore.DAOs;

import com.example.thelorestore.Pojo.Book;
import com.example.thelorestore.Pojo.Genre;

import java.util.ArrayList;

public interface BookGenreDAO {
    public void createBookGenreRelation(Book book, Genre genre);
    public ArrayList<Book> getAllBooksForGenre(int genreId);
    public ArrayList<Genre> getAllGenresForBook(int bookId);
    public void removeGenreRelation(Book book);
}
