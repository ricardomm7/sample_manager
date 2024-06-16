package org.sample_manager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SampleRepository implements Serializable {
    private final List<Sample> sampleList;

    public SampleRepository() {
        sampleList = new ArrayList<>();
    }

    public void createSample(String description, Boolean isDangerous, Date execution, Date expiration) {
        Sample s = new Sample(description, isDangerous, execution, expiration);
        boolean b = false;
        while (!b) {
            if (checkForDuplicateCode(s.getBarcode())) {
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
                return false;
            }
        }
        return true;
    }

    private void addSample(Sample s) {
        sampleList.add(s);
    }

    public void setLabIdentifier(String id) {
        Sample.labIdentifier = id;
    }

    public List<Sample> getSampleList() {
        return new ArrayList<>(sampleList);
    }
}

