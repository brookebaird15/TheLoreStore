package com.example.thelorestore.Panes.Tabs;

import com.example.thelorestore.Pojo.Genre;
import com.example.thelorestore.Tables.GenreTable;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class GenreTab extends Tab {
    private static GenreTab tab;
    public TableView tableView;
    public TextField genreField;
    public Button addGenreBtn;
    public Button saveAddBtn;
    public Button updateGenreBtn;
    public Button saveUpdateBtn;

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
        saveAddBtn = new Button();
        saveAddBtn.setOnAction(event -> {
            Genre genre = new Genre(genreField.getText());
            genreTable.createGenre(genre);
            saveAddBtn.setVisible(false);
            genreField.setVisible(false);
            refreshGenreTable();
            updateGenreBtn.setDisable(true);


        });

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

