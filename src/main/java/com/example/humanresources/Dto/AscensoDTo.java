package com.example.humanresources.Dto;

public class AscensoDTo {
    private Long id;
    private String fecha;
    private String descripcion;
    private PuestoDto puesto;
    private EmpleadoDto empleado;

    public EmpleadoDto getEmpleado() {
        return empleado;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PuestoDto getPuesto() {
        return puesto;
    }

    public void setPuesto(PuestoDto puesto) {
        this.puesto = puesto;
    }

    public Long getPuestoId() {
        return puesto != null ? puesto.getId() : null;
    }

    public void setPuestoId(Long id) {
        if (this.puesto == null) {
            this.puesto = new PuestoDto();
        }
        this.puesto.setId(id);
    }

    public void setEmpleado(EmpleadoDto empleadoDto) {
        this.empleado = empleadoDto;
    }
}
