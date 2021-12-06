package com.example.thelorestore.Panes;

import com.example.thelorestore.Database.DBConst;
import com.example.thelorestore.Database.DBTableValues;
import com.example.thelorestore.Database.Database;
import com.example.thelorestore.Scenes.MainTableScene;

import javafx.animation.SequentialTransition;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

import static com.example.thelorestore.Launcher.mainStage;

public class LoginPane extends StackPane {

    public static File loginFile = new File("credentials.txt");
    public static Text loginError = new Text("Invalid login - please try again");
    public static TextField userTextField = new TextField();
    public static TextField pwTextField = new PasswordField();
    public static TextField dbTextField = new TextField();

    public LoginPane(){
        Text user = new Text("Username");
        Text password = new Text("Password");
        Text database = new Text("Database Name");
        ImageView booksImage = new ImageView(new Image("file:Images/booksLogin.jpg"));
        loginError.setVisible(false);

        //New login button
        Button loginBtn = new Button("Log in");
        loginBtn.setMaxSize(150, 100);
        //Event Handling of login button to the main table
        loginBtn.setOnAction(event -> {
            logCredentials(loginFile, userTextField, pwTextField, dbTextField);
            validateLogin(loginFile);
        });

        //The vboxes used to set content in middle, holds username & password
        VBox usernameBox = new VBox();
        VBox passwordBox = new VBox();
        VBox databaseBox = new VBox();
        usernameBox.getChildren().addAll(user, userTextField);
        passwordBox.getChildren().addAll(password, pwTextField);
        databaseBox.getChildren().addAll(database, dbTextField);

        //Setting the alignments for username & password vboxes
        usernameBox.setAlignment(Pos.CENTER);
        passwordBox.setAlignment(Pos.CENTER);
        databaseBox.setAlignment(Pos.CENTER);

        /**
         * Login box to host the username, password textfields and login button
         * @author Brooke Baird
         */
        VBox loginBox = new VBox();
        loginBox.setStyle("-fx-background-color: rgba(255,255,255,0.5);"+
                "-fx-padding: 25;"+
                "-fx-background-radius: 50;"+
                "-fx-border-radius: 50;");
        loginBox.getChildren().addAll(loginError, usernameBox, passwordBox, databaseBox, loginBtn);
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
     * Logs the credentials entered to a text file
     * @param file is the file credentials are written to
     * @param user is the username input field
     * @param pass is the password input field
     * @param database is the database name input field
     */
    public void logCredentials(File file, TextField user, TextField pass, TextField database) {
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter(file, true));
            out.write(user.getText() + "\n" + pass.getText() + "\n" + database.getText());
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Reads the credentials from a text file
     * Assigns them to the DBCONST variables
     * Attempts login, if successful moves user to MainTableScene
     * @param file is the file being read from
     */
    public void validateLogin(File file) {
        ArrayList<String> credentials = new ArrayList<>();
        try {
            Scanner input = new Scanner(file);
            input.useDelimiter("\n");
            //read values from file
            while (input.hasNext()) {
                credentials.add(input.next());
            }
            //assign credentials
            DBConst.DB_USER = credentials.get(0);
            DBConst.DB_PASS = credentials.get(1);
            DBConst.DB_NAME = credentials.get(2);
            input.close();
            //delete login file once assigned
            loginFile.delete();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Database.getInstance();
        mainStage.setScene(new MainTableScene());
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
}
