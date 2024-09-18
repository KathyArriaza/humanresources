package com.example.humanresources.services;

import com.example.humanresources.Dto.AscensoDTo;
import com.example.humanresources.Dto.PuestoDto;
import com.example.humanresources.entities.Ascenso;
import com.example.humanresources.entities.Puesto;

import java.util.List;
import java.util.Optional;

public interface PuestoService {

   /* List<Puesto> findAll();
    Optional<Puesto> findById(Long id);
     Puesto save(Puesto puesto);
    Optional<Puesto> update(Long id, Puesto puesto);
    Optional<Puesto> delete(Long id);*/

    List<PuestoDto> findAll();
    Optional<PuestoDto> findById(Long id);
    PuestoDto save(PuestoDto puestoDto);
    Optional<PuestoDto> update(Long id, PuestoDto puestoDto);
    void deleteById(Long id);


   /* List<PuestoDto> findAll();
    Optional<PuestoDto> findById(Long id);
    PuestoDto save(PuestoDto puestoDto);
    Optional<PuestoDto> update(Long id, PuestoDto puestoDto);
    void deleteById(Long id)
*/

  /*  List<Puesto> findAll();
    Puesto findById(Long id);
    Puesto create(Puesto puesto);
    Puesto update(Long id, Puesto puesto);
    void deleteById(Long id);*/


}
