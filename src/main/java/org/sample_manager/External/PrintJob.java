package org.sample_manager.External;

import javafx.print.PrinterJob;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.io.Serializable;

public abstract class PrintJob implements Serializable {

    private static final String IMAGE_PATH = "temp_code.png";

    public static void execute() {
        Image image = new Image(new File(IMAGE_PATH).toURI().toString());
        ImageView imageView = new ImageView(image);
        PrinterJob job = PrinterJob.createPrinterJob();
        if (job != null) {
            boolean success = job.printPage(imageView);
            if (success) {
                job.endJob();
            }
        }
    }
}
