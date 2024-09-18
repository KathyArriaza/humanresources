package com.example.humanresources.services;

import com.example.humanresources.Dto.EmpleadoDto;
import com.example.humanresources.Dto.PuestoDto;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface EmpleadoService {


    List<EmpleadoDto> findAll();
    Optional<EmpleadoDto> findById(Long id);
    EmpleadoDto save(EmpleadoDto empleadoDto);
    Optional<EmpleadoDto> update(Long id, EmpleadoDto empleadoDto);
    void deleteById(Long id);

}
