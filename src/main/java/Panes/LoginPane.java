package Panes;

import Scenes.MainTableScene;
import com.example.thelorestore.Launcher;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

//Extend StackPane
public class LoginPane extends StackPane {
    public LoginPane(){
        /*** TODO
         * Add imageview that expands top half of login screen, animated on a loop going back and forth slowly
         */
        Text user = new Text("Username");
        Text password = new Text("Password");

        //New login button
        Button loginBtn = new Button("Log in");
        //Event Handling of login button to the main table
        loginBtn.setOnAction(event -> {
            Launcher.mainStage.setScene(new MainTableScene());
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
        loginBox.setMaxWidth(300);
        this.getChildren().addAll(loginBox);

    }


}
