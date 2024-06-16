module org.sample_manager {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.zxing;
    requires com.google.zxing.javase;


    opens org.sample_manager to javafx.fxml;
    exports org.sample_manager;
}