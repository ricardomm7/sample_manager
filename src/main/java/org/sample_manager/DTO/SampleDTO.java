package org.sample_manager.DTO;

import org.sample_manager.Domain.Date;

public class SampleDTO {
    public String barcode;
    public String description;
    public boolean isDangerous;
    public Date executionDate;
    public Date expirationDate;
    public String labIdentifier;

    public SampleDTO(String description, Boolean isDangerous, Date executionDate, Date expirationDate, String barcode, String labIdentifier) {
        this.description = description;
        this.isDangerous = isDangerous;
        this.executionDate = executionDate;
        this.expirationDate = expirationDate;
        this.barcode = barcode;
        this.labIdentifier = labIdentifier;
    }

    public SampleDTO(String description, Boolean isDangerous, Date executionDate, Date expirationDate) {
        this.description = description;
        this.isDangerous = isDangerous;
        this.executionDate = executionDate;
        this.expirationDate = expirationDate;
        this.barcode = null;
        this.labIdentifier = null;
    }
}
