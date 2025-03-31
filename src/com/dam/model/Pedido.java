package com.dam.model;

import java.util.HashMap;
import java.util.Map;

public class Pedido {
    private static int contadorId = 1;
    private int id;
    private EstadoPedido estado;
    private Map<Producto, Integer> productos; // Clave: Producto, Valor: Cantidad

    public enum EstadoPedido {
        EN_PREPARACION, LISTO_PARA_ENTREGAR, ENTREGADO
    }

    public Pedido() {
        this.id = contadorId++;
        this.estado = EstadoPedido.EN_PREPARACION;
        this.productos = new HashMap<>();
    }

    public int getId() {
        return id;
    }

    public EstadoPedido getEstado() {
        return estado;
    }

    public void cambiarEstado(EstadoPedido nuevoEstado) {
        this.estado = nuevoEstado;
    }

    public void agregarProducto(Producto producto, int cantidad) {
        if (producto == null || cantidad <= 0)
            return;

        if (productos.containsKey(producto)) {
            productos.put(producto, productos.get(producto) + cantidad); // Incrementar cantidad
        } else {
            productos.put(producto, cantidad);
        }
    }

    public void eliminarProducto(Producto producto) {
        productos.remove(producto);
    }

    public double calcularTotal() {
        double total = 0;
        for (Map.Entry<Producto, Integer> entry : productos.entrySet()) {
            total += entry.getKey().getPrecio() * entry.getValue();
        }
        return total;
    }

    public Map<Producto, Integer> getProductos() {
        return productos;
    }
}
