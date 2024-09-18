package com.example.humanresources.services.Implements;

import com.example.humanresources.Dto.DepartamentoDto;
import com.example.humanresources.entities.Departamento;
import com.example.humanresources.entities.Puesto;
import com.example.humanresources.repositories.DepartamentoRepository;
import com.example.humanresources.services.DepartamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class DepartamentoImpl implements DepartamentoService {

    @Autowired
    private DepartamentoRepository departamentoRepository;

    @Override
    public List<DepartamentoDto> findAll() {
        List<Departamento> puestos = StreamSupport
                .stream(departamentoRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
        return puestos.stream()
                .map(this::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<DepartamentoDto> findById(Long id) {
        Optional<Departamento> departamento = departamentoRepository.findById(id);
        return departamento.map(this::entityToDto);
    }

    @Override
    public DepartamentoDto save(DepartamentoDto departamentoDto) {

        Departamento departamento = dtoToEntity(departamentoDto);
        departamento = departamentoRepository.save(departamento);
        return entityToDto(departamento);
    }

    @Override
    public Optional<DepartamentoDto> update(Long id, DepartamentoDto departamentoDto) {
        if (departamentoRepository.existsById(id)) {
            Departamento departamento = dtoToEntity(departamentoDto);
            departamento.setId(id);
            departamento = departamentoRepository.save(departamento);
            return Optional.of(entityToDto(departamento));
        }
        return Optional.empty();
    }


    @Override
    public void deleteById(Long id) {
        departamentoRepository.deleteById(id);
    }


    private DepartamentoDto entityToDto(Departamento departamento) {
        DepartamentoDto dto = new DepartamentoDto();
        dto.setId(departamento.getId());
        dto.setNombre(departamento.getNombre());
        dto.setDescripcion(departamento.getDescripcion());
        return dto;
    }

    private Departamento dtoToEntity(DepartamentoDto dto) {
        Departamento departamento = new Departamento();
        departamento.setNombre(dto.getNombre());
        departamento.setDescripcion(dto.getDescripcion());
        return departamento;
    }
}
