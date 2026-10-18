package cibertec.pe.service;

import cibertec.pe.client.IOrderBillinClient;
import cibertec.pe.client.IOrderIndentityClient;
import cibertec.pe.client.IOrderPaymentClient;
import cibertec.pe.client.IOrderInventoryClient; // Cliente Feign para Inventario (o usar RestTemplate)
import cibertec.pe.client.IOrderShippingClient;   // Cliente Feign para Envío
import cibertec.pe.client.IOrderNotificationClient;// Cliente Feign para Notificaciones

import cibertec.pe.dto.ClientIdentityDto;
import cibertec.pe.dto.FacturaDto;
import cibertec.pe.dto.PaymentDto;
import cibertec.pe.dto.InventoryDto;   // Crear este DTO
import cibertec.pe.dto.ShippingDto;    // Crear este DTO
import cibertec.pe.dto.NotificationDto;// Crear este DTO

import cibertec.pe.entity.Order;
import cibertec.pe.repository.IOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderImplements implements IOrderService {

    @Autowired
    private IOrderRepository orderRepository;

    @Autowired
    private IOrderIndentityClient identityClient;

    @Autowired
    private IOrderPaymentClient paymentClient;

    @Autowired
    private IOrderBillinClient billingClient;

    // Inyectamos los clientes para los 3 nuevos servicios
    @Autowired
    private IOrderInventoryClient inventoryClient;

    @Autowired
    private IOrderShippingClient shippingClient;

    @Autowired
    private IOrderNotificationClient notificationClient;

    @Override
    public Order crearOrden(String numeroDocumento, String producto, Double total) {

        // 1. Validar identidad con Identity-Service
        ClientIdentityDto clienteDto = identityClient.obtenerCliente(numeroDocumento);
        if (clienteDto == null || clienteDto.getNombreRazonSocial() == null) {
            throw new RuntimeException("No se pudo validar el documento del cliente en Identity-Service.");
        }

        // 2. Guardar la orden en la base de datos local
        Order order = new Order();
        order.setNumeroDocumentoCliente(clienteDto.getNumeroDocumento());
        order.setNombreCliente(clienteDto.getNombreRazonSocial());
        order.setDireccionCliente(clienteDto.getDireccionFiscal());
        order.setProducto(producto);
        order.setTotal(total);
        Order ordenGuardada = orderRepository.save(order);

        // 3. Procesar el pago en Payment-Service
        PaymentDto paymentDto = new PaymentDto(
                ordenGuardada.getId(),
                ordenGuardada.getTotal(),
                "4557-XXXX-XXXX-1234"
        );
        String respuestaPago = paymentClient.procesarPago(paymentDto);
        System.out.println("Respuesta Pago: " + respuestaPago);

        // 4. Emitir comprobante en Billing-Service
        String tipoComprobante = (numeroDocumento.length() == 11) ? "Factura" : "Boleta";
        FacturaDto facturaDto = new FacturaDto(
                0,
                tipoComprobante,
                numeroDocumento.trim(),
                clienteDto.getNombreRazonSocial(),
                total,
                "Enviado"
        );
        FacturaDto facturaEmitida = billingClient.emitirFactura(facturaDto);
        System.out.println("Comprobante emitido con estado: " + facturaEmitida.getEstadoSunat());

        // 5. Verificar y descontar stock en Inventory-Service (Nuevo)
        try {
            InventoryDto inventoryDto = new InventoryDto(producto, 1);
            String respInventory = inventoryClient.verificarYDescontarStock(inventoryDto);
            System.out.println("Inventario: " + respInventory);
        } catch (Exception e) {
            System.err.println("Error en Inventory-Service: " + e.getMessage());
        }

        // 6. Programar envío en Shipping-Service (Nuevo)
        try {
            ShippingDto shippingDto = new ShippingDto(ordenGuardada.getId(), clienteDto.getDireccionFiscal());
            String respShipping = shippingDto != null ? shippingClient.programarEnvio(shippingDto) : "";
            System.out.println("Envío: " + respShipping);
        } catch (Exception e) {
            System.err.println("Error en Shipping-Service: " + e.getMessage());
        }

        // 7. Enviar notificación en Notification-Service (Nuevo)
        try {
            NotificationDto notificationDto = new NotificationDto(
                    ordenGuardada.getId(),
                    "Estimado " + clienteDto.getNombreRazonSocial() + ", su orden #" + ordenGuardada.getId() + " ha sido procesada con éxito."
            );
            String respNotif = notificationClient.enviarNotificacion(notificationDto);
            System.out.println("Notificación: " + respNotif);
        } catch (Exception e) {
            System.err.println("Error en Notification-Service: " + e.getMessage());
        }

        return ordenGuardada;
    }

    @Override
    public List<Order> listarOrdenes() {
        return orderRepository.findAll();
    }
}