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

import static com.example.thelorestore.Tabs.BookTab.*;

public class AddBookPane extends StackPane {

    public AddBookPane() {
        //ArrayLists to hold authors and genres
        ArrayList<Author> authorList = new ArrayList<>();
        ArrayList<Genre> genreList = new ArrayList<>();

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
        ComboBox<Author> authorCombo = new ComboBox<>();
        authorCombo.setItems(FXCollections.observableArrayList(authorTable.getAllAuthors()));
        Text authorDisplay = new Text("");
        Button addAuthBtn = new Button("+");

        //button to add authors to a list
        addAuthBtn.setOnAction(e-> {
            Author addedAuthor = authorCombo.getSelectionModel().getSelectedItem();
            //add author name to display list
            authorDisplay.setText(authorDisplay.getText() + addedAuthor.toString() + ", ");
            //add author to arraylist of authors
            authorList.add(addedAuthor);
        });

        //hbox to hold author input fields and display
        HBox addAuthBox = new HBox();
        addAuthBox.getChildren().addAll(authorCombo, addAuthBtn, authorDisplay);

        authorBox.getChildren().addAll(authorText, addAuthBox);
        authorBox.setSpacing(5);

        VBox genreBox = new VBox();
        Text genreText = new Text("Genre");
        ComboBox<Genre> genreCombo = new ComboBox<>();
        genreCombo.setItems(FXCollections.observableArrayList(genreTable.getAllGenres()));
        Text genreDisplay = new Text("");
        Button addGenreBtn = new Button("+");

        //button to add genres to a list
        addGenreBtn.setOnAction(e-> {
            Genre addedGenre = genreCombo.getSelectionModel().getSelectedItem();
            //add genre to display list
            genreDisplay.setText(genreDisplay.getText() + addedGenre.toString() + ", ");
            //add genre to arraylist of genres
            genreList.add(addedGenre);
        });

        //hbox to hold genre input and display
        HBox addGenreBox = new HBox();
        addGenreBox.getChildren().addAll(genreCombo, addGenreBtn, genreDisplay);

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

        if(updating) {
            //set fields by default if updating a book
            titleInput.setText(selectedBook.getTitle());
            publisherList.getSelectionModel().select(bookPub);
            yearInput.setText(selectedBook.getYear());
            commentInput.setText(selectedBook.getComment());
            if(selectedBook.getStatus().equals("unread")) {
                radioButton1.setSelected(true);
            } else if (selectedBook.getStatus().equals("in progress")) {
                radioButton2.setSelected(true);
            } else if (selectedBook.getStatus().equals("completed")) {
                radioButton3.setSelected(true);
            }

            //TODO - displays book authors and genres but does not update them?
            //TODO - also, add a way for user to remove author/genre? <- is it necessary????
            for(Author author : bookAuthors) {
                authorDisplay.setText(authorDisplay.getText() + author.toString() + ", ");
                System.out.println(author);
            }

            for(Genre genre : bookGenres) {
                genreDisplay.setText(genreDisplay.getText() + genre.toString() + ", ");
                System.out.println(genre);
            }
        }

        //buttons box holds all buttons for pane
        HBox buttons = new HBox();

        //button saves info and returns user to Main Table
        Button saveButton = new Button("Save Book");
        saveButton.setOnAction(e -> {
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

            //book to be inserted
            Book insertBook = new Book(bookTitle, bookPublisher, bookYear, bookStatus, comment);

            if(adding) {
                //create and return new book
                Book newestBook = bookTable.createBook(insertBook);

                //for each genre added to the list, create a link in the book/genre table
                for(Genre genre: genreList) {
                    bookGenreTable.createBookGenreRelation(newestBook, genre);
                }

                //for each author added to the list, create a link in the book/author table
                for(Author author : authorList) {
                    bookAuthorTable.createBookAuthorRelation(newestBook, author);
                }
            }

            if(updating) {
                insertBook.setId(selectedBook.getId());

                //update book
                bookTable.updateBook(insertBook);

                //remove all previous relations
                bookGenreTable.removeGenreRelation(insertBook);
                bookAuthorTable.removeAuthorRelation(insertBook);

                //add updated genre relations
                for(Genre genre: genreList) {
                    System.out.println("UPDATE GENRE: " + genre);
                    bookGenreTable.createBookGenreRelation(insertBook, genre);
                }

                //add updated author relations
                for(Author author: authorList) {
                    System.out.println("UPDATE AUTHOR: " + author);
                    bookAuthorTable.createBookAuthorRelation(insertBook, author);
                }
            }

            BookTab.refreshBookTable();
            Launcher.mainStage.setScene(new MainTableScene());
        });

        //Cancel button returns user to Main Table
        Button cancelButton = new Button("Cancel");
        cancelButton.setOnAction(e -> {
            Launcher.mainStage.setScene(new MainTableScene());
            BookTab.updating = false;
        });
        buttons.getChildren().addAll(saveButton, cancelButton);
        buttons.setSpacing(50);

        inputFields.getChildren().addAll(title, authorBox, genreBox, publisher, year, commentBox, checkboxes, buttons);
        inputFields.setAlignment(Pos.CENTER);
        inputFields.setMaxWidth(500);
        inputFields.setSpacing(20);

        this.getChildren().addAll(inputFields);
    }
}
