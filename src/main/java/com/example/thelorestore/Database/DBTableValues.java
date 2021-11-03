package com.example.thelorestore.Database;

public class DBTableValues {
    //Publisher Table
    public static final String PUBLISHER_TABLE = "publisher";
    public static final String PUBLISHER_ID_COLUMN = "id";
    public static final String PUBLISHER_COMPANY_COLUMN = "company_name";

    //Genre Table
    public static final String GENRE_TABLE = "genre";
    public static final String GENRE_ID_COLUMN = "id";
    public static final String GENRE_NAME_COLUMN = "genre";

    //Author Table
    public static final String AUTHOR_TABLE = "author";
    public static final String AUTHOR_ID_COLUMN = "id";
    public static final String AUTHOR_FIRST_COLUMN = "first_name";
    public static final String AUTHOR_MIDDLE_COLUMN = "middle_name";
    public static final String AUTHOR_LAST_COLUMN = "last_name";
    public static final String AUTHOR_PREFIX_COLUMN = "prefix";
    public static final String AUTHOR_SUFFIX_COLUMN = "suffix";

    //Book Table
    public static final String BOOK_TABLE = "book";
    public static final String BOOK_ID_COLUMN = "id";
    public static final String BOOK_TITLE_COLUMN = "title";
    public static final String BOOK_AUTHOR_COLUMN = "author";
    public static final String BOOK_GENRE_COLUMN = "genre";
    public static final String BOOK_PUBLISHER_COLUMN = "publisher";
    public static final String BOOK_YEAR_COLUMN = "year_published";
    public static final String BOOK_QUANTITY_COLUMN = "quantity";

    /**
     * Table Create Statements
     * PUBLISHER TABLE - PRIMARY KEY, COMPANY
     * GENRE TABLE - PRIMARY KEY, NAME
     * AUTHOR TABLE - PRIMARY KEY, FIRST_NAME, MIDDLE_NAME, LAST_NAME, PREFIX, SUFFIX
     * BOOK TABLE - PRIMARY KEY, TITLE, AUTHOR, GENRE, PUBLISHER, YEAR_PUBLISHED, QUANTITY
     */

    public static final String CREATE_AUTHOR_TABLE =
            "CREATE TABLE " + AUTHOR_TABLE + " ("
                    + AUTHOR_ID_COLUMN + " int NOT NULL AUTO_INCREMENT, "
                    + AUTHOR_FIRST_COLUMN + " VARCHAR(50) NOT NULL, "
                    + AUTHOR_MIDDLE_COLUMN + " VARCHAR(50), "
                    + AUTHOR_LAST_COLUMN + " VARCHAR(50) NOT NULL, "
                    + AUTHOR_PREFIX_COLUMN + " VARCHAR(10), "
                    + AUTHOR_SUFFIX_COLUMN + " VARCHAR(10), "
                    + "PRIMARY KEY(" + AUTHOR_ID_COLUMN + ")" + ");";

    public static final String CREATE_GENRE_TABLE =
            "CREATE TABLE " + GENRE_TABLE + " ("
                    + GENRE_ID_COLUMN + " int NOT NULL AUTO_INCREMENT, "
                    + GENRE_NAME_COLUMN + " VARCHAR(30) NOT NULL, "
                    + "PRIMARY KEY(" + GENRE_ID_COLUMN + ")" + ");";

    public static final String CREATE_PUBLISHER_TABLE =
            "CREATE TABLE " + PUBLISHER_TABLE + " ("
            + PUBLISHER_ID_COLUMN + " int NOT NULL AUTO_INCREMENT, "
            + PUBLISHER_COMPANY_COLUMN + " VARCHAR(50) NOT NULL, "
            + "PRIMARY KEY(" + PUBLISHER_ID_COLUMN + ")" + ");";

    public static final String CREATE_ITEM_TABLE =
            "CREATE TABLE " + BOOK_TABLE + "("
            + BOOK_ID_COLUMN + " int NOT NULL AUTO INCREMENT PRIMARY KEY,"
            + BOOK_TITLE_COLUMN + " VARCHAR(255) NOT NULL, "
            + BOOK_AUTHOR_COLUMN + " VARCHAR(255) NOT NULL, "
            + BOOK_GENRE_COLUMN + " VARCHAR(50) NOT NULL, "
            + BOOK_PUBLISHER_COLUMN + " VARCHAR(255) NOT NULL, "
            + BOOK_YEAR_COLUMN + " int(4) NOT NULL, "
            + BOOK_QUANTITY_COLUMN + " int, "
            + "FOREIGN KEY (" + BOOK_AUTHOR_COLUMN + ")" + " REFERENCES " + AUTHOR_TABLE + "(" + AUTHOR_ID_COLUMN + "),"
            + "FOREIGN KEY (" + BOOK_GENRE_COLUMN + ")" + " REFERENCES " + GENRE_TABLE + "(" + GENRE_ID_COLUMN + "),"
            + "FOREIGN KEY (" + BOOK_PUBLISHER_COLUMN + ")" + " REFERENCES " + PUBLISHER_TABLE + "(" + PUBLISHER_ID_COLUMN + "),"
            + ");";


}
