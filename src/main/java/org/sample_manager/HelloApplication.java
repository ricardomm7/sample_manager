package org.sample_manager;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.*;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        SampleRepository repo;
        try {
            FileInputStream fileIn = new FileInputStream("savedSamples.clcd");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            repo = (SampleRepository) in.readObject();
            in.close();
            fileIn.close();
        } catch (FileNotFoundException e) {
            repo = new SampleRepository();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return;
        }

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();

        //for test//////////////////////////////////////////////////////
        repo.createSample("alcohol", false, new Date(2024, 5, 15), new Date(2029, 5, 15));

        for (Sample s : repo.getSampleList()) {
            System.out.println(s.getDescription());
            System.out.println(s.getBarcode());
        }
        ///////////////////////////////////////////////////////////////

        try {
            FileOutputStream fileOut = new FileOutputStream("savedSamples.clcd");
            ObjectOutputStream outStream = new ObjectOutputStream(fileOut);
            outStream.writeObject(repo);
            outStream.close();
            fileOut.close();
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch();
    }
}