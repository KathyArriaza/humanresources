package com.example.humanresources.services;

import com.example.humanresources.Dto.AsistenciaDto;
import com.example.humanresources.Dto.PuestoDto;

import java.util.List;
import java.util.Optional;

public interface AsistenciaService {


    List<AsistenciaDto> findAll();
    Optional<AsistenciaDto> findById(Long id);
    AsistenciaDto save(AsistenciaDto asistenciaDto);
    Optional<AsistenciaDto> update(Long id, AsistenciaDto asistenciaDto);
    void deleteById(Long id);


}
