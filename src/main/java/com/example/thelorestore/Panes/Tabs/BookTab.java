package com.example.thelorestore.Panes.Tabs;

import com.example.thelorestore.Pojo.DisplayBook;
import com.example.thelorestore.Tables.BookTable;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;

public class BookTab extends Tab {
    private static BookTab tab;
    public TableView tableView;

    //TODO - Book tab does not display data, issue with SQL syntax (likely from displayPrettyBooks method)
    private BookTab() {
        this.setText("Books");
        BookTable bookTable = new BookTable();
        BorderPane root = new BorderPane();
        tableView = new TableView();

        TableColumn<DisplayBook, String> isbnColumn = new TableColumn<>("ISBN");
        isbnColumn.setCellValueFactory(e-> new SimpleStringProperty(e.getValue().getIsbn()));

        TableColumn<DisplayBook, String> titleColumn = new TableColumn<>("Title");
        titleColumn.setCellValueFactory(e-> new SimpleStringProperty(e.getValue().getTitle()));

        TableColumn<DisplayBook, String> author1Column = new TableColumn<>("Author");
        author1Column.setCellValueFactory(e-> new SimpleStringProperty(e.getValue().getAuthor1()));

        TableColumn<DisplayBook, String> author2Column = new TableColumn<>("Author (2)");
        author2Column.setCellValueFactory(e-> new SimpleStringProperty(e.getValue().getAuthor2()));

        TableColumn<DisplayBook, String> author3Column = new TableColumn<>("Author (3)");
        author3Column.setCellValueFactory(e-> new SimpleStringProperty(e.getValue().getAuthor3()));

        TableColumn<DisplayBook, String> genre1Column = new TableColumn<>("Genre (1)");
        genre1Column.setCellValueFactory(e-> new SimpleStringProperty(e.getValue().getGenre1()));

        TableColumn<DisplayBook, String> genre2Column = new TableColumn<>("Genre (2)");
        genre2Column.setCellValueFactory(e-> new SimpleStringProperty(e.getValue().getGenre2()));

        TableColumn<DisplayBook, String> genre3Column = new TableColumn<>("Genre (3)");
        genre3Column.setCellValueFactory(e-> new SimpleStringProperty(e.getValue().getGenre3()));

        TableColumn<DisplayBook, String> publisherColumn = new TableColumn<>("Publisher");
        publisherColumn.setCellValueFactory(e-> new SimpleStringProperty(e.getValue().getGenre3()));

        TableColumn<DisplayBook, String> yearColumn = new TableColumn<>("Year Published");
        yearColumn.setCellValueFactory(e-> new SimpleStringProperty(e.getValue().getYear()));

        TableColumn<DisplayBook, String> statusColumn = new TableColumn<>("Status");
        statusColumn.setCellValueFactory(e-> new SimpleStringProperty(e.getValue().getStatus()));

        tableView.getColumns().addAll(isbnColumn, titleColumn, author1Column, author2Column, author3Column, genre1Column, genre2Column, genre3Column, publisherColumn, yearColumn, statusColumn);
        tableView.getItems().addAll(bookTable.displayPrettyBooks());
        root.setCenter(tableView);

        this.setContent(root);
    }

    public static BookTab getInstance() {
        if (tab == null) {
            tab = new BookTab();
        }
        return tab;
    }
}
