package org.sample_manager.DTO;

import org.sample_manager.Domain.Sample;

import java.util.ArrayList;
import java.util.List;

public class SampleMapper {

    public static SampleDTO toDTO(Sample o) {
        return new SampleDTO(o.getDescription(), o.isDangerous(), o.getExecutionDate(), o.getExpirationDate(), o.getBarcode(), o.getLabIdentifier());
    }

    public static Sample toDomain(SampleDTO i) {
        Sample s1 = new Sample(i.description, i.isDangerous, i.executionDate, i.expirationDate);
        if (i.barcode != null && i.labIdentifier != null) {
            s1.setBarcode(i.barcode);
            s1.setLabIdentifier(i.labIdentifier);
        }
        return s1;
    }

    public static List<SampleDTO> toDTOList(List<Sample> i) {
        List<SampleDTO> z = new ArrayList<>();
        for (Sample y : i) {
            z.add(new SampleDTO(y.getDescription(), y.isDangerous(), y.getExecutionDate(), y.getExpirationDate(), y.getBarcode(), y.getLabIdentifier()));
        }
        return z;
    }

}
