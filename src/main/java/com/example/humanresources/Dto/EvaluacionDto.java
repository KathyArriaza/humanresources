package com.example.humanresources.Dto;

public class EvaluacionDto {

    private Long id;
    private String fecha;
    private String competencias;
    private Double calificacion;
    private String comentarios;
   private EmpleadoDto empleado;

    public Double getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(Double calificacion) {
        this.calificacion = calificacion;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    public String getCompetencias() {
        return competencias;
    }

    public void setCompetencias(String competencias) {
        this.competencias = competencias;
    }

    public EmpleadoDto getEmpleado() {
        return empleado;
    }

    public void setEmpleado(EmpleadoDto empleado) {
        this.empleado = empleado;
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

    public void setEmpleadoId(Long id) {
        if (empleado == null) {
            empleado = new EmpleadoDto();
        }
        empleado.setId(id);
    }
}
