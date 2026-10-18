package cibertec.pe;

import cibertec.pe.service.BillingImplements;
import jakarta.xml.ws.Endpoint;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class BillingServiceApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(BillingServiceApplication.class, args);
		BillingImplements service = context.getBean(BillingImplements.class);

		// Publicando el servicio SOAP de facturación en el puerto 8093
		Endpoint.publish("http://localhost:8094/ws/billing", service);
	}
}
