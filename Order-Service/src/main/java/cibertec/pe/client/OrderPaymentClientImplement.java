package cibertec.pe.client;

import cibertec.pe.dto.PaymentDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OrderPaymentClientImplement implements IOrderPaymentClient {

    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${services.payment.url:http://localhost:8092/api/payments}")
    private String paymentUrl;

    @Override
    public String procesarPago(PaymentDto paymentDto) {
        try {
            ResponseEntity<String> response = restTemplate.postForEntity(paymentUrl, paymentDto, String.class);
            return response.getBody();
        } catch (Exception e) {
            System.err.println("Error al conectar con Payment-Service: " + e.getMessage());
            throw new RuntimeException("Error en la pasarela de pagos: " + e.getMessage());
        }
    }
}
