package cibertec.pe.dto;

import lombok.Data;
@Data
public class ClientIdentityDto {
    private String numeroDocumento;
    private String tipoDocumento; // "DNI" o "RUC"
    private String nombreRazonSocial;
    private String direccionFiscal;

}
