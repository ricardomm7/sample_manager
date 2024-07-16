package org.sample_manager.DTO;

import org.sample_manager.Domain.Sample;
import org.sample_manager.Util.Exceptions.EmptyStringException;
import org.sample_manager.Util.Exceptions.SymbolsStringException;
import org.sample_manager.Util.Exceptions.TemperatureException;

import java.util.ArrayList;
import java.util.List;

public class SampleMapper {

    public static SampleDTO toDTO(Sample o) {
        return new SampleDTO(o.getDescription(), o.getHazard(), o.getExecutionDate(), o.getExpirationDate(), o.getBarcode(), o.getIdentifier(), o.getFirstTimePrint(), o.getTemperature());
    }

    public static Sample toDomain(SampleDTO i) throws EmptyStringException, SymbolsStringException, TemperatureException {
        Sample s1 = new Sample(i.description, i.hazard, i.executionDate, i.expirationDate, i.firstTimePrint, i.identifier, i.temperature);
        if (i.barcode != null) {
            s1.setBarcode(i.barcode);
            //s1.setIdentifier(i.identifier);
        }
        return s1;
    }

    public static List<SampleDTO> toDTOList(List<Sample> i) {
        List<SampleDTO> z = new ArrayList<>();
        for (Sample y : i) {
            z.add(new SampleDTO(y.getDescription(), y.getHazard(), y.getExecutionDate(), y.getExpirationDate(), y.getBarcode(), y.getIdentifier(), y.getFirstTimePrint(), y.getTemperature()));
        }
        return z;
    }
}
