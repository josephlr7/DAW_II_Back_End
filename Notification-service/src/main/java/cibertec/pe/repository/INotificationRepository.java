package cibertec.pe.repository;

import cibertec.pe.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface INotificationRepository extends JpaRepository<Notification , Long> {
}
