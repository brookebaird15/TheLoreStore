package com.example.thelorestore.Panes;

import com.example.thelorestore.Tabs.BookTab;
import com.example.thelorestore.Pojo.Author;
import com.example.thelorestore.Pojo.Book;
import com.example.thelorestore.Pojo.Genre;
import com.example.thelorestore.Pojo.Publisher;
import com.example.thelorestore.Scenes.MainTableScene;
import com.example.thelorestore.Launcher;
import com.example.thelorestore.Tables.*;
import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.util.ArrayList;

public class AddBookPane extends StackPane {

    public AddBookPane() {
        ArrayList<Author> addedAuthors = new ArrayList<>();
        ArrayList<Genre> addedGenres = new ArrayList<>();

        //inputFields box holds all inputs for pane
        VBox inputFields = new VBox();

        AuthorTable authorTable = new AuthorTable();
        GenreTable genreTable = new GenreTable();
        PublisherTable publisherTable = new PublisherTable();
        BookTable bookTable = new BookTable();
        BookGenreTable bookGenreTable = new BookGenreTable();
        BookAuthorTable bookAuthorTable = new BookAuthorTable();

        VBox title = new VBox();
        Text titleText = new Text("Title");
        TextField titleInput = new TextField();
        title.getChildren().addAll(titleText, titleInput);
        title.setSpacing(5);

        VBox authorBox = new VBox();
        Text authorText = new Text("Author");
        ComboBox<Author> authorList = new ComboBox<>();
        authorList.setItems(FXCollections.observableArrayList(authorTable.getAllAuthors()));
        Text authorDisplay = new Text("");
        Button addAuthBtn = new Button("+");

        //button to add authors to a list
        addAuthBtn.setOnAction(e-> {
            Author addedAuthor = authorList.getSelectionModel().getSelectedItem();
            //add author name to display list
            authorDisplay.setText(authorDisplay.getText() + addedAuthor.toString() + ", ");
            //add author to arraylist of authors
            addedAuthors.add(addedAuthor);
        });

        //hbox to hold author input fields and display
        HBox addAuthBox = new HBox();
        addAuthBox.getChildren().addAll(authorList, addAuthBtn, authorDisplay);

        authorBox.getChildren().addAll(authorText, addAuthBox);
        authorBox.setSpacing(5);

        VBox genreBox = new VBox();
        Text genreText = new Text("Genre");
        ComboBox<Genre> genreList = new ComboBox<>();
        genreList.setItems(FXCollections.observableArrayList(genreTable.getAllGenres()));
        Text genreDisplay = new Text("");
        Button addGenreBtn = new Button("+");

        //button to add genres to a list
        addGenreBtn.setOnAction(e-> {
            Genre addedGenre = genreList.getSelectionModel().getSelectedItem();
            //add genre to display list
            genreDisplay.setText(genreDisplay.getText() + addedGenre.toString() + ", ");
            //add genre to arraylist of genres
            addedGenres.add(addedGenre);
        });

        //hbox to hold genre input and display
        HBox addGenreBox = new HBox();
        addGenreBox.getChildren().addAll(genreList, addGenreBtn, genreDisplay);

        genreBox.getChildren().addAll(genreText, addGenreBox);
        genreBox.setSpacing(5);

        VBox publisher = new VBox();
        Text publisherText = new Text("Publisher");
        ComboBox<Publisher> publisherList = new ComboBox<>();
        publisherList.setItems(FXCollections.observableArrayList(publisherTable.getAllPublishers()));
        publisher.getChildren().addAll(publisherText, publisherList);
        publisher.setSpacing(5);

        VBox year = new VBox();
        Text yearText = new Text("Year Published");
        Text warningText = new Text("Please enter a valid year");
        TextField yearInput = new TextField();
        year.getChildren().addAll(yearText, yearInput, warningText);
        year.setSpacing(5);
        warningText.setVisible(false);

        VBox commentBox = new VBox();
        Text commentText = new Text("Comment");
        TextArea commentInput = new TextArea();
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
        Button addButton = new Button("Add Book");
        addButton.setOnAction(e -> {
            String bookTitle = titleInput.getText();
            int bookPublisher = publisherList.getSelectionModel().getSelectedItem().getId();
            int bookYear = 0;

            //checks for valid year input
            //TODO - limit input to 4 characters
            try {
                bookYear = Integer.parseInt(yearInput.getText().trim());
                warningText.setVisible(false);
            } catch (NumberFormatException exception) {
                warningText.setVisible(true);
                yearInput.setText("");
                return;
            }

            int bookStatus = 0;
            if(radioButton1.isSelected()) {
                bookStatus = 1;
            }
            if(radioButton2.isSelected()) {
                bookStatus = 2;
            }
            if(radioButton3.isSelected()) {
                bookStatus = 3;
            }

            String comment = commentInput.getText();

            //create and insert new book
            Book insertBook = new Book(bookTitle, bookPublisher, bookYear, bookStatus, comment);
            Book newestBook = bookTable.createBook(insertBook);

            //for each genre added to the list, create a link in the book/genre table
            for(Genre genre: addedGenres) {
                bookGenreTable.createBookGenreRelation(newestBook, genre);
            }

            //for each author added to the list, create a link in the book/author table
            for(Author author : addedAuthors) {
                bookAuthorTable.createBookAuthorRelation(newestBook, author);
            }

            BookTab.refreshBookTable();
            Launcher.mainStage.setScene(new MainTableScene());
        });

        //Cancel button returns user to Main Table
        Button cancelButton = new Button("Cancel");
        cancelButton.setOnAction(e -> {
            Launcher.mainStage.setScene(new MainTableScene());
        });
        buttons.getChildren().addAll(addButton, cancelButton);
        buttons.setSpacing(50);

        inputFields.getChildren().addAll(title, authorBox, genreBox, publisher, year, commentBox, checkboxes, buttons);
        inputFields.setAlignment(Pos.CENTER);
        inputFields.setMaxWidth(500);
        inputFields.setSpacing(20);

        this.getChildren().addAll(inputFields);
    }
}
