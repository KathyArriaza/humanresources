package com.example.humanresources.services.Implements;

import com.example.humanresources.Dto.AsistenciaDto;
import com.example.humanresources.Dto.DepartamentoDto;
import com.example.humanresources.Dto.EmpleadoDto;
import com.example.humanresources.entities.Asistencia;
import com.example.humanresources.entities.Departamento;
import com.example.humanresources.entities.Empleado;
import com.example.humanresources.repositories.AsistenciaRepository;
import com.example.humanresources.repositories.EmpleadoRepository;
import com.example.humanresources.services.AsistenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class AsistenciaImpl implements AsistenciaService {

    @Autowired
    private AsistenciaRepository asistenciaRepository;

    @Autowired
    private EmpleadoRepository empleadoRepository;

    @Override
    public List<AsistenciaDto> findAll() {
        List<Asistencia> asistencias = StreamSupport
                .stream(asistenciaRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
        return asistencias.stream()
                .map(this::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<AsistenciaDto> findById(Long id) {
        Optional<Asistencia> asistencia = asistenciaRepository.findById(id);
        return asistencia.map(this::entityToDto);
    }

    @Override
    public AsistenciaDto save(AsistenciaDto asistenciaDto) {
        Asistencia asistencia = dtoToEntity(asistenciaDto);
        asistencia = asistenciaRepository.save(asistencia);
        return entityToDto(asistencia);
    }

    @Override
    public Optional<AsistenciaDto> update(Long id, AsistenciaDto asistenciaDto) {
        if (asistenciaRepository.existsById(id)) {
            Asistencia asistencia = dtoToEntity(asistenciaDto);
            asistencia.setId(id);
            asistencia = asistenciaRepository.save(asistencia);
            return Optional.of(entityToDto(asistencia));
        }
        return Optional.empty();
    }

    @Override
    public void deleteById(Long id) {
        asistenciaRepository.deleteById(id);
    }


    private Asistencia dtoToEntity(AsistenciaDto dto) {
        Asistencia asistencia = new Asistencia();
        asistencia.setFecha(dto.getFecha());
        asistencia.setHoraEntrada(dto.getHoraEntrada());
        asistencia.setHoraSalida(dto.getHoraSalida());
        asistencia.setEstado(dto.getEstado());

        if (dto.getEmpleado() != null) {
            Empleado empleado = new Empleado();
            EmpleadoDto empleadoDto = dto.getEmpleado();
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

            asistencia.setEmpleado(empleado);
        }

        return asistencia;
    }

    private AsistenciaDto entityToDto(Asistencia asistencia) {
        AsistenciaDto dto = new AsistenciaDto();
        dto.setId(asistencia.getId());
        dto.setFecha(asistencia.getFecha());
        dto.setHoraEntrada(asistencia.getHoraEntrada());
        dto.setHoraSalida(asistencia.getHoraSalida());
        dto.setEstado(asistencia.getEstado());

        if (asistencia.getEmpleado() != null) {
            EmpleadoDto empleadoDto = new EmpleadoDto();
            Empleado empleado = asistencia.getEmpleado();
            empleadoDto.setId(empleado.getId());
            empleadoDto.setNombres(empleado.getNombres());
            empleadoDto.setApellidos(empleado.getApellidos());
            empleadoDto.setFechaNacimiento(empleado.getFechaNacimiento());
            empleadoDto.setDireccion(empleado.getDireccion());
            empleadoDto.setTelefono(empleado.getTelefono());
            empleadoDto.setEmail(empleado.getEmail());
            empleadoDto.setDui(empleado.getDui());
            empleadoDto.setFechaContratacion(empleado.getFechaContratacion());

            Departamento departamento = empleado.getDepartamento();
            if (departamento != null) {
                DepartamentoDto departamentoDto = new DepartamentoDto();
                departamentoDto.setId(departamento.getId());
                departamentoDto.setNombre(departamento.getNombre());
                departamentoDto.setDescripcion(departamento.getDescripcion());
                empleadoDto.setDepartamento(departamentoDto);
            }

            dto.setEmpleado(empleadoDto);
        }

        return dto;
    }

}
