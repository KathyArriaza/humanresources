package com.example.humanresources.Dto;

import com.example.humanresources.entities.Capacitacion;
import com.example.humanresources.entities.Empleado;

public class CapacitacionEmpleadoDto {
    private Long id;
    private String estado;
    private CapacitacionDto capacitacion;
    private EmpleadoDto empleado;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public EmpleadoDto getEmpleado() {
        return empleado;
    }

    public void setEmpleado(EmpleadoDto empleado) {
        this.empleado = empleado;
    }

    public CapacitacionDto getCapacitacion() {
        return capacitacion;
    }

    public void setCapacitacion(CapacitacionDto capacitacion) {
        this.capacitacion = capacitacion;
    }
}
