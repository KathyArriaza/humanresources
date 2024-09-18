package com.example.humanresources.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import org.hibernate.annotations.Generated;

@Entity
@Table(name = "ascensos")
@JsonIgnoreProperties({"handler", "hibernateLazyInitializer"})
public class Ascenso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fecha;
    private String descripcion;

    @OneToOne
    @JoinColumn(name = "puesto_id")
    private Puesto puesto;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public void setPuesto(Puesto puesto) {
        this.puesto = puesto;
    }

    public Puesto getPuesto() {
        return puesto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Empleado getEmpleado() {
        return null;
    }
}
