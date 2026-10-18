package cibertec.pe.client;

import cibertec.pe.dto.NotificationDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "notification-service")
public interface IOrderNotificationClient {
    @PostMapping("/api/notifications")
    String enviarNotificacion(@RequestBody NotificationDto notificationDto);
}
