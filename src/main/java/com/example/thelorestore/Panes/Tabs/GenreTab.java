package com.example.thelorestore.Panes.Tabs;

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
    private Button saveAddBtn;
    private Button updateGenreBtn;
    private Button saveUpdateBtn;
    private Button cancelBtn;

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
            saveAddBtn.setVisible(true);
            updateGenreBtn.setDisable(true);
            cancelBtn.setVisible(true);
        });

        //Save Button for Adding
        saveAddBtn = new Button("Save");
        saveAddBtn.setOnAction(event -> {
            Genre genre = new Genre(genreField.getText());
            genreTable.createGenre(genre);
            saveAddBtn.setVisible(false);
            genreField.setVisible(false);
            refreshGenreTable();
            updateGenreBtn.setDisable(false);
            cancelBtn.setVisible(false);
        });
        saveAddBtn.setVisible(false);

        //Allow user to update a genre
        updateGenreBtn = new Button("Update Genre");
        updateGenreBtn.setOnAction(event -> {
            genreField.setText(tableView.getSelectionModel().getSelectedItem().toString());
            genreField.setVisible(true);
            saveUpdateBtn.setVisible(true);
            addGenreBtn.setDisable(true);
            cancelBtn.setVisible(true);
        });

        //Save button for Updating
        saveUpdateBtn = new Button("Save");
        saveUpdateBtn.setOnAction(event -> {
            Genre genre = new Genre(tableView.getSelectionModel().getSelectedIndex() + 1, genreField.getText());
            genreTable.updateGenre(genre);
            saveAddBtn.setVisible(false);
            genreField.setVisible(false);
            refreshGenreTable();
            addGenreBtn.setDisable(false);
            cancelBtn.setVisible(false);
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

        //Adding children to HBox
        editButtons.getChildren().addAll(addGenreBtn, saveAddBtn, cancelBtn, saveUpdateBtn, updateGenreBtn);
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

