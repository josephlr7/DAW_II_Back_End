package cibertec.pe.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "tbl_factura")
public class Factura {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String tipoDocumento;
    private String NumeroDocumentoCliente;
    private String razonSocial;
    private Double montoTotal;
    private String estadoSunat;

    public Factura() {}

    public Factura(String tipoDocumento, String numeroDocumentoCliente, String razonSocial, Double montoTotal, String estadoSunat) {
        this.tipoDocumento = tipoDocumento;
        NumeroDocumentoCliente = numeroDocumentoCliente;
        this.razonSocial = razonSocial;
        this.montoTotal = montoTotal;
        this.estadoSunat = estadoSunat;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getNumeroDocumentoCliente() {
        return NumeroDocumentoCliente;
    }

    public void setNumeroDocumentoCliente(String numeroDocumentoCliente) {
        NumeroDocumentoCliente = numeroDocumentoCliente;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public Double getMontoTotal() {
        return montoTotal;
    }

    public void setMontoTotal(Double montoTotal) {
        this.montoTotal = montoTotal;
    }

    public String getEstadoSunat() {
        return estadoSunat;
    }

    public void setEstadoSunat(String estadoSunat) {
        this.estadoSunat = estadoSunat;
    }
}
