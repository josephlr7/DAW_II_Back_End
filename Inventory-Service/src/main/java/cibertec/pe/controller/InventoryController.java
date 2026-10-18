package cibertec.pe.controller;

import cibertec.pe.model.Inventory;
import cibertec.pe.repository.IInventorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {
    @Autowired
    private IInventorRepository inventoryRepository;

    @PostMapping("/check")
    public ResponseEntity<String> verificarYDescontarStock(@RequestBody Inventory request) {
        Inventory item = inventoryRepository.findByProducto(request.getProducto()).orElse(null);

        if (item == null) {
            item = new Inventory();
            item.setProducto(request.getProducto());
            item.setStock(100); // Stock inicial por defecto para pruebas
            inventoryRepository.save(item);
        }

        if (item.getStock() >= request.getStock()) {
            item.setStock(item.getStock() - request.getStock());
            inventoryRepository.save(item);
            return ResponseEntity.ok("Stock verificado y descontado correctamente. Stock restante: " + item.getStock());
        } else {
            return ResponseEntity.badRequest().body("Stock insuficiente para el producto: " + request.getProducto());
        }
    }
}
