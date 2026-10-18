package cibertec.pe.client;

import cibertec.pe.dto.ShippingDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "shipping-service")
public interface IOrderShippingClient {

    @PostMapping("/api/shipping")
    String programarEnvio(@RequestBody ShippingDto shippingDto);
}
