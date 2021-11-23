package com.example.thelorestore.Panes;

import com.example.thelorestore.Panes.Tabs.AuthorTab;
import com.example.thelorestore.Panes.Tabs.GenreTab;
import com.example.thelorestore.Panes.Tabs.BookTab;
import com.example.thelorestore.Panes.Tabs.PublisherTab;
import com.example.thelorestore.Scenes.AddItemScene;
import com.example.thelorestore.Scenes.UpdateItemScene;
import com.example.thelorestore.Launcher;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class MainTablePane extends BorderPane {

    public MainTablePane() {
        //contents box holds all pane contents
        VBox contents = new VBox();

        //tabs for top of table
        TabPane tabPane = new TabPane();
        BookTab bookTab = BookTab.getInstance();
        AuthorTab authorTab = AuthorTab.getInstance();
        GenreTab genreTab = GenreTab.getInstance();
        PublisherTab publisherTab = PublisherTab.getInstance();
        tabPane.getTabs().addAll(bookTab, authorTab, genreTab, publisherTab);
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);

        this.setTop(tabPane);
    }
}
