package com.example.thelorestore.Panes;

import com.example.thelorestore.Tabs.*;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;

public class MainTablePane extends BorderPane {

    public MainTablePane() {
       this.setId("main");

        //tabs for top of table
        TabPane tabPane = new TabPane();
        BookTab bookTab = BookTab.getInstance();
        AuthorTab authorTab = AuthorTab.getInstance();
        GenreTab genreTab = GenreTab.getInstance();
        PublisherTab publisherTab = PublisherTab.getInstance();
        StatusStatsTab statusStatsTab = StatusStatsTab.getInstance();
        GenreStatsTab genreStatsTab = GenreStatsTab.getInstance();
        tabPane.getTabs().addAll(bookTab, authorTab, genreTab, publisherTab, statusStatsTab, genreStatsTab);
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);

        this.setTop(tabPane);
    }
}
