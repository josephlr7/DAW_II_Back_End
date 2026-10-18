package cibertec.pe.service;

import cibertec.pe.entity.Order;

import java.util.List;

public interface IOrderService {
    Order crearOrden(String numeroDocumento, String producto, Double total);
    List<Order> listarOrdenes();
}
