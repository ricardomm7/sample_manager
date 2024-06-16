package org.sample_manager.Controller;

import org.sample_manager.DTO.SampleDTO;
import org.sample_manager.DTO.SampleMapper;
import org.sample_manager.Domain.Date;
import org.sample_manager.Domain.Repositories;
import org.sample_manager.Domain.SampleRepository;
import org.sample_manager.GUI.Alert;

import java.util.List;

public class SampleController {
    private final SampleRepository sampleRepository;

    public SampleController() {
        this.sampleRepository = Repositories.getInstance().getSampleRepository();
    }

    public void create(String description, boolean isDangerous, Date execution, Date expiration) {
        try {
            sampleRepository.createSample(new SampleDTO(description, isDangerous, execution, expiration));
        } catch (Exception e) {
            Alert.error("Error", e.getMessage(), "Error creating the sample.");
        }
    }

    public void remove(SampleDTO s) {
    }

    public List<SampleDTO> getAllSamples() {
        return SampleMapper.toDTOList(sampleRepository.getSampleList());
    }

}
