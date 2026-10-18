package cibertec.pe.service.impl;

import cibertec.pe.dto.ApiResponseDni;
import cibertec.pe.dto.ApiResponseRuc;
import cibertec.pe.entity.ClientIdentity;
import cibertec.pe.service.IIDentityService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class IdentityImplements implements IIDentityService {

    private final String TOKEN_API = "a82d9f7a32dc7b3a83f9c61af8a342eeb70803e139227f17155037c30d37ef7c";
    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public ClientIdentity consultarReniec(String dni) {
        if (dni == null || dni.trim().isEmpty()) {
            return null;
        }

        HttpHeaders headers = new HttpHeaders();

        try {
            String url = "https://apiperu.dev/api/dni/" + dni.trim();
            headers.setBearerAuth(TOKEN_API);
            headers.set("Content-Type", "application/json");

            HttpEntity<String> entity = new HttpEntity<>(headers);
            ResponseEntity<ApiResponseDni> response = restTemplate.exchange(
                    url, HttpMethod.GET, entity, ApiResponseDni.class
            );

            if (response.getBody() != null && response.getBody().isSuccess() && response.getBody().getData() != null) {
                var data = response.getBody().getData();
                ClientIdentity client = new ClientIdentity();
                client.setNumeroDocumento(dni.trim());
                client.setTipoDocumento("DNI");

                String nombreCompleto = data.getNombre_completo();
                if (nombreCompleto == null || nombreCompleto.trim().isEmpty()) {
                    StringBuilder sb = new StringBuilder();
                    if (data.getNombre() != null) sb.append(data.getNombre().trim()).append(" ");
                    if (data.getApellido_paterno() != null) sb.append(data.getApellido_paterno().trim()).append(" ");
                    if (data.getApellido_materno() != null) sb.append(data.getApellido_materno().trim());
                    nombreCompleto = sb.toString().trim();
                }

                client.setNombreRazonSocial(nombreCompleto.isEmpty() ? "-" : nombreCompleto);
                client.setDireccionFiscal(data.getDireccion() != null && !data.getDireccion().trim().isEmpty()
                        ? data.getDireccion().trim()
                        : "-");
                return client;
            }
        } catch (Exception e) {
            System.err.println("Error al consultar DNI: " + e.getMessage());
        }

        return null;
    }

    @Override
    public ClientIdentity consultarSunat(String ruc) {
        if (ruc == null || ruc.trim().isEmpty()) {
            return null;
        }

        try {
            String url = "https://apiperu.dev/api/ruc/" + ruc.trim();

            HttpHeaders headers = new HttpHeaders();
            headers.setBearerAuth(TOKEN_API);
            headers.set("Content-Type", "application/json");

            HttpEntity<String> entity = new HttpEntity<>(headers);

            ResponseEntity<ApiResponseRuc> response = restTemplate.exchange(
                    url, HttpMethod.GET, entity, ApiResponseRuc.class
            );

            if (response.getBody() != null && response.getBody().isSuccess() && response.getBody().getData() != null) {
                var data = response.getBody().getData();
                ClientIdentity client = new ClientIdentity();
                client.setNumeroDocumento(ruc.trim());
                client.setTipoDocumento("RUC");
                client.setNombreRazonSocial(data.getRazon_social() != null ? data.getRazon_social().trim() : "-");
                client.setDireccionFiscal(data.getDireccion() != null && !data.getDireccion().trim().isEmpty()
                        ? data.getDireccion().trim()
                        : "-");
                return client;
            }
        } catch (Exception e) {
            System.err.println("Error al consultar RUC: " + e.getMessage());
        }

        return null;
    }
}

