package cibertec.pe.controller;

import cibertec.pe.entity.Order;
import cibertec.pe.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    @Autowired
    private IOrderService orderService;

    @PostMapping
    public ResponseEntity<?> crearOrden(@RequestBody Map<String, Object> request) {
        try {
            String numeroDocumento = (String) request.get("numeroDocumento");
            String producto = (String) request.get("producto");
            Double total = Double.valueOf(request.get("total").toString());

            Order nuevaOrden = orderService.crearOrden(numeroDocumento, producto, total);
            return ResponseEntity.ok(nuevaOrden);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al crear la orden: " + e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<Order>> listarOrdenes() {
        return ResponseEntity.ok(orderService.listarOrdenes());
    }
}
