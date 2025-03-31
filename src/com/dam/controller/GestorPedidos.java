package com.dam.controller;

import java.util.ArrayList;
import com.dam.model.Pedido;

public class GestorPedidos {
    private ArrayList<Pedido> pedidos;

    public GestorPedidos() {
        this.pedidos = new ArrayList<>();
    }

    public void agregarPedido(Pedido pedido) {
        pedidos.add(pedido);
    }

    public Pedido buscarPedidoPorId(int id) {
        for (Pedido pedido : pedidos) {
            if (pedido.getId() == id) {
                return pedido;
            }
        }
        return null;
    }

    public ArrayList<Pedido> listarPedidos() {
        return pedidos;
    }
}
