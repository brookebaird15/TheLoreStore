package com.example.thelorestore.Panes;

import com.example.thelorestore.Tabs.*;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class MainTablePane extends BorderPane {

    public MainTablePane() {
       this.setId("main");

        //tabs for top of table
        TabPane tabPane = new TabPane();
        BookTab bookTab = BookTab.getInstance();
        AuthorTab authorTab = AuthorTab.getInstance();
        GenreTab genreTab = GenreTab.getInstance();
        PublisherTab publisherTab = PublisherTab.getInstance();
        StatsTab statsTab = StatsTab.getInstance();
        tabPane.getTabs().addAll(bookTab, authorTab, genreTab, publisherTab, statsTab);
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);

        this.setTop(tabPane);
    }
}
