package org.sample_manager.Domain;

import java.io.Serializable;
import java.util.Objects;

public class Identifier implements Serializable {
    private String mainName;
    private String idPrefixField;

    public Identifier(String mainName) {
        this.mainName = mainName;
        this.idPrefixField = mainName.toLowerCase().trim();
    }

    public String getMainName() {
        return mainName;
    }

    public void setMainName(String mainName) {
        this.mainName = mainName;
    }

    public String getIdPrefixField() {
        return idPrefixField;
    }

    public void setIdPrefixField(String idPrefixField) {
        this.idPrefixField = idPrefixField;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Identifier lab = (Identifier) o;
        return mainName.equalsIgnoreCase(lab.getMainName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(mainName);
    }
}
