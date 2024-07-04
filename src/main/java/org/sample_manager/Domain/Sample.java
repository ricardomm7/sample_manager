package org.sample_manager.Domain;

import org.sample_manager.External.BarcodeGenerator;
import org.sample_manager.External.PrintJob;
import org.sample_manager.Util.Exceptions.EmptyStringException;
import org.sample_manager.Util.StringValidator;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Random;

public class Sample implements Serializable {
    private String barcode;
    private String description;
    private HazardTypes hazard;
    private LocalDate executionDate;
    private LocalDate expirationDate;
    private String identifier;

    public Sample(String description, HazardTypes hazard, LocalDate executionDate, LocalDate expirationDate) throws EmptyStringException {
        generateBarcode();
        setDescription(description);
        setDangerous(hazard);
        setDates(executionDate, expirationDate);
    }

    private void setDates(LocalDate executionDate, LocalDate expirationDate) {
        if (!executionDate.isAfter(expirationDate)) {
            setExecutionDate(executionDate);
            setExpirationDate(expirationDate);
        } else {
            throw new IllegalArgumentException("The expiration date must be after the execution date.");
        }
    }

    public void generateBarcode() {
        this.barcode = identifier + generateRandomNumericString();
    }

    private String generateRandomNumericString() {
        Random rnd = new Random();
        StringBuilder sb = new StringBuilder(20);
        for (int i = 0; i < 20; i++) {
            sb.append((char) ('0' + rnd.nextInt(10)));
        }
        return sb.toString();
    }

    public void runAndPrint() {
        BarcodeGenerator.execute(this.barcode);
        PrintJob.execute();
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

    public HazardTypes isDangerous() {
        return hazard;
    }

    public void setDangerous(HazardTypes dangerous) {
        this.hazard = dangerous;
    }

    public LocalDate getExecutionDate() {
        return executionDate;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
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
        Sample other = (Sample) o;
        return barcode.equalsIgnoreCase(other.barcode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(barcode);
    }
}

