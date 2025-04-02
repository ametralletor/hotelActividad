package com.dam.view;

import com.dam.model.Habitacion;
import com.dam.model.Reserva;
import java.util.List;
import com.dam.controller.GestorHotel;

import java.time.LocalDate;

public class Consola {
    private GestorHotel gestorHotel;

    // Constructor
    public Consola(GestorHotel gestorHotel) {
        this.gestorHotel = gestorHotel;

        // Initialize or display the current state of the hotel
        imprimir("Consola inicializada.");
        imprimir("\nResumen inicial de habitaciones:");
        gestorHotel.getHabitaciones().forEach(habitacion ->
            imprimir("Habitación " + habitacion.getNumero() + " - " + habitacion.getTipo() + " - " + habitacion.getEstado() + " - Precio: " + habitacion.getPrecio() + "€")
        );
    }

    public static void imprimir(String mensaje) {
        System.out.println(mensaje);
    }

    public void imprimirMensaje(String mensaje) {
        System.out.println(mensaje);
    }

    public void crearReservasIniciales() {
        imprimirMensaje("\nCreando reservas...");
        try {
            Reserva reserva1 = new Reserva(1, gestorHotel.buscarClientePorId(1), gestorHotel.buscarHabitacionPorNum(101), LocalDate.now().plusDays(1), LocalDate.now().plusDays(5));
            Reserva reserva2 = new Reserva(2, gestorHotel.buscarClientePorId(2), gestorHotel.buscarHabitacionPorNum(102), LocalDate.now().plusDays(2), LocalDate.now().plusDays(4));
            Reserva reserva3 = new Reserva(3, gestorHotel.buscarClientePorId(10), gestorHotel.buscarHabitacionPorNum(103), LocalDate.now().plusDays(1), LocalDate.now().plusDays(3));

            gestorHotel.hacerReserva(reserva1);
            gestorHotel.hacerReserva(reserva2);
            gestorHotel.hacerReserva(reserva3);

            imprimirMensaje("Reservas creadas exitosamente.");
        } catch (Exception e) {
            imprimirMensaje("Error al crear reservas: " + e.getMessage());
        }
    }

    public void crearReservasAdicionales() {
        try {
            imprimirMensaje("\nCreando reservas adicionales...");
            Reserva reserva4 = new Reserva(4, gestorHotel.buscarClientePorId(2), gestorHotel.buscarHabitacionPorNum(201), LocalDate.now().plusDays(3), LocalDate.now().plusDays(6));
            Reserva reserva5 = new Reserva(5, gestorHotel.buscarClientePorId(3), gestorHotel.buscarHabitacionPorNum(202), LocalDate.now().plusDays(2), LocalDate.now().plusDays(5));

            gestorHotel.hacerReserva(reserva4);
            gestorHotel.hacerReserva(reserva5);

            imprimirMensaje("Nuevas reservas creadas exitosamente.");
        } catch (Exception e) {
            imprimirMensaje("Error al crear nuevas reservas: " + e.getMessage());
        }
    }

    public void verificarEstadosHabitaciones() {
        imprimirMensaje("\nVerificando estados de habitaciones después de las reservas:");
        gestorHotel.getHabitaciones().forEach(habitacion ->
            imprimirMensaje("Habitación " + habitacion.getNumero() + " - " + habitacion.getTipo() + " - " + habitacion.getEstado())
        );
    }

    public void mostrarReservasActivas() {
        imprimirMensaje("\nBuscando reservas activas de los clientes...");
        try {
            gestorHotel.buscarReservasActivasCliente(1);
            gestorHotel.buscarReservasActivasCliente(2);
            gestorHotel.buscarReservasActivasCliente(10);
        } catch (Exception e) {
            imprimirMensaje("Error: " + e.getMessage());
        }
    }

    public void cancelarReserva(int idReserva) {
        imprimirMensaje("\nCancelando reserva...");
        try {
            gestorHotel.cancelarReserva(idReserva);
            imprimirMensaje("Reserva cancelada exitosamente. ID: " + idReserva);
        } catch (Exception e) {
            imprimirMensaje("Error al cancelar reserva: " + e.getMessage());
        }
    }

    public void mostrarHistorialReservas() {
        imprimirMensaje("\nMostrando historial de reservas...");
        try {
            gestorHotel.listarHistorialReservasCliente(10);
            gestorHotel.listarHistorialReservasCliente(2);
        } catch (Exception e) {
            imprimirMensaje("Error: " + e.getMessage());
        }
    }

    public void mostrarHistorialReservasTodosLosUsuarios() {
        imprimirMensaje("\nMostrando historial de reservas de todos los usuarios...");
        gestorHotel.getClientes().forEach(cliente -> {
            imprimirMensaje("\nHistorial de reservas del cliente " + cliente.getNombre() + " (ID: " + cliente.getId() + "):");
            if (cliente.getHistorialReservas().isEmpty()) {
                imprimirMensaje("El cliente no tiene historial de reservas.");
            } else {
                cliente.getHistorialReservas().forEach(reserva -> 
                    imprimirMensaje("Reserva ID: " + reserva.getId() + ", Habitación: " + reserva.getHabitacion().getNumero() + ", Checkin: " + reserva.getFechaCheckin() + ", Checkout: " + reserva.getFechaCheckout())
                );
            }
        });
    }

    public void mostrarResumenHabitaciones() {
        imprimirMensaje("\nResumen de habitaciones:");
        gestorHotel.getHabitaciones().forEach(habitacion ->
            imprimirMensaje("Habitación " + habitacion.getNumero() + " - " + habitacion.getTipo() + " - " + habitacion.getEstado())
        );
    }

    public void crearYCancelarReserva() {
        try {
            imprimirMensaje("\nCreando una reserva que se pueda cancelar...");
            Reserva reservaCancelable = new Reserva(8, gestorHotel.buscarClientePorId(2), gestorHotel.buscarHabitacionPorNum(201), LocalDate.now().plusDays(3), LocalDate.now().plusDays(5));
            gestorHotel.hacerReserva(reservaCancelable);
            imprimirMensaje("Reserva creada exitosamente. ID: " + reservaCancelable.getId());

            imprimirMensaje("\nCancelando la reserva creada...");
            gestorHotel.cancelarReserva(8);
            imprimirMensaje("Reserva cancelada exitosamente. ID: " + reservaCancelable.getId());

            imprimirMensaje("\nVerificando estado de la habitación después de la cancelación:");
            gestorHotel.getHabitaciones().forEach(habitacion -> {
                if (habitacion.getNumero() == 201) {
                    imprimirMensaje("Habitación " + habitacion.getNumero() + " - Estado: " + habitacion.getEstado());
                }
            });
        } catch (Exception e) {
            imprimirMensaje("Error: " + e.getMessage());
        }
    }

    public void mostrarReservasDeCadaCliente() {
        imprimirMensaje("\nMostrando reservas de cada cliente...");
        gestorHotel.getClientes().forEach(cliente -> {
            imprimirMensaje("\nReservas activas del cliente " + cliente.getNombre() + " (ID: " + cliente.getId() + "):");
            if (cliente.getReservasActuales().isEmpty()) {
                imprimirMensaje("El cliente no tiene reservas activas.");
            } else {
                cliente.getReservasActuales().forEach(reserva ->
                    imprimirMensaje("Reserva ID: " + reserva.getId() + ", Habitación: " + reserva.getHabitacion().getNumero() + ", Checkin: " + reserva.getFechaCheckin() + ", Checkout: " + reserva.getFechaCheckout())
                );
            }

            imprimirMensaje("\nHistorial de reservas del cliente " + cliente.getNombre() + " (ID: " + cliente.getId() + "):");
            if (cliente.getHistorialReservas().isEmpty()) {
                imprimirMensaje("El cliente no tiene historial de reservas.");
            } else {
                cliente.getHistorialReservas().forEach(reserva ->
                    imprimirMensaje("Reserva ID: " + reserva.getId() + ", Habitación: " + reserva.getHabitacion().getNumero() + ", Checkin: " + reserva.getFechaCheckin() + ", Checkout: " + reserva.getFechaCheckout())
                );
            }
        });
    }
}
