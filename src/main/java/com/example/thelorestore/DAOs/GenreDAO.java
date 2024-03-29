package com.example.thelorestore.DAOs;

import com.example.thelorestore.Pojo.Genre;

import java.util.ArrayList;

public interface GenreDAO {
    public void createGenre(Genre genre);
    public ArrayList<Genre> getAllGenres();
    public void updateGenre(Genre genre);
}
