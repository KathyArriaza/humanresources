package com.example.humanresources.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "empleados")
public class Empleado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombres;
    private String apellidos;
    private String fechaNacimiento;
    private String direccion;
    private String telefono;
    private String email;
    private String dui;
    private String fechaContratacion;

    @ManyToOne
    @JoinColumn(name = "departamento_id")
    private Departamento departamento;

    @OneToOne(mappedBy = "empleado", cascade = CascadeType.ALL)
    private Puesto puesto;

    @OneToOne(mappedBy = "empleado", cascade = CascadeType.ALL)
    private Salario salario;


    @OneToMany(mappedBy = "empleado", cascade = CascadeType.ALL)
    private List<Asistencia> asistencias;

    @OneToMany(mappedBy = "empleado", cascade = CascadeType.ALL)
    private List<CapacitacionEmpleado> capacitaciones;

    public Empleado() {
    }

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

    public List<CapacitacionEmpleado> getCapacitaciones() {
        return capacitaciones;
    }

    public void setCapacitaciones(List<CapacitacionEmpleado> capacitaciones) {
        this.capacitaciones = capacitaciones;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    public Puesto getPuesto() {
        return puesto;
    }

    public void setPuesto(Puesto puesto) {
        this.puesto = puesto;
    }

    public Salario getSalario() {
        return salario;
    }

    public void setSalario(Salario salario) {
        this.salario = salario;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}
