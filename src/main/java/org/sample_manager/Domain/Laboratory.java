package org.sample_manager.Domain;

import java.io.Serializable;
import java.util.Objects;

public class Laboratory implements Serializable {
    private String name;
    private String identifier;

    public Laboratory(String name) {
        this.name = name;
        this.identifier = name.toLowerCase().trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Laboratory lab = (Laboratory) o;
        return name.equalsIgnoreCase(lab.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
