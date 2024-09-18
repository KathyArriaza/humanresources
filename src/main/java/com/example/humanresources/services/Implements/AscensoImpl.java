package com.example.humanresources.services.Implements;

import com.example.humanresources.Dto.*;
import com.example.humanresources.entities.*;
import com.example.humanresources.repositories.AscensoRepository;
import com.example.humanresources.repositories.PuestoRepository;
import com.example.humanresources.services.AscensoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.beans.Transient;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class AscensoImpl implements AscensoService {


    @Autowired
    private AscensoRepository ascensoRepository;

    @Autowired
    private PuestoRepository puestoRepository;


    @Override
    public List<AscensoDTo> findAll() {
        List<Ascenso> ascensos = StreamSupport
                .stream(ascensoRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
        return ascensos.stream()
                .map(this::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<AscensoDTo> findById(Long id) {
        Optional<Ascenso> ascenso = ascensoRepository.findById(id);
        return ascenso.map(this::entityToDto);
    }

    @Override
    public AscensoDTo save(AscensoDTo ascensoDTo) {
        Ascenso ascenso = dtoToEntity(ascensoDTo);
        Puesto puesto = puestoRepository.findById(ascensoDTo.getPuestoId())
                .orElseThrow(() -> new RuntimeException("Puesto no encontrado"));
        ascenso.setPuesto(puesto);
        Ascenso savedAscenso = ascensoRepository.save(ascenso);
        return entityToDto(savedAscenso);
    }

    @Override
    public Optional<AscensoDTo> update(Long id, AscensoDTo ascensoDTo) {
        Optional<Ascenso> optionalAscenso = ascensoRepository.findById(id);
        if (optionalAscenso.isPresent()) {
            Ascenso ascensoToUpdate = optionalAscenso.get();
            ascensoToUpdate.setFecha(ascensoDTo.getFecha());
            ascensoToUpdate.setDescripcion(ascensoDTo.getDescripcion());
            Puesto puesto = puestoRepository.findById(ascensoDTo.getPuestoId())
                    .orElseThrow(() -> new RuntimeException("Puesto no encontrado"));
            ascensoToUpdate.setPuesto(puesto);
            Ascenso updatedAscenso = ascensoRepository.save(ascensoToUpdate);
            return Optional.of(entityToDto(updatedAscenso));
        }
        return Optional.empty();
    }

    @Override
    public void deleteById(Long id) {
        ascensoRepository.deleteById(id);
    }

    private Ascenso dtoToEntity(AscensoDTo ascensoDTo) {
        Ascenso ascenso = new Ascenso();
        ascenso.setFecha(ascensoDTo.getFecha());
        ascenso.setDescripcion(ascensoDTo.getDescripcion());
        return ascenso;
    }


    private AscensoDTo entityToDto(Ascenso ascenso) {
        AscensoDTo ascensoDTo = new AscensoDTo();
        ascensoDTo.setId(ascenso.getId());
        if (ascenso.getFecha() != null) {
            ascensoDTo.setFecha(ascenso.getFecha());
        }
        if (ascenso.getDescripcion() != null) {
            ascensoDTo.setDescripcion(ascenso.getDescripcion());
        }
        if (ascenso.getPuesto() != null) {


            PuestoDto puestoDto = new PuestoDto();
            puestoDto.setId(ascenso.getPuesto().getId());
            puestoDto.setFecha(ascenso.getPuesto().getFecha());
            puestoDto.setDescripcion(ascenso.getPuesto().getDescripcion());

            ascensoDTo.setPuesto(puestoDto);
        }
        return ascensoDTo;
    }













/*
    private Ascenso dtoToEntity(AscensoDTo ascensoDTo) {
        Ascenso ascenso = new Ascenso();
        ascenso.setFecha(ascensoDTo.getFecha());
        ascenso.setDescripcion(ascensoDTo.getDescripcion());
        return ascenso;
    }

    private AscensoDTo entityToDto(Ascenso ascenso) {
        AscensoDTo ascensoDTo = new AscensoDTo();
        ascensoDTo.setId(ascenso.getId());
        if (ascenso.getFecha() != null) {
            ascensoDTo.setFecha(ascenso.getFecha());
        }
        if (ascenso.getDescripcion() != null) {
            ascensoDTo.setDescripcion(ascenso.getDescripcion());
        }
        if (ascenso.getPuesto() != null) {
            PuestoDto puestoDto = new PuestoDto();
            puestoDto.setId(ascenso.getPuesto().getId());
            puestoDto.setFecha(ascenso.getPuesto().getFecha());
            puestoDto.setDescripcion(ascenso.getPuesto().getDescripcion());

            // Convert Empleado entity to EmpleadoDto
            EmpleadoDto empleadoDto = new EmpleadoDto();
            Empleado empleado = ascenso.getEmpleado();
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

                ascensoDTo.setPuesto(puestoDto);
        }
        return ascensoDTo;
    }*/
}
