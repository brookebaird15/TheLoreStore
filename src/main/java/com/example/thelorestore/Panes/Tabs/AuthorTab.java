package com.example.thelorestore.Panes.Tabs;

import com.example.thelorestore.Pojo.Author;
import com.example.thelorestore.Tables.AuthorTable;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;

public class AuthorTab extends Tab {
    private static AuthorTab tab;
    public TableView tableView;

    private AuthorTab() {
        this.setText("Author");
        AuthorTable authorTable = new AuthorTable();
        BorderPane root = new BorderPane();
        tableView = new TableView();

        TableColumn<Author, String> firstNameColumn = new TableColumn<>("First Name");
        firstNameColumn.setCellValueFactory(e -> new SimpleStringProperty(e.getValue().getFirstName()));

        TableColumn<Author, String> middleNameColumn = new TableColumn<>("Middle Name");
        middleNameColumn.setCellValueFactory(e -> new SimpleStringProperty(e.getValue().getMiddleName()));

        TableColumn<Author, String> lastNameColumn = new TableColumn<>("Last Name");
        lastNameColumn.setCellValueFactory(e-> new SimpleStringProperty(e.getValue().getLastName()));

        tableView.getColumns().addAll(firstNameColumn, middleNameColumn, lastNameColumn);
        tableView.getItems().addAll(authorTable.getAllAuthors());
        root.setCenter(tableView);

        this.setContent(root);
    }

    public static AuthorTab getInstance() {
        if (tab == null) {
            tab = new AuthorTab();
        }
        return tab;
    }
}
