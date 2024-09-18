package com.example.humanresources.Dto;

public class SalarioDto {
    private Long id;
    private Double monto;
    // Double horasExtras;
    private Double AFP;
    private Double ISS;
    private Double renta;
    private Double descuentoTotal;
    private Double salarioTotal;
   private EmpleadoDto empleado;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

   /* public Double getHorasExtras() {
        return horasExtras;
    }

    public void setHorasExtras(Double horasExtras) {
        this.horasExtras = horasExtras;
    }*/

    public Double getAFP() {
        return AFP;
    }

    public void setAFP(Double AFP) {
        this.AFP = AFP;
    }

    public Double getISS() {
        return ISS;
    }

    public void setISS(Double ISS) {
        this.ISS = ISS;
    }

    public Double getRenta() {
        return renta;
    }

    public void setRenta(Double renta) {
        this.renta = renta;
    }

    public Double getDescuentoTotal() {
        return descuentoTotal;
    }

    public void setDescuentoTotal(Double descuentoTotal) {
        this.descuentoTotal = descuentoTotal;
    }

    public double getSalarioTotal() {
        return salarioTotal;
    }

    public void setSalarioTotal(Double salarioTotal) {
        this.salarioTotal = salarioTotal;
    }

    public EmpleadoDto getEmpleado() {
        return empleado;
    }

    public void setEmpleado(EmpleadoDto empleado) {
        this.empleado = empleado;
    }
}
