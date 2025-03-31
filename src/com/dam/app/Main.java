package com.dam.app;

import com.dam.controller.GestorPedidos;
import com.dam.controller.GestorProductos;
import com.dam.model.Categoria;
import com.dam.model.Pedido;
import com.dam.model.Producto;
import com.dam.view.Consola;

public class Main {
    public static void main(String[] args) {
        // Crear consola
        Consola consola = new Consola();

        // Crear gestor de productos
        GestorProductos gestorProductos = new GestorProductos();

        // Crear categorías
        Categoria electronica = new Categoria("Electrónica");
        Categoria hogar = new Categoria("Hogar");

        // Crear productos con categorías
        Producto p1 = new Producto("Ordenador ", 999.99, electronica);
        Producto p2 = new Producto("Aspiradora", 149.99, hogar);
        Producto p3 = new Producto("Smartphone", 599.99, electronica);

        // Agregar productos al gestor
        gestorProductos.agregarProducto(p1);
        gestorProductos.agregarProducto(p2);
        gestorProductos.agregarProducto(p3);

        // Mostrar productos
        consola.mostrarProducto(gestorProductos.listarProductos());

        // Buscar un producto por nombre y mostrarlo
        Consola.imprimir("\nBuscando producto 'Smartphone'...");
        Producto productoBuscado = gestorProductos.buscarProductoPorNombre("Smartphone");
        if (productoBuscado != null) {
            Consola.imprimir("Producto encontrado:");
            consola.mostrarProducto(productoBuscado);
        } else {
            Consola.imprimir("Producto no encontrado.");
        }

        // Crear gestor de pedidos
        GestorPedidos gestorPedidos = new GestorPedidos();

        // Crear pedidos
        Pedido pedido1 = new Pedido();
        Pedido pedido2 = new Pedido();

        // Agregar pedidos al gestor
        gestorPedidos.agregarPedido(pedido1);
        gestorPedidos.agregarPedido(pedido2);

        // Agregar productos a los pedidos usando la búsqueda
        Pedido pedidoEncontrado = gestorPedidos.buscarPedidoPorId(pedido1.getId());
        if (pedidoEncontrado != null) {
            Consola.imprimir("\nAgregando productos al pedido...");
            pedidoEncontrado.agregarProducto(productoBuscado, 2);
            pedidoEncontrado.agregarProducto(p1, 1);
            pedidoEncontrado.cambiarEstado(Pedido.EstadoPedido.LISTO_PARA_ENTREGAR);
        }

        // Mostrar pedidos
        Consola.imprimir("\nListado de pedidos:");
        consola.mostrarPedido(pedido1);
        consola.mostrarPedido(pedido2);

        // Buscar un pedido por ID y mostrarlo
        Consola.imprimir("\nBuscando pedido por ID: " + pedido1.getId());
        Pedido pedidoBuscado = gestorPedidos.buscarPedidoPorId(pedido1.getId());
        if (pedidoBuscado != null) {
            pedidoBuscado.cambiarEstado(Pedido.EstadoPedido.ENTREGADO);
            Consola.imprimir("Pedido encontrado:");
            consola.mostrarPedido(pedidoBuscado);
        } else {
            Consola.imprimir("Pedido no encontrado.");
        }
    }
}
