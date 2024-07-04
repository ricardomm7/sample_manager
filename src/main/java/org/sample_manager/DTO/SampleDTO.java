package org.sample_manager.DTO;

import org.sample_manager.Domain.HazardTypes;

import java.time.LocalDate;

public class SampleDTO {
    public String barcode;
    public String description;
    public HazardTypes isDangerous;
    public LocalDate executionDate;
    public LocalDate expirationDate;
    public String labIdentifier;
    public Boolean firstTimePrint;

    public SampleDTO(String description, HazardTypes isDangerous, LocalDate executionDate, LocalDate expirationDate, String barcode, String labIdentifier, Boolean firstTimePrint) {
        this.description = description;
        this.isDangerous = isDangerous;
        this.executionDate = executionDate;
        this.expirationDate = expirationDate;
        this.barcode = barcode;
        this.labIdentifier = labIdentifier;
        this.firstTimePrint = firstTimePrint;
    }

    public SampleDTO(String description, HazardTypes isDangerous, LocalDate executionDate, LocalDate expirationDate, Boolean firstTimePrint) {
        this.description = description;
        this.isDangerous = isDangerous;
        this.executionDate = executionDate;
        this.expirationDate = expirationDate;
        this.barcode = null;
        this.labIdentifier = null;
        this.firstTimePrint = firstTimePrint;
    }
}
