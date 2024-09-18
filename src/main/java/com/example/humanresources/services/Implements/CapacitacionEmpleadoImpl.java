package com.example.humanresources.services.Implements;

import com.example.humanresources.Dto.CapacitacionDto;
import com.example.humanresources.Dto.CapacitacionEmpleadoDto;
import com.example.humanresources.Dto.DepartamentoDto;
import com.example.humanresources.Dto.EmpleadoDto;
import com.example.humanresources.entities.Capacitacion;
import com.example.humanresources.entities.CapacitacionEmpleado;
import com.example.humanresources.entities.Departamento;
import com.example.humanresources.entities.Empleado;
import com.example.humanresources.repositories.CapacitacionEmpleadoRepository;
import com.example.humanresources.services.CapacitacionEmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class CapacitacionEmpleadoImpl implements CapacitacionEmpleadoService {

    @Autowired
    private CapacitacionEmpleadoRepository capacitacionEmpleadoRepository;

    @Override
    public List<CapacitacionEmpleadoDto> findAll() {
        List<CapacitacionEmpleado> capacitacionEmpleados = StreamSupport
                .stream(capacitacionEmpleadoRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
        return capacitacionEmpleados.stream()
                .map(this::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<CapacitacionEmpleadoDto> findById(Long id) {
        Optional<CapacitacionEmpleado> capacitacionEmpleado = capacitacionEmpleadoRepository.findById(id);
        return capacitacionEmpleado.map(this::entityToDto);
    }

    @Override
    public CapacitacionEmpleadoDto save(CapacitacionEmpleadoDto capacitacionEmpleadoDto) {
        CapacitacionEmpleado capacitacionEmpleado = dtoToEntity(capacitacionEmpleadoDto);
        capacitacionEmpleado = capacitacionEmpleadoRepository.save(capacitacionEmpleado);
        return entityToDto(capacitacionEmpleado);
    }

    @Override
    public Optional<CapacitacionEmpleadoDto> update(Long id, CapacitacionEmpleadoDto capacitacionEmpleadoDto) {
        if (capacitacionEmpleadoRepository.existsById(id)) {
            CapacitacionEmpleado capacitacionEmpleado = dtoToEntity(capacitacionEmpleadoDto);
            capacitacionEmpleado.setId(id);
            capacitacionEmpleado = capacitacionEmpleadoRepository.save(capacitacionEmpleado);
            return Optional.of(entityToDto(capacitacionEmpleado));
        }
        return Optional.empty();
    }

    @Override
    public void deleteById(Long id) {
        capacitacionEmpleadoRepository.deleteById(id);
    }

    private CapacitacionEmpleado dtoToEntity(CapacitacionEmpleadoDto dto) {
        CapacitacionEmpleado capacitacionEmpleado = new CapacitacionEmpleado();
        capacitacionEmpleado.setEstado(dto.getEstado());

        // Convert EmpleadoDto to Empleado entity
        Empleado empleado = new Empleado();
        EmpleadoDto empleadoDto = dto.getEmpleado();
        if (empleadoDto != null) {
            empleado.setId(empleadoDto.getId());
            empleado.setNombres(empleadoDto.getNombres());
            empleado.setApellidos(empleadoDto.getApellidos());
            empleado.setFechaNacimiento(empleadoDto.getFechaNacimiento());
            empleado.setDireccion(empleadoDto.getDireccion());
            empleado.setTelefono(empleadoDto.getTelefono());
            empleado.setTelefono(empleadoDto.getTelefono());
            empleado.setEmail(empleadoDto.getEmail());
            empleado.setDui(empleadoDto.getDui());
            empleado.setFechaContratacion(empleadoDto.getFechaContratacion());

            DepartamentoDto departamentoDto = empleadoDto.getDepartamento();
            if (departamentoDto != null) {
                Departamento departamento = new Departamento();
                departamento.setId(departamentoDto.getId());
                departamento.setNombre(departamentoDto.getNombre());
                departamento.setDescripcion(departamentoDto.getDescripcion());
                empleado.setDepartamento(departamento);
            }

        }
        capacitacionEmpleado.setEmpleado(empleado);

        // Convert CapacitacionDto to Capacitacion entity
        Capacitacion capacitacion = new Capacitacion();
        CapacitacionDto capacitacionDto = dto.getCapacitacion();
        if (capacitacionDto != null) {
            capacitacion.setId(capacitacionDto.getId());
            capacitacion.setNombre(capacitacionDto.getNombre());
            capacitacion.setDescripcion(capacitacionDto.getDescripcion());
            capacitacion.setFechaInicio(capacitacionDto.getFechaInicio());
            capacitacion.setFechaFin(capacitacionDto.getFechaFin());
        }
        capacitacionEmpleado.setCapacitacion(capacitacion);

        return capacitacionEmpleado;
    }

    private CapacitacionEmpleadoDto entityToDto(CapacitacionEmpleado capacitacionEmpleado) {
        CapacitacionEmpleadoDto dto = new CapacitacionEmpleadoDto();
        dto.setId(capacitacionEmpleado.getId());
        dto.setEstado(capacitacionEmpleado.getEstado());

        // Convert Empleado entity to EmpleadoDto
        EmpleadoDto empleadoDto = new EmpleadoDto();
        Empleado empleado = capacitacionEmpleado.getEmpleado();
        if (empleado != null) {
            empleadoDto.setId(empleado.getId());
            empleadoDto.setNombres(empleado.getNombres());
            empleadoDto.setApellidos(empleado.getApellidos());
            empleadoDto.setFechaNacimiento(empleado.getFechaNacimiento());
            empleadoDto.setDireccion(empleado.getDireccion());
            empleadoDto.setTelefono(empleado.getTelefono());
            empleadoDto.setEmail(empleado.getEmail());
            empleadoDto.setDui(empleado.getDui());
            empleadoDto.setFechaContratacion(empleado.getFechaContratacion());

            // Convertir Departamento a DepartamentoDto
            Departamento departamento = empleado.getDepartamento();
            if (departamento != null) {
                DepartamentoDto departamentoDto = new DepartamentoDto();
                departamentoDto.setId(departamento.getId());
                departamentoDto.setNombre(departamento.getNombre());
                departamentoDto.setDescripcion(departamento.getDescripcion());
                empleadoDto.setDepartamento(departamentoDto);
            }

        }
        dto.setEmpleado(empleadoDto);

        // Convert Capacitacion entity to CapacitacionDto
        CapacitacionDto capacitacionDto = new CapacitacionDto();
        Capacitacion capacitacion = capacitacionEmpleado.getCapacitacion();
        if (capacitacion != null) {
            capacitacionDto.setId(capacitacion.getId());
            capacitacionDto.setNombre(capacitacion.getNombre());
            capacitacionDto.setDescripcion(capacitacion.getDescripcion());
            capacitacionDto.setFechaInicio(capacitacion.getFechaInicio());
            capacitacionDto.setFechaFin(capacitacion.getFechaFin());
        }
        dto.setCapacitacion(capacitacionDto);

        return dto;
    }
}