package cibertec.pe.repository;

import cibertec.pe.model.Shipping;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface IShippingRepository extends JpaRepository<Shipping, Long> {
    Optional<Shipping> findFirstByOrderIdOrderByIdDesc(Long orderId);
    Optional<Shipping> findByOrderId(Long orderId);
    List<Shipping> findAllByOrderByIdDesc();
}
