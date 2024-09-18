package com.example.humanresources.services;

import com.example.humanresources.Dto.DepartamentoDto;


import java.util.List;
import java.util.Optional;

public interface DepartamentoService {

    List<DepartamentoDto> findAll();
    Optional<DepartamentoDto> findById(Long id);
    DepartamentoDto save(DepartamentoDto departamentoDto);
    Optional<DepartamentoDto> update(Long id, DepartamentoDto departamentoDto);
    void deleteById(Long id);


       /* List<PuestoDto> findAll();
    Optional<PuestoDto> findById(Long id);
    PuestoDto save(PuestoDto puestoDto);
    Optional<PuestoDto> update(Long id, PuestoDto puestoDto);
    void deleteById(Long id)
*/
}
