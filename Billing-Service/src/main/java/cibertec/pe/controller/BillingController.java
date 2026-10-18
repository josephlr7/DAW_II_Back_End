package cibertec.pe.controller;

import cibertec.pe.Dto.FacturaDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/billing")
public class BillingController {
    @PostMapping
    public ResponseEntity<String> generarFactura(@RequestBody FacturaDto facturaDto) {
        // Lógica para registrar y emitir la factura electrónica / comprobante
        return ResponseEntity.ok("Factura generada e impresa con éxito para la orden ID: " + facturaDto.getOrderId()
                + " a nombre del cliente con documento: " + facturaDto.getNumeroDocumentoCliente());
    }
}
