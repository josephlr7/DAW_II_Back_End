package cibertec.pe.dto;

public class ShippingStatusUpdateRequest {
    private String estadoEnvio;
    private String courier;
    private String numeroGuia;
    private String notas;

    public ShippingStatusUpdateRequest() {
    }

    public String getEstadoEnvio() { return estadoEnvio; }
    public void setEstadoEnvio(String estadoEnvio) { this.estadoEnvio = estadoEnvio; }

    public String getCourier() { return courier; }
    public void setCourier(String courier) { this.courier = courier; }

    public String getNumeroGuia() { return numeroGuia; }
    public void setNumeroGuia(String numeroGuia) { this.numeroGuia = numeroGuia; }

    public String getNotas() { return notas; }
    public void setNotas(String notas) { this.notas = notas; }
}
