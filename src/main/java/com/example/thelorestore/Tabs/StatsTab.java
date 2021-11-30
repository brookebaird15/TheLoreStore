package com.example.thelorestore.Tabs;

import com.example.thelorestore.Pojo.Genre;
import com.example.thelorestore.Pojo.Status;
import com.example.thelorestore.Tables.BookGenreTable;
import com.example.thelorestore.Tables.BookTable;
import com.example.thelorestore.Tables.GenreTable;
import com.example.thelorestore.Tables.StatusTable;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Tab;
import javafx.scene.layout.BorderPane;

import java.util.ArrayList;

public class StatsTab extends Tab {
    private static StatsTab tab;
    public PieChart genreChart;
    public PieChart statusChart;

    private StatsTab() {
        this.setText("Statistics");
        BorderPane root = new BorderPane();
        String[] charts = {"Genre", "Completion Status"};

        //combobox to hold chart options
        ComboBox<String> chartPicker = new ComboBox<>();
        chartPicker.setItems(FXCollections.observableArrayList(charts));
        chartPicker.getSelectionModel().selectFirst();

        //generate charts
        genreChart = new PieChart();
        genreChart.setTitle("Genres");
        genreChart.setLabelsVisible(true);
        root.setCenter(genreChart);
        statusChart = new PieChart();
        statusChart.setTitle("Completion Status");
        statusChart.setLabelsVisible(true);

        //change chart on click
        chartPicker.setOnAction(e-> {
            if(chartPicker.getSelectionModel().getSelectedItem().equals("Genre")) {
                root.setCenter(genreChart);
            } else if (chartPicker.getSelectionModel().getSelectedItem().equals("Completion Status")) {
                root.setCenter(statusChart);
            }
        });

        root.setTop(chartPicker);
        generateGenreChart();
        generateStatusChart();
        this.setContent(root);
    }

    /**
     * Generates a pie chart depicting the genre distribution of books
     */
    public void generateGenreChart() {
        BookGenreTable bookGenreTable = new BookGenreTable();
        GenreTable genreTable = new GenreTable();

        //list of all genres
        ArrayList<Genre> genres = genreTable.getAllGenres();

        ArrayList<PieChart.Data> data = new ArrayList<>();
        for(Genre genre: genres) {
            //for each genre, count the number of occurrences in book/genre relation table
            double count = bookGenreTable.getGenreCount(genre.getId());
            if(count > 0) {
                //add genre name and count to data
                data.add(new PieChart.Data(genre.getName(), count));
            }
        }

        ObservableList<PieChart.Data> genreData = FXCollections.observableArrayList(data);

        genreChart.setData(genreData);
    }

    /**
     * Generates a pie chart depicting the status distribution of books
     */
    public void generateStatusChart() {
        StatusTable statusTable = new StatusTable();
        BookTable bookTable = new BookTable();

        //list of all statuses
        ArrayList<Status> statuses = statusTable.getAllStatus();

        ArrayList<PieChart.Data> data = new ArrayList<>();
        for(Status status: statuses) {
            //for each status, count the number of occurrences in book table
            double count = bookTable.getStatusCount(status.getId());
            if (count > 0) {
                //add status name and count to data
                data.add(new PieChart.Data(status.getName().toUpperCase(), count));
            }
        }

        ObservableList<PieChart.Data> statusData = FXCollections.observableArrayList(data);

        statusChart.setData(statusData);
    }

    public static StatsTab getInstance() {
        if (tab == null) {
            tab = new StatsTab();
        }
        return tab;
    }
}
