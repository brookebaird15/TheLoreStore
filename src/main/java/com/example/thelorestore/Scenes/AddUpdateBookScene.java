package com.example.thelorestore.Scenes;

import com.example.thelorestore.Panes.AddUpdateBookPane;
import javafx.scene.Scene;

import static com.example.thelorestore.Launcher.stylesheet;

public class AddUpdateBookScene extends Scene {

    public AddUpdateBookScene() {
        super(new AddUpdateBookPane(), 1024, 768);
        this.getStylesheets().add(stylesheet);
    }
}
