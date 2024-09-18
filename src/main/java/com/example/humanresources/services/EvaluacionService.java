package com.example.humanresources.services;

import com.example.humanresources.Dto.EvaluacionDto;

import java.util.List;
import java.util.Optional;

public interface EvaluacionService {

    List<EvaluacionDto> findAll();
    Optional<EvaluacionDto> findById(Long id);
    EvaluacionDto save(EvaluacionDto evaluacionDto);
    Optional<EvaluacionDto> update(Long id, EvaluacionDto evaluacionDto);
    void deleteById(Long id);

}
