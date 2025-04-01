package com.dam.model;

public class Habitacion {
    private int numero;
    private TipoHabitacion tipo;
    private EstadoHabitacion estado;
    private String descripcion;
    private double precio;

    public Habitacion(int numero, TipoHabitacion tipo, String descripcion) {
        this.numero = numero;
        this.tipo = tipo;
        this.estado = EstadoHabitacion.DISPONIBLE;
        this.descripcion = descripcion;
    }

    public Habitacion(int numero, TipoHabitacion tipo, double precio, EstadoHabitacion estado) {
        this.numero = numero;
        this.tipo = tipo;
        this.precio = precio;
        this.estado = estado;
    }

    public int getNumero() {
        return numero;
    }

    public TipoHabitacion getTipo() {
        return tipo;
    }

    public EstadoHabitacion getEstado() {
        return estado;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public double getPrecio(){
        return tipo.getPrecio();
    }
    
    public void setEstado(EstadoHabitacion estado) {
        this.estado = estado;
    }


}
