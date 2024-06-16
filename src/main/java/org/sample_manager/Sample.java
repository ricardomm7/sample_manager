package org.sample_manager;

import java.io.Serializable;
import java.util.Random;

public class Sample implements Serializable {
    private String barcode;
    private String description;
    private boolean isDangerous;
    private Date executionDate;
    private Date expirationDate;
    public static String labIdentifier;


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
            System.out.println("The expiration date must be after the execution date.");
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
        Print.execute(); //MISSING the implementation of the print part
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

    public void setExecutionDate(Date executionDate) {
        this.executionDate = executionDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

}

