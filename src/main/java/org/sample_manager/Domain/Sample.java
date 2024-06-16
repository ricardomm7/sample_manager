package org.sample_manager.Domain;

import org.sample_manager.External.BarcodeGenerator;
import org.sample_manager.External.PrintJob;
import org.sample_manager.Util.DangerValidator;
import org.sample_manager.Util.Exceptions.EmptyStringException;
import org.sample_manager.Util.Exceptions.ZeroHazardException;
import org.sample_manager.Util.StringValidator;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Random;

public class Sample implements Serializable {
    private String barcode;
    private String description;
    private boolean isDangerous;
    private LocalDate executionDate;
    private LocalDate expirationDate;
    private String labIdentifier;


    public Sample(String description, Boolean isDangerous, LocalDate executionDate, LocalDate expirationDate) throws EmptyStringException, ZeroHazardException {
        generateBarcode();
        setDescription(description);
        setDangerous(isDangerous);
        setDates(executionDate, expirationDate);
    }

    private void setDates(LocalDate executionDate, LocalDate expirationDate) {
        if (!executionDate.isAfter(expirationDate)) {
            this.executionDate = executionDate;
            this.expirationDate = expirationDate;
        } else {
            throw new IllegalArgumentException("The expiration date must be after the execution date.");
        }
    }

    public void generateBarcode() {
        this.barcode = labIdentifier + generateRandomNumericString();
        runAndPrint();
    }

    private String generateRandomNumericString() {
        Random rnd = new Random();
        StringBuilder sb = new StringBuilder(20);
        for (int i = 0; i < 20; i++) {
            sb.append((char) ('0' + rnd.nextInt(10)));
        }
        return sb.toString();
    }

    private void runAndPrint() {
        BarcodeGenerator.execute(this.barcode);
        PrintJob.execute(); //MISSING the implementation of the print part
    }

    public String getBarcode() {
        return barcode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) throws EmptyStringException {
        StringValidator.validateNotEmpty(description, "Description");
        this.description = description;
    }

    public boolean isDangerous() {
        return isDangerous;
    }

    public void setDangerous(Boolean dangerous) throws ZeroHazardException {
        DangerValidator.validateNotEmpty(dangerous, "Danger");
    }

    public LocalDate getExecutionDate() {
        return executionDate;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public String getLabIdentifier() {
        return labIdentifier;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public void setLabIdentifier(String labIdentifier) {
        this.labIdentifier = labIdentifier;
    }

    public void setExecutionDate(LocalDate executionDate) {
        this.executionDate = executionDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sample sample = (Sample) o;
        return barcode.equalsIgnoreCase(sample.barcode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(barcode);
    }
}

