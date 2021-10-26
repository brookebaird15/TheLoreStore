package com.example.thelorestore.Panes;

import com.example.thelorestore.Panes.Tabs.AuthorTab;
import com.example.thelorestore.Panes.Tabs.GenreTab;
import com.example.thelorestore.Panes.Tabs.MainTab;
import com.example.thelorestore.Panes.Tabs.PublisherTab;
import com.example.thelorestore.Scenes.AddItemScene;
import com.example.thelorestore.Scenes.UpdateItemScene;
import com.example.thelorestore.Launcher;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public class MainTablePane extends StackPane {

    public MainTablePane() {
        //contents box holds all pane contents
        VBox contents = new VBox();

        //TODO - replace placeholder with table(s)
        Rectangle tablePlaceholder = new Rectangle(700, 500, Paint.valueOf("red"));

        //tabs for top of table
        TabPane tabPane = new TabPane();
        MainTab mainTab = MainTab.getInstance();
        AuthorTab authorTab = AuthorTab.getInstance();
        GenreTab genreTab = GenreTab.getInstance();
        PublisherTab publisherTab = PublisherTab.getInstance();
        //TODO - add tab functionality, should switch between tables
        tabPane.getTabs().addAll(mainTab, authorTab, genreTab, publisherTab);
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);

        //searchInput allows the user to search by SKU
        TextField searchInput = new TextField();
        searchInput.setPromptText("Search SKU");
        searchInput.setPrefWidth(300);
        searchInput.setOnKeyPressed(e -> {
            if(e.getCode() == KeyCode.ENTER) {
                //TODO - add search function
            }
        });

        //menuSearch box holds tabs and search buttons
        HBox tabsSearch = new HBox();
        tabsSearch.getChildren().addAll(tabPane, searchInput);
        tabsSearch.setAlignment(Pos.CENTER);

        //editButtons box holds addItem and viewItem buttons
        HBox editButtons = new HBox();

        //addItemButton directs user to AddItemPane
        Button addItemButton = new Button("Add Item");
        addItemButton.setOnAction(e -> {
            Launcher.mainStage.setScene(new AddItemScene());
        });

        //viewItemButton directs user to ViewItemPane
        Button viewItemButton = new Button("View Item");
        viewItemButton.setOnAction(e -> {
            viewItemButton.setOnAction(event -> {
                Launcher.mainStage.setScene(new UpdateItemScene());
            });
        });
        editButtons.getChildren().addAll(addItemButton, viewItemButton);
        editButtons.setAlignment(Pos.CENTER);
        editButtons.setSpacing(500);
        editButtons.requestFocus();

        contents.getChildren().addAll(tabsSearch, tablePlaceholder, editButtons);
        contents.setAlignment(Pos.CENTER);

        this.getChildren().addAll(contents);
    }
}
