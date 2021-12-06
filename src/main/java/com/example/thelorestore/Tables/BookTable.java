package com.example.thelorestore.Tables;

import com.example.thelorestore.DAOs.BookDAO;
import com.example.thelorestore.Database.DBTableValues;
import com.example.thelorestore.Database.Database;
import com.example.thelorestore.Pojo.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class BookTable implements BookDAO {

    Database db = Database.getInstance();
    ArrayList<Book> books;
    BookGenreTable bookGenreTable = new BookGenreTable();
    BookAuthorTable bookAuthorTable = new BookAuthorTable();

    /**
     * createBook() adds a book to the Book Table
     * @param book is the book being added
     */
    @Override
    public Book createBook(Book book) {
        String query = "INSERT INTO " + DBTableValues.BOOK_TABLE + "(" +
                DBTableValues.BOOK_TITLE_COLUMN + ", " +
                DBTableValues.BOOK_PUBLISHER_COLUMN + ", " +
                DBTableValues.BOOK_YEAR_COLUMN + ", " +
                DBTableValues.BOOK_STATUS_COLUMN + ", " +
                DBTableValues.BOOK_COMMENT_COLUMN + ") VALUES ('" +
                book.getTitle() + "','" +
                book.getPublisher() + "','" +
                book.getYear() + "','" +
                book.getStatus() + "','" +
                book.getComment() + "')";
        try {
            db.getConnection().createStatement().execute(query);
            System.out.println("Book record inserted");
            query = "SELECT * FROM " + DBTableValues.BOOK_TABLE + " ORDER BY " + DBTableValues.BOOK_ID_COLUMN + " DESC LIMIT 0,1";
            Statement getBook = db.getConnection().createStatement();
            ResultSet data = getBook.executeQuery(query);
            if(data.next()) {
                Book newestBook = new Book(data.getInt(DBTableValues.BOOK_ID_COLUMN),
                        data.getString(DBTableValues.BOOK_TITLE_COLUMN),
                        data.getInt(DBTableValues.BOOK_PUBLISHER_COLUMN),
                        data.getInt(DBTableValues.BOOK_YEAR_COLUMN),
                        data.getInt(DBTableValues.BOOK_STATUS_COLUMN),
                        data.getString(DBTableValues.BOOK_COMMENT_COLUMN));
                return newestBook;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
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

//    /**
//     * getBook() returns the book at the ISBN provided or null if no match
//     * @param id is the column id
//     * @return book | null
//     */
//    @Override
//    public Book getBook(int id) {
//        String query = "SELECT * FROM " + DBTableValues.BOOK_TABLE + " WHERE " + DBTableValues.BOOK_ID_COLUMN + " = " + id;
//        try {
//            Statement getBook = db.getConnection().createStatement();
//            ResultSet data = getBook.executeQuery(query);
//            if(data.next()) {
//                Book book = new Book(data.getInt(DBTableValues.BOOK_ID_COLUMN),
//                        data.getString(DBTableValues.BOOK_TITLE_COLUMN),
//                        data.getInt(DBTableValues.BOOK_PUBLISHER_COLUMN),
//                        data.getInt(DBTableValues.BOOK_YEAR_COLUMN),
//                        data.getInt(DBTableValues.BOOK_STATUS_COLUMN),
//                        data.getString(DBTableValues.BOOK_COMMENT_COLUMN));
//                return book;
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }

    /**
     * updateBook() updates the book information to the values provided
     * @param book is the book being updated
     */
    @Override
    public Book updateBook(Book book) {
        String query = "UPDATE " + DBTableValues.BOOK_TABLE + " SET " +
                DBTableValues.BOOK_TITLE_COLUMN + " = '" + book.getTitle() + "', " +
                DBTableValues.BOOK_PUBLISHER_COLUMN + " = '" + book.getPublisher() + "', " +
                DBTableValues.BOOK_YEAR_COLUMN + " = " + book.getYear() + ", " +
                DBTableValues.BOOK_STATUS_COLUMN + " = '" + book.getStatus() + "', " +
                DBTableValues.BOOK_COMMENT_COLUMN + " = '" + book.getComment() +
                "' WHERE " + DBTableValues.BOOK_ID_COLUMN + " = " + book.getId();
        try {
            Statement updateItem = db.getConnection().createStatement();
            updateItem.executeUpdate(query);
            System.out.println("Book updated");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * deleteBook() deletes the book with the specified id
     * First removes genre and author references in respective joining tables
     * Second deletes record
     * @param book is the book being deleted
     */
    @Override
    public void deleteBook(Book book) {
        String query = "DELETE FROM " + DBTableValues.BOOK_TABLE + " WHERE " + DBTableValues.BOOK_ID_COLUMN + " = " + book.getId();
        bookGenreTable.removeGenreRelation(book);
        bookAuthorTable.removeAuthorRelation(book);
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
    public ArrayList<DisplayBook> displayPrettyBooks() {
        ArrayList<DisplayBook> books = new ArrayList<>();
        String query = "SELECT * FROM " + DBTableValues.BOOK_VIEW;
        try {
            Statement getBooks = db.getConnection().createStatement();
            ResultSet data = getBooks.executeQuery(query);
            while(data.next()) {
                books.add(new DisplayBook(
                        data.getInt(DBTableValues.BOOK_VIEW_ID),
                        data.getString(DBTableValues.BOOK_VIEW_TITLE),
                        data.getString(DBTableValues.BOOK_VIEW_AUTHOR),
                        data.getString(DBTableValues.BOOK_VIEW_GENRE),
                        data.getString(DBTableValues.BOOK_VIEW_PUBLISHER),
                        data.getString(DBTableValues.BOOK_VIEW_YEAR),
                        data.getString(DBTableValues.BOOK_VIEW_STATUS),
                        data.getString(DBTableValues.BOOK_VIEW_COMMENT)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

    /**
     * Counts the number of books with each 'status'
     * @param status is the status id being counted
     * @return count - number of books with that status
     */
    public int getStatusCount(int status) {
        int count = -1;
        try {
            PreparedStatement getCount = db.getConnection().prepareStatement("SELECT * FROM " + DBTableValues.BOOK_TABLE
                    + " WHERE " + DBTableValues.BOOK_STATUS_COLUMN + " = '" + status + "'", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet data = getCount.executeQuery();
            data.last();
            count = data.getRow();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }
}
