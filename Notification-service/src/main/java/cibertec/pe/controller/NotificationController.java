package cibertec.pe.controller;

import cibertec.pe.model.Notification;
import cibertec.pe.repository.INotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {
    @Autowired
    private INotificationRepository notificationRepository;

    @PostMapping
    public ResponseEntity<String> enviarNotificacion(@RequestBody Notification notification) {
        notificationRepository.save(notification);
        System.out.println("[NOTIFICACIÓN] Enviando correo de confirmación de la orden ID: " + notification.getOrderId());
        return ResponseEntity.ok("Notificación enviada y registrada con éxito para la orden: " + notification.getOrderId());
    }
}
