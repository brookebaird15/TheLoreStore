package com.example.thelorestore.Tables;

import com.example.thelorestore.DAOs.BookAuthorDAO;
import com.example.thelorestore.Database.DBTableValues;
import com.example.thelorestore.Database.Database;
import com.example.thelorestore.Pojo.Author;
import com.example.thelorestore.Pojo.Book;
import com.example.thelorestore.Pojo.BookAuthor;
import com.example.thelorestore.Pojo.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class BookAuthorTable implements BookAuthorDAO {

    Database db = Database.getInstance();
    ArrayList<Book> books;
    ArrayList<Author> authors;

    @Override
    public void createBookAuthorRelation(Book book, Author author) {
        String query = "INSERT INTO " + DBTableValues.BOOK_AUTHOR_TABLE + "(" +
                DBTableValues.BOOK_ID_COLUMN_FOR_AUTHOR + ", " +
                DBTableValues.AUTHOR_FK_ID_COLUMN + ") VALUES (" +
                book.getId() + "," + author.getId() + ")";
        try {
            db.getConnection().createStatement().execute(query);
            System.out.println("Book/author relation created");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<Book> getAllBooksForAuthor(BookAuthor bookAuthor) {
        String query = "SELECT * FROM " + DBTableValues.BOOK_TABLE + " WHERE "
                + DBTableValues.BOOK_ID_COLUMN + " = " + bookAuthor.getBook();
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
    public ArrayList<Author> getAllAuthorsForBook(BookAuthor bookAuthor) {
        String query = "SELECT * FROM " + DBTableValues.AUTHOR_TABLE + " WHERE "
                + DBTableValues.AUTHOR_ID_COLUMN + " = " + bookAuthor.getAuthor();
        authors = new ArrayList<>();
        try {
            Statement getGenres = db.getConnection().createStatement();
            ResultSet data = getGenres.executeQuery(query);

            while (data.next()){
                authors.add(new Author(
                        data.getInt(DBTableValues.AUTHOR_ID_COLUMN),
                        data.getString(DBTableValues.AUTHOR_FIRST_COLUMN),
                        data.getString(DBTableValues.AUTHOR_MIDDLE_COLUMN),
                        data.getString(DBTableValues.AUTHOR_LAST_COLUMN)
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return authors;
    }
}
