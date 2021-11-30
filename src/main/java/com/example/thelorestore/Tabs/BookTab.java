package com.example.thelorestore.Tabs;

import com.example.thelorestore.Launcher;
import com.example.thelorestore.Pojo.Book;
import com.example.thelorestore.Pojo.DisplayBook;
import com.example.thelorestore.Scenes.AddItemScene;
import com.example.thelorestore.Scenes.UpdateItemScene;
import com.example.thelorestore.Tables.BookTable;
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.util.jar.JarEntry;

public class BookTab extends Tab {
    private static BookTab tab;
    public static TableView tableView;
    public static Book selectedBook;
    private VBox confirmation;

    //TODO - Book tab does not display data, issue with SQL syntax (likely from displayPrettyBooks method)
    private BookTab() {
        this.setText("Books");
        BookTable bookTable = new BookTable();
        BorderPane root = new BorderPane();
        tableView = new TableView();

        TableColumn<DisplayBook, String> titleColumn = new TableColumn<>("Title");
        titleColumn.setCellValueFactory(e-> new SimpleStringProperty(e.getValue().getTitle()));

        TableColumn<DisplayBook, String> authorColumn = new TableColumn<>("Author");
        authorColumn.setCellValueFactory(e-> new SimpleStringProperty(e.getValue().getAuthor()));

        TableColumn<DisplayBook, String> genreColumn = new TableColumn<>("Genre");
        genreColumn.setCellValueFactory(e-> new SimpleStringProperty(e.getValue().getGenre()));

        TableColumn<DisplayBook, String> publisherColumn = new TableColumn<>("Publisher");
        publisherColumn.setCellValueFactory(e-> new SimpleStringProperty(e.getValue().getPublisher()));

        TableColumn<DisplayBook, String> yearColumn = new TableColumn<>("Year Published");
        yearColumn.setCellValueFactory(e-> new SimpleStringProperty(e.getValue().getYear()));

        TableColumn<DisplayBook, String> statusColumn = new TableColumn<>("Status");
        statusColumn.setCellValueFactory(e-> new SimpleStringProperty(e.getValue().getStatus()));

        TableColumn<DisplayBook, String> commentColumn = new TableColumn<>("Comments");
        commentColumn.setCellValueFactory(e-> new SimpleStringProperty(e.getValue().getComment()));

        tableView.getColumns().addAll(titleColumn, authorColumn, genreColumn, publisherColumn, yearColumn, statusColumn, commentColumn);
        tableView.getItems().addAll(bookTable.displayPrettyBooks());

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
            //TODO - selected book needs to select on book id, not index (repeating or removed book entries will change index vs id)
            selectedBook = bookTable.getBook(tableView.getSelectionModel().getSelectedIndex());
            Launcher.mainStage.setScene(new UpdateItemScene());
        });

        //btn to confirm user wants to delete book
        Button confirmButton = new Button("Yes - Delete Book");
        confirmButton.setOnAction(e-> {
            bookTable.deleteBook(selectedBook);
            confirmation.setVisible(false);
        });

        //button to cancel deletion
        Button cancelButton = new Button("Cancel");
        cancelButton.setOnAction(e-> {
            confirmation.setVisible(false);
        });

        //text prompt to notify user
        Text confirmationPrompt = new Text("Are you sure you want to delete this book?");

        //box to hold delete confirmation buttons
        HBox confirmationButtons = new HBox();
        confirmationButtons.getChildren().addAll(confirmButton, cancelButton);

        //box to hold confirm prompt and buttons
        confirmation = new VBox();
        confirmation.getChildren().addAll(confirmationPrompt, confirmationButtons);
        confirmation.setVisible(false);

        //button to delete book from table
        Button deleteItemButton = new Button("Delete Book");
        deleteItemButton.setOnAction(e-> {
            confirmation.setVisible(true);
        });

        editButtons.getChildren().addAll(addItemButton, viewItemButton, deleteItemButton);
        editButtons.setAlignment(Pos.CENTER);
        editButtons.setSpacing(200);

        VBox allButtons = new VBox();
        allButtons.getChildren().addAll(editButtons, confirmation);

        root.setBottom(allButtons);

        this.setContent(root);
    }

    public static BookTab getInstance() {
        if (tab == null) {
            tab = new BookTab();
        }
        return tab;
    }
}