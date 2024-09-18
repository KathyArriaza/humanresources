package com.example.humanresources.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "salarios")
public class Salario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double monto;
   // private Double horasExtras;
    private Double AFP;
    private Double ISS;
    private Double renta;
    private Double descuentoTotal;
    private Double salarioTotal;

    @OneToOne
    @JoinColumn(name = "empleado_id")
    private Empleado empleado;

    public Double getAFP() {
        return AFP;
    }

    public void setAFP(Double AFP) {
        this.AFP = AFP;
    }

    public Double getDescuentoTotal() {
        return descuentoTotal;
    }

    public void setDescuentoTotal(Double descuentoTotal) {
        this.descuentoTotal = descuentoTotal;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

   /* public Double getHorasExtras() {
        return horasExtras;
    }

    public void setHorasExtras(Double horasExtras) {
        this.horasExtras = horasExtras;
    }*/

    public Double getISS() {
        return ISS;
    }

    public void setISS(Double ISS) {
        this.ISS = ISS;
    }

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    public Double getRenta() {
        return renta;
    }

    public void setRenta(Double renta) {
        this.renta = renta;
    }

    public double getSalarioTotal() {
        return salarioTotal;
    }

    public void setSalarioTotal(Double salarioTotal) {
        this.salarioTotal = salarioTotal;
    }
}
