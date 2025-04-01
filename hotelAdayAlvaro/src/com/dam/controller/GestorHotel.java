//HAY QUE HACER UNA FUNCION PARA AGREGAR HABITACIONES. LAS HABITACIONES ESTAN DIVIDIDAS EN PLANTAS:
//Planta 1: 101 - 105
//Planta 2: 201 - 205
//Planta 3: 301 - 305

//Tipos de habitación y precios por noche:

//INDIVIDUAL: 50€
//DOBLE: 80€
//SUITE: 150€


package com.dam.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import com.dam.model.*;


public class GestorHotel{
    private ArrayList<Cliente> clientes;
    private ArrayList<Reserva> reservas;
    private ArrayList<Habitacion> habitaciones;

    
    public GestorHotel(){
    this.clientes = new ArrayList<>();
    this.reservas = new ArrayList<>();
    this.habitaciones = new ArrayList<>();
}

    public Cliente buscarClientePorId(int id){
        for (Cliente cliente : clientes) {
            if (cliente.getId() == id) {
                return cliente;
            }
        }
        return null;
    }

    public Habitacion buscarHabitacionPoNum(int numero){
        for (Habitacion habitacion : habitaciones) {
            if (habitacion.getNumero() == numero) {
                return habitacion;
            }
        }
        return null;
    }

    public Reserva buscarReservaPorId(int id){
        for (Reserva reserva : reservas) {
            if (reserva.getId() == id) {
                return reserva;
            }
        }
        return null;
    }

    public void agregarCliente(Cliente cliente){
        clientes.add(cliente);
    }

    public void hacerReserva(Reserva reserva) throws Exception{
        Cliente cliente= buscarClientePorId(reserva.getCliente().getId());
        if (cliente == null) {
            throw new Exception("Cliente no encontrado.");
        }
        Habitacion habitacion= buscarHabitacionPoNum(reserva.getHabitacion().getNumero());
        if (habitacion == null) {
            throw new Exception("Habitacion no encontrada.");
        }
        if (habitacion.getEstado().equals(EstadoHabitacion.DISPONIBLE) ) {
            cliente.addReserva(reserva);
            habitacion.setEstado(EstadoHabitacion.RESERVADA);
            reservas.add(reserva);
            
        } else {
            throw new Exception("La habitacion no esta disponible.");
        }


        reservas.add(reserva);
        
    }

    public void cancelarReserva(int id) throws Exception{
        Reserva reserva= buscarReservaPorId(id);
        if (reserva == null) {
            throw new Exception("Reserva no encontrada.");
        }
        Cliente cliente= reserva.getCliente();
        Habitacion habitacion= reserva.getHabitacion();

        if (reserva.getFechaCheckin().isBefore(LocalDate.now())) {
            throw new Exception("No se puede cancelar una reserva con fecha de checkin pasada.");
            
        }
        if (habitacion.getEstado().equals(EstadoHabitacion.RESERVADA) ) {
            cliente.getReservasActuales().remove(reserva);
            habitacion.setEstado(EstadoHabitacion.DISPONIBLE);
            reservas.remove(reserva);
            
        } else {
            throw new Exception("Error. La habitacion está "+habitacion.getEstado());
        }
        
    }


    public void buscarReservasActivasCliente(int id) throws Exception{
        Cliente cliente= buscarClientePorId(id);
        if (cliente == null) {
            throw new Exception("Cliente no encontrado.");
        }
        ArrayList<Reserva> reservasActivas= cliente.getReservasActuales();
        if (reservasActivas.isEmpty()) {
            System.out.println("El cliente no tiene reservas activas.");
        } else {
            for (Reserva reserva : reservasActivas) {
                System.out.println("Reserva ID: " + reserva.getId() + ", Habitacion: " + reserva.getHabitacion().getNumero() + ", Checkin: " + reserva.getFechaCheckin() + ", Checkout: " + reserva.getFechaCheckout());
            }
        }
    }

    public void listarHistorialReservasCliente(int id) throws Exception{
        Cliente cliente= buscarClientePorId(id);
        if (cliente == null) {
            throw new Exception("Cliente no encontrado.");
        }
        ArrayList<Reserva> historialReservas= cliente.getHistorialReservas();
        if (historialReservas.isEmpty()) {
            System.out.println("El cliente no tiene historial de reservas.");
        } else {
            System.out.println("Historial de reservas del cliente " + cliente.getNombre() + " (ID:"+cliente.getId()+"):");
            for (Reserva reserva : historialReservas) {
                System.out.println("Reserva ID: " + reserva.getId() + ", Habitacion: " + reserva.getHabitacion().getNumero() + ", Checkin: " + reserva.getFechaCheckin() + ", Checkout: " + reserva.getFechaCheckout());
            }
        }
    }

}
