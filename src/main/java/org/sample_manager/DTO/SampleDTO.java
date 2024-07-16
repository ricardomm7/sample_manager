package org.sample_manager.DTO;

import org.sample_manager.Domain.HazardTypes;

import java.time.LocalDate;

public class SampleDTO {
    public String barcode;
    public String description;
    public HazardTypes hazard;
    public LocalDate executionDate;
    public LocalDate expirationDate;
    public String identifier;
    public Boolean firstTimePrint;
    public Double temperature;

    public SampleDTO(String description, HazardTypes hazard, LocalDate executionDate, LocalDate expirationDate, String barcode, String identifier, Boolean firstTimePrint, Double temperature) {
        this.description = description;
        this.hazard = hazard;
        this.executionDate = executionDate;
        this.expirationDate = expirationDate;
        this.barcode = barcode;
        this.identifier = identifier;
        this.firstTimePrint = firstTimePrint;
        this.temperature = temperature;
    }

    public SampleDTO(String description, HazardTypes hazard, LocalDate executionDate, LocalDate expirationDate, Boolean firstTimePrint, String identifier, Double temperature) {
        this.description = description;
        this.hazard = hazard;
        this.executionDate = executionDate;
        this.expirationDate = expirationDate;
        this.barcode = null;
        this.identifier = identifier;
        this.firstTimePrint = firstTimePrint;
        this.temperature = temperature;
    }
}
