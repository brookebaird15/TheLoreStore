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
     * createGenre() adds a genre to the Genre Table
     * @param genre is the genre being added
     */
    @Override
    public void createGenre(Genre genre) {
        String query = "INSERT INTO " + DBTableValues.GENRE_TABLE + "(" +
                DBTableValues.GENRE_NAME_COLUMN + ") VALUES ('" +
                genre.getName() + "')";
        try {
            db.getConnection().createStatement().execute(query);
            System.out.println("Genre record inserted");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method to create the Genre Table is executing an SQL query and creating table
     * @String query
     * @ArrayList genres
     * @return
     */
    public ArrayList<Genre> getAllGenres() {
        String query = "SELECT * FROM " + DBTableValues.GENRE_TABLE + " ORDER BY " + DBTableValues.GENRE_NAME_COLUMN + " ASC";
        genres = new ArrayList<>();

        //Database connect using createStatement, surrounded in a try-catch
        try {
            Statement getGenres = db.getConnection().createStatement();
            ResultSet data = getGenres.executeQuery(query);

            while (data.next()){
                genres.add(new Genre(
                        data.getInt(DBTableValues.GENRE_ID_COLUMN),
                        data.getString(DBTableValues.GENRE_NAME_COLUMN)
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return genres;
    }

//    /**
//     * Method to grab one entry from the table
//     * @param genreID
//     * @return
//     */
//    @Override
//    public Genre getGenre(int genreID) {
//        String query = "SELECT * FROM " + DBTableValues.GENRE_TABLE + " WHERE "
//                + DBTableValues.GENRE_ID_COLUMN + " = "
//                + genreID;
//        Statement getGenre = null;
//
//        //Statement being created and connected to database, caught in try-catch
//        try {
//            getGenre = db.getConnection().createStatement();
//            ResultSet data = getGenre.executeQuery(query);
//
//            if (data.next()){
//                Genre genre = new Genre(
//                        data.getInt(DBTableValues.GENRE_ID_COLUMN),
//                        data.getString(DBTableValues.GENRE_NAME_COLUMN)
//                );
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }

    /**
     * updateGenre() updates the genre information to the values provided
     * @param genre is the genre being updated
     */
    @Override
    public void updateGenre(Genre genre) {
        String query = "UPDATE " + DBTableValues.GENRE_TABLE + " SET " +
                DBTableValues.GENRE_NAME_COLUMN + " = '" + genre.getName() +
                "' WHERE " + DBTableValues.GENRE_ID_COLUMN + " = " + genre.getId();
        try {
            Statement updateGenre = db.getConnection().createStatement();
            updateGenre.executeUpdate(query);
            System.out.println("Genre updated");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
