package com.example.humanresources.Dto;

public class puestotoAscensoDTo {
    private Long id;

    private String descripcion;
    private String fecha;
    //private EmpleadoDto empleado;

//    public EmpleadoDto getEmpleado() {
//        return empleado;
//    }
//
//    public void setEmpleado(EmpleadoDto empleado) {
//        this.empleado = empleado;
//    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Long getId() {
        return id;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
