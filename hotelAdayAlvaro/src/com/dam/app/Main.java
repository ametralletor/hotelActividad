package com.dam.app;

import com.dam.controller.GestorHotel;
import com.dam.model.Cliente;
import com.dam.model.Reserva;
import com.dam.view.Consola;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        // Crear consola
        Consola consola = new Consola();

        // Crear gestor de hotel
        GestorHotel gestorHotel = new GestorHotel();

        // Agregar habitaciones al hotel
        Consola.imprimir("Agregando habitaciones al hotel...");
        gestorHotel.agregarHabitaciones();

        // Crear clientes
        Cliente cliente1 = new Cliente(1, "Juan Pérez");
        Cliente cliente2 = new Cliente(2, "María López");

        // Agregar clientes al gestor
        gestorHotel.agregarCliente(cliente1);
        gestorHotel.agregarCliente(cliente2);

        // Crear reservas
        Consola.imprimir("\nCreando reservas...");
        try {
            Reserva reserva1 = new Reserva(1, cliente1, gestorHotel.buscarHabitacionPorNum(101), LocalDate.now().plusDays(1), LocalDate.now().plusDays(100));
            Reserva reserva2 = new Reserva(2, cliente2, gestorHotel.buscarHabitacionPorNum(202), LocalDate.now().plusDays(2), LocalDate.now().plusDays(4));

            gestorHotel.hacerReserva(reserva1);
            gestorHotel.hacerReserva(reserva2);

            Consola.imprimir("Reservas creadas exitosamente.");
        } catch (Exception e) {
            Consola.imprimir("Error al crear reservas: " + e.getMessage());
        }

        // Mostrar resumen de habitaciones
       

        // Mostrar reservas activas de un cliente
        Consola.imprimir("\nBuscando reservas activas del cliente Juan Pérez...");
        try {
            gestorHotel.buscarReservasActivasCliente(1);
        } catch (Exception e) {
            Consola.imprimir("Error: " + e.getMessage());
        }

        // Cancelar una reserva
        Consola.imprimir("\nCancelando reserva...");
        try {
            gestorHotel.cancelarReserva(1);
            Consola.imprimir("Reserva cancelada exitosamente.");
        } catch (Exception e) {
            Consola.imprimir("Error al cancelar reserva: " + e.getMessage());
        }

        // Mostrar historial de reservas de un cliente
        Consola.imprimir("\nHistorial de reservas del cliente Juan Pérez:");
        try {
            gestorHotel.listarHistorialReservasCliente(1);
        } catch (Exception e) {
            Consola.imprimir("Error: " + e.getMessage());
        }

        Consola.imprimir("\nResumen de habitaciones:");
        gestorHotel.getHabitaciones().forEach(habitacion -> 
            Consola.imprimir("Habitación " + habitacion.getNumero() + " - " + habitacion.getTipo() + " - " + habitacion.getEstado())
        );
    }
    
}
