package org.sample_manager.Domain;

import org.sample_manager.Util.Exceptions.EmptyStringException;
import org.sample_manager.Util.StringValidator;

import java.io.Serializable;
import java.util.Objects;

public class Identifier implements Serializable {
    private String mainName;
    private String idPrefixField;

    public Identifier(String mainName) throws EmptyStringException {
        setName(mainName);
    }

    private void setName(String mainName) throws EmptyStringException {
        StringValidator.validateNotEmpty(mainName, "The identificator main name");
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
        Identifier other = (Identifier) o;
        return mainName.equalsIgnoreCase(other.getMainName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(mainName);
    }
}
