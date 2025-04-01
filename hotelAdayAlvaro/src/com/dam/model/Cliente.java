package com.dam.model;
import java.time.LocalDate;
import java.util.ArrayList;


public class Cliente {
    private int id;
    private String nombre;
    private ArrayList<Reserva> historialReservas;
    private ArrayList<Reserva> reservasActuales;


    public Cliente(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
        this.historialReservas = new ArrayList<>();
        this.reservasActuales = new ArrayList<>();
    }

    public int getId() {
        return id;
    }
    
    public String getNombre() {
        return nombre;
    }

    public ArrayList<Reserva> getHistorialReservas() {
        return historialReservas;
    }

    public ArrayList<Reserva> getReservasActuales() {
        return reservasActuales;
    }

    public void addReserva(Reserva reserva) throws Exception{
        if (reservasActuales.size()<3) {
            reservasActuales.add(reserva);
            historialReservas.add(reserva);
            
        } else{
            throw new Exception("El cliente (ID:"+id+") "+nombre+" ya tiene 3 reservas actuales.");
        }
        
        
    }

    public void cancelReserva(Reserva reserva) throws Exception{
        try {
            reservasActuales.remove(reserva);
        } catch (Exception e) {
            throw new Exception("No se ha podido cancelar la reserva (ID:"+reserva.getId()+") del cliente (ID:"+id+") "+nombre+".");
        }
        
    }

    
}
