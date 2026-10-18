package cibertec.pe.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "shipping")
public class Shipping {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "order_id")
    private Long orderId;
    
    @Column(name = "direccion_destino")
    private String direccionDestino;
    
    @Column(name = "estado_envio")
    private String estadoEnvio = "ALISTANDO_PEDIDO"; // EN_VERIFICACION | ALISTANDO_PEDIDO | EN_OLVA_COURIER | ENTREGADO
    
    @Column(name = "courier")
    private String courier = "Olva Courier";
    
    @Column(name = "numero_guia")
    private String numeroGuia;
    
    @Column(name = "tracking_url")
    private String trackingUrl;
    
    @Column(name = "notas")
    private String notas;
    
    @Column(name = "fecha_actualizacion")
    private LocalDateTime fechaActualizacion;

    public Shipping() {
    }

    @PrePersist
    public void onPrePersist() {
        this.fechaActualizacion = LocalDateTime.now();
        if (this.courier == null || this.courier.trim().isEmpty()) {
            this.courier = "Olva Courier";
        }
        if (this.numeroGuia == null && this.orderId != null) {
            this.numeroGuia = "OLV-" + String.format("%06d", this.orderId);
        }
        if (this.trackingUrl == null && this.numeroGuia != null) {
            this.trackingUrl = "https://www.olvacourier.com/tracking?guia=" + this.numeroGuia;
        }
    }

    @PreUpdate
    public void onPreUpdate() {
        this.fechaActualizacion = LocalDateTime.now();
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public Long getOrderId() { return orderId; }
    public void setOrderId(Long orderId) { this.orderId = orderId; }
    
    public String getDireccionDestino() { return direccionDestino; }
    public void setDireccionDestino(String direccionDestino) { this.direccionDestino = direccionDestino; }
    
    public String getEstadoEnvio() { return estadoEnvio; }
    public void setEstadoEnvio(String estadoEnvio) { this.estadoEnvio = estadoEnvio; }

    public String getCourier() { return courier; }
    public void setCourier(String courier) { this.courier = courier; }

    public String getNumeroGuia() { return numeroGuia; }
    public void setNumeroGuia(String numeroGuia) { this.numeroGuia = numeroGuia; }

    public String getTrackingUrl() { return trackingUrl; }
    public void setTrackingUrl(String trackingUrl) { this.trackingUrl = trackingUrl; }

    public String getNotas() { return notas; }
    public void setNotas(String notas) { this.notas = notas; }

    public LocalDateTime getFechaActualizacion() { return fechaActualizacion; }
    public void setFechaActualizacion(LocalDateTime fechaActualizacion) { this.fechaActualizacion = fechaActualizacion; }
}
