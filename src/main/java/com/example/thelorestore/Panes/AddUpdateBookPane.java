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
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.util.ArrayList;

import static com.example.thelorestore.Tabs.BookTab.*;

public class AddUpdateBookPane extends BorderPane {

    private ComboBox<Author> currentAuthCombo;
    private ComboBox<Genre> currentGenreCombo;
    private ArrayList<Genre> allGenres;
    private ArrayList<Author> allAuthors;
    private boolean validInput = true;

    public AddUpdateBookPane() {
        //inputFields box holds all inputs for pane
        VBox inputFields = new VBox();

        AuthorTable authorTable = new AuthorTable();
        GenreTable genreTable = new GenreTable();
        PublisherTable publisherTable = new PublisherTable();
        BookTable bookTable = new BookTable();
        BookGenreTable bookGenreTable = new BookGenreTable();
        BookAuthorTable bookAuthorTable = new BookAuthorTable();
        allGenres = genreTable.getAllGenres();
        allAuthors = authorTable.getAllAuthors();

        VBox title = new VBox();
        Text titleText = new Text("Title");
        TextField titleInput = new TextField();
        title.getChildren().addAll(titleText, titleInput);
        title.setSpacing(5);

        VBox authorBox = new VBox();
        Text authorText = new Text("Author");

        //combobox to hold all authors
        ComboBox<Author> allAuthorCombo = new ComboBox<>();
        allAuthorCombo.setItems(FXCollections.observableArrayList(allAuthors));
        TextArea authorDisplay = new TextArea();
        authorDisplay.setEditable(false);
        authorDisplay.setPrefWidth(150);
        authorDisplay.setPrefHeight(100);

        //button to add authors to a list
        Button addAuthBtn = new Button("+");
        addAuthBtn.setOnAction(e-> {
            Author addedAuthor = allAuthorCombo.getSelectionModel().getSelectedItem();
            //add author name to display list
            authorDisplay.setText(authorDisplay.getText() + addedAuthor.toString() + "\n");
            //add author to arraylist of authors
            currentAuthors.add(addedAuthor);
            //refresh combobox
            refreshAuthCombo(currentAuthCombo, currentAuthors);
        });

        //combobox to hold current authors
        currentAuthCombo = new ComboBox<>();
        currentAuthCombo.setItems(FXCollections.observableArrayList(currentAuthors));
        currentAuthCombo.setVisible(false);

        //button to remove authors from current auth list
        Button removeAuthBtn = new Button("-");
        removeAuthBtn.setOnAction(e-> {
            Author removedAuthor = currentAuthCombo.getSelectionModel().getSelectedItem();
            currentAuthors.remove(removedAuthor);
            authorDisplay.setText(currentAuthors.toString().replace("[", "")
                    .replace("]", "").replace(",", "\n"));
            refreshAuthCombo(currentAuthCombo, currentAuthors);
        });
        removeAuthBtn.setVisible(false);

        //hbox to hold author input fields and display
        HBox addAuthBox = new HBox();
        addAuthBox.getChildren().addAll(allAuthorCombo, addAuthBtn);
        HBox rmvAuthBox = new HBox();
        rmvAuthBox.getChildren().addAll(currentAuthCombo, removeAuthBtn);

        //holds all author related items
        authorBox.getChildren().addAll(authorText, addAuthBox, authorDisplay, rmvAuthBox);
        authorBox.setSpacing(5);

        VBox genreBox = new VBox();
        Text genreText = new Text("Genre");

        //combobox to hold all genres
        ComboBox<Genre> allGenreCombo = new ComboBox<>();
        allGenreCombo.setItems(FXCollections.observableArrayList(allGenres));
        TextArea genreDisplay = new TextArea();
        genreDisplay.setEditable(false);
        genreDisplay.setPrefHeight(100);
        genreDisplay.setPrefWidth(150);

        //button to add genres to a list
        Button addGenreBtn = new Button("+");
        addGenreBtn.setOnAction(e-> {
            Genre addedGenre = allGenreCombo.getSelectionModel().getSelectedItem();
            genreDisplay.setText(genreDisplay.getText() + addedGenre.toString() + "\n");
            //add genre to arraylist of genres
            currentGenres.add(addedGenre);
            //refresh combo box
            refreshGenreCombo(currentGenreCombo, currentGenres);
        });

        //combobox to hold current genres
        currentGenreCombo = new ComboBox<>();
        currentGenreCombo.setItems(FXCollections.observableArrayList(currentGenres));
        currentGenreCombo.setVisible(false);

        //button to remove authors from current auth list
        Button removeGenreBtn = new Button("-");
        removeGenreBtn.setOnAction(e-> {
            Genre removedGenre = currentGenreCombo.getSelectionModel().getSelectedItem();
            //remove genre from list of current genres
            currentGenres.remove(removedGenre);
            genreDisplay.setText(currentGenres.toString().replace("[", "")
                    .replace("]", "").replace(",", "\n"));
            refreshGenreCombo(currentGenreCombo, currentGenres);
        });
        removeGenreBtn.setVisible(false);

        //hbox to hold genre input and display
        HBox addGenreBox = new HBox();
        addGenreBox.getChildren().addAll(allGenreCombo, addGenreBtn);
        HBox rmvGenreBox = new HBox();
        rmvGenreBox.getChildren().addAll(currentGenreCombo, removeGenreBtn);

        //holds all genre related items
        genreBox.getChildren().addAll(genreText, addGenreBox, genreDisplay, rmvGenreBox);
        genreBox.setSpacing(5);

        VBox publisher = new VBox();
        Text publisherText = new Text("Publisher");
        ComboBox<Publisher> publisherList = new ComboBox<>();
        publisherList.setItems(FXCollections.observableArrayList(publisherTable.getAllPublishers()));
        publisher.getChildren().addAll(publisherText, publisherList);
        publisher.setSpacing(5);

        VBox year = new VBox();
        Text yearText = new Text("Year Published");
        Text yearWarningText = new Text("Please enter a valid year - YYYY");
        TextField yearInput = new TextField();
        year.getChildren().addAll(yearText, yearInput, yearWarningText);
        year.setSpacing(5);
        yearWarningText.setVisible(false);

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

        Text authorGenreWarningText = new Text("Author and genre cannot be empty");
        authorGenreWarningText.setVisible(false);

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

            for(Author author : currentAuthors) {
                authorDisplay.setText(authorDisplay.getText() + author.toString() + "\n");
                System.out.println(author);
            }

            for(Genre genre : currentGenres) {
                genreDisplay.setText(genreDisplay.getText() + genre.toString() + "\n");
                System.out.println(genre);
            }

            currentAuthCombo.setVisible(true);
            removeAuthBtn.setVisible(true);
            currentGenreCombo.setVisible(true);
            removeGenreBtn.setVisible(true);
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
            if(yearInput.getText().trim().length() != 4) {
                yearWarningText.setVisible(true);
                yearInput.setText("");
                validInput = false;
            }
            try {
                bookYear = Integer.parseInt(yearInput.getText().trim());
                yearWarningText.setVisible(false);
                validInput = true;
            } catch (NumberFormatException exception) {
                yearWarningText.setVisible(true);
                yearInput.setText("");
                validInput = false;
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

            //if author and genre are not empty, allow book to be created
            if(!currentAuthors.isEmpty() && !currentGenres.isEmpty()) {
                validInput = true;
                //book to be inserted
                Book insertBook = new Book(bookTitle, bookPublisher, bookYear, bookStatus, comment);

                if(adding) {
                    //create and return new book
                    Book newestBook = bookTable.createBook(insertBook);

                    //for each genre added to the list, create a link in the book/genre table
                    for(Genre genre: currentGenres) {
                        bookGenreTable.createBookGenreRelation(newestBook, genre);
                    }

                    //for each author added to the list, create a link in the book/author table
                    for(Author author : currentAuthors) {
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
                    for(Genre genre: currentGenres) {
                        bookGenreTable.createBookGenreRelation(insertBook, genre);
                    }

                    //add updated author relations
                    for(Author author: currentAuthors) {
                        bookAuthorTable.createBookAuthorRelation(insertBook, author);
                    }
                }
            } else {
                authorGenreWarningText.setVisible(true);
                validInput = false;
            }

            if(validInput) {
                removeGenreBtn.setVisible(false);
                currentGenreCombo.setVisible(false);
                removeAuthBtn.setVisible(false);
                currentAuthCombo.setVisible(false);
                authorGenreWarningText.setVisible(false);
                BookTab.refreshBookTable();
                Launcher.mainStage.setScene(new MainTableScene());
            }

        });

        //Cancel button returns user to Main Table
        Button cancelButton = new Button("Cancel");
        cancelButton.setOnAction(e -> {
            Launcher.mainStage.setScene(new MainTableScene());
            updating = false;
            adding = false;
        });
        buttons.getChildren().addAll(saveButton, cancelButton);
        buttons.setSpacing(50);

        inputFields.getChildren().addAll(title, publisher, year, commentBox, checkboxes, buttons);
        inputFields.setAlignment(Pos.CENTER);
        inputFields.setMaxWidth(500);
        inputFields.setSpacing(20);

        VBox authorGenreBox = new VBox();
        authorGenreBox.getChildren().addAll(authorBox, genreBox);
        authorGenreBox.setAlignment(Pos.CENTER);
        this.setLeft(inputFields);
        this.setRight(authorGenreBox);
        this.setBottom(authorGenreWarningText);
    }

    /**
     * Refreshes the combo box to clear removed authors from the list
     * @param comboBox is the box being updated
     * @param authors is the list populating the box
     */
    public static void refreshAuthCombo(ComboBox comboBox, ArrayList<Author> authors) {
        comboBox.setItems(null);
        comboBox.setItems(FXCollections.observableArrayList(authors));
    }

    /**
     * Refreshes the combo box to clear removed genres from the list
     * @param comboBox is the box being updated
     * @param genres is the list populating the box
     */
    public static void refreshGenreCombo(ComboBox comboBox, ArrayList<Genre> genres) {
        comboBox.setItems(null);
        comboBox.setItems(FXCollections.observableArrayList(genres));
    }
}
