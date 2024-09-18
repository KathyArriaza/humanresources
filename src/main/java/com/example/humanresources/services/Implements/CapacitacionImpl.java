package com.example.humanresources.services.Implements;

import com.example.humanresources.Dto.CapacitacionDto;
import com.example.humanresources.entities.Capacitacion;
import com.example.humanresources.repositories.CapacitacionRepository;
import com.example.humanresources.services.CapacitacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class CapacitacionImpl implements CapacitacionService {

    @Autowired
    private CapacitacionRepository capacitacionRepository;

    @Override
    public List<CapacitacionDto> findAll() {
        return StreamSupport.stream(capacitacionRepository.findAll().spliterator(), false)
                .map(this::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<CapacitacionDto> findById(Long id) {
        return capacitacionRepository.findById(id)
                .map(this::entityToDto);
    }

    @Override
    public CapacitacionDto save(CapacitacionDto capacitacionDto) {
        Capacitacion capacitacion = dtoToEntity(capacitacionDto);
        capacitacion = capacitacionRepository.save(capacitacion);
        return entityToDto(capacitacion);
    }

    @Override
    public Optional<CapacitacionDto> update(Long id, CapacitacionDto capacitacionDto) {
        if (capacitacionRepository.existsById(id)) {
            Capacitacion capacitacion = dtoToEntity(capacitacionDto);
            capacitacion.setId(id);
            capacitacion.setNombre(capacitacionDto.getNombre());
            capacitacion.setDescripcion(capacitacionDto.getDescripcion());
            capacitacion.setFechaInicio(capacitacionDto.getFechaInicio());
            capacitacion.setFechaFin(capacitacionDto.getFechaFin());
            capacitacion = capacitacionRepository.save(capacitacion);
            return Optional.of(entityToDto(capacitacion));
        }
        return Optional.empty();
    }

    @Override
    public void deleteById(Long id) {
        capacitacionRepository.deleteById(id);
    }

    private CapacitacionDto entityToDto(Capacitacion capacitacion) {
        CapacitacionDto dto = new CapacitacionDto();
        dto.setId(capacitacion.getId());
        dto.setNombre(capacitacion.getNombre());
        dto.setDescripcion(capacitacion.getDescripcion());
        dto.setFechaInicio(capacitacion.getFechaInicio());
        dto.setFechaFin(capacitacion.getFechaFin());
        return dto;
    }

    private Capacitacion dtoToEntity(CapacitacionDto dto) {
        Capacitacion capacitacion = new Capacitacion();
        capacitacion.setNombre(dto.getNombre());
        capacitacion.setDescripcion(dto.getDescripcion());
        capacitacion.setFechaInicio(dto.getFechaInicio());
        capacitacion.setFechaFin(dto.getFechaFin());
        return capacitacion;
    }

}
