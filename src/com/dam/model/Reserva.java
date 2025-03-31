package com.dam.model;

import java.time.LocalDate;

public class Reserva {
    private int id;
    private Habitacion habitacion;
    private Cliente cliente;
    private LocalDate fechaCheckin;
    private LocalDate fechaCheckout;
    private double precioTotal;
    private int noches;

    public Reserva(int id, Habitacion habitacion, Cliente cliente, LocalDate fechaCheckin, int noches) {
        this.id = id;
        this.habitacion = habitacion;
        this.cliente = cliente;
        this.fechaCheckin = fechaCheckin;
        this.noches = noches;
        this.fechaCheckout = fechaCheckin.plusDays(noches);
        this.precioTotal = calcularPrecioTotal();
    }

    public int getId() {
        return id;
    }

    public Habitacion getHabitacion() {
        return habitacion;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public int getNoches() {
        return noches;
    }

    public LocalDate getFechaCheckin() {
        return fechaCheckin;
    }

    public LocalDate getFechaCheckout() {
        return fechaCheckout;
    }

    public double getPrecioTotal() {
        return precioTotal;
    }

    private double calcularPrecioTotal() {
        int dias = fechaCheckin.until(fechaCheckout).getDays();
        return dias * habitacion.getPrecio();
    }
}
