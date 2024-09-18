package com.example.humanresources.services.Implements;

import com.example.humanresources.Dto.DepartamentoDto;
import com.example.humanresources.Dto.EmpleadoDto;
import com.example.humanresources.Dto.EvaluacionDto;
import com.example.humanresources.entities.Departamento;
import com.example.humanresources.entities.Empleado;
import com.example.humanresources.entities.Evaluacion;
import com.example.humanresources.repositories.EvaluacionRepository;
import com.example.humanresources.services.EvaluacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class EvaluacionesImpl implements EvaluacionService {

    @Autowired
    private EvaluacionRepository evaluacionRepository;

    @Override
    public List<EvaluacionDto> findAll() {
        List<Evaluacion> evaluaciones = StreamSupport
                .stream(evaluacionRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());

        return evaluaciones.stream()
                .map(this::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<EvaluacionDto> findById(Long id) {
        return evaluacionRepository.findById(id)
                .map(this::entityToDto);
    }

    @Override
    public EvaluacionDto save(EvaluacionDto evaluacionDto) {
        Evaluacion evaluacion = dtoToEntity(evaluacionDto);
        evaluacion = evaluacionRepository.save(evaluacion);
        return entityToDto(evaluacion);
    }

    @Override
    public Optional<EvaluacionDto> update(Long id, EvaluacionDto evaluacionDto) {
        return evaluacionRepository.findById(id).map(evaluacionExistente -> {
            evaluacionExistente.setFecha(evaluacionDto.getFecha());
            evaluacionExistente.setCompetencias(evaluacionDto.getCompetencias());
            evaluacionExistente.setCalificacion(evaluacionDto.getCalificacion());
            evaluacionExistente.setComentarios(evaluacionDto.getComentarios());
            evaluacionExistente.setFecha(evaluacionDto.getFecha());
            // Update other fields as necessary

            evaluacionRepository.save(evaluacionExistente);
            return entityToDto(evaluacionExistente);
        });
    }

    @Override
    public void deleteById(Long id) {
        evaluacionRepository.deleteById(id);
    }


    // Convert EvaluacionDto to Evaluacion entity
    private Evaluacion dtoToEntity(EvaluacionDto dto) {
        Evaluacion evaluacion = new Evaluacion();
        evaluacion.setId(dto.getId());
        evaluacion.setFecha(dto.getFecha());
        evaluacion.setCompetencias(dto.getCompetencias());
        evaluacion.setCalificacion(dto.getCalificacion());
        evaluacion.setComentarios(dto.getComentarios());

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
        evaluacion.setEmpleado(empleado);

        return evaluacion;
    }

    // Convert Evaluacion entity to EvaluacionDto
    private EvaluacionDto entityToDto(Evaluacion evaluacion) {
        EvaluacionDto dto = new EvaluacionDto();
        dto.setId(evaluacion.getId());
        dto.setFecha(evaluacion.getFecha());
        dto.setCompetencias(evaluacion.getCompetencias());
        dto.setCalificacion(evaluacion.getCalificacion());
        dto.setComentarios(evaluacion.getComentarios());

        // Convert Empleado entity to EmpleadoDto
        EmpleadoDto empleadoDto = new EmpleadoDto();
        Empleado empleado = evaluacion.getEmpleado();
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
