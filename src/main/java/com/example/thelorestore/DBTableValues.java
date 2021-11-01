package com.example.thelorestore;

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

    //Item Table
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
     * ITEM TABLE - PRIMARY KEY, TITLE, AUTHOR, GENRE, PUBLISHER, YEAR_PUBLISHED, QUANTITY
     */

    public static final String CREATE_PUBLISHER_TABLE =
            "CREATE TABLE " + PUBLISHER_TABLE + " ("
            + PUBLISHER_ID_COLUMN + " int NOT NULL AUTO_INCREMENT, "
            + PUBLISHER_COMPANY_COLUMN + " VARCHAR(50), "
            + "PRIMARY KEY(" + PUBLISHER_ID_COLUMN + ")" + ");";

}
