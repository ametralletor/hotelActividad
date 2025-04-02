//HAY QUE HACER UNA FUNCION PARA AGREGAR HABITACIONES. LAS HABITACIONES ESTAN DIVIDIDAS EN PLANTAS:
//Planta 1: 101 - 105
//Planta 2: 201 - 205
//Planta 3: 301 - 305

//Tipos de habitación y precios por noche:

//INDIVIDUAL: 50€
//DOBLE: 80€
//SUITE: 150€

//----------TERMINADO-----------------------//

//¿?¿?¿CONSOLA?¿?¿
//AÑADIR FUNCION PARA BUSCAR UNA HABITACION POR TIPO Y OTRA POR ESTADO
//Resumen de Habitaciones: Listar todas las habitaciones y su estado actual. 
//Resumen de Clientes: Listar todos los clientes registrados y las habitaciones que tienen reservadas.

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
    actualizarEstadoHabitaciones();
}

    //excepcion para reserva
    class ReservaNoDisponibleException extends Exception {
        public ReservaNoDisponibleException(String message) {
            super(message);
        }
    }

    //excepcion para cliente no encontrado
    class ClienteNoEncontradoException extends Exception {
        public ClienteNoEncontradoException(String message) {
            super(message);
        }
    }

    //metodo para actualizar el estado de las habitaciones
    public void actualizarEstadoHabitaciones() {
        LocalDate hoy = LocalDate.now();
        for (Reserva reserva : reservas) {
            Habitacion habitacion = reserva.getHabitacion();
            if (reserva.getFechaCheckin().equals(hoy) && habitacion.getEstado().equals(EstadoHabitacion.RESERVADA)) {
                habitacion.setEstado(EstadoHabitacion.OCUPADA);
                System.out.println("Habitación " + habitacion.getNumero() + " ahora está OCUPADA.");
            }
        }
    }

    //metodo para buscar cliente por id
    public Cliente buscarClientePorId(int id){
        for (Cliente cliente : clientes) {
            if (cliente.getId() == id) {
                return cliente;
            }
        }
        return null;
    }

    //metodo para buscar habitacion por numero
    public Habitacion buscarHabitacionPorNum(int numero){
        for (Habitacion habitacion : habitaciones) {
            if (habitacion.getNumero() == numero) {
                return habitacion; 
            }
        }
        return null;
    }
     //metodo para buscar habitacion por estado
    public Habitacion buscarHabitacionPorEstado(EstadoHabitacion estado){
        for (Habitacion habitacion : habitaciones) {
            if (habitacion.getEstado() == estado) {
                return habitacion; 
            }
        }
        return null;
    }

    //metodo para buscar habitacion por tipo
    public Habitacion buscarHabitacionPorTipo(TipoHabitacion tipo){
        for (Habitacion habitacion : habitaciones) {
            if (habitacion.getTipo() == tipo) {
                return habitacion; 
            }
        }
        return null;
    }

    //metodo para buscar reserva por id
    public Reserva buscarReservaPorId(int id){
        for (Reserva reserva : reservas) {
            if (reserva.getId() == id) {
                return reserva;
            }
        }
        return null;
    }

    //metodo para agregar cliente
    public void agregarCliente(Cliente cliente){
        clientes.add(cliente);
    }

    //metodo para hacer reserva
    public void hacerReserva(Reserva reserva) throws Exception {
        Cliente cliente = buscarClientePorId(reserva.getCliente().getId());
        if (cliente == null) {
            throw new ClienteNoEncontradoException("Cliente no encontrado.");
        }
        Habitacion habitacion = buscarHabitacionPorNum(reserva.getHabitacion().getNumero());
        if (habitacion == null) {
            throw new Exception("Habitación no encontrada.");
        }
        if (habitacion.getEstado().equals(EstadoHabitacion.DISPONIBLE)) {
            cliente.addReserva(reserva);
            if (reserva.getFechaCheckin().equals(LocalDate.now())) {
                habitacion.setEstado(EstadoHabitacion.OCUPADA); // Actualiza el estado a ocupada, ya que la fecha es hoy
            } else {
                habitacion.setEstado(EstadoHabitacion.RESERVADA); // Actualiza el estado a reservada
                
            }
            reservas.add(reserva);
            System.out.println("Habitación " + habitacion.getNumero() + " reservada correctamente. Estado actual: " + habitacion.getEstado());
        } else {
            throw new ReservaNoDisponibleException("La habitación " + habitacion.getNumero() + " no está disponible para reserva. Estado actual: " + habitacion.getEstado());
        }
    }


    //metodo para cancelar reserva
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
            cliente.cancelReserva(reserva);
            habitacion.actualizarEstado(EstadoHabitacion.DISPONIBLE);
            reservas.remove(reserva);
            
        } else {
            throw new Exception("Error. La habitacion está "+habitacion.getEstado());
        }
        
    }


    //metodo para listar reservas activas de un cliente
    public void buscarReservasActivasCliente(int id) throws Exception{
        Cliente cliente= buscarClientePorId(id);
        if (cliente == null) {
            throw new ClienteNoEncontradoException("Cliente no encontrado.");
        }
        ArrayList<Reserva> reservasActivas= cliente.getReservasActuales();
        if (reservasActivas.isEmpty()) {
            System.out.println("El cliente no tiene reservas activas.");
        } else {
            System.out.println("Reservas activas del cliente " + cliente.getNombre() + " (ID:"+cliente.getId()+"):");
            for (Reserva reserva : reservasActivas) {
                System.out.println("Reserva ID: " + reserva.getId() + ", Habitacion: " + reserva.getHabitacion().getNumero() + ", Checkin: " + reserva.getFechaCheckin() + ", Checkout: " + reserva.getFechaCheckout());
            }
        }
    }

    //metodo para listar historial de reservas de un cliente
    public void listarHistorialReservasCliente(int id) throws Exception{
        Cliente cliente= buscarClientePorId(id);
        if (cliente == null) {
            throw new ClienteNoEncontradoException("Cliente no encontrado.");
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

    //metodo para listar todas las habitaciones
    public void agregarHabitaciones() {
        for (int i = 1; i <= 3; i++) { // Loop through floors
            for (int j = 1; j <= 3; j++) { // Loop through rooms per floor (101-103, 201-203, 301-303)
                int numeroHabitacion = i * 100 + j;
                TipoHabitacion tipo;
                double precio;

                if (j == 1) { // First room on each floor is a Suite
                    tipo = TipoHabitacion.SUITE;
                    precio = 150.0;
                } else if (j == 2) { // Second room on each floor is Double
                    tipo = TipoHabitacion.DOBLE;
                    precio = 80.0;
                } else { // Third room on each floor is Individual
                    tipo = TipoHabitacion.INDIVIDUAL;
                    precio = 50.0;
                }

                Habitacion habitacion = new Habitacion(numeroHabitacion, tipo, precio, EstadoHabitacion.DISPONIBLE);
                habitaciones.add(habitacion);
            }
        }
    }

    //metodo para listar todas las habitaciones
    public ArrayList<Habitacion> getHabitaciones() {
        return habitaciones;
    }

    //metodo para listar todas los clientes
    public ArrayList<Cliente> getClientes() {
        return clientes;
    }

}
