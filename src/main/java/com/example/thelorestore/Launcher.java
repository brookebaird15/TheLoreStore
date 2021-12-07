package com.example.thelorestore;

import com.example.thelorestore.Database.Database;
import com.example.thelorestore.Panes.LoginPane;
import com.example.thelorestore.Scenes.LoginScene;
import javafx.application.Application;
import javafx.stage.Stage;

public class Launcher extends Application {

    public static Stage mainStage;
    public static String stylesheet;

    @Override
    public void start(Stage stage) throws Exception {

        stylesheet = getClass().getResource("CSS/stylesheet.css").toExternalForm();
        mainStage = stage;
        mainStage.setScene(new LoginScene());
        mainStage.setResizable(false);
        mainStage.setTitle("Book Tracker - by Ashley McCallum and Brooke Baird");
        mainStage.show();
    }
}
