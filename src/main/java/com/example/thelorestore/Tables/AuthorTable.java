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
     * getAllAuthors() returns all authors in Author Table
     * @return Arraylist of authors
     */
    @Override
    public ArrayList<Author> getAllAuthors() {
        String query = "SELECT * FROM " + DBTableValues.AUTHOR_TABLE;
        authors = new ArrayList<>();
        try {
            Statement getAuthors = db.getConnection().createStatement();
            ResultSet data = getAuthors.executeQuery(query);
            while (data.next()) {
                authors.add(new Author(data.getInt(DBTableValues.AUTHOR_ID_COLUMN),
                        data.getString(DBTableValues.AUTHOR_PREFIX_COLUMN),
                        data.getString(DBTableValues.AUTHOR_FIRST_COLUMN),
                        data.getString(DBTableValues.AUTHOR_MIDDLE_COLUMN),
                        data.getString(DBTableValues.AUTHOR_LAST_COLUMN),
                        data.getString(DBTableValues.AUTHOR_SUFFIX_COLUMN)));
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
        String query = "SELECT * FROM " + DBTableValues.AUTHOR_TABLE + " WHERE " + DBTableValues.AUTHOR_ID_COLUMN + " = " + id;
        try {
            Statement getAuthor = db.getConnection().createStatement();
            ResultSet data = getAuthor.executeQuery(query);
            if(data.next()) {
                Author author = new Author(data.getInt(DBTableValues.AUTHOR_ID_COLUMN),
                        data.getString(DBTableValues.AUTHOR_PREFIX_COLUMN),
                        data.getString(DBTableValues.AUTHOR_FIRST_COLUMN),
                        data.getString(DBTableValues.AUTHOR_MIDDLE_COLUMN),
                        data.getString(DBTableValues.AUTHOR_LAST_COLUMN),
                        data.getString(DBTableValues.AUTHOR_SUFFIX_COLUMN));
                return author;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
