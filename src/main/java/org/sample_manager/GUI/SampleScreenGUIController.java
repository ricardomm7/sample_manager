package org.sample_manager.GUI;

import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.util.Callback;
import org.sample_manager.Controller.SampleController;
import org.sample_manager.DTO.SampleDTO;
import org.sample_manager.Domain.HazardTypes;
import org.sample_manager.Util.Exceptions.EmptyStringException;
import org.sample_manager.Util.Exceptions.SymbolsStringException;

import java.text.Normalizer;
import java.time.LocalDate;
import java.util.List;
import java.util.regex.Pattern;
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

        ChoiceBox<HazardTypes> isDangerousField = new ChoiceBox<>();
        isDangerousField.getItems().addAll(HazardTypes.values());
        isDangerousField.setValue(HazardTypes.NONE);

        DatePicker executionDatePicker = new DatePicker();
        DatePicker expirationDatePicker = new DatePicker();

        TextField identifierField = new TextField();
        identifierField.setPromptText("i: No accents or symbols.");

        grid.add(new Label("Description:"), 0, 0);
        grid.add(descriptionField, 1, 0);
        grid.add(new Label("Hazard Type:"), 0, 1);
        grid.add(isDangerousField, 1, 1);
        grid.add(new Label("Execution Date:"), 0, 2);
        grid.add(executionDatePicker, 1, 2);
        grid.add(new Label("Expiration Date:"), 0, 3);
        grid.add(expirationDatePicker, 1, 3);
        grid.add(new Label("Identifier:"), 0, 4);
        grid.add(identifierField, 1, 4);

        dialog.getDialogPane().setContent(grid);

        // Button reference
        Button createButton = (Button) dialog.getDialogPane().lookupButton(createButtonType);
        createButton.setDisable(true); // Initially disable the create button

        identifierField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.length() > 20) {
                identifierField.setText(oldValue);
            } else {
                if (isIdentifierValid(newValue)) {
                    identifierField.setStyle("");
                    createButton.setDisable(false);
                } else {
                    identifierField.setStyle("-fx-border-color: red;");
                    createButton.setDisable(true);
                }
            }
        });

        dialog.setResultConverter(new Callback<ButtonType, SampleDTO>() {
            @Override
            public SampleDTO call(ButtonType dialogButton) {
                if (dialogButton == createButtonType) {
                    String description = descriptionField.getText();
                    HazardTypes isDangerous = isDangerousField.getValue();
                    LocalDate executionDate = executionDatePicker.getValue();
                    LocalDate expirationDate = expirationDatePicker.getValue();
                    String identifier = identifierField.getText();

                    // Create the new sample and return
                    SampleDTO newSample = new SampleDTO(description, isDangerous, executionDate, expirationDate, true, identifier);
                    try {
                        controller.create(newSample.description, newSample.isDangerous, newSample.executionDate, newSample.expirationDate, newSample.identifier);
                    } catch (EmptyStringException | SymbolsStringException e) {
                        throw new RuntimeException(e);
                    }
                    return newSample;
                }
                return null;
            }
        });

        dialog.showAndWait().ifPresent(sampleDTO -> updateListView());
    }

    private boolean isIdentifierValid(String identifier) {
        if (identifier.length() > 20) {
            return false;
        }
        String normalized = Normalizer.normalize(identifier, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        String withoutAccents = pattern.matcher(normalized).replaceAll("");
        return identifier.equals(withoutAccents);
    }

    @FXML
    void printBarcHandler(ActionEvent event) throws EmptyStringException, SymbolsStringException {
        int selectedIdx = sampleListView.getSelectionModel().getSelectedIndex();
        if (selectedIdx != -1) {
            String selectedSampleDescription = sampleListView.getSelectionModel().getSelectedItem();
            SampleDTO selectedSample = allSamples.stream()
                    .filter(sample -> (sample.barcode + " - " + sample.description).equals(selectedSampleDescription))
                    .findFirst()
                    .orElse(null);

            if (selectedSample != null) {
                controller.printBarc(selectedSample);
            }
        }
    }

    @FXML
    void propriertiesHandler(ActionEvent event) {
        int selectedIdx = sampleListView.getSelectionModel().getSelectedIndex();
        if (selectedIdx != -1) {
            String selectedSampleDescription = sampleListView.getSelectionModel().getSelectedItem();
            SampleDTO selectedSample = allSamples.stream()
                    .filter(sample -> (sample.barcode + " - " + sample.description).equals(selectedSampleDescription))
                    .findFirst()
                    .orElse(null);

            if (selectedSample != null) {
                Dialog<SampleDTO> dialog = new Dialog<>();
                dialog.setTitle("Edit Sample");

                ButtonType saveButtonType = new ButtonType("Save", ButtonBar.ButtonData.OK_DONE);
                dialog.getDialogPane().getButtonTypes().addAll(saveButtonType, ButtonType.CANCEL);

                GridPane grid = new GridPane();
                grid.setHgap(10);
                grid.setVgap(10);

                TextField descriptionField = new TextField(selectedSample.description);

                ChoiceBox<HazardTypes> isDangerousField = new ChoiceBox<>();
                isDangerousField.getItems().addAll(HazardTypes.values());
                isDangerousField.setValue(selectedSample.isDangerous);

                DatePicker executionDatePicker = new DatePicker(selectedSample.executionDate);
                DatePicker expirationDatePicker = new DatePicker(selectedSample.expirationDate);

                grid.add(new Label("Description:"), 0, 0);
                grid.add(descriptionField, 1, 0);
                grid.add(new Label("Hazard Type:"), 0, 1);
                grid.add(isDangerousField, 1, 1);
                grid.add(new Label("Execution Date:"), 0, 2);
                grid.add(executionDatePicker, 1, 2);
                grid.add(new Label("Expiration Date:"), 0, 3);
                grid.add(expirationDatePicker, 1, 3);

                Label infoLabel = new Label("It is not possible to edit the sample identifier. \nIf you want to, create a new one.");
                infoLabel.setStyle("-fx-opacity: 0.5; -fx-font-size: 8px;");
                grid.add(infoLabel, 0, 5);

                dialog.getDialogPane().setContent(grid);

                dialog.setResultConverter(new Callback<ButtonType, SampleDTO>() {
                    @Override
                    public SampleDTO call(ButtonType dialogButton) {
                        if (dialogButton == saveButtonType) {
                            String description = descriptionField.getText();
                            HazardTypes isDangerous = isDangerousField.getValue();
                            LocalDate executionDate = executionDatePicker.getValue();
                            LocalDate expirationDate = expirationDatePicker.getValue();

                            // Update the selected sample
                            selectedSample.description = description;
                            selectedSample.isDangerous = isDangerous;
                            selectedSample.executionDate = executionDate;
                            selectedSample.expirationDate = expirationDate;

                            // Save changes to the controller
                            try {
                                controller.update(selectedSample);
                            } catch (EmptyStringException e) {
                                throw new RuntimeException(e);
                            } catch (SymbolsStringException e) {
                                throw new RuntimeException(e);
                            }
                            return selectedSample;
                        }
                        return null;
                    }
                });

                dialog.showAndWait().ifPresent(sampleDTO -> updateListView());
            }
        }
    }

    @FXML
    void removeBtnHandler(ActionEvent event) throws EmptyStringException, SymbolsStringException {
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
    void onEnterSearchHandler(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            onSearchButtonClick(new ActionEvent());
        }
    }

    @FXML
    void onSearchButtonClick(ActionEvent event) {
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