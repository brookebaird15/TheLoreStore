package com.example.thelorestore;

import com.example.thelorestore.Database.Database;
import com.example.thelorestore.Scenes.LoginScene;
import javafx.application.Application;
import javafx.stage.Stage;

public class Launcher extends Application {

    public static Stage mainStage;
    public static String stylesheet;

    @Override
    public void start(Stage stage) throws Exception {

        stylesheet = getClass().getResource("CSS/stylesheet.css").toExternalForm();

        //TODO - change project name and title
        Database.getInstance();
        mainStage = stage;
        mainStage.setScene(new LoginScene());
        mainStage.setResizable(false);
        mainStage.setTitle("The Lore Store: Inventory Manager");
        mainStage.show();
    }
}
