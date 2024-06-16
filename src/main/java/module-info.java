module org.sample_manager {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.zxing;
    requires com.google.zxing.javase;


    opens org.sample_manager to javafx.fxml;
    exports org.sample_manager;
    exports org.sample_manager.Domain;
    exports org.sample_manager.DTO;
    opens org.sample_manager.Domain to javafx.fxml;
    exports org.sample_manager.External;
    opens org.sample_manager.External to javafx.fxml;
    exports org.sample_manager.GUI;
    opens org.sample_manager.GUI to javafx.fxml;
}