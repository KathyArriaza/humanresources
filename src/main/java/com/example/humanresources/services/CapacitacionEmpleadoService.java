package com.example.humanresources.services;

import com.example.humanresources.Dto.CapacitacionEmpleadoDto;

import java.util.List;
import java.util.Optional;

public interface CapacitacionEmpleadoService {

    List<CapacitacionEmpleadoDto> findAll();
    Optional<CapacitacionEmpleadoDto> findById(Long id);
    CapacitacionEmpleadoDto save(CapacitacionEmpleadoDto capacitacionEmpleadoDto);
    Optional<CapacitacionEmpleadoDto> update(Long id, CapacitacionEmpleadoDto capacitacionEmpleadoDto);
    void deleteById(Long id);

        /* List<PuestoDto> findAll();
    Optional<PuestoDto> findById(Long id);
    PuestoDto save(PuestoDto puestoDto);
    Optional<PuestoDto> update(Long id, PuestoDto puestoDto);
    void deleteById(Long id)
*/
}
