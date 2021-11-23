package com.example.thelorestore.Panes;

import com.example.thelorestore.Scenes.MainTableScene;
import com.example.thelorestore.Launcher;
import javafx.animation.SequentialTransition;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Text;

//Extend StackPane
public class LoginPane extends StackPane {
    public LoginPane(){

        Text user = new Text("Username");
        Text password = new Text("Password");

        ImageView booksImage = new ImageView(new Image("file:Images/booksLogin.jpg"));

//        New login button
        Button loginBtn = new Button("Log in");
        //Event Handling of login button to the main table
        loginBtn.setOnAction(event -> {
                    Launcher.mainStage.setScene(new MainTableScene());
                    /*** TODO
                     * Check login credentials for logging into database
                     */
                });
            TextField userTextField = new TextField();
            TextField pwTextField = new TextField();

            //The vboxes used to set content in middle, holds username & password
            VBox usernameBox = new VBox();
            VBox passwordBox = new VBox();
            usernameBox.getChildren().addAll(user, userTextField);
            passwordBox.getChildren().addAll(password, pwTextField);

            //Setting the alignments for username & password vboxes
            usernameBox.setAlignment(Pos.CENTER);
            passwordBox.setAlignment(Pos.CENTER);


            //Holds all the username and password vboxes
            VBox loginBox = new VBox();
            loginBox.getChildren().addAll(usernameBox, passwordBox, loginBtn);
            loginBox.setAlignment(Pos.CENTER);
            loginBox.setSpacing(10);
            loginBox.setMaxWidth(200);

            this.getChildren().addAll(booksImage, loginBox);

        //Animations for background
        SequentialTransition moveImage = new SequentialTransition();
            moveImage.getChildren().addAll(
            )
        }




}
