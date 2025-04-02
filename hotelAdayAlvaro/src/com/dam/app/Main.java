package com.dam.app;

import com.dam.controller.GestorHotel;
import com.dam.model.Cliente;
import com.dam.model.Reserva;
import com.dam.view.Consola;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        // Crear gestor de hotel
        GestorHotel gestorHotel = new GestorHotel();

        // Crear consola con el gestor de hotel
        Consola consola = new Consola(gestorHotel);

        // Agregar habitaciones al hotel
        consola.imprimirMensaje("Agregando habitaciones al hotel...");
        gestorHotel.agregarHabitaciones();

        // Crear clientes
        Cliente cliente1 = new Cliente(1, "Juan Pérez");
        Cliente cliente2 = new Cliente(2, "María López");
        Cliente cliente3 = new Cliente(10, "Martín Oliver");

        // Agregar clientes al gestor
        gestorHotel.agregarCliente(cliente1);
        gestorHotel.agregarCliente(cliente2);
        gestorHotel.agregarCliente(cliente3);

        // Crear reservas
        consola.crearReservasIniciales();

        // Crear reservas adicionales
        consola.crearReservasAdicionales();

        // Verificar estados de habitaciones
        consola.verificarEstadosHabitaciones();

        // Mostrar reservas de cada cliente
        consola.mostrarReservasDeCadaCliente();
    }
}
