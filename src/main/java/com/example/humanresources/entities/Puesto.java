package com.example.humanresources.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="puestos")
public class Puesto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fecha;
    private String descripcion;


   @OneToOne(mappedBy = "puesto", cascade = CascadeType.ALL)
    private Ascenso ascenso;

    @OneToOne
    @JoinColumn(name = "empleado_id")
    private Empleado empleado;

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

   public Ascenso getAscenso() {
        return ascenso;
    }

    public void setAscenso(Ascenso ascenso) {
        this.ascenso = ascenso;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }
}
