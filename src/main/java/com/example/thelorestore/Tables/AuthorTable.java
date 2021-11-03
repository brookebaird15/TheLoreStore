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

    @Override
    public Author getAuthor(int id) {
        return null;
    }
}
