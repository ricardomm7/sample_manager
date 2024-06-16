package org.sample_manager;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Sample Manager - v0.1 (Beta)");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        Bootstrap b1 = new Bootstrap();
        b1.deserializeAll();
        b1.run();
        launch();
        b1.serializeAll();
    }
}