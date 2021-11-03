package com.example.thelorestore.Tables;

import com.example.thelorestore.DAOs.BookDAO;
import com.example.thelorestore.Database.DBTableValues;
import com.example.thelorestore.Database.Database;
import com.example.thelorestore.Pojo.Book;

import java.sql.SQLException;
import java.util.ArrayList;

public class BookTable implements BookDAO {

    Database db = Database.getInstance();
    ArrayList<Book> books;

    /**
     * createBook() adds a book to the Book Table
     * @param book is the book being added
     */
    @Override
    public void createBook(Book book) {
        String query = "INSERT INTO " + DBTableValues.BOOK_TABLE + "(" +
                DBTableValues.BOOK_TITLE_COLUMN + ", " +
                DBTableValues.BOOK_AUTHOR_COLUMN + ", " +
                DBTableValues.BOOK_GENRE_COLUMN + ", " +
                DBTableValues.BOOK_PUBLISHER_COLUMN + ", " +
                DBTableValues.BOOK_YEAR_COLUMN + ", " +
                DBTableValues.BOOK_QUANTITY_COLUMN + ", " +
                DBTableValues.BOOK_PRICE_COLUMN + ") VALUES ('" +
                book.getTitle() + "','" +
                book.getAuthor() + "','" +
                book.getGenre() + "','" +
                book.getPublisher() + "','" +
                book.getYear() + "','" +
                book.getQuantity() + "','" +
                book.getPrice() + "')";
        try {
            db.getConnection().createStatement().execute(query);
            System.out.println("Record inserted");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<Book> getAllBooks() {
        return null;
    }

    @Override
    public Book getBook(int id) {
        return null;
    }

    @Override
    public void updateBook(Book book) {

    }

    @Override
    public void deleteBook(Book book) {

    }
}
