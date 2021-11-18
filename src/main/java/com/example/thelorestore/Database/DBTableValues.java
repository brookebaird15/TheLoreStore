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

    //Book Table
    public static final String BOOK_TABLE = "book";
    public static final String BOOK_ISBN_COLUMN = "isbn";
    public static final String BOOK_TITLE_COLUMN = "title";
    public static final String BOOK_AUTHOR_COLUMN_1 = "author_1";
    public static final String BOOK_AUTHOR_COLUMN_2 = "author_2";
    public static final String BOOK_AUTHOR_COLUMN_3 = "author_3";
    public static final String BOOK_GENRE_COLUMN_1 = "genre_1";
    public static final String BOOK_GENRE_COLUMN_2 = "genre_2";
    public static final String BOOK_GENRE_COLUMN_3 = "genre_3";
    public static final String BOOK_PUBLISHER_COLUMN = "publisher";
    public static final String BOOK_YEAR_COLUMN = "year_published";
    public static final String BOOK_QUANTITY_COLUMN = "quantity";
    public static final String BOOK_PRICE_COLUMN = "price";
    public static final String BOOK_BORROWED_COLUMN = "borrowed";

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

    public static final String CREATE_BOOK_TABLE =
            "CREATE TABLE " + BOOK_TABLE + " ("
            + BOOK_ISBN_COLUMN + " int NOT NULL PRIMARY KEY,"
            + BOOK_TITLE_COLUMN + " VARCHAR(255) NOT NULL, "
            + BOOK_AUTHOR_COLUMN_1 + " int NOT NULL, "
            + BOOK_AUTHOR_COLUMN_2 + " int, "
            + BOOK_AUTHOR_COLUMN_3 + " int, "
            + BOOK_GENRE_COLUMN_1 + " int NOT NULL, "
            + BOOK_GENRE_COLUMN_2 + " int, "
            + BOOK_GENRE_COLUMN_3 + " int, "
            + BOOK_PUBLISHER_COLUMN + " int NOT NULL, "
            + BOOK_YEAR_COLUMN + " int(4) NOT NULL, "
            + BOOK_QUANTITY_COLUMN + " int, "
            + BOOK_PRICE_COLUMN + " decimal(3,2) NOT NULL, "
            + BOOK_BORROWED_COLUMN + " VARCHAR(3) NOT NULL,"
            + "FOREIGN KEY (" + BOOK_AUTHOR_COLUMN_1 + ")" + " REFERENCES " + AUTHOR_TABLE + "(" + AUTHOR_ID_COLUMN + "),"
            + "FOREIGN KEY (" + BOOK_AUTHOR_COLUMN_2 + ")" + " REFERENCES " + AUTHOR_TABLE + "(" + AUTHOR_ID_COLUMN + "),"
            + "FOREIGN KEY (" + BOOK_AUTHOR_COLUMN_3 + ")" + " REFERENCES " + AUTHOR_TABLE + "(" + AUTHOR_ID_COLUMN + "),"
            + "FOREIGN KEY (" + BOOK_GENRE_COLUMN_1 + ")" + " REFERENCES " + GENRE_TABLE + "(" + GENRE_ID_COLUMN + "),"
            + "FOREIGN KEY (" + BOOK_GENRE_COLUMN_2 + ")" + " REFERENCES " + GENRE_TABLE + "(" + GENRE_ID_COLUMN + "),"
            + "FOREIGN KEY (" + BOOK_GENRE_COLUMN_3 + ")" + " REFERENCES " + GENRE_TABLE + "(" + GENRE_ID_COLUMN + "),"
            + "FOREIGN KEY (" + BOOK_PUBLISHER_COLUMN + ")" + " REFERENCES " + PUBLISHER_TABLE + "(" + PUBLISHER_ID_COLUMN + ")"
            + ");";


}
