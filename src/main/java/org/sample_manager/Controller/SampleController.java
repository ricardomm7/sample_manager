package org.sample_manager.Controller;

import org.sample_manager.DTO.SampleDTO;
import org.sample_manager.DTO.SampleMapper;
import org.sample_manager.Domain.Repositories;
import org.sample_manager.Domain.SampleRepository;
import org.sample_manager.Util.Exceptions.EmptyStringException;
import org.sample_manager.Util.Exceptions.ZeroHazardException;

import java.time.LocalDate;
import java.util.List;

public class SampleController {
    private final SampleRepository sampleRepository;

    public SampleController() {
        this.sampleRepository = Repositories.getInstance().getSampleRepository();
    }

    public void create(String description, Boolean isDangerous, LocalDate execution, LocalDate expiration) throws EmptyStringException, ZeroHazardException {
        sampleRepository.createSample(new SampleDTO(description, isDangerous, execution, expiration));
    }

    public void remove(SampleDTO s) throws EmptyStringException, ZeroHazardException {
        sampleRepository.removeSample(SampleMapper.toDomain(s));
    }

    public List<SampleDTO> getAllSamples() {
        return SampleMapper.toDTOList(sampleRepository.getSampleList());
    }
}
