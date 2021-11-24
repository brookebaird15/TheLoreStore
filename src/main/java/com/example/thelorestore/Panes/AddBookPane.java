package com.example.thelorestore.Panes;

import com.example.thelorestore.Pojo.Author;
import com.example.thelorestore.Pojo.Genre;
import com.example.thelorestore.Pojo.Publisher;
import com.example.thelorestore.Scenes.MainTableScene;
import com.example.thelorestore.Launcher;
import com.example.thelorestore.Tables.AuthorTable;
import com.example.thelorestore.Tables.GenreTable;
import com.example.thelorestore.Tables.PublisherTable;
import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class AddBookPane extends StackPane {

    public AddBookPane() {
        //inputFields box holds all inputs for pane
        VBox inputFields = new VBox();

        AuthorTable authorTable = new AuthorTable();
        GenreTable genreTable = new GenreTable();
        PublisherTable publisherTable = new PublisherTable();

        VBox title = new VBox();
        Text titleText = new Text("Title");
        TextField titleInput = new TextField();
        title.getChildren().addAll(titleText, titleInput);
        title.setSpacing(5);

        VBox author = new VBox();
        Text authorText = new Text("Author");
//        TextField authorInput = new TextField();
        ComboBox<Author> authorList = new ComboBox<>();
        authorList.setItems(FXCollections.observableArrayList(authorTable.getAllAuthors()));
        author.getChildren().addAll(authorText, authorList);
        author.setSpacing(5);

        VBox genre = new VBox();
        Text genreText = new Text("Genre");
//        TextField genreInput = new TextField();
        ComboBox<Genre> genreList = new ComboBox<>();
        genreList.setItems(FXCollections.observableArrayList(genreTable.getAllGenres()));
        genre.getChildren().addAll(genreText, genreList);
        genre.setSpacing(5);

        VBox publisher = new VBox();
        Text publisherText = new Text("Publisher");
//        TextField publisherInput = new TextField();
        ComboBox<Publisher> publisherList = new ComboBox<>();
        publisherList.setItems(FXCollections.observableArrayList(publisherTable.getAllPublishers()));
        publisher.getChildren().addAll(publisherText, publisherList);
        publisher.setSpacing(5);

        VBox year = new VBox();
        Text yearText = new Text("Year Published");
        TextField yearInput = new TextField();
        year.getChildren().addAll(yearText, yearInput);
        year.setSpacing(5);

        VBox commentBox = new VBox();
        Text commentText = new Text("Comment");
        TextField commentInput = new TextField();
        commentBox.getChildren().addAll(commentText, commentInput);
        commentBox.setSpacing(5);

        //Checkboxes for the status
        RadioButton radioButton1 = new RadioButton("Unread");
        RadioButton radioButton2 = new RadioButton("In Progress");
        RadioButton radioButton3 = new RadioButton("Completed");
        ToggleGroup buttonGroup = new ToggleGroup();
        radioButton1.setToggleGroup(buttonGroup);
        radioButton2.setToggleGroup(buttonGroup);
        radioButton3.setToggleGroup(buttonGroup);
        //Hbox for the radio buttons
        HBox checkboxes = new HBox();
        checkboxes.setSpacing(5);
        checkboxes.getChildren().addAll(radioButton1, radioButton2, radioButton3);

        //buttons box holds all buttons for pane
        HBox buttons = new HBox();

        //Add button saves info and returns user to Main Table
        Button addButton = new Button("Add Item");
        addButton.setOnAction(e -> {
            //TODO: create add button function
            Launcher.mainStage.setScene(new MainTableScene());
        });

        //Cancel button returns user to Main Table
        Button cancelButton = new Button("Cancel");
        cancelButton.setOnAction(e -> {
            Launcher.mainStage.setScene(new MainTableScene());
        });
        buttons.getChildren().addAll(addButton, cancelButton);
        buttons.setSpacing(50);

        inputFields.getChildren().addAll(title, author, genre, publisher, year, commentBox, checkboxes, buttons);
        inputFields.setAlignment(Pos.CENTER);
        inputFields.setMaxWidth(500);
        inputFields.setSpacing(20);

        this.getChildren().addAll(inputFields);
    }
}
