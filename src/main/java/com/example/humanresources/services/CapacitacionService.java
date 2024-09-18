package com.example.humanresources.services;

import com.example.humanresources.Dto.CapacitacionDto;

import java.util.List;
import java.util.Optional;

public interface CapacitacionService {

    List<CapacitacionDto> findAll();
    Optional<CapacitacionDto> findById(Long id);
    CapacitacionDto save(CapacitacionDto capacitacionDto);
    Optional<CapacitacionDto> update(Long id, CapacitacionDto capacitacionDto);
    void deleteById(Long id);

        /* List<PuestoDto> findAll();
    Optional<PuestoDto> findById(Long id);
    PuestoDto save(PuestoDto puestoDto);
    Optional<PuestoDto> update(Long id, PuestoDto puestoDto);
    void deleteById(Long id)
*/
}
