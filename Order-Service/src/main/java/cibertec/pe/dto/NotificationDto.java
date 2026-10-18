package cibertec.pe.dto;

public class NotificationDto {
    private Long orderId;
    private String mensaje;

    public NotificationDto() {}

    public NotificationDto(Long orderId, String mensaje) {
        this.orderId = orderId;
        this.mensaje = mensaje;
    }

    public Long getOrderId() { return orderId; }
    public void setOrderId(Long orderId) { this.orderId = orderId; }
    public String getMensaje() { return mensaje; }
    public void setMensaje(String mensaje) { this.mensaje = mensaje; }
}
