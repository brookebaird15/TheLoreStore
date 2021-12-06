package com.example.thelorestore.Tabs;

import com.example.thelorestore.Launcher;
import com.example.thelorestore.Pojo.*;
import com.example.thelorestore.Scenes.AddUpdateBookScene;
import com.example.thelorestore.Tables.BookAuthorTable;
import com.example.thelorestore.Tables.BookGenreTable;
import com.example.thelorestore.Tables.BookTable;
import com.example.thelorestore.Tables.PublisherTable;
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.util.ArrayList;

public class BookTab extends Tab {
    private static BookTab tab;
    public static TableView tableView;
    public static DisplayBook selectedBook;
    public static ArrayList<Author> currentAuthors;
    public static ArrayList<Genre> currentGenres;
    public static Publisher bookPub;
    private VBox confirmation;
    public static boolean updating = false;
    public static boolean adding = false;

    private BookTab() {
        this.setText("Books");
        BookTable bookTable = new BookTable();
        BookAuthorTable bookAuthorTable = new BookAuthorTable();
        BookGenreTable bookGenreTable = new BookGenreTable();
        PublisherTable publisherTable = new PublisherTable();
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
        Button addBookBtn = new Button("Add Book");
        addBookBtn.setOnAction(e -> {
            currentAuthors = new ArrayList<>();
            currentGenres = new ArrayList<>();
            updating = false;
            adding = true;
            selectedBook = null;
            Launcher.mainStage.setScene(new AddUpdateBookScene());
        });

        Text updateMsg = new Text("Please select a book to update!");
        updateMsg.setVisible(false);

        //updateBookBtn directs user to UpdateBookPane
        Button updateBookBtn = new Button("Update Book");
        updateBookBtn.setOnAction(e -> {
            try {
                selectedBook = (DisplayBook) tableView.getSelectionModel().getSelectedItem();
                currentAuthors = bookAuthorTable.getAllAuthorsForBook(selectedBook.getId());
                currentGenres = bookGenreTable.getAllGenresForBook(selectedBook.getId());
                bookPub = publisherTable.getPublisher(selectedBook.getId());
                updateMsg.setVisible(false);
            } catch (Exception ex) {
                updateMsg.setVisible(true);
            }
            updating = true;
            adding = false;
            Launcher.mainStage.setScene(new AddUpdateBookScene());
        });

        //btn to confirm user wants to delete book
        Button confirmButton = new Button("Yes - Delete Book");
        confirmButton.setOnAction(e-> {
            //delete the book at the selected book ID
            selectedBook = (DisplayBook) tableView.getSelectionModel().getSelectedItem();
            Book deleteBook = new Book(selectedBook.getId());
            bookTable.deleteBook(deleteBook);
            confirmation.setVisible(false);
            refreshBookTable();
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
        confirmationButtons.setAlignment(Pos.CENTER);

        //box to hold confirm prompt and buttons
        confirmation = new VBox();
        confirmation.getChildren().addAll(confirmationPrompt, confirmationButtons);
        confirmation.setVisible(false);
        confirmation.setAlignment(Pos.CENTER);

        //button to delete book from table
        Button deleteItemButton = new Button("Delete Book");
        deleteItemButton.setOnAction(e-> {
            selectedBook = (DisplayBook) tableView.getSelectionModel().getSelectedItem();
            if(selectedBook != null) {
                confirmation.setVisible(true);
            }
        });

        editButtons.getChildren().addAll(addBookBtn, updateBookBtn, deleteItemButton);
        editButtons.setAlignment(Pos.CENTER);
        editButtons.setSpacing(200);

        VBox allButtons = new VBox();
        allButtons.getChildren().addAll(updateMsg, editButtons, confirmation);
        allButtons.setAlignment(Pos.CENTER);

        root.setBottom(allButtons);

        this.setContent(root);
    }

    public static BookTab getInstance() {
        if (tab == null) {
            tab = new BookTab();
        }
        return tab;
    }

    public static void refreshBookTable() {
        BookTable table = new BookTable();
        BookTab.tableView.getItems().clear();
        BookTab.tableView.getItems().addAll(table.displayPrettyBooks());
    }
}
