package com.example.humanresources.services;

import com.example.humanresources.Dto.PermisoDto;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface PermisoService {

    List<PermisoDto> findAll();
    Optional<PermisoDto> findById(Long id);
    PermisoDto save(PermisoDto permisoDto);
    Optional<PermisoDto> update(Long id, PermisoDto permisoDto);
    void deleteById(Long id);



}
