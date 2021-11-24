package com.example.thelorestore.Panes.Tabs;

import com.example.thelorestore.Pojo.Genre;
import com.example.thelorestore.Tables.GenreTable;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class GenreTab extends Tab {
    private static GenreTab tab;
    private TableView tableView;
    private TextField genreField;
    private Button addGenreBtn;
    private Button saveAddBtn;
    private Button updateGenreBtn;
    private Button saveUpdateBtn;
    private Button cancelBtn;

    private GenreTab() {
        this.setText("Genre");
        GenreTable genreTable = new GenreTable();
        BorderPane root = new BorderPane();
        tableView = new TableView();

        /** HBOX **/
        HBox editButtons = new HBox();

        /** BUTTONS **/
        //Allows user to add a genre
        addGenreBtn = new Button("Add Genre");
        addGenreBtn.setOnAction(event -> {
            genreField.setVisible(true);
            saveAddBtn.setVisible(true);
            updateGenreBtn.setDisable(true);
        });

        //Save Button for Adding
        saveAddBtn = new Button("Save");
        saveAddBtn.setOnAction(event -> {
            Genre genre = new Genre(genreField.getText());
            genreTable.createGenre(genre);
            saveAddBtn.setVisible(false);
            genreField.setVisible(false);
            refreshGenreTable();
            updateGenreBtn.setDisable(true);
        });
        saveAddBtn.setVisible(false);

        //Allow user to update a genre
        updateGenreBtn = new Button("Update Genre");
        updateGenreBtn.setOnAction(event -> {
            genreField.setText(tableView.getSelectionModel().getSelectedItem().toString());
            saveUpdateBtn.setVisible(true);
            addGenreBtn.setDisable(true);
        });

        //Save button for Updating
        saveUpdateBtn = new Button("Save");
        saveUpdateBtn.setOnAction(event -> {
            Genre genre = new Genre(tableView.getSelectionModel().getSelectedIndex() + 1, genreField.getText());
            genreTable.updateGenre(genre);
            saveAddBtn.setVisible(false);
            genreField.setVisible(false);
            refreshGenreTable();
            addGenreBtn.setDisable(true);
        });
        saveUpdateBtn.setVisible(false);

        //Cancel button
        cancelBtn = new Button("Cancel");
        cancelBtn.setOnAction(event -> {
            genreField.setVisible(false);
            saveAddBtn.setVisible(false);
            saveUpdateBtn.setVisible(false);
            cancelBtn.setVisible(false);
            updateGenreBtn.setDisable(false);
            addGenreBtn.setDisable(false);
        });
        cancelBtn.setVisible(false);

        TableColumn<Genre, String> genreColumn = new TableColumn<>("Genre");
        genreColumn.setCellValueFactory(e-> new SimpleStringProperty(e.getValue().getName()));
        genreColumn.setPrefWidth(1000);

        tableView.getColumns().addAll(genreColumn);
        tableView.getItems().addAll(genreTable.getAllGenres());

        root.setCenter(tableView);

        //Set genreField to false visibility
        genreField.setVisible(false);

        this.setContent(root);
    }

    public static GenreTab getInstance() {
        if (tab == null) {
            tab = new GenreTab();
        }
        return tab;
    }

    /**
     * Method to refresh the table of it's values and place them inside a tableview
     */
    public void refreshGenreTable(){
        GenreTable table = new GenreTable();
        tableView.getItems().clear();
        tableView.getItems().addAll(table.getAllGenres());
    }
}

