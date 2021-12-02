package com.example.thelorestore.Tabs;

import com.example.thelorestore.Pojo.Genre;
import com.example.thelorestore.Tables.GenreTable;
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class GenreTab extends Tab {
    private static GenreTab tab;
    private TableView tableView;
    private TextField genreField;
    private Button addGenreBtn;
    private Button saveBtn;
    private Button updateGenreBtn;
    private Button cancelBtn;
    private boolean updating;

    private GenreTab() {
        this.setText("Genre");
        GenreTable genreTable = new GenreTable();
        BorderPane root = new BorderPane();
        tableView = new TableView();

        /** TABLEVIEW **/
        TableColumn<Genre, String> genreColumn = new TableColumn<>("Genre");
        genreColumn.setCellValueFactory(e-> new SimpleStringProperty(e.getValue().getName()));
        genreColumn.setPrefWidth(1000);

        tableView.getColumns().addAll(genreColumn);
        tableView.getItems().addAll(genreTable.getAllGenres());
        tableView.getSelectionModel().selectFirst();

        /** TEXTFIELD **/
        //Set genreField to false visibility
        genreField = new TextField();
        genreField.setVisible(false);

        /** HBOX **/
        HBox editButtons = new HBox();

        /** BUTTONS **/
        //Allows user to add a genre
        addGenreBtn = new Button("Add Genre");
        addGenreBtn.setOnAction(event -> {
            genreField.setVisible(true);
            saveBtn.setVisible(true);
            updateGenreBtn.setDisable(true);
            cancelBtn.setVisible(true);
        });

        //Save Button to commit changes
        saveBtn = new Button("Save");
        saveBtn.setOnAction(event -> {
            //TODO - catch exception where user entry is too long
            if(updating) {
                Genre selection = (Genre) tableView.getSelectionModel().getSelectedItem();
                selection.setName(genreField.getText());
                genreTable.updateGenre(selection);
                addGenreBtn.setDisable(false);
            } else {
                Genre genre = new Genre(genreField.getText());
                genreTable.createGenre(genre);
                updateGenreBtn.setDisable(false);
            }
            //set updating back to false
            updating = false;

            //hide field and buttons
            saveBtn.setVisible(false);
            genreField.setVisible(false);
            cancelBtn.setVisible(false);

            //refresh table
            refreshGenreTable();
        });
        saveBtn.setVisible(false);

        //Allow user to update a genre
        updateGenreBtn = new Button("Update Genre");
        updateGenreBtn.setOnAction(event -> {
            genreField.setText(tableView.getSelectionModel().getSelectedItem().toString());
            genreField.setVisible(true);
            saveBtn.setVisible(true);
            addGenreBtn.setDisable(true);
            cancelBtn.setVisible(true);
            updating = true;
        });

        //Cancel button
        cancelBtn = new Button("Cancel");
        cancelBtn.setOnAction(event -> {
            genreField.setVisible(false);
            saveBtn.setVisible(false);
            cancelBtn.setVisible(false);
            updateGenreBtn.setDisable(false);
            addGenreBtn.setDisable(false);
        });
        cancelBtn.setVisible(false);

        //Adding children to HBox
        editButtons.getChildren().addAll(addGenreBtn, saveBtn, cancelBtn, updateGenreBtn);
        editButtons.setAlignment(Pos.CENTER);

        //VBox to hold buttons HBox and textfield
        VBox genreFields = new VBox();
        genreFields.getChildren().addAll(genreField, editButtons);

        //Set the tableview to the center of the root
        root.setCenter(tableView);
        //Set the textfield and buttons to bottom of root
        root.setBottom(genreFields);
        this.setContent(root);
    }

    /**
     *  Generate a new tab if one doesn't exist
     * @return tab
     */
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

