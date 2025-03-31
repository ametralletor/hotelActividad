package com.dam.controller;

import java.util.ArrayList;
import com.dam.model.Producto;

public class GestorProductos {
    private ArrayList<Producto> productos;

    public GestorProductos() {
        this.productos = new ArrayList<>();
    }

    public void agregarProducto(Producto producto) {
        productos.add(producto);
    }

    public Producto buscarProductoPorNombre(String nombre) {
        for (Producto producto : productos) {
            if (producto.getNombre().equalsIgnoreCase(nombre)) {
                return producto;
            }
        }
        return null;
    }

    public ArrayList<Producto> listarProductos() {
        return productos;
    }
}
