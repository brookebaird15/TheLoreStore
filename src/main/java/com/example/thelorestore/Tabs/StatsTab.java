package com.example.thelorestore.Tabs;

import com.example.thelorestore.Pojo.Genre;
import com.example.thelorestore.Tables.BookGenreTable;
import com.example.thelorestore.Tables.GenreTable;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
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
        genreChart = new PieChart();
        genreChart.setTitle("Genres");
        genreChart.setLabelsVisible(true);
        root.setCenter(genreChart);
        generateChart();
        this.setContent(root);
    }

    public void generateChart() {
        BookGenreTable bookGenreTable = new BookGenreTable();
        GenreTable genreTable = new GenreTable();

        ArrayList<Genre> genres = genreTable.getAllGenres();

        ArrayList<PieChart.Data> data = new ArrayList<>();

        for(Genre genre: genres) {
            double count = bookGenreTable.getGenreCount(genre.getId());
            if(count > 0) {
                data.add(new PieChart.Data(genre.getName(), count));
            }
        }

        ObservableList<PieChart.Data> genreData = FXCollections.observableArrayList(data);

        genreChart.setData(genreData);

//        for()
    }

    public static StatsTab getInstance() {
        if (tab == null) {
            tab = new StatsTab();
        }
        return tab;
    }
}
