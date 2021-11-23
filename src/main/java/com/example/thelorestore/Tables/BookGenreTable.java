package com.example.thelorestore.Tables;

import com.example.thelorestore.DAOs.BookGenreDAO;
import com.example.thelorestore.Database.DBTableValues;
import com.example.thelorestore.Database.Database;
import com.example.thelorestore.Pojo.Book;
import com.example.thelorestore.Pojo.BookGenre;
import com.example.thelorestore.Pojo.Genre;

import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class BookGenreTable implements BookGenreDAO {

    Database db = Database.getInstance();
    ArrayList<Book> books;
    ArrayList<Genre> genres;

    @Override
    public void createBookGenreRelation(Book book, Genre genre) {
        String query = "INSERT INTO " + DBTableValues.BOOK_GENRE_TABLE + "(" +
                DBTableValues.BOOK_ID_COLUMN_FOR_GENRE + ", " +
                DBTableValues.GENRE_FK_ID_COLUMN + ") VALUES (" +
                book.getId() + "," + genre.getId() + ")";
        try {
            db.getConnection().createStatement().execute(query);
            System.out.println("Book/genre relation created");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<Book> getAllBooksForGenre(BookGenre bookGenre) {
        String query = "SELECT * FROM " + DBTableValues.BOOK_TABLE + " WHERE "
                + DBTableValues.BOOK_ID_COLUMN + " = " + bookGenre.getBook();
        books = new ArrayList<>();
        try {
            Statement getBooks = db.getConnection().createStatement();
            ResultSet data = getBooks.executeQuery(query);
            while (data.next()) {
                books.add(new Book(data.getInt(DBTableValues.BOOK_ID_COLUMN),
                        data.getString(DBTableValues.BOOK_TITLE_COLUMN),
                        data.getInt(DBTableValues.BOOK_PUBLISHER_COLUMN),
                        data.getInt(DBTableValues.BOOK_YEAR_COLUMN),
                        data.getInt(DBTableValues.BOOK_STATUS_COLUMN),
                        data.getString(DBTableValues.BOOK_COMMENT_COLUMN)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

    @Override
    public ArrayList<Genre> getAllGenresForBook(BookGenre bookGenre) {
        String query = "SELECT * FROM " + DBTableValues.GENRE_TABLE + " WHERE "
                + DBTableValues.GENRE_ID_COLUMN + " = " + bookGenre.getGenre();
        genres = new ArrayList<>();
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

    @Override
    public void updateGenreRelation(Book book, Genre genre) {
        String query = "UPDATE " + DBTableValues.BOOK_GENRE_TABLE + " SET " +
                DBTableValues.GENRE_FK_ID_COLUMN + " = " + genre.getId() +
                " WHERE " + DBTableValues.BOOK_ID_COLUMN + " = " + book.getId();
        try {
            Statement updateRelation = db.getConnection().createStatement();
            updateRelation.executeUpdate(query);
            System.out.println("Book/genre relation updated");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
