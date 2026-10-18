package cibertec.pe.controller;

import cibertec.pe.Dto.PaymentDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    @PostMapping
    public ResponseEntity<String> procesarPago(@RequestBody PaymentDto paymentDto) {
        // Lógica simulada de pasarela de pagos
        if (paymentDto.getMonto() != null && paymentDto.getMonto() > 0) {
            return ResponseEntity.ok("Pago procesado y aprobado exitosamente para la orden ID: " + paymentDto.getOrderId());
        } else {
            return ResponseEntity.badRequest().body("Error: El monto del pago no es válido.");
        }
    }

}
