package com.example.thelorestore.Tabs;

import com.example.thelorestore.Pojo.Author;
import com.example.thelorestore.Tables.AuthorTable;
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.Arrays;

public class AuthorTab extends Tab {
    private static AuthorTab tab;
    public TableView tableView;
    private Button addAuthButton;
    private Button updateAuthButton;
    private Button cancelButton;
    private Button saveButton;
//    private Button saveUpdateButton;
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

        tableView.getColumns().addAll(firstNameColumn, middleNameColumn, lastNameColumn);
        tableView.getItems().addAll(authorTable.getAllAuthors());
        tableView.getSelectionModel().selectFirst();
        root.setCenter(tableView);

        //textfields to allow user input
        TextField firstField = new TextField();
        TextField middleField = new TextField();
        TextField lastField = new TextField();

        //namefields box to hold input fields
        HBox nameFields = new HBox();
        nameFields.getChildren().addAll(firstField, middleField, lastField);
        nameFields.setVisible(false);

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
        });
        cancelButton.setVisible(false);


        //saveAddButton saves changes made with the add button
        saveButton = new Button("Save");
        saveButton.setOnAction(e-> {
            if(updating) {
                //TODO - get author by id not index?
                int index = tableView.getSelectionModel().getSelectedIndex() + 1;
                String firstName = firstField.getText();
                String middleName = middleField.getText();
                String lastName = lastField.getText();
                Author author = new Author(index, firstName, middleName, lastName);
                authorTable.updateAuthor(author);
                addAuthButton.setDisable(false);
            } else {
                String firstName = firstField.getText();
                String middleName = middleField.getText();
                String lastName = lastField.getText();
                Author author = new Author(firstName, middleName, lastName);
                authorTable.createAuthor(author);
                updateAuthButton.setDisable(false);
            }
            //set updating back to false
            updating = false;

            //hide fields and buttons
            saveButton.setVisible(false);
            cancelButton.setVisible(false);
            nameFields.setVisible(false);

            //refresh table
            refreshAuthTable();
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

        editButtons.getChildren().addAll(addAuthButton, saveButton, cancelButton, updateAuthButton);
        editButtons.setAlignment(Pos.CENTER);
        editButtons.requestFocus();

        //authfields box holds buttons and fields for entry
        VBox authFields = new VBox();
        authFields.getChildren().addAll(nameFields, editButtons);

        root.setBottom(authFields);

        this.setContent(root);
    }

    public void refreshAuthTable() {
        AuthorTable table = new AuthorTable();
        tableView.getItems().clear();
        tableView.getItems().addAll(table.getAllAuthors());
    }

    public static AuthorTab getInstance() {
        if (tab == null) {
            tab = new AuthorTab();
        }
        return tab;
    }
}