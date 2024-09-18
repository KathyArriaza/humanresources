package com.example.humanresources.services;

import com.example.humanresources.Dto.SalarioDto;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface SalarioService {

List<SalarioDto> findAll();
Optional<SalarioDto> findById(Long id);
SalarioDto save(SalarioDto salarioDto);
Optional<SalarioDto> update(Long id, SalarioDto salarioDto);
void deleteById(Long id);


       /* List<PuestoDto> findAll();
    Optional<PuestoDto> findById(Long id);
    PuestoDto save(PuestoDto puestoDto);
    Optional<PuestoDto> update(Long id, PuestoDto puestoDto);
    void deleteById(Long id)
*/
}
