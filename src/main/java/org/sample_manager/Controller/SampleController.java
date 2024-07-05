package org.sample_manager.Controller;

import org.sample_manager.DTO.SampleDTO;
import org.sample_manager.DTO.SampleMapper;
import org.sample_manager.Domain.HazardTypes;
import org.sample_manager.Domain.Repositories;
import org.sample_manager.Domain.SampleRepository;
import org.sample_manager.Util.Exceptions.EmptyStringException;

import java.time.LocalDate;
import java.util.List;

public class SampleController {
    private final SampleRepository sampleRepository;

    public SampleController() {
        this.sampleRepository = Repositories.getInstance().getSampleRepository();
    }

    public void create(String description, HazardTypes isDangerous, LocalDate execution, LocalDate expiration) throws EmptyStringException {
        sampleRepository.createSample(new SampleDTO(description, isDangerous, execution, expiration, true));
    }

    public void remove(SampleDTO s) throws EmptyStringException {
        sampleRepository.removeSample(SampleMapper.toDomain(s));
    }

    public List<SampleDTO> getAllSamples() {
        return SampleMapper.toDTOList(sampleRepository.getSampleList());
    }

    public void printBarc(SampleDTO selectedSample) throws EmptyStringException {
        sampleRepository.printBarcode(SampleMapper.toDomain(selectedSample));
    }

    public void update(SampleDTO selectedSample) throws EmptyStringException {
        sampleRepository.updateSample(SampleMapper.toDomain(selectedSample));
    }
}
