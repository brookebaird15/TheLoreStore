package com.example.thelorestore.Tabs;

import com.example.thelorestore.Pojo.Genre;
import com.example.thelorestore.Pojo.Status;
import com.example.thelorestore.Tables.BookGenreTable;
import com.example.thelorestore.Tables.BookTable;
import com.example.thelorestore.Tables.GenreTable;
import com.example.thelorestore.Tables.StatusTable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Tab;
import javafx.scene.layout.BorderPane;

import java.util.ArrayList;

public class GenreStatsTab extends Tab {

    private static GenreStatsTab tab;
    public PieChart genreChart;

    private GenreStatsTab() {
        this.setText("Genre Stats");
        BorderPane root = new BorderPane();
        root.setId("genre-stats");

        //generate chart
        genreChart = new PieChart();
        genreChart.setTitle("Genres");
        genreChart.setLabelsVisible(true);
        root.setCenter(genreChart);
        generateGenreChart();
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

    public static GenreStatsTab getInstance() {
        if (tab == null) {
            tab = new GenreStatsTab();
        }
        return tab;
    }
}

