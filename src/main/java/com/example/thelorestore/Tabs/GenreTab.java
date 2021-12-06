package com.example.thelorestore.Tabs;

import com.example.thelorestore.Pojo.Genre;
import com.example.thelorestore.Tables.GenreTable;
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

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

        Text warningText = new Text("Genre name must be between 0 and 30 characters");
        warningText.setVisible(false);

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
            boolean changesSaved = false;

            if(updating) {
                //check input length
                if(genreField.getText().length() <= 30 && genreField.getText().length() != 0) {
                    Genre selection = (Genre) tableView.getSelectionModel().getSelectedItem();
                    selection.setName(genreField.getText());
                    genreTable.updateGenre(selection);
                    changesSaved = true;
                } else {
                    warningText.setVisible(true);
                }

            } else {
                //check input length
                if(genreField.getText().length() <= 30 && genreField.getText().length() != 0) {
                    Genre genre = new Genre(genreField.getText());
                    genreTable.createGenre(genre);
                    changesSaved = true;
                } else {
                    warningText.setVisible(true);
                }
            }

            //if changes saved (input length was correct), close add/update fields
            if(changesSaved) {
                //set updating back to false
                updating = false;

                //hide field and buttons
                addGenreBtn.setDisable(false);
                updateGenreBtn.setDisable(false);
                saveBtn.setVisible(false);
                genreField.setText("");
                genreField.setVisible(false);
                cancelBtn.setVisible(false);
                warningText.setVisible(false);

                //refresh table
                refreshGenreTable();
            }
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
        editButtons.getChildren().addAll(addGenreBtn, updateGenreBtn);
        editButtons.setAlignment(Pos.CENTER);

        HBox confirmButtons = new HBox();
        confirmButtons.getChildren().addAll(saveBtn, cancelBtn);
        confirmButtons.setAlignment(Pos.CENTER);

        //VBox to hold buttons HBox and textfield
        VBox genreFields = new VBox();
        genreFields.getChildren().addAll(genreField, editButtons, confirmButtons, warningText);
        genreFields.setAlignment(Pos.CENTER);

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

