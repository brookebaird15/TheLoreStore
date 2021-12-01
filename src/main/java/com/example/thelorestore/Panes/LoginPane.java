package com.example.thelorestore.Panes;

import com.example.thelorestore.Database.DBTableValues;
import com.example.thelorestore.Database.Database;
import com.example.thelorestore.Scenes.MainTableScene;
import com.example.thelorestore.Launcher;
import javafx.animation.SequentialTransition;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

//Extend StackPane
public class LoginPane extends StackPane {
    Database db = Database.getInstance();
    TextField userTextField = new TextField();
    TextField pwTextField = new PasswordField();
    Label successfulLogin = new Label("");
    public LoginPane(){
        Text user = new Text("Username");
        Text password = new Text("Password");
        //Textfield for username and password entry

        ImageView booksImage = new ImageView(new Image("file:Images/booksLogin.jpg"));

//        New login button
        Button loginBtn = new Button("Log in");
        loginBtn.setMaxSize(150, 100);
        //Event Handling of login button to the main table
        loginBtn.setOnAction(event -> {
                    if (userTextField.getText().isBlank() == false && !pwTextField.getText().isBlank()){
                        loginValidation();
                    } else {
                        successfulLogin.setText("Invalid login. Please try again.");
                    }
                });
        //Cancel button
        Button cancelBtn = new Button("Cancel");
        cancelBtn.setMaxSize(150, 100);
        //When pressed, exits the program
        cancelBtn.setOnAction(event -> {
           System.exit(0);
        });

            //The vboxes used to set content in middle, holds username & password
            VBox usernameBox = new VBox();
            VBox passwordBox = new VBox();
            usernameBox.getChildren().addAll(user, userTextField);
            passwordBox.getChildren().addAll(password, pwTextField);

            //Setting the alignments for username & password vboxes
            usernameBox.setAlignment(Pos.CENTER);
            passwordBox.setAlignment(Pos.CENTER);

        //Add the successful login to the pane VBox
        VBox success = new VBox();
        success.getChildren().add(successfulLogin);
        success.setAlignment(Pos.CENTER);


        /**
         * Login box to host the username, password textfields and login button
         * @author Brooke Baird
         */
        VBox loginBox = new VBox();
            loginBox.setStyle("-fx-background-color: rgba(255,255,255,0.5);"+
                    "-fx-font-size: 16;"+
                    "-fx-padding: 25;"+
                    "-fx-background-radius: 50;"+
                    "-fx-border-radius: 50;");
            loginBox.getChildren().addAll(success, usernameBox, passwordBox, loginBtn, cancelBtn);
            loginBox.setAlignment(Pos.CENTER);
            loginBox.setSpacing(10);
            loginBox.setMaxSize(350, 250);


            this.getChildren().addAll(booksImage, loginBox);

        //Animations for background
        /**
         * Sequential transition to move the image
         * @author Ashley McCallum
         */
        SequentialTransition moveImage = new SequentialTransition();
            moveImage.getChildren().addAll(translateHorizontal(booksImage, -250, 250),
                    translateVertical(booksImage, 200, -200),
                    translateHorizontal(booksImage, 250, -250),
                    translateVertical(booksImage, -200, 200));
            moveImage.setCycleCount(Timeline.INDEFINITE);
            moveImage.play();
        }


    /**
     * Translate the object on the X axis of the screen
     * @param node
     * @param fromX
     * @param toX
     * @return
     * @author Ashley McCallum
     */
    public TranslateTransition translateHorizontal(Node node, int fromX, int toX){
      TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(25), node);
      translateTransition.setFromX(fromX);
      translateTransition.setToX(toX);
      return translateTransition;
}

    /**
     * Translate the object on the Y axis of the screen
     * @param node
     * @param fromY
     * @param toY
     * @return
     * @author Ashley McCallum
     */
    public TranslateTransition translateVertical(Node node, int fromY, int toY){
        TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(25), node);
        translateTransition.setFromY(fromY);
        translateTransition.setToY(toY);
        return translateTransition;
    }

    /**
     * Connection to the database and querying the account information where if it matches the input text
     * proceed onto main table
     * @author Brooke Baird
     */
    public void loginValidation(){
        db.getConnection();

        String verifyLogin = "SELECT count(1) FROM useraccounts WHERE username = '"
                + userTextField.getText() + "'AND password = '"
                + pwTextField.getText() + "'";

        try {
            Statement getAccount = db.getConnection().createStatement();
            //Handle the query
            ResultSet accountData = getAccount.executeQuery(verifyLogin);

            //Process the query
            while(accountData.next()){
                if (accountData.getInt(1) == 1){
                    successfulLogin.setText("Login successful.");
                    Launcher.mainStage.setScene(new MainTableScene());

                } else {
                    successfulLogin.setText("Invalid login. Please try again.");
                }
            }

        } catch (SQLException e){
            e.printStackTrace();
        }
    }

}
