package com.example.humanresources.services.Implements;

import com.example.humanresources.Dto.DepartamentoDto;
import com.example.humanresources.Dto.EmpleadoDto;
import com.example.humanresources.Dto.PermisoDto;
import com.example.humanresources.entities.Departamento;
import com.example.humanresources.entities.Empleado;
import com.example.humanresources.entities.Permiso;
import com.example.humanresources.repositories.DepartamentoRepository;
import com.example.humanresources.repositories.PermisoRepository;
import com.example.humanresources.services.DepartamentoService;
import com.example.humanresources.services.PermisoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class PermisoImpl  implements PermisoService {

    @Autowired
    private PermisoRepository permisoRepository;

    @Override
    public List<PermisoDto> findAll() {
        List<Permiso> permisos = StreamSupport
                .stream(permisoRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());

        return permisos.stream()
                .map(this::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<PermisoDto> findById(Long id) {
        return permisoRepository.findById(id)
                .map(this::entityToDto);
    }

    @Override
    public PermisoDto save(PermisoDto permisoDto) {
        Permiso permiso = dtoToEntity(permisoDto);
        permiso = permisoRepository.save(permiso);
        return entityToDto(permiso);
    }

    @Override
    public Optional<PermisoDto> update(Long id, PermisoDto permisoDto) {
        return permisoRepository.findById(id).map(permisoExistente -> {
            permisoExistente.setFechaInicio(permisoDto.getFechaInicio());
            permisoExistente.setFechaFin(permisoDto.getFechaFin());
            permisoExistente.setMotivo(permisoDto.getMotivo());
            permisoExistente.setEstado(permisoDto.getEstado());
            // Update other fields as necessary

            permisoRepository.save(permisoExistente);
            return entityToDto(permisoExistente);
        });
    }

    @Override
    public void deleteById(Long id) {
        permisoRepository.deleteById(id);
    }

    // Convert PermisoDto to Permiso entity
    private Permiso dtoToEntity(PermisoDto dto) {
        Permiso permiso = new Permiso();
        permiso.setId(dto.getId());
        permiso.setFechaInicio(dto.getFechaInicio());
        permiso.setFechaFin(dto.getFechaFin());
        permiso.setMotivo(dto.getMotivo());
        permiso.setEstado(dto.getEstado());

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
        permiso.setEmpleado(empleado);

        return permiso;
    }

    // Convert Permiso entity to PermisoDto
    private PermisoDto entityToDto(Permiso permiso) {
        PermisoDto dto = new PermisoDto();
        dto.setId(permiso.getId());
        dto.setFechaInicio(permiso.getFechaInicio());
        dto.setFechaFin(permiso.getFechaFin());
        dto.setMotivo(permiso.getMotivo());
        dto.setEstado(permiso.getEstado());

        // Convert Empleado entity to EmpleadoDto
        EmpleadoDto empleadoDto = new EmpleadoDto();
        Empleado empleado = permiso.getEmpleado();
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

        return dto;
    }

}
