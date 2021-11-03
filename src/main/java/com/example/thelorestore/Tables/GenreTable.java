package com.example.thelorestore.Tables;

import com.example.thelorestore.DAOs.GenreDAO;
import com.example.thelorestore.Database.DBTableValues;
import com.example.thelorestore.Database.Database;
import com.example.thelorestore.Pojo.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class GenreTable implements GenreDAO {
    Database db = Database.getInstance();
    ArrayList<Genre> genres;

    /**
     * Method to create the Genre Table is executing an SQL query and creating table
     * @String query
     * @ArrayList genres
     * @return
     */

    public ArrayList<Genre> getAllGenres() {
        String query = "SELECT * FROM " + DBTableValues.GENRE_TABLE;
        genres = new ArrayList<>();

        //Database connect using createStatement, surrounded in a try-catch
        try {
            Statement getGenres = db.getConnection().createStatement();
            ResultSet data= getGenres.executeQuery(query);

            while (data.next()){
                genres.add((new Genre(
                        data.getInt(DBTableValues.GENRE_ID_COLUMN),
                        data.getString(DBTableValues.GENRE_NAME_COLUMN)
                )));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return genres;
    }

    /**
     * Method to grab one entry from the table
     * @param genreID
     * @return
     */
    @Override
    public Genre getGenre(int genreID) {
        String query = "SELECT * FROM " + DBTableValues.GENRE_TABLE + " WHERE "
                + DBTableValues.GENRE_ID_COLUMN + " = "
                + genreID;
        Statement getGenre = null;

    }
}
