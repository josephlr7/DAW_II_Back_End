package cibertec.pe.controller;

import cibertec.pe.dto.ShippingStatusUpdateRequest;
import cibertec.pe.model.Shipping;
import cibertec.pe.repository.IShippingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/shipping")
public class ShippingController {

    @Autowired
    private IShippingRepository shippingRepository;

    // 1. Programar nuevo envío (Llamado automáticamente por Order-Service al confirmar compra)
    @PostMapping
    public ResponseEntity<String> programarEnvio(@RequestBody Shipping shipping) {
        if (shipping.getEstadoEnvio() == null || shipping.getEstadoEnvio().trim().isEmpty()) {
            shipping.setEstadoEnvio("ALISTANDO_PEDIDO");
        }
        if (shipping.getCourier() == null || shipping.getCourier().trim().isEmpty()) {
            shipping.setCourier("Olva Courier");
        }
        if (shipping.getOrderId() != null && shipping.getNumeroGuia() == null) {
            shipping.setNumeroGuia("OLV-" + String.format("%06d", shipping.getOrderId()));
        }

        // Si ya existe un registro de envío para esta orden, actualizarlo en vez de crear duplicados
        if (shipping.getOrderId() != null) {
            Optional<Shipping> existing = shippingRepository.findFirstByOrderIdOrderByIdDesc(shipping.getOrderId());
            if (existing.isPresent()) {
                Shipping current = existing.get();
                current.setDireccionDestino(shipping.getDireccionDestino());
                if (shipping.getEstadoEnvio() != null) current.setEstadoEnvio(shipping.getEstadoEnvio());
                shippingRepository.save(current);
                return ResponseEntity.ok("Envío actualizado exitosamente para la orden ID: " + shipping.getOrderId());
            }
        }

        shippingRepository.save(shipping);
        System.out.println("[SHIPPING] Envío programado para orden ID: " + shipping.getOrderId() + " con Guía: " + shipping.getNumeroGuia() + " hacia: " + shipping.getDireccionDestino());
        return ResponseEntity.ok("Envío registrado y programado exitosamente para la orden ID: " + shipping.getOrderId());
    }

    // 2. Listar todos los despachos registrados (Para el Panel de Control Administrador)
    @GetMapping
    public ResponseEntity<List<Shipping>> listarEnvios() {
        return ResponseEntity.ok(shippingRepository.findAllByOrderByIdDesc());
    }

    // 3. Obtener el estado de seguimiento de una orden específica (Para el Cliente / Tracking)
    @GetMapping("/order/{orderId}")
    public ResponseEntity<Shipping> obtenerPorOrdenId(@PathVariable Long orderId) {
        Optional<Shipping> opt = shippingRepository.findFirstByOrderIdOrderByIdDesc(orderId);
        if (opt.isPresent()) {
            return ResponseEntity.ok(opt.get());
        }
        // Fallback dinámico: Si la orden existe en Order-Service pero aún no tenía despacho en BD, lo inicializa
        Shipping fallback = new Shipping();
        fallback.setOrderId(orderId);
        fallback.setEstadoEnvio("ALISTANDO_PEDIDO");
        fallback.setCourier("Olva Courier");
        fallback.setNumeroGuia("OLV-" + String.format("%06d", orderId));
        fallback.setDireccionDestino("Dirección registrada en la compra");
        return ResponseEntity.ok(shippingRepository.save(fallback));
    }

    // 4. Actualizar estado logístico (Para que el Admin cambie a "EN_OLVA_COURIER", "ENTREGADO", etc.)
    @PutMapping("/order/{orderId}/status")
    public ResponseEntity<Shipping> actualizarEstado(
            @PathVariable Long orderId,
            @RequestBody ShippingStatusUpdateRequest request
    ) {
        Optional<Shipping> opt = shippingRepository.findByOrderId(orderId);
        Shipping shipping = opt.orElseGet(() -> {
            Shipping s = new Shipping();
            s.setOrderId(orderId);
            return s;
        });

        if (request.getEstadoEnvio() != null && !request.getEstadoEnvio().trim().isEmpty()) {
            shipping.setEstadoEnvio(request.getEstadoEnvio().trim());
        }
        if (request.getCourier() != null && !request.getCourier().trim().isEmpty()) {
            shipping.setCourier(request.getCourier().trim());
        }
        if (request.getNumeroGuia() != null && !request.getNumeroGuia().trim().isEmpty()) {
            shipping.setNumeroGuia(request.getNumeroGuia().trim());
        }
        if (request.getNotas() != null) {
            shipping.setNotas(request.getNotas().trim());
        }

        Shipping guardado = shippingRepository.save(shipping);
        System.out.println("[SHIPPING] Estado actualizado para orden ID: " + orderId + " -> " + guardado.getEstadoEnvio());
        return ResponseEntity.ok(guardado);
    }
}
