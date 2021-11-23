package com.example.thelorestore.Panes.Tabs;

import com.example.thelorestore.Launcher;
import com.example.thelorestore.Pojo.DisplayBook;
import com.example.thelorestore.Scenes.AddItemScene;
import com.example.thelorestore.Scenes.UpdateItemScene;
import com.example.thelorestore.Tables.BookTable;
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

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

        TableColumn<DisplayBook, String> commentColumn = new TableColumn<>("Comments");
        commentColumn.setCellValueFactory(e-> new SimpleStringProperty(e.getValue().getComment()));

        tableView.getColumns().addAll(isbnColumn, titleColumn, author1Column, author2Column, author3Column, genre1Column, genre2Column, genre3Column, publisherColumn, yearColumn, statusColumn, commentColumn);

        //TODO - uncomment once displayPrettyBooks method sql resolved

//        tableView.getItems().addAll(bookTable.displayPrettyBooks());
        root.setCenter(tableView);

        //editButtons box holds addItem and viewItem buttons
        HBox editButtons = new HBox();

        //addItemButton directs user to AddItemPane
        Button addItemButton = new Button("Add Book");
        addItemButton.setOnAction(e -> {
            Launcher.mainStage.setScene(new AddItemScene());
        });

        //viewItemButton directs user to ViewItemPane
        Button viewItemButton = new Button("Update Book");
        viewItemButton.setOnAction(e -> {
            Launcher.mainStage.setScene(new UpdateItemScene());
        });
        editButtons.getChildren().addAll(addItemButton, viewItemButton);
        editButtons.setAlignment(Pos.CENTER);
        editButtons.setSpacing(500);
        editButtons.requestFocus();

        root.setBottom(editButtons);

        this.setContent(root);
    }

    public static BookTab getInstance() {
        if (tab == null) {
            tab = new BookTab();
        }
        return tab;
    }
}
