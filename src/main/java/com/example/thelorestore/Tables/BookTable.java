package com.example.thelorestore.Tables;

import com.example.thelorestore.DAOs.BookDAO;
import com.example.thelorestore.Database.DBTableValues;
import com.example.thelorestore.Database.Database;
import com.example.thelorestore.Pojo.*;

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
    public void createBook(Book book, Genre genre, Author author) {
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
        bookGenreTable.createBookGenreRelation(book, genre);
        bookAuthorTable.createBookAuthorRelation(book, author);
        try {
            db.getConnection().createStatement().execute(query);
            System.out.println("Book record inserted");
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
     * getBook() returns the book at the ISBN provided or null if no match
     * @param isbn is the column id
     * @return book | null
     */
    @Override
    public Book getBook(int isbn) {
        String query = "SELECT * FROM " + DBTableValues.BOOK_TABLE + " WHERE " + DBTableValues.BOOK_ID_COLUMN + " = " + isbn;
        try {
            Statement getBook = db.getConnection().createStatement();
            ResultSet data = getBook.executeQuery(query);
            if(data.next()) {
                Book book = new Book(data.getInt(DBTableValues.BOOK_ID_COLUMN),
                        data.getString(DBTableValues.BOOK_TITLE_COLUMN),
                        data.getInt(DBTableValues.BOOK_PUBLISHER_COLUMN),
                        data.getInt(DBTableValues.BOOK_YEAR_COLUMN),
                        data.getInt(DBTableValues.BOOK_STATUS_COLUMN),
                        data.getString(DBTableValues.BOOK_COMMENT_COLUMN));
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
    public void updateBook(Book book, Genre genre, Author author) {
        String query = "UPDATE " + DBTableValues.BOOK_TABLE + " SET " +
                DBTableValues.BOOK_TITLE_COLUMN + " = '" + book.getTitle() + "', " +
                DBTableValues.BOOK_PUBLISHER_COLUMN + " = '" + book.getPublisher() + "', " +
                DBTableValues.BOOK_YEAR_COLUMN + " = " + book.getYear() + ", " +
                DBTableValues.BOOK_STATUS_COLUMN + " = '" + book.getStatus() + "', " +
                DBTableValues.BOOK_COMMENT_COLUMN + " = '" + book.getComment() +
                "' WHERE " + DBTableValues.BOOK_ID_COLUMN + " = " + book.getId();
        bookGenreTable.updateGenreRelation(book, genre);
        bookAuthorTable.updateBookAuthorRelation(book, author);
        try {
            Statement updateItem = db.getConnection().createStatement();
            updateItem.executeUpdate(query);
            System.out.println("Book updated");
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
        String query = "DELETE FROM " + DBTableValues.BOOK_TABLE + " WHERE " + DBTableValues.BOOK_ID_COLUMN + " = " + book;
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

    //TODO - check if sql statement correct
    //TODO - is it possible to have all authors displayed in one column and all genres displayed in one column
    public ArrayList<DisplayBook> displayPrettyBooks() {
        ArrayList<DisplayBook> books = new ArrayList<>();
        String query = "SELECT book.id, book.title, " +
                "book.author_1, book.author_2, book.author_3, " +
                "genre.genre as genre_1, " +
                "genre.genre as genre_2, " +
                "genre.genre as genre_3, " +
                "publisher.company_name as publisher, " +
                "book.year_published, " +
                "status.status as status, " + "book.comment " +
                "JOIN author on book.author_1 = author.id " +
                "JOIN author on book.author_2 = author.id " +
                "JOIN author on book.author_3 = author.id " +
                "JOIN genre on book.genre_1 = genre.id " +
                "JOIN genre on book.genre_2 = genre.id " +
                "JOIN genre on book.genre_3 = genre.id " +
                "JOIN publisher on book.publisher = publisher.id " +
                "JOIN status on book.status = status.id " +
                "ORDER BY item.id ASC";
        try {
            Statement getBooks = db.getConnection().createStatement();
            ResultSet data = getBooks.executeQuery(query);
            while(data.next()) {
                books.add(new DisplayBook(data.getString(DBTableValues.BOOK_ID_COLUMN),
                        data.getString(DBTableValues.BOOK_TITLE_COLUMN),
                        data.getString(DBTableValues.BOOK_PUBLISHER_COLUMN),
                        data.getString(DBTableValues.BOOK_YEAR_COLUMN),
                        data.getString(DBTableValues.BOOK_STATUS_COLUMN),
                        data.getString(DBTableValues.BOOK_COMMENT_COLUMN)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }
}
