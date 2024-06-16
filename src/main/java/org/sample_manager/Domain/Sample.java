package org.sample_manager.Domain;

import org.sample_manager.External.BarcodeGenerator;
import org.sample_manager.External.PrintJob;

import java.io.Serializable;
import java.util.Objects;
import java.util.Random;

public class Sample implements Serializable {
    private String barcode;
    private String description;
    private boolean isDangerous;
    private Date executionDate;
    private Date expirationDate;
    private String labIdentifier;


    public Sample(String description, Boolean isDangerous, Date executionDate, Date expirationDate) {
        generateBarcode();
        this.description = description;
        this.isDangerous = isDangerous;
        setDates(executionDate, expirationDate);
    }

    private void setDates(Date executionDate, Date expirationDate) {
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

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isDangerous() {
        return isDangerous;
    }

    public void setDangerous(boolean dangerous) {
        isDangerous = dangerous;
    }

    public Date getExecutionDate() {
        return executionDate;
    }

    public Date getExpirationDate() {
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

    public void setExecutionDate(Date executionDate) {
        this.executionDate = executionDate;
    }

    public void setExpirationDate(Date expirationDate) {
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

