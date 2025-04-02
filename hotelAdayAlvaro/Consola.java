package com.dam.app;

import com.dam.controller.GestorHotel;
import com.dam.model.Cliente;
import com.dam.model.Reserva;
import com.dam.view.Consola;

import java.time.LocalDate;

public class Consola {

    public static void main(String[] args) {
        GestorHotel gestorHotel = new GestorHotel(); 

        // Mostrar reservas activas de un cliente
        Consola.imprimir("\nBuscando reservas activas del cliente Martín Oliver...");
        try {
            gestorHotel.buscarReservasActivasCliente(10);
        } catch (Exception e) {
            Consola.imprimir("Error: " + e.getMessage());
        }

        // Cancelar una reserva
        Consola.imprimir("\nCancelando reserva...");
        try {
            gestorHotel.cancelarReserva(2);
            Consola.imprimir("Reserva cancelada exitosamente.");
        } catch (Exception e) {
            Consola.imprimir("Error al cancelar reserva: " + e.getMessage());
        }

        // Mostrar historial de reservas de un cliente
        Consola.imprimir("\nHistorial de reservas del hotel:");
        try {
            gestorHotel.listarHistorialReservasCliente(1);
            gestorHotel.listarHistorialReservasCliente(2);
            gestorHotel.listarHistorialReservasCliente(10);
        } catch (Exception e) {
            Consola.imprimir("Error: " + e.getMessage());
        }

        // Mostrar resumen de habitaciones
        Consola.imprimir("\nResumen de habitaciones:");
        gestorHotel.getHabitaciones().forEach(habitacion -> 
            Consola.imprimir("Habitación " + habitacion.getNumero() + " - " + habitacion.getTipo() + " - " + habitacion.getEstado())
        );
    }

    public static void imprimir(String mensaje) {
        System.out.println(mensaje);
    }
}
