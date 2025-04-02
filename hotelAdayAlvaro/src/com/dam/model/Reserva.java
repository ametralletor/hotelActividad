package com.dam.model;

import java.time.LocalDate;

public class Reserva {
    private int id;
    private Habitacion habitacion;
    private Cliente cliente;
    private LocalDate fechaCheckin;
    private LocalDate fechaCheckout;
    private double precioTotal;


    public Reserva(int id, Cliente cliente, Habitacion habitacion, LocalDate fechaCheckin, LocalDate fechaCheckout) {
        if (fechaCheckout.isAfter(fechaCheckin.plusDays(90))) {
            throw new IllegalArgumentException("No se puede reservar por más de 90 días.");
        }
        if (fechaCheckout.isBefore(LocalDate.now()) || fechaCheckin.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("La fecha de checkin o checkout no puede ser anterior a la fecha actual.");
            
        }
        if (fechaCheckout.isBefore(fechaCheckin)) {
            throw new IllegalArgumentException("La fecha de checkout no puede ser anterior a la fecha de checkin.");
            
        }
        this.id = id;
        this.habitacion = habitacion; // Asegúrate de que esta referencia provenga de GestorHotel
        this.cliente = cliente;
        this.fechaCheckin = fechaCheckin;
        this.fechaCheckout = fechaCheckout;
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
