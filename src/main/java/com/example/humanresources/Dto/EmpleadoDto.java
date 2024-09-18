package com.example.humanresources.Dto;

import java.util.List;

public class EmpleadoDto {
    private Long id;

    private String nombres;
    private String apellidos;
    private String fechaNacimiento;
    private String direccion;
    private String telefono;
    private String email;
    private String dui;
    private String fechaContratacion;
    private DepartamentoDto departamento;

   // private List<AsistenciaDto> asistencias;
  //
    //  private List<CapacitacionEmpleadoDto> capacitaciones;


    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getDui() {
        return dui;
    }

    public void setDui(String dui) {
        this.dui = dui;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFechaContratacion() {
        return fechaContratacion;
    }

    public void setFechaContratacion(String fechaContratacion) {
        this.fechaContratacion = fechaContratacion;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

  /*  public List<AsistenciaDto> getAsistencias() {
        return asistencias;
    }

    public void setAsistencias(List<AsistenciaDto> asistencias) {
        this.asistencias = asistencias;
    }
*/
  /*  public List<CapacitacionEmpleadoDto> getCapacitaciones() {
        return capacitaciones;
    }

    public void setCapacitaciones(List<CapacitacionEmpleadoDto> capacitaciones) {
        this.capacitaciones = capacitaciones;
    }
*/
    public DepartamentoDto getDepartamento() {
        return departamento;
    }

    public void setDepartamento(DepartamentoDto departamento) {
        this.departamento = departamento;
    }


}
