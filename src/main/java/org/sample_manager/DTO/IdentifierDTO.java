package org.sample_manager.DTO;

public class IdentifierDTO {
    public String mainName;
    public String idPrefixField;

    public IdentifierDTO(String mainName) {
        this.idPrefixField = mainName.toLowerCase().trim();
        this.mainName = mainName;
    }
}
