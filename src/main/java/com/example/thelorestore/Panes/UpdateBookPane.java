package com.example.thelorestore.Panes;

import com.example.thelorestore.Pojo.Author;
import com.example.thelorestore.Pojo.Book;
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

import static com.example.thelorestore.Tabs.BookTab.selectedBook;

public class UpdateBookPane extends StackPane {
    public UpdateBookPane() {
        AuthorTable authorTable = new AuthorTable();
        GenreTable genreTable = new GenreTable();
        PublisherTable publisherTable = new PublisherTable();

        //A vbox to hold all the input boxes
        VBox inputFieldsBox = new VBox();
        //Vbox to hold title of book
        VBox title = new VBox();
        //Vbox to hold author
        VBox author = new VBox();
        //Vbox to hold genre
        VBox genre = new VBox();
        //Vbox to hold publisher
        VBox publisher = new VBox();
        //Vbox to hold year published
        VBox yearPublished = new VBox();
        //Vbox to hold stock amount
        VBox commentBox = new VBox();

        //An Hbox to hold all the buttons
        HBox buttons = new HBox();

        //Texts for each input
        Text titleTxt = new Text("Title");
        Text authorTxt = new Text("Author");
        Text genreTxt = new Text("Genre");
        Text publisherTxt = new Text("Publisher");
        Text yearTxt = new Text("Year Published");
        Text commentTxt = new Text("Comment");

        //Textfields and combo boxes to allow for input of each category
        TextField titleTextField = new TextField();
        titleTextField.setText(selectedBook.getTitle());

        ComboBox<Author> authorList = new ComboBox<>();
        authorList.setItems(FXCollections.observableArrayList(authorTable.getAllAuthors()));

        ComboBox<Genre> genreList = new ComboBox<>();
        genreList.setItems(FXCollections.observableArrayList(genreTable.getAllGenres()));

        ComboBox<Publisher> publisherList = new ComboBox<>();
        publisherList.setItems(FXCollections.observableArrayList(publisherTable.getAllPublishers()));

        TextField yearTextField = new TextField();
        yearTextField.setText(String.valueOf(selectedBook.getYear()));
        Text warningText = new Text("Please enter a valid year");
        warningText.setVisible(false);

        TextArea commentTextField = new TextArea();
        commentTextField.setText(selectedBook.getComment());

        //Checkboxes for the status
        //TODO - have current status button selected on screen load?
        RadioButton radioButton1 = new RadioButton("Unread");
        RadioButton radioButton2 = new RadioButton("In Progress");
        RadioButton radioButton3 = new RadioButton("Completed");
        ToggleGroup buttonGroup = new ToggleGroup();
        radioButton1.setToggleGroup(buttonGroup);
        radioButton2.setToggleGroup(buttonGroup);
        radioButton3.setToggleGroup(buttonGroup);

        if(selectedBook.getStatus().equals("unread")) {
            radioButton1.setSelected(true);
        } else if (selectedBook.getStatus().equals("in progress")) {
            radioButton2.setSelected(true);
        } else if (selectedBook.getStatus().equals("completed")) {
            radioButton3.setSelected(true);
        }

        //An Update button to update info and return user to Main Table
        Button updateBtn = new Button("Update Item");
        updateBtn.setOnAction(event -> {
            //TODO - allow user to update author, pub, genre

            //variables to hold update values
            String updateTitle = titleTextField.getText();
            int updatePub = publisherList.getSelectionModel().getSelectedItem().getId();
            String updateComment = commentTextField.getText();
            int updateYear = 0;

            //checks for valid year input
            //TODO - limit input to 4 characters
            try {
                updateYear = Integer.parseInt(yearTextField.getText().trim());
                warningText.setVisible(false);
            } catch (NumberFormatException exception) {
                warningText.setVisible(true);
                yearTextField.setText("");
                return;
            }

            int updateStatus = 0;
            if(radioButton1.isSelected()) {
                updateStatus = 1;
            }
            if(radioButton2.isSelected()) {
                updateStatus = 2;
            }
            if(radioButton3.isSelected()) {
                updateStatus = 3;
            }

            //book value to be updated
            Book updateBook = new Book(updateTitle, updatePub, updateYear, updateStatus, updateComment);

            Launcher.mainStage.setScene(new MainTableScene());
        });

        //A Cancel button to return user to Main Table
        Button cancelBtn = new Button("Cancel");
        cancelBtn.setOnAction(event -> {
            Launcher.mainStage.setScene(new MainTableScene());
        });

        //Add the buttons to the hbox
        buttons.getChildren().addAll(updateBtn, cancelBtn);
        buttons.setSpacing(50);

        //Add the content of text & textfield to matching vbox
        title.getChildren().addAll(titleTxt, titleTextField);
        title.setSpacing(5);

        author.getChildren().addAll(authorTxt, authorList);
        author.setSpacing(5);

        genre.getChildren().addAll(genreTxt, genreList);
        genre.setSpacing(5);

        publisher.getChildren().addAll(publisherTxt, publisherList);
        publisher.setSpacing(5);

        yearPublished.getChildren().addAll(yearTxt, yearTextField);
        yearPublished.setSpacing(5);

        commentBox.getChildren().addAll(commentTxt, commentTextField);
        commentBox.setSpacing(5);

        //Hbox for the radio buttons
        HBox checkboxes = new HBox();
        checkboxes.setSpacing(5);
        checkboxes.getChildren().addAll(radioButton1, radioButton2, radioButton3);

        //Vboxes get added to the input field box
        inputFieldsBox.getChildren().addAll(title, author, genre, publisher, yearPublished, commentBox, checkboxes, buttons);
        inputFieldsBox.setAlignment(Pos.CENTER);
        inputFieldsBox.setMaxWidth(500);
        inputFieldsBox.setSpacing(20);

        //Add to the pane
        this.getChildren().addAll(inputFieldsBox);

    }
}
