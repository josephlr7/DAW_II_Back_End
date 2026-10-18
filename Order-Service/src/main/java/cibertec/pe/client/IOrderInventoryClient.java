package cibertec.pe.client;

import cibertec.pe.dto.InventoryDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "inventory-service")
public interface IOrderInventoryClient {
    @PostMapping("/api/inventory/check")
    String verificarYDescontarStock(@RequestBody InventoryDto inventoryDto);
}
