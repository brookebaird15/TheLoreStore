package com.example.thelorestore.Tables;

import com.example.thelorestore.DAOs.AuthorDAO;
import com.example.thelorestore.Database.DBTableValues;
import com.example.thelorestore.Database.Database;
import com.example.thelorestore.Pojo.Author;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class AuthorTable implements AuthorDAO {

    Database db = Database.getInstance();
    ArrayList<Author> authors;

    /**
     * createAuthor() adds an author to the Author table
     * @param author is the author being added
     */
    @Override
    public void createAuthor(Author author) {
        String query = "INSERT INTO " + DBTableValues.AUTHOR_TABLE + "(" +
                DBTableValues.AUTHOR_FIRST_COLUMN + ", " +
                DBTableValues.AUTHOR_MIDDLE_COLUMN + ", " +
                DBTableValues.AUTHOR_LAST_COLUMN + ") VALUES ('" +
                author.getFirstName() + "','" +
                author.getMiddleName() + "','" +
                author.getLastName() + "')";
        try {
            db.getConnection().createStatement().execute(query);
            System.out.println("Author record inserted");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * getAllAuthors() returns all authors in Author Table
     * @return Arraylist of authors
     */
    @Override
    public ArrayList<Author> getAllAuthors() {
        String query = "SELECT " + DBTableValues.AUTHOR_ID_COLUMN + ", "
                + DBTableValues.AUTHOR_FIRST_COLUMN
                + ", COALESCE(" + DBTableValues.AUTHOR_MIDDLE_COLUMN + ", \"\") AS " + DBTableValues.AUTHOR_MIDDLE_COLUMN + ", "
                + DBTableValues.AUTHOR_LAST_COLUMN + " FROM "
                + DBTableValues.AUTHOR_TABLE;
        authors = new ArrayList<>();
        try {
            Statement getAuthors = db.getConnection().createStatement();
            ResultSet data = getAuthors.executeQuery(query);
            while (data.next()) {
                authors.add(new Author(data.getInt(DBTableValues.AUTHOR_ID_COLUMN),
                        data.getString(DBTableValues.AUTHOR_FIRST_COLUMN),
                        data.getString(DBTableValues.AUTHOR_MIDDLE_COLUMN),
                        data.getString(DBTableValues.AUTHOR_LAST_COLUMN)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return authors;
    }

    /**
     * getAuthor() returns the author at the ID provided or null if no match
     * @param id is the column id
     * @return author | null
     */
    @Override
    public Author getAuthor(int id) {
        String query = "SELECT " + DBTableValues.AUTHOR_ID_COLUMN + ", "
                + DBTableValues.AUTHOR_FIRST_COLUMN
                + ", COALESCE(" + DBTableValues.AUTHOR_MIDDLE_COLUMN + ", \"\") AS " + DBTableValues.AUTHOR_MIDDLE_COLUMN + ", "
                + DBTableValues.AUTHOR_LAST_COLUMN + " FROM "
                + DBTableValues.AUTHOR_TABLE + " WHERE " + DBTableValues.AUTHOR_ID_COLUMN + " = " + id;
        try {
            Statement getAuthor = db.getConnection().createStatement();
            ResultSet data = getAuthor.executeQuery(query);
            if(data.next()) {
                Author author = new Author(data.getInt(DBTableValues.AUTHOR_ID_COLUMN),
                        data.getString(DBTableValues.AUTHOR_FIRST_COLUMN),
                        data.getString(DBTableValues.AUTHOR_MIDDLE_COLUMN),
                        data.getString(DBTableValues.AUTHOR_LAST_COLUMN));
                return author;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * updateAuthor() updates the author information to the values provided
     * @param author is the author being updated
     */
    @Override
    public void updateAuthor(Author author) {
        String query = "UPDATE " + DBTableValues.AUTHOR_TABLE + " SET " +
                DBTableValues.AUTHOR_FIRST_COLUMN + " = '" + author.getFirstName() + "', " +
                DBTableValues.AUTHOR_MIDDLE_COLUMN + " = '" + author.getMiddleName() + "', " +
                DBTableValues.AUTHOR_LAST_COLUMN + " = '" + author.getLastName() +
                "' WHERE " + DBTableValues.AUTHOR_ID_COLUMN + " = " + author.getId();
        try {
            Statement updateAuthor = db.getConnection().createStatement();
            updateAuthor.executeUpdate(query);
            System.out.println("Author updated");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
