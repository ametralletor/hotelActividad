package com.dam.model;

public enum TipoHabitacion {
    INDIVIDUAL(50), DOBLE(80), SUITE(150);


private final double precio;

TipoHabitacion(double precio){
    this.precio = precio;
}

public double getPrecio(){
    return precio;
}
}
