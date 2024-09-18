package com.example.humanresources.services.Implements;

import com.example.humanresources.Dto.DepartamentoDto;
import com.example.humanresources.Dto.EmpleadoDto;
import com.example.humanresources.Dto.SalarioDto;
import com.example.humanresources.entities.Departamento;
import com.example.humanresources.entities.Empleado;
import com.example.humanresources.entities.Salario;
import com.example.humanresources.repositories.SalarioRepository;
import com.example.humanresources.services.SalarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class SalarioImpl implements SalarioService {


        @Autowired
        private SalarioRepository salarioRepository;

        @Override
        public List<SalarioDto> findAll() {
            List<Salario> salarios = StreamSupport
                    .stream(salarioRepository.findAll().spliterator(), false)
                    .collect(Collectors.toList());

            return salarios.stream()
                    .map(this::convertirADto)
                    .collect(Collectors.toList());
        }

        @Override
        public Optional<SalarioDto> findById(Long id) {
            return salarioRepository.findById(id)
                    .map(this::convertirADto);
        }

        @Override
        public SalarioDto save(SalarioDto salarioDto) {
            // Realizamos los cálculos antes de guardar
            SalarioDto salarioCalculado = calcularSalario(salarioDto);

            // Convertimos el DTO a entidad y guardamos
            Salario salario = convertirAEntidad(salarioCalculado);
            salario = salarioRepository.save(salario);
            return convertirADto(salario);
        }

        @Override
        public Optional<SalarioDto> update(Long id, SalarioDto salarioDto) {
            return salarioRepository.findById(id).map(salarioExistente -> {
                // Realizamos los cálculos antes de actualizar
                SalarioDto salarioCalculado = calcularSalario(salarioDto);

                // Actualizamos los valores en la entidad existente
                salarioExistente.setMonto(salarioCalculado.getMonto());
                salarioExistente.setAFP(salarioCalculado.getAFP());
                salarioExistente.setISS(salarioCalculado.getISS());
                salarioExistente.setRenta(salarioCalculado.getRenta());
                salarioExistente.setDescuentoTotal(salarioCalculado.getDescuentoTotal());
                salarioExistente.setSalarioTotal(salarioCalculado.getSalarioTotal());

                // Guardamos la entidad actualizada
                salarioRepository.save(salarioExistente);
                return convertirADto(salarioExistente);
            });
        }

        @Override
        public void deleteById(Long id) {
            salarioRepository.deleteById(id);
        }

        // Método para calcular AFP, ISS, Renta y descuentos totales
        public SalarioDto calcularSalario(SalarioDto salarioDto) {
            Double monto = salarioDto.getMonto();
            Double afp = calcularAFP(monto);
            Double iss = calcularISS(monto);
            Double renta = calcularRenta(monto);
            Double descuentoTotal = afp + iss + renta;
            Double salarioTotal = monto - descuentoTotal;

            salarioDto.setAFP(afp);
            salarioDto.setISS(iss);
            salarioDto.setRenta(renta);
            salarioDto.setDescuentoTotal(descuentoTotal);
            salarioDto.setSalarioTotal(salarioTotal);

            return salarioDto;
        }

        private Double calcularAFP(Double monto) {
            return monto * 0.0775;
        }

        private Double calcularISS(Double monto) {
            return monto * 0.075;
        }

        private Double calcularRenta(Double monto) {
            if (monto < 472) {
                System.out.println("No paga renta");
                return 0.0;
            } else if (monto > 2038) {
                return monto * 0.30;
            } else if (monto > 895) {
                return monto * 0.20;
            } else {
                return monto * 0.10;
            }
        }

    // Conversión de DTO a Entidad
    private Salario convertirAEntidad(SalarioDto salarioDto) {
        Salario salario = new Salario();
        salario.setId(salarioDto.getId());
        salario.setMonto(salarioDto.getMonto());
        salario.setAFP(salarioDto.getAFP());
        salario.setISS(salarioDto.getISS());
        salario.setRenta(salarioDto.getRenta());
        salario.setDescuentoTotal(salarioDto.getDescuentoTotal());
        salario.setSalarioTotal(salarioDto.getSalarioTotal());

        // Convert EmpleadoDto to Empleado entity
        Empleado empleado = new Empleado();
        EmpleadoDto empleadoDto = salarioDto.getEmpleado();
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
        salario.setEmpleado(empleado);

        return salario;
    }

    // Conversión de Entidad a DTO
    private SalarioDto convertirADto(Salario salario) {
        SalarioDto salarioDto = new SalarioDto();
        salarioDto.setId(salario.getId());
        salarioDto.setMonto(salario.getMonto());
        salarioDto.setAFP(salario.getAFP());
        salarioDto.setISS(salario.getISS());
        salarioDto.setRenta(salario.getRenta());
        salarioDto.setDescuentoTotal(salario.getDescuentoTotal());
        salarioDto.setSalarioTotal(salario.getSalarioTotal());

        // Convert Empleado entity to EmpleadoDto
        EmpleadoDto empleadoDto = new EmpleadoDto();
        Empleado empleado = salario.getEmpleado();
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
        salarioDto.setEmpleado(empleadoDto);

        return salarioDto;
    }







       /* // Conversión de DTO a Entidad
        private Salario convertirAEntidad(SalarioDto salarioDto) {
            Salario salario = new Salario();
            salario.setId(salarioDto.getId());
            salario.setMonto(salarioDto.getMonto());
            salario.setAFP(salarioDto.getAFP());
            salario.setISS(salarioDto.getISS());
            salario.setRenta(salarioDto.getRenta());
            salario.setDescuentoTotal(salarioDto.getDescuentoTotal());
            salario.setSalarioTotal(salarioDto.getSalarioTotal());
            return salario;
        }

        // Conversión de Entidad a DTO
        private SalarioDto convertirADto(Salario salario) {
            SalarioDto salarioDto = new SalarioDto();
            salarioDto.setId(salario.getId());
            salarioDto.setMonto(salario.getMonto());
            salarioDto.setAFP(salario.getAFP());
            salarioDto.setISS(salario.getISS());
            salarioDto.setRenta(salario.getRenta());
            salarioDto.setDescuentoTotal(salario.getDescuentoTotal());
            salarioDto.setSalarioTotal(salario.getSalarioTotal());
            return salarioDto;
        }*/
    }


