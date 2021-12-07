package com.example.thelorestore.Tabs;

import com.example.thelorestore.Pojo.Author;
import com.example.thelorestore.Pojo.Book;
import com.example.thelorestore.Pojo.Genre;
import com.example.thelorestore.Tables.AuthorTable;
import com.example.thelorestore.Tables.BookAuthorTable;
import com.example.thelorestore.Tables.BookGenreTable;
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.Arrays;

public class AuthorTab extends Tab {
    private static AuthorTab tab;
    public TableView tableView;
    private Button addAuthButton;
    private Button updateAuthButton;
    private Button cancelButton;
    private Button saveButton;
    private boolean updating;

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
        lastNameColumn.setMinWidth(150);

        tableView.getColumns().addAll(lastNameColumn, firstNameColumn, middleNameColumn);
        tableView.getItems().addAll(authorTable.getAllAuthors());
        tableView.getSelectionModel().selectFirst();
        root.setCenter(tableView);

        //textfields to allow user input
        TextField firstField = new TextField();
        firstField.setPromptText("First Name");
        TextField middleField = new TextField();
        middleField.setPromptText("Middle Name (Optional)");
        TextField lastField = new TextField();
        lastField.setPromptText("Last Name");

        //namefields box to hold input fields
        HBox nameFields = new HBox();
        nameFields.getChildren().addAll(firstField, middleField, lastField);
        nameFields.setVisible(false);
        nameFields.setAlignment(Pos.CENTER);
        nameFields.getStyleClass().add("entry-field");
        nameFields.getStyleClass().add("button-box");

        Text warningText = new Text("Author names must be between 0 and 50 characters (middle name may be left blank)");
        warningText.setVisible(false);

        //editButtons box holds add, save and update buttons
        HBox editButtons = new HBox();

       //Cancel button to cancel out of making a change
        cancelButton = new Button("Cancel");
        cancelButton.setOnAction(event -> {
            nameFields.setVisible(false);
            saveButton.setVisible(false);
            cancelButton.setVisible(false);
            updateAuthButton.setDisable(false);
            addAuthButton.setDisable(false);
            firstField.setText("");
            middleField.setText("");
            lastField.setText("");
        });
        cancelButton.setVisible(false);
        cancelButton.getStyleClass().add("cancel");

        //save button to commit changes
        saveButton = new Button("Save");
        saveButton.setOnAction(e-> {
            boolean changesSaved = false;

            if(updating) {
                if(firstField.getText().length() <= 50 && firstField.getText().length() != 0
                 && lastField.getText().length() <= 50 && lastField.getText().length() != 0
                 && middleField.getText().length() <= 50) {
                    Author selection = (Author) tableView.getSelectionModel().getSelectedItem();
                    selection.setFirstName(firstField.getText());
                    selection.setMiddleName(middleField.getText());
                    selection.setLastName(lastField.getText());
                    authorTable.updateAuthor(selection);
                    changesSaved = true;
                } else {
                    warningText.setVisible(true);
                }
            } else {
                if(firstField.getText().length() <= 50 && firstField.getText().length() != 0
                 && lastField.getText().length() <= 50 && lastField.getText().length() != 0
                 && middleField.getText().length() <= 50) {
                    String firstName = firstField.getText();
                    String middleName = middleField.getText();
                    String lastName = lastField.getText();
                    Author author = new Author(firstName, middleName, lastName);
                    authorTable.createAuthor(author);
                    changesSaved = true;
                } else {
                    warningText.setVisible(true);
                }
            }

            if(changesSaved) {
                //set updating back to false
                updating = false;

                //hide fields and buttons
                updateAuthButton.setDisable(false);
                addAuthButton.setDisable(false);
                saveButton.setVisible(false);
                cancelButton.setVisible(false);
                nameFields.setVisible(false);
                warningText.setVisible(false);
                firstField.setText("");
                middleField.setText("");
                lastField.setText("");

                //refresh table
                refreshAuthTable();
            }
        });
        saveButton.setVisible(false);

        //addAuthButton allows user to add an author
        addAuthButton = new Button("Add Author");
        addAuthButton.setOnAction(e -> {
            nameFields.setVisible(true);
            saveButton.setVisible(true);
            updateAuthButton.setDisable(true);
            cancelButton.setVisible(true);
        });

        //updateAuthButton allows user to update an author
        updateAuthButton = new Button("Update Author");
        updateAuthButton.setOnAction(e -> {
            String fullName = tableView.getSelectionModel().getSelectedItem().toString();
            System.out.println(fullName);
            String[] nameSplit = fullName.split(" ");
            System.out.println(Arrays.toString(nameSplit));
            firstField.setText(nameSplit[0]);
            middleField.setText(nameSplit[1]);
            lastField.setText(nameSplit[2]);
            nameFields.setVisible(true);
            saveButton.setVisible(true);
            addAuthButton.setDisable(true);
            cancelButton.setVisible(true);
            updating = true;
        });

        editButtons.getChildren().addAll(addAuthButton, updateAuthButton);
        editButtons.setAlignment(Pos.CENTER);
        editButtons.getStyleClass().add("button-box");

        HBox confirmButtons = new HBox();
        confirmButtons.getChildren().addAll(saveButton, cancelButton);
        confirmButtons.setAlignment(Pos.CENTER);
        confirmButtons.getStyleClass().add("button-box");

        TextArea bookList = new TextArea("");
        bookList.setEditable(false);
        bookList.setMaxSize(500, 150);

        BookAuthorTable bookAuthorTable = new BookAuthorTable();
        tableView.setOnMouseClicked(e-> {
            bookList.setText("");
            Author selection = (Author) tableView.getSelectionModel().getSelectedItem();
            ArrayList<Book> books = bookAuthorTable.getAllBooksForAuthor(selection.getId());
            for (Book book: books) {
                bookList.setText(bookList.getText() + book.getTitle() + "\n");
            }
        });

        //authfields box holds buttons and fields for entry
        VBox authFields = new VBox();
        authFields.getChildren().addAll(nameFields, editButtons, confirmButtons, warningText, bookList);
        authFields.setAlignment(Pos.CENTER);
        authFields.getStyleClass().add("fields");

        root.setBottom(authFields);

        this.setContent(root);
    }

    /**
     * Refreshes auth table with new or updated author
     */
    public void refreshAuthTable() {
        AuthorTable table = new AuthorTable();
        tableView.getItems().clear();
        tableView.getItems().addAll(table.getAllAuthors());
    }

    /**
     * Generates new tab if one does not already exist
     * @return AuthorTab
     */
    public static AuthorTab getInstance() {
        if (tab == null) {
            tab = new AuthorTab();
        }
        return tab;
    }
}
