package org.sample_manager.GUI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.sample_manager.Bootstrap;

import java.io.IOException;
import java.util.Objects;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("samplelist-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Sample Manager - v0.1 (Beta)");
        stage.setScene(scene);
        stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("icon.png"))));
        stage.show();
    }

    public static void main(String[] args) {
        Bootstrap b1 = new Bootstrap();
        b1.deserializeAll();
        //b1.run();
        launch();
        b1.serializeAll();
    }
}