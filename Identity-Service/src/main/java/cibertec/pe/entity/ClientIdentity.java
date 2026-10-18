package cibertec.pe.entity;

import lombok.Data;

@Data
public class ClientIdentity {
    private String numeroDocumento;
    private String tipoDocumento; // "DNI" o "RUC"
    private String nombreRazonSocial;
    private String direccionFiscal;
}
