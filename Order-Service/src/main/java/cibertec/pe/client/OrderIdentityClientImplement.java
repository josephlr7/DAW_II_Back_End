package cibertec.pe.client;

import cibertec.pe.dto.ClientIdentityDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OrderIdentityClientImplement implements IOrderIndentityClient{

    private final RestTemplate restTemplate = new RestTemplate();

    // Lee la URL de la configuración o usa localhost:8091 por defecto
    @Value("${services.identity.url:http://localhost:8091}")
    private String identityServiceUrl;

    @Override
    public ClientIdentityDto obtenerCliente(String numeroDocumento) {
        String url;
        if (numeroDocumento.length() == 11) {
            url = identityServiceUrl + "/api/identity/ruc/" + numeroDocumento;
        } else {
            url = identityServiceUrl + "/api/identity/dni/" + numeroDocumento;
        }

        try {
            return restTemplate.getForObject(url, ClientIdentityDto.class);
        } catch (Exception e) {
            System.err.println("Error al conectar con el identity-service: " + e.getMessage());
            return null;
        }
    }
}
