package com.example.thelorestore.Panes.Tabs;

import com.example.thelorestore.Pojo.Genre;
import com.example.thelorestore.Tables.GenreTable;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;

public class GenreTab extends Tab {
    private static GenreTab tab;
    public TableView tableView;

    private GenreTab() {
        this.setText("Genre");
        GenreTable genreTable = new GenreTable();
        BorderPane root = new BorderPane();
        tableView = new TableView();

        TableColumn<Genre, String> genreColumn = new TableColumn<>("Genre");
        genreColumn.setCellValueFactory(e-> new SimpleStringProperty(e.getValue().getName()));

        tableView.getColumns().addAll(genreColumn);
        tableView.getItems().addAll(genreTable.getAllGenres());
        genreColumn.setPrefWidth(700);
        root.setCenter(tableView);

        this.setContent(root);
    }

    public static GenreTab getInstance() {
        if (tab == null) {
            tab = new GenreTab();
        }
        return tab;
    }
}
