package com.example.thelorestore.Tables;

import com.example.thelorestore.DAOs.BookGenreDAO;
import com.example.thelorestore.Database.DBTableValues;
import com.example.thelorestore.Database.Database;
import com.example.thelorestore.Pojo.Book;
import com.example.thelorestore.Pojo.Genre;

import java.sql.*;
import java.util.ArrayList;

public class BookGenreTable implements BookGenreDAO {

    Database db = Database.getInstance();
    ArrayList<Book> books;
    ArrayList<Genre> genres;

    /**
     * Creates an entry in the book_genre_relation table for a specified book and genre
     * @param book is the book
     * @param genre is the genre
     */
    @Override
    public void createBookGenreRelation(Book book, Genre genre) {
        String query = "INSERT INTO " + DBTableValues.BOOK_GENRE_TABLE + "(" +
                DBTableValues.BOOK_ID_COLUMN_FOR_GENRE + ", " +
                DBTableValues.GENRE_FK_ID_COLUMN + ") VALUES (" +
                book.getId() + "," + genre.getId() + ")";
        try {
            db.getConnection().createStatement().execute(query);
            System.out.println("Book/genre relation created");
        } catch (SQLIntegrityConstraintViolationException ex) {
            System.out.println("GENRE: " + genre + " already in table");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Retrieves all books with a specified genre
     * @param genreId is the genre being queried
     * @return ArrayList of books
     */
    @Override
    public ArrayList<Book> getAllBooksForGenre(int genreId) {
        //SELECT b.* FROM book AS b INNER JOIN book_genre_relation AS bgr ON b.id=bgr.book_id INNER JOIN genre AS g ON bgr.genre_id=g.id WHERE g.id=1
        String query = "SELECT b.* FROM " + DBTableValues.BOOK_TABLE + " AS b INNER JOIN "
        + DBTableValues.BOOK_GENRE_TABLE + " AS bgr ON b." + DBTableValues.BOOK_ID_COLUMN + "=bgr." + DBTableValues.BOOK_ID_COLUMN_FOR_GENRE
        + " INNER JOIN " + DBTableValues.GENRE_TABLE + " AS g ON bgr." + DBTableValues.GENRE_FK_ID_COLUMN + "=g." + DBTableValues.GENRE_ID_COLUMN
        + " WHERE g." + DBTableValues.GENRE_ID_COLUMN + "=" + genreId;
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

    /**
     * Retrieves all genres for a specified book
     * @param bookId is the book being queried
     * @return ArrayList of genres
     */
    @Override
    public ArrayList<Genre> getAllGenresForBook(int bookId) {
        String query = "SELECT " + DBTableValues.GENRE_ID_COLUMN + ", " + DBTableValues.GENRE_NAME_COLUMN
                + " FROM " + DBTableValues.GENRE_TABLE + " INNER JOIN " + DBTableValues.BOOK_GENRE_TABLE
                + " ON " + DBTableValues.GENRE_TABLE + "." + DBTableValues.GENRE_ID_COLUMN
                + " = " + DBTableValues.BOOK_GENRE_TABLE + "." + DBTableValues.GENRE_FK_ID_COLUMN
                + " WHERE " + DBTableValues.BOOK_GENRE_TABLE + "." + DBTableValues.BOOK_ID_COLUMN_FOR_GENRE + " = " + bookId;
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
    public void removeGenreRelation(Book book) {
        String query = "DELETE FROM " + DBTableValues.BOOK_GENRE_TABLE  + " WHERE " + DBTableValues.BOOK_ID_COLUMN_FOR_GENRE + " = " + book.getId();
        try {
            db.getConnection().createStatement().execute(query);
            System.out.println("Genre relation deleted");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Counts the number of books with a specified genre
     * @param genre is the genre id being counted
     * @return count - the number of books with that genre
     */
    public int getGenreCount(int genre) {
        int count = -1;
        try {
            PreparedStatement getCount = db.getConnection().prepareStatement("SELECT * FROM " + DBTableValues.BOOK_GENRE_TABLE
                    + " WHERE " + DBTableValues.GENRE_FK_ID_COLUMN + " = '" + genre + "'", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet data = getCount.executeQuery();
            data.last();
            count = data.getRow();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }
}
