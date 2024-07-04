package org.sample_manager.Domain;

import org.sample_manager.DTO.SampleDTO;
import org.sample_manager.DTO.SampleMapper;
import org.sample_manager.Util.Exceptions.EmptyStringException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SampleRepository implements Serializable {
    private final List<Sample> sampleList;

    public SampleRepository() {
        sampleList = new ArrayList<>();
    }

    public void createSample(SampleDTO p) throws EmptyStringException {
        Sample s = SampleMapper.toDomain(p);
        boolean b = false;
        while (!b) {
            if (!checkForDuplicateCode(s.getBarcode())) {
                addSample(s);
                b = true;
            } else {
                s.generateBarcode();
            }
        }
    }

    public Boolean checkForDuplicateCode(String s) {
        for (Sample r : sampleList) {
            if (s.equals(r.getBarcode())) {
                return true;
            }
        }
        return false;
    }

    private void addSample(Sample s) {
        sampleList.add(s);
    }

    public void removeSample(Sample f) {
        for (Sample s : sampleList) {
            if (s.getBarcode().equalsIgnoreCase(f.getBarcode()) && s.getDescription().equalsIgnoreCase(f.getDescription())) {
                sampleList.remove(s);
                return;
            }
        }
    }

    public void printBarcode(Sample f) {
        f.printBarcode();
    }

    public List<Sample> getSampleList() {
        return new ArrayList<>(sampleList);
    }
}

