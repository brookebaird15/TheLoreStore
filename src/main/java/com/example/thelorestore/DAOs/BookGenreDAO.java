package com.example.thelorestore.DAOs;

import com.example.thelorestore.Pojo.Book;
import com.example.thelorestore.Pojo.BookGenre;
import com.example.thelorestore.Pojo.Genre;

import java.util.ArrayList;

public interface BookGenreDAO {
    public void createBookGenreRelation(Book book, Genre genre);
    public ArrayList<Book> getAllBooksForGenre(BookGenre bookGenre);
    public ArrayList<Genre> getAllGenresForBook(BookGenre bookGenre);
}
