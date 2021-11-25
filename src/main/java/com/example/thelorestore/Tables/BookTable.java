package com.example.thelorestore.Tables;

import com.example.thelorestore.DAOs.BookDAO;
import com.example.thelorestore.Database.DBTableValues;
import com.example.thelorestore.Database.Database;
import com.example.thelorestore.Pojo.*;

import java.sql.Array;
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

    /**
     * getBook() returns the book at the ISBN provided or null if no match
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

    //TODO - is it possible to have all authors displayed in one column and all genres displayed in one column
    public ArrayList<DisplayBook> displayPrettyBooks() {
        ArrayList<DisplayBook> books = new ArrayList<>();
        ArrayList<String> genres;
        ArrayList<String> authors;
        String query = "SELECT * FROM " + DBTableValues.BOOK_VIEW;
        try {
            Statement getBooks = db.getConnection().createStatement();
            ResultSet data = getBooks.executeQuery(query);
            while(data.next()) {
                books.add(new DisplayBook(data.getInt(DBTableValues.BOOK_VIEW_ID),
                        data.getString(DBTableValues.BOOK_VIEW_TITLE),
                        data.getString(DBTableValues.BOOK_VIEW_PUBLISHER),
                        data.getString(DBTableValues.BOOK_VIEW_YEAR),
                        data.getString(DBTableValues.BOOK_VIEW_STATUS),
                        data.getString(DBTableValues.BOOK_VIEW_COMMENT)));
            }
            for(DisplayBook book : books) {
                genres = new ArrayList<>();
                String genreQuery = "SELECT " + DBTableValues.GENRE_TABLE + " AS " + DBTableValues.GENRE_TABLE + " FROM " + DBTableValues.GENRE_TABLE + " g JOIN "
                        + DBTableValues.BOOK_GENRE_TABLE + " bgr ON g." + DBTableValues.GENRE_ID_COLUMN + " = bgr."
                        + DBTableValues.GENRE_FK_ID_COLUMN + " JOIN " + DBTableValues.BOOK_TABLE + " b ON bgr."
                        + DBTableValues.BOOK_ID_COLUMN_FOR_GENRE + " = b." + DBTableValues.BOOK_ID_COLUMN
                        + " WHERE b." + DBTableValues.BOOK_ID_COLUMN + " = " + book.getId();
                Statement getGenres = db.getConnection().createStatement();
                ResultSet genreData = getGenres.executeQuery(genreQuery);
                while (genreData.next()) {
                    genres.add(genreData.getString(DBTableValues.GENRE_TABLE));
                }
                String genreList = genres.toString();
                genreList = genreList.replace("[", "").replace("]", "");
                book.setGenre(genreList);

                authors = new ArrayList<>();
                String authorQuery = "SELECT CONCAT(a." + DBTableValues.AUTHOR_FIRST_COLUMN + ", ' ', COALESCE(a."
                        + DBTableValues.AUTHOR_MIDDLE_COLUMN + ", \"\"), ' ', a." + DBTableValues.AUTHOR_LAST_COLUMN + ") AS " + DBTableValues.BOOK_VIEW_AUTHOR
                        + " FROM " + DBTableValues.AUTHOR_TABLE + " a JOIN "
                        + DBTableValues.BOOK_AUTHOR_TABLE + " bar ON a." + DBTableValues.AUTHOR_ID_COLUMN + " = bar."
                        + DBTableValues.AUTHOR_FK_ID_COLUMN + " JOIN " + DBTableValues.BOOK_TABLE + " b ON bar."
                        + DBTableValues.BOOK_ID_COLUMN_FOR_AUTHOR + " = b." + DBTableValues.BOOK_ID_COLUMN
                        + " WHERE b." + DBTableValues.BOOK_ID_COLUMN + " = " + book.getId();
                Statement getAuthors = db.getConnection().createStatement();
                ResultSet authorData = getAuthors.executeQuery(authorQuery);
                while (authorData.next()) {
                    authors.add(authorData.getString(DBTableValues.BOOK_VIEW_AUTHOR));
                }
                String authorList = authors.toString();
                authorList = authorList.replace("[", "").replace("]", "");
                book.setAuthor(authorList);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }
}
