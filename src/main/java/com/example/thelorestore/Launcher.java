package com.example.thelorestore;

import Scenes.AddItemScene;
import Scenes.LoginScene;
import javafx.application.Application;
import javafx.stage.Stage;

public class Launcher extends Application {

    public static Stage mainStage;

    @Override
    public void start(Stage stage) throws Exception {

        mainStage = stage;
        mainStage.setScene(new LoginScene());
        mainStage.setResizable(false);
        mainStage.setTitle("The Lore Store: Inventory Manager");
        mainStage.show();
    }
}
