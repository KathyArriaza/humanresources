package com.example.humanresources.services.Implements;

import com.example.humanresources.Dto.DepartamentoDto;
import com.example.humanresources.Dto.EmpleadoDto;
import com.example.humanresources.Dto.PuestoDto;
import com.example.humanresources.Dto.SalarioDto;
import com.example.humanresources.entities.Departamento;
import com.example.humanresources.entities.Empleado;
import com.example.humanresources.entities.Puesto;
import com.example.humanresources.entities.Salario;
import com.example.humanresources.repositories.DepartamentoRepository;
import com.example.humanresources.repositories.EmpleadoRepository;
import com.example.humanresources.repositories.PuestoRepository;
import com.example.humanresources.repositories.SalarioRepository;
import com.example.humanresources.services.EmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class EmpleadoImpl implements EmpleadoService {
    @Autowired
    private EmpleadoRepository empleadoRepository;



    @Autowired
    private DepartamentoRepository departamentoRepository;


    @Override
    public List<EmpleadoDto> findAll() {
        List<Empleado> empleados = StreamSupport
                .stream(empleadoRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
        return empleados.stream()
                .map(this::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<EmpleadoDto> findById(Long id) {
        Optional<Empleado> empleado = empleadoRepository.findById(id);
        return empleado.map(this::entityToDto);
    }

    @Override
    public EmpleadoDto save(EmpleadoDto empleadoDto) {
        Empleado empleado = dtoToEntity(empleadoDto);
        empleado = empleadoRepository.save(empleado);
        return entityToDto(empleado);
    }

    @Override
    public Optional<EmpleadoDto> update(Long id, EmpleadoDto empleadoDto) {
        if (empleadoRepository.existsById(id)) {
            Empleado empleado = dtoToEntity(empleadoDto);
            empleado.setId(id);
            empleado.setNombres(empleadoDto.getNombres());
            empleado.setApellidos(empleadoDto.getApellidos());
            empleado.setFechaNacimiento(empleadoDto.getFechaNacimiento());
            empleado.setDireccion(empleadoDto.getDireccion());
            empleado.setTelefono(empleadoDto.getTelefono());
            empleado.setEmail(empleadoDto.getEmail());
            empleado.setDui(empleadoDto.getDui());
            empleado.setFechaContratacion(empleadoDto.getFechaContratacion());

            empleado = empleadoRepository.save(empleado);
            return Optional.of(entityToDto(empleado));
        }
        return Optional.empty();
    }

    @Override
    public void deleteById(Long id) {
        empleadoRepository.deleteById(id);
    }

    private Empleado dtoToEntity(EmpleadoDto dto) {
        Empleado empleado = new Empleado();
        empleado.setNombres(dto.getNombres());
        empleado.setApellidos(dto.getApellidos());
        empleado.setFechaNacimiento(dto.getFechaNacimiento());
        empleado.setDireccion(dto.getDireccion());
        empleado.setTelefono(dto.getTelefono());
        empleado.setEmail(dto.getEmail());
        empleado.setDui(dto.getDui());
        empleado.setFechaContratacion(dto.getFechaContratacion());


        if (dto.getDepartamento() != null) {
            Departamento departamento = departamentoRepository.findById(dto.getDepartamento().getId())
                    .orElseThrow(() -> new RuntimeException("Departamento no encontrado"));
            empleado.setDepartamento(departamento);
        }



        return empleado;
    }

    private EmpleadoDto entityToDto(Empleado empleado) {
        EmpleadoDto dto = new EmpleadoDto();
        dto.setId(empleado.getId());
        dto.setNombres(empleado.getNombres());
        dto.setApellidos(empleado.getApellidos());
        dto.setFechaNacimiento(empleado.getFechaNacimiento());
        dto.setDireccion(empleado.getDireccion());
        dto.setTelefono(empleado.getTelefono());
        dto.setEmail(empleado.getEmail());
        dto.setDui(empleado.getDui());
        dto.setFechaContratacion(empleado.getFechaContratacion());



        if (empleado.getDepartamento() != null) {
            DepartamentoDto departamentoDto = new DepartamentoDto();
            departamentoDto.setId(empleado.getDepartamento().getId());
            departamentoDto.setNombre(empleado.getDepartamento().getNombre());
            departamentoDto.setDescripcion(empleado.getDepartamento().getDescripcion());
            dto.setDepartamento(departamentoDto);
        }



        return dto;
    }
}
