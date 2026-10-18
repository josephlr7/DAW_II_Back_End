package cibertec.pe.Dto;

public class FacturaDto {
    private Long orderId;
    private String numeroDocumentoCliente; // Corregido a String para evitar desborde de RUC
    private String producto;
    private Double total;

    // Getters y Setters
    public Long getOrderId() { return orderId; }
    public void setOrderId(Long orderId) { this.orderId = orderId; }
    public String getNumeroDocumentoCliente() { return numeroDocumentoCliente; }
    public void setNumeroDocumentoCliente(String numeroDocumentoCliente) { this.numeroDocumentoCliente = numeroDocumentoCliente; }
    public String getProducto() { return producto; }
    public void setProducto(String producto) { this.producto = producto; }
    public Double getTotal() { return total; }
    public void setTotal(Double total) { this.total = total; }
}
