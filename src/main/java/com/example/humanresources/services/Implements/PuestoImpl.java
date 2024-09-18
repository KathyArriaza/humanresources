package com.example.humanresources.services.Implements;

import com.example.humanresources.Dto.DepartamentoDto;
import com.example.humanresources.Dto.EmpleadoDto;
import com.example.humanresources.Dto.PuestoDto;
import com.example.humanresources.entities.Departamento;
import com.example.humanresources.entities.Empleado;
import com.example.humanresources.entities.Puesto;
import com.example.humanresources.repositories.EmpleadoRepository;
import com.example.humanresources.repositories.PuestoRepository;
import com.example.humanresources.services.PuestoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class PuestoImpl implements PuestoService {

    @Autowired
    private PuestoRepository puestoRepository;


    @Override
    public List<PuestoDto> findAll() {
        List<Puesto> puestos = StreamSupport
                .stream(puestoRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
        return puestos.stream()
                .map(this::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<PuestoDto> findById(Long id) {
        Optional<Puesto> puesto = puestoRepository.findById(id);
        return puesto.map(this::entityToDto);
    }

    @Override
    public PuestoDto save(PuestoDto puestoDto) {
        Puesto puesto = dtoToEntity(puestoDto);
        Puesto savedPuesto = puestoRepository.save(puesto);
        return entityToDto(savedPuesto);
    }

    @Override
    public Optional<PuestoDto> update(Long id, PuestoDto puestoDto) {
        Optional<Puesto> optionalPuesto = puestoRepository.findById(id);
        if (optionalPuesto.isPresent()) {
            Puesto puestoToUpdate = optionalPuesto.get();
            puestoToUpdate.setFecha(puestoDto.getFecha());
            puestoToUpdate.setDescripcion(puestoDto.getDescripcion());
            Puesto updatedPuesto = puestoRepository.save(puestoToUpdate);
            return Optional.of(entityToDto(updatedPuesto));
        }
        return Optional.empty();
    }

    @Override
    public void deleteById(Long id) {
        puestoRepository.deleteById(id);
    }

    // Métodos auxiliares de conversión

    private Puesto dtoToEntity(PuestoDto puestoDto) {
        Puesto puesto = new Puesto();
        puesto.setFecha(puestoDto.getFecha());
        puesto.setDescripcion(puestoDto.getDescripcion());

        // Convert EmpleadoDto to Empleado entity
        Empleado empleado = new Empleado();
        EmpleadoDto empleadoDto = puestoDto.getEmpleado();
        if (empleadoDto != null) {
            empleado.setId(empleadoDto.getId());
            empleado.setNombres(empleadoDto.getNombres());
            empleado.setApellidos(empleadoDto.getApellidos());
            empleado.setFechaNacimiento(empleadoDto.getFechaNacimiento());
            empleado.setDireccion(empleadoDto.getDireccion());
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
        puesto.setEmpleado(empleado);

        return puesto;
    }

    private PuestoDto entityToDto(Puesto puesto) {
        PuestoDto puestoDto = new PuestoDto();
        puestoDto.setId(puesto.getId());
        puestoDto.setFecha(puesto.getFecha());
        puestoDto.setDescripcion(puesto.getDescripcion());

        // Convert Empleado entity to EmpleadoDto
        EmpleadoDto empleadoDto = new EmpleadoDto();
        Empleado empleado = puesto.getEmpleado();
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
        puestoDto.setEmpleado(empleadoDto);

        return puestoDto;
    }
}
