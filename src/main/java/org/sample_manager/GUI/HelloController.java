package org.sample_manager.GUI;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import org.sample_manager.Controller.SampleController;
import org.sample_manager.DTO.SampleDTO;

import java.util.List;
import java.util.stream.Collectors;

public class HelloController {
    private final SampleController controller = new SampleController();
    private List<SampleDTO> allSamples;
    @FXML
    private TextField searchField;

    @FXML
    private ListView<String> sampleListView;

    @FXML
    private Button searchButton;

    @FXML
    public void initialize() {
        updateListView();
    }

    @FXML
    protected void onSearchButtonClick() {
        String searchText = searchField.getText().toLowerCase();

        // Filtra a lista com base no texto de busca
        List<String> filteredSamples = allSamples.stream()
                .filter(sample -> sample.barcode.toLowerCase().contains(searchText) ||
                        sample.description.toLowerCase().contains(searchText))
                .map(sample -> sample.barcode + " - " + sample.description)
                .collect(Collectors.toList());

        sampleListView.getItems().setAll(filteredSamples);
    }

    private void updateListView() {
        allSamples = controller.getAllSamples();
        List<String> sampleList = allSamples.stream()
                .map(sample -> sample.barcode + " - " + sample.description)
                .collect(Collectors.toList());

        sampleListView.getItems().setAll(sampleList);
    }
}