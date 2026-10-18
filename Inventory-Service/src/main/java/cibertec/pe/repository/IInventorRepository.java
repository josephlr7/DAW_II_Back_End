package cibertec.pe.repository;

import cibertec.pe.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IInventorRepository extends JpaRepository<Inventory , Long> {
    Optional<Inventory> findByProducto(String producto);
}
