package com.dam.view;

import java.util.ArrayList;
import java.util.Map;

import com.dam.model.Pedido;
import com.dam.model.Producto;

public class Consola {
    public static void imprimir(String mensaje) {
        System.out.println(mensaje);
    }

    // Muestra un solo producto
    public void mostrarProducto(Producto producto) {
        if (producto == null) {
            imprimir("Producto no disponible.");
            return;
        }

        imprimir(String.format("Producto: %s | Precio: %.2f€ | Categoría: %s",
                producto.getNombre(), producto.getPrecio(), producto.getCategoria().getNombre()));
    }

    // Sobrecarga: Muestra una lista de productos
    public void mostrarProducto(ArrayList<Producto> productos) {
        if (productos == null || productos.isEmpty()) {
            imprimir("No hay productos disponibles.");
            return;
        }

        imprimir("\nListado de productos disponibles:");
        for (Producto p : productos) {
            mostrarProducto(p);
        }
    }

    // Muestra un solo pedido
    public void mostrarPedido(Pedido pedido) {
        if (pedido == null) {
            imprimir("Pedido no encontrado.");
            return;
        }

        imprimir("\nPedido ID: " + pedido.getId());
        imprimir("Estado: " + pedido.getEstado());
        imprimir("Productos:");

        if (pedido.getProductos().isEmpty()) {
            imprimir("El pedido no tiene productos.");
        } else {
            for (Map.Entry<Producto, Integer> entry : pedido.getProductos().entrySet()) {
                Producto producto = entry.getKey();
                int cantidad = entry.getValue();
                double subtotal = producto.getPrecio() * cantidad;

                imprimir(String.format("%s x%d - Subtotal: %.2f€",
                        producto.getNombre(), cantidad, subtotal));
            }
        }

        imprimir(String.format("Total del pedido: %.2f€", pedido.calcularTotal()));
    }

    // Sobrecarga: Muestra una lista de pedidos
    public void mostrarPedido(ArrayList<Pedido> pedidos) {
        if (pedidos == null || pedidos.isEmpty()) {
            imprimir("No hay pedidos registrados.");
            return;
        }

        imprimir("\nLista de pedidos:");
        for (Pedido p : pedidos) {
            mostrarPedido(p);
        }
    }
}
