package org.sample_manager.DTO;

public class IdentifierDTO {
    public String mainName;
    public String idPrefixField;

    public IdentifierDTO(String mainName, String idPrefixField) {
        this.idPrefixField = idPrefixField;
        this.mainName = mainName;
    }
}
