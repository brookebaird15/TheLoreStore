package com.example.thelorestore.Tables;

import com.example.thelorestore.DAOs.BookAuthorDAO;
import com.example.thelorestore.Database.DBTableValues;
import com.example.thelorestore.Database.Database;
import com.example.thelorestore.Pojo.Author;
import com.example.thelorestore.Pojo.Book;
import com.example.thelorestore.Pojo.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class BookAuthorTable implements BookAuthorDAO {

    Database db = Database.getInstance();
    ArrayList<Book> books;
    ArrayList<Author> authors;

    /**
     * Creates an entry in the book_author_relation table for a specified book and author
     * @param book is the book
     * @param author is the author
     */
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

    /**
     * Retrieves all books for a specified author
     * @param authorId is the author being queried
     * @return ArrayList of books
     */
    @Override
    public ArrayList<Book> getAllBooksForAuthor(int authorId) {
        String query = "SELECT * FROM " + DBTableValues.BOOK_TABLE + " WHERE "
                + DBTableValues.BOOK_ID_COLUMN + " = " + authorId;
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
     * Retrieves all authors for a specified book
     * @param bookId is the book being queried
     * @return ArrayList of authors
     */
    @Override
    public ArrayList<Author> getAllAuthorsForBook(int bookId) {
        String query = "SELECT " + DBTableValues.AUTHOR_ID_COLUMN + ", "
                + DBTableValues.AUTHOR_FIRST_COLUMN
                + ", COALESCE(" + DBTableValues.AUTHOR_MIDDLE_COLUMN + ", \"\") AS " + DBTableValues.AUTHOR_MIDDLE_COLUMN + ", "
                + DBTableValues.AUTHOR_LAST_COLUMN + " FROM "
                + DBTableValues.AUTHOR_TABLE + " INNER JOIN " + DBTableValues.BOOK_AUTHOR_TABLE + " ON "
                + DBTableValues.AUTHOR_TABLE + "." + DBTableValues.AUTHOR_ID_COLUMN + " = "
                + DBTableValues.BOOK_AUTHOR_TABLE + "." + DBTableValues.AUTHOR_FK_ID_COLUMN + " WHERE "
                + DBTableValues.BOOK_AUTHOR_TABLE + "." + DBTableValues.BOOK_ID_COLUMN_FOR_AUTHOR + " = " + bookId;
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

    @Override
    public void removeAuthorRelation(Book book) {
        String query = "DELETE FROM " + DBTableValues.BOOK_AUTHOR_TABLE  + " WHERE " + DBTableValues.BOOK_ID_COLUMN_FOR_AUTHOR + " = " + book.getId();
        try {
            db.getConnection().createStatement().execute(query);
            System.out.println("Author relation deleted");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
