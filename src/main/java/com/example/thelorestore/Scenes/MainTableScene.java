package com.example.thelorestore.Scenes;

import com.example.thelorestore.Panes.MainTablePane;
import javafx.scene.Scene;

import static com.example.thelorestore.Launcher.stylesheet;

public class MainTableScene extends Scene {

    public MainTableScene() {
        super(new MainTablePane(), 1024, 768);
        this.getStylesheets().add(stylesheet);
    }

}
