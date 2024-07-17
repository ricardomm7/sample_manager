package org.sample_manager.Controller;

import org.sample_manager.DTO.SampleDTO;
import org.sample_manager.DTO.SampleMapper;
import org.sample_manager.Domain.HazardTypes;
import org.sample_manager.Domain.Repositories;
import org.sample_manager.Domain.SampleRepository;
import org.sample_manager.Util.Exceptions.DateException;
import org.sample_manager.Util.Exceptions.EmptyStringException;
import org.sample_manager.Util.Exceptions.SymbolsStringException;
import org.sample_manager.Util.Exceptions.TemperatureException;

import java.time.LocalDate;
import java.util.List;

public class SampleController {
    private final SampleRepository sampleRepository;

    public SampleController() {
        this.sampleRepository = Repositories.getInstance().getSampleRepository();
    }

    public void create(String description, HazardTypes hazard, LocalDate execution, LocalDate expiration, String identifier, Double temperature) throws EmptyStringException, SymbolsStringException, TemperatureException, DateException {
        sampleRepository.createSample(new SampleDTO(description, hazard, execution, expiration, true, identifier, temperature));
    }

    public void remove(SampleDTO s) throws EmptyStringException, SymbolsStringException, TemperatureException, DateException {
        sampleRepository.removeSample(SampleMapper.toDomain(s));
    }

    public List<SampleDTO> getAllSamples() {
        return SampleMapper.toDTOList(sampleRepository.getSampleList());
    }

    public void printBarc(SampleDTO selectedSample) throws EmptyStringException, SymbolsStringException, TemperatureException, DateException {
        sampleRepository.printBarcode(SampleMapper.toDomain(selectedSample));
    }

    public void update(SampleDTO selectedSample) throws EmptyStringException, SymbolsStringException, TemperatureException, DateException {
        sampleRepository.updateSample(SampleMapper.toDomain(selectedSample));
    }
}
