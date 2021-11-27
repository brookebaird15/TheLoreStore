package com.example.thelorestore.Panes;

import com.example.thelorestore.Scenes.MainTableScene;
import com.example.thelorestore.Launcher;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class UpdateBookPane extends StackPane {
    public UpdateBookPane() {
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

        //Textfields to allow for input of each category
        TextField titleTextField = new TextField();
        TextField authorTextField = new TextField();
        TextField genreTextField = new TextField();
        TextField publisherTextField = new TextField();
        TextField yearTextField = new TextField();
        TextField commentTextField = new TextField();

        //An Update button to update info and return user to Main Table
        Button updateBtn = new Button("Update Item");
        updateBtn.setOnAction(event -> {
            /*** TODO
             * Create an update function
             */
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

        author.getChildren().addAll(authorTxt, authorTextField);
        author.setSpacing(5);

        genre.getChildren().addAll(genreTxt, genreTextField);
        genre.setSpacing(5);

        publisher.getChildren().addAll(publisherTxt, publisherTextField);
        publisher.setSpacing(5);

        yearPublished.getChildren().addAll(yearTxt, yearTextField);
        yearPublished.setSpacing(5);

        commentBox.getChildren().addAll(commentTxt, commentTextField);
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

        //Vboxes get added to the input field box
        inputFieldsBox.getChildren().addAll(title, author, genre, yearPublished, commentBox, checkboxes, buttons);
        inputFieldsBox.setAlignment(Pos.CENTER);
        inputFieldsBox.setMaxWidth(500);
        inputFieldsBox.setSpacing(20);

        //Add to the pane
        this.getChildren().addAll(inputFieldsBox);

    }
}
