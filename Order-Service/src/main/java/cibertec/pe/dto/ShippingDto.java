package cibertec.pe.dto;

public class ShippingDto {
    private Long orderId;
    private String direccionDestino;

    public ShippingDto() {}

    public ShippingDto(Long orderId, String direccionDestino) {
        this.orderId = orderId;
        this.direccionDestino = direccionDestino;
    }

    public Long getOrderId() { return orderId; }
    public void setOrderId(Long orderId) { this.orderId = orderId; }
    public String getDireccionDestino() { return direccionDestino; }
    public void setDireccionDestino(String direccionDestino) { this.direccionDestino = direccionDestino; }
}
