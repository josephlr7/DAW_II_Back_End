package cibertec.pe.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FacturaDto {
    private int id;
    private String tipoDocumento;
    private String numeroDocumentoCliente;
    private String razonSocial;
    private Double montoTotal;
    private String estadoSunat;
}
