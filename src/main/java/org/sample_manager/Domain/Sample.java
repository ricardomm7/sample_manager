package org.sample_manager.Domain;

import org.sample_manager.External.BarcodeGenerator;
import org.sample_manager.External.PrintJob;
import org.sample_manager.Util.Exceptions.DateException;
import org.sample_manager.Util.Exceptions.EmptyStringException;
import org.sample_manager.Util.Exceptions.SymbolsStringException;
import org.sample_manager.Util.Exceptions.TemperatureException;
import org.sample_manager.Util.StringValidator;
import org.sample_manager.Util.TemperatureValidator;

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
    private Boolean firstTimePrint;
    private Double temperature;

    public Sample(String description, HazardTypes hazard, LocalDate executionDate, LocalDate expirationDate, Boolean firstTimePrint, String identifier, Double temperature) throws EmptyStringException, SymbolsStringException, TemperatureException, DateException {
        setDescription(description);
        setHazard(hazard);
        setDates(executionDate, expirationDate);
        setIdentifier(identifier);
        setTemperature(temperature);
        this.firstTimePrint = firstTimePrint;
        generateBarcode();
    }

    public void setTemperature(Double temperature) throws TemperatureException {
        TemperatureValidator.validateRange(temperature, "Temperature");
        this.temperature = temperature;
    }

    public Double getTemperature() {
        return temperature;
    }

    private void setDates(LocalDate executionDate, LocalDate expirationDate) throws DateException {
        if (!executionDate.isAfter(expirationDate)) {
            setExecutionDate(executionDate);
            setExpirationDate(expirationDate);
        } else {
            throw new DateException("The expiration date must be after the execution date.");
        }
    }

    public void generateBarcode() {
        this.barcode = this.identifier.replaceAll("\\s+", "").toLowerCase() + generateRandomNumericString();
        if (firstTimePrint) {
            runAndPrint();
        }
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
        this.firstTimePrint = false;
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

    public HazardTypes getHazard() {
        return hazard;
    }

    public void setHazard(HazardTypes hazard) {
        this.hazard = hazard;
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

    public void setIdentifier(String identifier) throws EmptyStringException, SymbolsStringException {
        StringValidator.validateNotEmpty(identifier, "Identifier");
        StringValidator.validateSymbols(identifier, "Identifier");
        this.identifier = identifier;
    }

    public void setFirstTimePrint(Boolean firstTimePrint) {
        this.firstTimePrint = firstTimePrint;
    }

    public Boolean getFirstTimePrint() {
        return firstTimePrint;
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

