package org.sample_manager.GUI;

import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.util.Callback;
import org.sample_manager.Controller.SampleController;
import org.sample_manager.DTO.IdentifierDTO;
import org.sample_manager.DTO.SampleDTO;
import org.sample_manager.Util.Exceptions.EmptyStringException;
import org.sample_manager.Util.Exceptions.ZeroHazardException;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class SampleScreenGUIController {
    private final SampleController controller = new SampleController();
    private List<SampleDTO> allSamples;
    @FXML
    private TextField searchField;

    @FXML
    private ListView<String> sampleListView;

    @FXML
    private Button removeBtn;

    @FXML
    private MenuItem removeMenuItem;

    @FXML
    private MenuItem printBarcMenuItem;

    @FXML
    private MenuItem propertiesMenuItem;


    @FXML
    public void initialize() {
        updateListView();
        removeBtn.disableProperty().bind(Bindings.isEmpty(sampleListView.getSelectionModel().getSelectedItems()));
        removeMenuItem.disableProperty().bind(Bindings.isEmpty(sampleListView.getSelectionModel().getSelectedItems()));
        printBarcMenuItem.disableProperty().bind(Bindings.isEmpty(sampleListView.getSelectionModel().getSelectedItems()));
        propertiesMenuItem.disableProperty().bind(Bindings.isEmpty(sampleListView.getSelectionModel().getSelectedItems()));
    }

    @FXML
    void createBtnHandler(ActionEvent event) {
        Dialog<SampleDTO> dialog = new Dialog<>();
        dialog.setTitle("Create sample");

        ButtonType createButtonType = new ButtonType("Create", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(createButtonType, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);

        TextField descriptionField = new TextField();

        ChoiceBox<Boolean> isDangerousField = new ChoiceBox<>();
        isDangerousField.getItems().addAll(true, false);
        isDangerousField.setValue(false);

        DatePicker executionDatePicker = new DatePicker();
        DatePicker expirationDatePicker = new DatePicker();

        grid.add(new Label("Description:"), 0, 0);
        grid.add(descriptionField, 1, 0);
        grid.add(new Label("Is Dangerous:"), 0, 1);
        grid.add(isDangerousField, 1, 1);
        grid.add(new Label("Execution Date:"), 0, 2);
        grid.add(executionDatePicker, 1, 2);
        grid.add(new Label("Expiration Date:"), 0, 3);
        grid.add(expirationDatePicker, 1, 3);

        dialog.getDialogPane().setContent(grid);

        dialog.setResultConverter(new Callback<ButtonType, SampleDTO>() {
            @Override
            public SampleDTO call(ButtonType dialogButton) {
                if (dialogButton == createButtonType) {
                    String description = descriptionField.getText();
                    Boolean isDangerous = isDangerousField.getValue();
                    LocalDate executionDate = executionDatePicker.getValue();
                    LocalDate expirationDate = expirationDatePicker.getValue();

                    // Create the new sample and return
                    SampleDTO newSample = new SampleDTO(description, isDangerous, executionDate, expirationDate);
                    try {
                        controller.create(newSample.description, newSample.isDangerous, newSample.executionDate, newSample.expirationDate);
                    } catch (EmptyStringException | ZeroHazardException e) {
                        throw new RuntimeException(e);
                    }
                    return newSample;
                }
                return null;
            }
        });

        dialog.showAndWait().ifPresent(sampleDTO -> updateListView());
    }

    @FXML
    void createIdHandler(ActionEvent event) {
        Dialog<IdentifierDTO> dialog = new Dialog<>();
        dialog.setTitle("Create identifier");

        ButtonType createButtonType = new ButtonType("Create", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(createButtonType, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);

        TextField name = new TextField();
        Label descriptionLabel = new Label();
        descriptionLabel.setStyle("-fx-font-weight: bold;"); // Set the text to be bold

        // Add ChangeListener to update label in real-time and enforce 20 character limit
        name.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.length() > 20) {
                name.setText(oldValue); // Prevent input beyond 20 characters
            } else {
                descriptionLabel.setText(newValue.toLowerCase().trim());
            }
        });

        grid.add(new Label("Name:"), 0, 0);
        grid.add(name, 1, 0);
        grid.add(new Label("Code Prefix:"), 0, 1);
        grid.add(descriptionLabel, 1, 1); // Add the label below the text field

        dialog.getDialogPane().setContent(grid);

        dialog.setResultConverter(new Callback<ButtonType, IdentifierDTO>() {
            @Override
            public IdentifierDTO call(ButtonType dialogButton) {
                if (dialogButton == createButtonType) {
                    String description = name.getText();

                    // Create the new identifier and return
                    IdentifierDTO id = new IdentifierDTO(description, description.toLowerCase().trim());
                    return id;
                }
                return null;
            }
        });

        dialog.showAndWait().ifPresent(identifierDTO -> updateListView());
    }


    @FXML
    void removeBtnHandler(ActionEvent event) throws EmptyStringException, ZeroHazardException {
        int selectedIdx = sampleListView.getSelectionModel().getSelectedIndex();
        if (selectedIdx != -1) {
            String selectedSampleDescription = sampleListView.getSelectionModel().getSelectedItem();
            SampleDTO selectedSample = allSamples.stream()
                    .filter(sample -> (sample.barcode + " - " + sample.description).equals(selectedSampleDescription))
                    .findFirst()
                    .orElse(null);

            if (selectedSample != null) {
                controller.remove(selectedSample);
                updateListView();
            }
        }
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

    @FXML
    void aboutMenuHandler(ActionEvent event) {
        Alert.information("About", "You're on version v0.1 (Beta), of Sample Manager.\n@ricardomm7", "About Sample Manager");
    }

    @FXML
    void closeHandler(ActionEvent event) {
        Platform.exit();
    }
}