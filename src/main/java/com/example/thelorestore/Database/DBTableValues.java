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

    //book genre relation table
    public static final String BOOK_GENRE_TABLE = "book_genre_relation";
    public static final String BOOK_ID_COLUMN_FOR_GENRE = "book_id";
    public static final String GENRE_FK_ID_COLUMN = "genre_id";

    //Status Table
    public static final String STATUS_TABLE = "status";
    public static final String STATUS_ID_COLUMN = "id";
    public static final String STATUS_NAME_COLUMN = "status";

    //Author Table
    public static final String AUTHOR_TABLE = "author";
    public static final String AUTHOR_ID_COLUMN = "id";
    public static final String AUTHOR_FIRST_COLUMN = "first_name";
    public static final String AUTHOR_MIDDLE_COLUMN = "middle_name";
    public static final String AUTHOR_LAST_COLUMN = "last_name";

    //book author relation table
    public static final String BOOK_AUTHOR_TABLE = "book_author_relation";
    public static final String BOOK_ID_COLUMN_FOR_AUTHOR = "book_id";
    public static final String AUTHOR_FK_ID_COLUMN = "author_id";

    //Book Table
    public static final String BOOK_TABLE = "book";
    public static final String BOOK_ID_COLUMN = "id";
    public static final String BOOK_TITLE_COLUMN = "title";
    public static final String BOOK_PUBLISHER_COLUMN = "publisher";
    public static final String BOOK_YEAR_COLUMN = "year_published";
    public static final String BOOK_STATUS_COLUMN = "status";
    public static final String BOOK_COMMENT_COLUMN = "comment";

    public static final String BOOK_VIEW = "book_info1";
    public static final String BOOK_VIEW_ID = "id";
    public static final String BOOK_VIEW_TITLE = "title";
    public static final String BOOK_VIEW_AUTHOR = "author_name";
    public static final String BOOK_VIEW_GENRE = "genre";
    public static final String BOOK_VIEW_PUBLISHER = "company_name";
    public static final String BOOK_VIEW_YEAR = "year_published";
    public static final String BOOK_VIEW_STATUS = "status";
    public static final String BOOK_VIEW_COMMENT = "comment";

    /**
     * Table Create Statements
     * PUBLISHER TABLE - ID, COMPANY
     * GENRE TABLE - ID, GENRE
     * AUTHOR TABLE - ID, FIRST_NAME, MIDDLE_NAME, LAST_NAME
     * BOOK TABLE - ID, TITLE, AUTHOR_1, AUTHOR_2, AUTHOR_3, GENRE_1, GENRE_2, GENRE_3, PUBLISHER, YEAR_PUBLISHED, READ, COMMENTS
     */

    public static final String CREATE_AUTHOR_TABLE =
            "CREATE TABLE " + AUTHOR_TABLE + " ("
                    + AUTHOR_ID_COLUMN + " int NOT NULL AUTO_INCREMENT, "
                    + AUTHOR_FIRST_COLUMN + " VARCHAR(50) NOT NULL, "
                    + AUTHOR_MIDDLE_COLUMN + " VARCHAR(50), "
                    + AUTHOR_LAST_COLUMN + " VARCHAR(50) NOT NULL, "
                    + "PRIMARY KEY(" + AUTHOR_ID_COLUMN + ")" + ");";

    public static final String CREATE_BOOK_AUTHOR_TABLE =
            "CREATE TABLE " + BOOK_AUTHOR_TABLE + " ("
                    + BOOK_ID_COLUMN_FOR_AUTHOR + " int NOT NULL, "
                    + AUTHOR_FK_ID_COLUMN + " int NOT NULL,"
                    + "FOREIGN KEY (" + BOOK_ID_COLUMN_FOR_AUTHOR + ") REFERENCES " + BOOK_TABLE + "(" + BOOK_ID_COLUMN + "),"
                    + "FOREIGN KEY (" + AUTHOR_FK_ID_COLUMN + ") REFERENCES " + AUTHOR_TABLE + "(" + AUTHOR_ID_COLUMN + "),"
                    + "PRIMARY KEY(" + BOOK_ID_COLUMN_FOR_AUTHOR + ", " + AUTHOR_FK_ID_COLUMN + ")" + ");";

    public static final String CREATE_GENRE_TABLE =
            "CREATE TABLE " + GENRE_TABLE + " ("
                    + GENRE_ID_COLUMN + " int NOT NULL AUTO_INCREMENT, "
                    + GENRE_NAME_COLUMN + " VARCHAR(30) NOT NULL, "
                    + "PRIMARY KEY(" + GENRE_ID_COLUMN + ")" + ");";

    public static final String CREATE_BOOK_GENRE_TABLE =
            "CREATE TABLE " + BOOK_GENRE_TABLE + " ("
                    + BOOK_ID_COLUMN_FOR_GENRE + " int NOT NULL, "
                    + GENRE_FK_ID_COLUMN + " int NOT NULL,"
                    + "FOREIGN KEY (" + BOOK_ID_COLUMN_FOR_GENRE + ") REFERENCES " + BOOK_TABLE + "(" + BOOK_ID_COLUMN + "),"
                    + "FOREIGN KEY (" + GENRE_FK_ID_COLUMN + ") REFERENCES " + GENRE_TABLE + "(" + GENRE_ID_COLUMN + "),"
                    + "PRIMARY KEY(" + BOOK_ID_COLUMN_FOR_GENRE + ", " + GENRE_FK_ID_COLUMN + ")" + ");";

    public static final String CREATE_STATUS_TABLE =
            "CREATE TABLE " + STATUS_TABLE + " ("
                    + STATUS_ID_COLUMN + " int NOT NULL AUTO_INCREMENT, "
                    + STATUS_NAME_COLUMN + " VARCHAR(15) NOT NULL, "
                    + "PRIMARY KEY(" + STATUS_ID_COLUMN + ")" + ");";

    public static final String CREATE_PUBLISHER_TABLE =
            "CREATE TABLE " + PUBLISHER_TABLE + " ("
            + PUBLISHER_ID_COLUMN + " int NOT NULL AUTO_INCREMENT, "
            + PUBLISHER_COMPANY_COLUMN + " VARCHAR(50) NOT NULL, "
            + "PRIMARY KEY(" + PUBLISHER_ID_COLUMN + ")" + ");";

    public static final String CREATE_BOOK_TABLE =
            "CREATE TABLE " + BOOK_TABLE + " ("
            + BOOK_ID_COLUMN + " int NOT NULL AUTO_INCREMENT PRIMARY KEY,"
            + BOOK_TITLE_COLUMN + " VARCHAR(255) NOT NULL, "
            + BOOK_PUBLISHER_COLUMN + " int NOT NULL, "
            + BOOK_YEAR_COLUMN + " int(4) NOT NULL, "
            + BOOK_STATUS_COLUMN + " int NOT NULL, "
            + BOOK_COMMENT_COLUMN + " VARCHAR(1000),"
            + "FOREIGN KEY (" + BOOK_STATUS_COLUMN + ")" + " REFERENCES " + STATUS_TABLE + "(" + STATUS_ID_COLUMN + "),"
            + "FOREIGN KEY (" + BOOK_PUBLISHER_COLUMN + ")" + " REFERENCES " + PUBLISHER_TABLE + "(" + PUBLISHER_ID_COLUMN + ")"
            + ");";

    public static final String CREATE_BOOK_VIEW =
            "CREATE VIEW " + BOOK_VIEW + " AS SELECT b."
                    + BOOK_VIEW_TITLE + ", CONCAT(a."
                    + AUTHOR_FIRST_COLUMN + ", ' ', COALESCE(a."
                    + AUTHOR_MIDDLE_COLUMN + ", \"\"), ' ', a."
                    + AUTHOR_LAST_COLUMN + " AS "
                    + BOOK_VIEW_AUTHOR + ", g."
                    + BOOK_VIEW_GENRE + ", p."
                    + BOOK_VIEW_PUBLISHER + ", b."
                    + BOOK_VIEW_YEAR + ", s."
                    + BOOK_VIEW_STATUS + ", COALESCE(b."
                    + BOOK_VIEW_COMMENT + ", \"\") AS "
                    + BOOK_VIEW_COMMENT + " FROM "
                    + BOOK_VIEW_GENRE + " AS g "
                    + "INNER JOIN " + BOOK_GENRE_TABLE + " AS bgr ON g." + GENRE_ID_COLUMN + "=bgr." + GENRE_FK_ID_COLUMN
                    + " INNER JOIN " + BOOK_TABLE + " AS b ON bgr." + BOOK_ID_COLUMN_FOR_GENRE + "=b." + BOOK_ID_COLUMN
                    + " INNER JOIN " + PUBLISHER_TABLE + " AS p ON p." + PUBLISHER_ID_COLUMN + "=b." + BOOK_PUBLISHER_COLUMN
                    + " INNER JOIN " + STATUS_TABLE + " AS s ON s." + STATUS_ID_COLUMN + "=b." + BOOK_STATUS_COLUMN
                    + " INNER JOIN " + BOOK_AUTHOR_TABLE + " AS bar ON b." + BOOK_ID_COLUMN + "=bar." + BOOK_ID_COLUMN_FOR_AUTHOR
                    + " INNER JOIN " + AUTHOR_TABLE + " AS a ON bar." + AUTHOR_FK_ID_COLUMN + "=a." + AUTHOR_ID_COLUMN;
    //CREATE VIEW book_info AS SELECT b.title, CONCAT(a.first_name, ' ', COALESCE(a.middle_name, ""), ' ', a.last_name) AS author_name,
    // g.genre, p.company_name, b.year_published, s.status, COALESCE(b.comment, "") AS comment
    //FROM genre AS g INNER JOIN book_genre_relation AS bgr
    //ON g.id=bgr.genre_id
    //INNER JOIN book AS b
    //ON bgr.book_id=b.id
    //INNER JOIN publisher AS p
    //ON p.id=b.publisher
    //INNER JOIN status AS s
    //ON s.id=b.status
    //INNER JOIN book_author_relation AS bar
    //ON b.id=bar.book_id
    //INNER JOIN author AS a
    //ON bar.author_id = a.id
}
