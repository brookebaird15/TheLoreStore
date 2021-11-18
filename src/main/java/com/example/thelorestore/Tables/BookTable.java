package com.example.thelorestore.Tables;

import com.example.thelorestore.DAOs.BookDAO;
import com.example.thelorestore.Database.DBTableValues;
import com.example.thelorestore.Database.Database;
import com.example.thelorestore.Pojo.Book;
import com.example.thelorestore.Pojo.DisplayBook;

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
                DBTableValues.BOOK_AUTHOR_COLUMN_1 + ", " +
                DBTableValues.BOOK_AUTHOR_COLUMN_2 + ", " +
                DBTableValues.BOOK_AUTHOR_COLUMN_3 + ", " +
                DBTableValues.BOOK_GENRE_COLUMN_1 + ", " +
                DBTableValues.BOOK_GENRE_COLUMN_2 + ", " +
                DBTableValues.BOOK_GENRE_COLUMN_3 + ", " +
                DBTableValues.BOOK_PUBLISHER_COLUMN + ", " +
                DBTableValues.BOOK_YEAR_COLUMN + ", " +
                DBTableValues.BOOK_QUANTITY_COLUMN + ", " +
                DBTableValues.BOOK_PRICE_COLUMN + ", " +
                DBTableValues.BOOK_BORROWED_COLUMN + ") VALUES ('" +
                book.getTitle() + "','" +
                book.getAuthor1() + "','" +
                book.getAuthor2() + "','" +
                book.getAuthor3() + "','" +
                book.getGenre1() + "','" +
                book.getGenre2() + "','" +
                book.getGenre3() + "','" +
                book.getPublisher() + "','" +
                book.getYear() + "','" +
                book.getQuantity() + "','" +
                book.getPrice() + "','" +
                book.getBorrowed() + "')";
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
                books.add(new Book(data.getInt(DBTableValues.BOOK_ISBN_COLUMN),
                        data.getString(DBTableValues.BOOK_TITLE_COLUMN),
                        data.getInt(DBTableValues.BOOK_AUTHOR_COLUMN_1),
                        data.getInt(DBTableValues.BOOK_AUTHOR_COLUMN_2),
                        data.getInt(DBTableValues.BOOK_AUTHOR_COLUMN_3),
                        data.getInt(DBTableValues.BOOK_GENRE_COLUMN_1),
                        data.getInt(DBTableValues.BOOK_GENRE_COLUMN_2),
                        data.getInt(DBTableValues.BOOK_GENRE_COLUMN_3),
                        data.getInt(DBTableValues.BOOK_PUBLISHER_COLUMN),
                        data.getInt(DBTableValues.BOOK_YEAR_COLUMN),
                        data.getInt(DBTableValues.BOOK_QUANTITY_COLUMN),
                        data.getDouble(DBTableValues.BOOK_PRICE_COLUMN),
                        data.getString(DBTableValues.BOOK_BORROWED_COLUMN)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

    /**
     * getBook() returns the book at the ISBN provided or null if no match
     * @param isbn is the column id
     * @return book | null
     */
    @Override
    public Book getBook(int isbn) {
        String query = "SELECT * FROM " + DBTableValues.BOOK_TABLE + " WHERE " + DBTableValues.BOOK_ISBN_COLUMN + " = " + isbn;
        try {
            Statement getBook = db.getConnection().createStatement();
            ResultSet data = getBook.executeQuery(query);
            if(data.next()) {
                Book book = new Book(data.getInt(DBTableValues.BOOK_ISBN_COLUMN),
                        data.getString(DBTableValues.BOOK_TITLE_COLUMN),
                        data.getInt(DBTableValues.BOOK_AUTHOR_COLUMN_1),
                        data.getInt(DBTableValues.BOOK_AUTHOR_COLUMN_2),
                        data.getInt(DBTableValues.BOOK_AUTHOR_COLUMN_3),
                        data.getInt(DBTableValues.BOOK_GENRE_COLUMN_1),
                        data.getInt(DBTableValues.BOOK_GENRE_COLUMN_2),
                        data.getInt(DBTableValues.BOOK_GENRE_COLUMN_3),
                        data.getInt(DBTableValues.BOOK_PUBLISHER_COLUMN),
                        data.getInt(DBTableValues.BOOK_YEAR_COLUMN),
                        data.getInt(DBTableValues.BOOK_QUANTITY_COLUMN),
                        data.getDouble(DBTableValues.BOOK_PRICE_COLUMN),
                        data.getString(DBTableValues.BOOK_BORROWED_COLUMN));
                return book;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * updateBook() updates the book information to the values provided
     * @param book is the book being updated
     */
    @Override
    public void updateBook(Book book) {
        String query = "UPDATE " + DBTableValues.BOOK_TABLE + " SET " +
                DBTableValues.BOOK_TITLE_COLUMN + " = " + book.getTitle() + ", " +
                DBTableValues.BOOK_AUTHOR_COLUMN_1 + " = " + book.getAuthor1() + ", " +
                DBTableValues.BOOK_AUTHOR_COLUMN_2 + " = " + book.getAuthor2() + ", " +
                DBTableValues.BOOK_AUTHOR_COLUMN_3 + " = " + book.getAuthor3() + ", " +
                DBTableValues.BOOK_GENRE_COLUMN_1 + " = " + book.getGenre1() + ", " +
                DBTableValues.BOOK_GENRE_COLUMN_2 + " = " + book.getGenre2() + ", " +
                DBTableValues.BOOK_GENRE_COLUMN_3 + " = " + book.getGenre3() + ", " +
                DBTableValues.BOOK_PUBLISHER_COLUMN + " = " + book.getPublisher() + ", " +
                DBTableValues.BOOK_YEAR_COLUMN + " = " + book.getYear() + ", " +
                DBTableValues.BOOK_QUANTITY_COLUMN + " = " + book.getQuantity() + ", " +
                DBTableValues.BOOK_PRICE_COLUMN + " = " + book.getPrice() +
                DBTableValues.BOOK_BORROWED_COLUMN + " = " + book.getBorrowed() +
                " WHERE " + DBTableValues.BOOK_ISBN_COLUMN + " = " + book.getISBN();
        try {
            Statement updateItem = db.getConnection().createStatement();
            updateItem.executeUpdate(query);
            System.out.println("Record updated");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * deleteBook() deletes the book with the specified id
     * @param book is the book being deleted
     */
    @Override
    public void deleteBook(Book book) {
        String query = "DELETE FROM " + DBTableValues.BOOK_TABLE + " WHERE " + DBTableValues.BOOK_ISBN_COLUMN + " = " + book;
        try {
            db.getConnection().createStatement().execute(query);
            System.out.println("Deleted record");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * displayPrettyBooks() displays all book table values formatted
     * @return books
     */

    //TODO - check if sql statement correct, need to account for author having multiple columns?
    public ArrayList<DisplayBook> displayPrettyBooks() {
        ArrayList<DisplayBook> books = new ArrayList<>();
        String query = "SELECT book.id, book.title, book.author, book.genre, " +
                "book.publisher, book.year_published, book.quantity, book.price, book.borrowed " +
                "JOIN author on book.author_1 = author.id " +
                "JOIN author on book.author_2 = author.id " +
                "JOIN author on book.author_3 = author.id " +
                "JOIN genre on book.genre_1 = genre.id " +
                "JOIN genre on book.genre_2 = genre.id " +
                "JOIN genre on book.genre_3 = genre.id " +
                "JOIN publisher on book.publisher = publisher.id " +
                "ORDER BY item.id ASC";
        try {
            Statement getBooks = db.getConnection().createStatement();
            ResultSet data = getBooks.executeQuery(query);
            while(data.next()) {
                books.add(new DisplayBook(data.getInt(DBTableValues.BOOK_ISBN_COLUMN), data.getString(DBTableValues.BOOK_TITLE_COLUMN),
                        data.getString(DBTableValues.BOOK_AUTHOR_COLUMN_1), data.getString(DBTableValues.BOOK_AUTHOR_COLUMN_2),
                        data.getString(DBTableValues.BOOK_AUTHOR_COLUMN_3), data.getString(DBTableValues.BOOK_GENRE_COLUMN_1),
                        data.getString(DBTableValues.BOOK_GENRE_COLUMN_2), data.getString(DBTableValues.BOOK_GENRE_COLUMN_3),
                        data.getString(DBTableValues.BOOK_PUBLISHER_COLUMN), data.getInt(DBTableValues.BOOK_YEAR_COLUMN),
                        data.getInt(DBTableValues.BOOK_QUANTITY_COLUMN), data.getDouble(DBTableValues.BOOK_PRICE_COLUMN),
                        data.getString(DBTableValues.BOOK_BORROWED_COLUMN)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }
}
