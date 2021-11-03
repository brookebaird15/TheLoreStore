package com.example.thelorestore.Tables;

import com.example.thelorestore.DAOs.BookDAO;
import com.example.thelorestore.Database.DBTableValues;
import com.example.thelorestore.Database.Database;
import com.example.thelorestore.Pojo.Book;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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

    /**
     * getAllBooks() returns all books in Book Table
     * @return Arraylist of books
     */
    @Override
    public ArrayList<Book> getAllBooks() {
        String query = "SELECT * FROM " + DBTableValues.BOOK_TABLE;
        books = new ArrayList<>();
        try {
            Statement getBooks = db.getConnection().createStatement();
            ResultSet data = getBooks.executeQuery(query);
            while (data.next()) {
                books.add(new Book(data.getInt(DBTableValues.BOOK_ID_COLUMN),
                        data.getString(DBTableValues.BOOK_TITLE_COLUMN),
                        data.getInt(DBTableValues.BOOK_AUTHOR_COLUMN),
                        data.getInt(DBTableValues.BOOK_GENRE_COLUMN),
                        data.getInt(DBTableValues.BOOK_PUBLISHER_COLUMN),
                        data.getInt(DBTableValues.BOOK_YEAR_COLUMN),
                        data.getInt(DBTableValues.BOOK_QUANTITY_COLUMN),
                        data.getDouble(DBTableValues.BOOK_PRICE_COLUMN)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

    /**
     * getBook() returns the book at the ID provided or null if no match
     * @param id is the column id
     * @return book | null
     */
    @Override
    public Book getBook(int id) {
        String query = "SELECT * FROM " + DBTableValues.BOOK_TABLE + " WHERE " + DBTableValues.BOOK_ID_COLUMN + " = " + id;
        try {
            Statement getBook = db.getConnection().createStatement();
            ResultSet data = getBook.executeQuery(query);
            if(data.next()) {
                Book book = new Book(data.getInt(DBTableValues.BOOK_ID_COLUMN),
                        data.getString(DBTableValues.BOOK_TITLE_COLUMN),
                        data.getInt(DBTableValues.BOOK_AUTHOR_COLUMN),
                        data.getInt(DBTableValues.BOOK_GENRE_COLUMN),
                        data.getInt(DBTableValues.BOOK_PUBLISHER_COLUMN),
                        data.getInt(DBTableValues.BOOK_YEAR_COLUMN),
                        data.getInt(DBTableValues.BOOK_QUANTITY_COLUMN),
                        data.getDouble(DBTableValues.BOOK_PRICE_COLUMN));
                return book;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void updateBook(Book book) {
        //TODO - Add updateBook function
    }

    @Override
    public void deleteBook(Book book) {
        //TODO - Add deleteBook function
    }
}
